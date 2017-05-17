package com.weiCommity.Controller;


import com.weiCommity.Model.Login;
import com.weiCommity.Model.ProjectDynamic;
import com.weiCommity.Model.ProjectFile;
import com.weiCommity.Model.ProjectInfoPersonalOriented;
import com.weiCommity.Service.LoginService;
import com.weiCommity.Service.PersonOrientedService;
import com.weiCommity.Service.ProjectService;
import com.weiCommity.Util.HttpJson;
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
    final PersonOrientedService personOrientedService;
    final ProjectService projectService;
    final ProjectDynamic projectDynamic;
    @Autowired
    public WebController(LoginService loginService, PersonOrientedService personOrientedService, ProjectService projectService, ProjectDynamic projectDynamic) {
        this.loginService = loginService;
        this.personOrientedService = personOrientedService;
        this.projectService = projectService;
        this.projectDynamic = projectDynamic;
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
            if (result.length() != 36) {
                result = loginService.LoginWithStyle(login.getUAName(), login.getUPwd(), "M");
                if (result.length() != 36)
                    result = loginService.LoginWithStyle(login.getUAName(), login.getUPwd(), "T");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result.length() == 36) {
//            List<Login> send = new ArrayList<>();
//            for (Integer i = 0; i < 3; i++) {
//                Login thisl = new Login();
//                thisl.setUAName(i.toString() + "啦啦啦啦");
//                send.add(thisl);
//            }
//
//            HttpSession session = request.getSession();
//            session.setAttribute("user", result);
//            model.addAttribute("list", send);
            ProjectInfoPersonalOriented thisUser = new ProjectInfoPersonalOriented();
            thisUser.setThisUUuid(result);
            thisUser.setStartState(0);
            thisUser.setEndState(4);
            //获得全部要显示的list
            List<ProjectInfoPersonalOriented> list = personOrientedService.getAllProject(thisUser);
            for (ProjectInfoPersonalOriented elem : list) {
                elem.setUJoinTimeStr();
            }
            HttpSession session = request.getSession();
            session.setAttribute("user", result);
            session.setAttribute("list", list);
            model.addAttribute("list", list);
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

    //准备上传

    @RequestMapping(value = "api/Upload")
    public String jumptoUpload(ProjectFile pfile, ModelMap model, HttpServletRequest request) {
        model.addAttribute("thisPWId", pfile.getPWId());
        return "file";
    }
    //上传进程
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    String fileUpload(MultipartFile file, ProjectFile pTarFile) {
        try {
            //decode
            pTarFile.setPFNotice(new String(pTarFile.getPFNotice().getBytes("iso-8859-1"), "UTF-8"));
            String realFileName = new String(file.getOriginalFilename().getBytes("iso-8859-1"), "UTF-8");
            //Service
//            FileUtils.writeByteArrayToFile(new File(StaticVar.getToFilePath() + "CommonSpace/testUpload/" + realFileName),
//                    file.getBytes());
            String PFId = projectService.addFiletoUWid(realFileName, pTarFile, file);
            //动态生成更新字段

            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
}
