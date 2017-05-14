package com.weiCommity.Controller;


import com.weiCommity.Model.Login;
import com.weiCommity.Service.LoginService;
import com.weiCommity.Util.HttpJson;
import com.weiCommity.Util.StaticVar;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * PackageName com.weiCommity.Controller
 * Created by uryuo on 17/5/10.
 */
@Controller

public class WebController {
    final LoginService loginService;

    @Autowired
    public WebController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(value = "api/loginWeb")
    public @ResponseBody
    String webLogin(Login login, HttpServletRequest request) {
        String result = "";
        try {
            result = loginService.LoginWithStyle(login.getUAName(), login.getUPwd(), "A");
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpJson json = new HttpJson();
        json.setMessage(result + "123");
        json.constractJsonString();
        String callback = request.getParameter("callback"); //不指定函数名默认 callback

        return callback + "(" + json.getJsonString() + ")";

    }

    @RequestMapping(value = "api/loginPage")
    public String testwebLogin(Login login, ModelMap model, HttpServletRequest request) {
        String result = "";
        try {
            result = loginService.LoginWithStyle(login.getUAName(), login.getUPwd(), "A");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result.length() == 36) {
            List<Login> send = new ArrayList<>();
            for (Integer i = 0; i < 3; i++) {
                Login thisl = new Login();
                thisl.setUAName(i.toString() + "啦啦啦啦");
                send.add(thisl);
            }

            HttpSession session = request.getSession();
            session.setAttribute("user", result);
            model.addAttribute("list", send);
            return "index";
        } else
            return "signin";
    }

    @RequestMapping("/webloginAJ")
    public void countGoodsNumByCategoryId(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/piain;charset=Utf-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);


        HttpJson json = new HttpJson();
        json.setStatusCode(400);
        json.setMessage("testMessage");
        json.constractJsonString();

        try {
            PrintWriter out = response.getWriter();
            String jsonpCallback = request.getParameter("jsonpCallback");// 客户端请求
            JSONObject object = new JSONObject();
            List<Login> send = new ArrayList<>();
            for (Integer i = 0; i < 3; i++) {
                Login thisl = new Login();
                thisl.setUAName(i.toString() + "啦啦啦啦");
                send.add(thisl);
            }
            object.put("list", send);
            out.println(jsonpCallback + "(" + object.toString() + ")");// 返回jsonp格式数据
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //上传进程
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    String fileUpload(MultipartFile file) {
        try {
            FileUtils.writeByteArrayToFile(new File(StaticVar.getToFilePath() + "CommonSpace/testUpload/" + file.getOriginalFilename()),
                    file.getBytes());
            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
}
