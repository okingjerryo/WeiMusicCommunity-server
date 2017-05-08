package com.weiCommity.Controller;

import com.weiCommity.Service.LoginService;
import com.weiCommity.Service.RegistService;
import com.weiCommity.Util.HttpJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by uryuo on 17/4/17.
 */
@RestController
@RequestMapping("/api")
public class LoginController {
    //Controller 也需要使用注入的方式
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService, RegistService registService) {
        this.loginService = loginService;
    }

    //登录事件
    @RequestMapping(value = "/login",produces = "application/json;charset=Utf-8")
    public ResponseEntity<HttpJson> Login(@RequestBody String getJsonString) throws Exception {
        //username password
        HttpJson inObj = new HttpJson(getJsonString);
        inObj.resolveJsonString();
        HttpJson re = new HttpJson();
        try {
            String reName = inObj.getClassName();
            if (!reName.equals("form:login")) {
               throw new Exception();
            }

            String username = inObj.getPara("username");
            String password = inObj.getPara("password");
            String style = inObj.getPara("style");

            String reMessage = loginService.LoginWithStyle(username, password, style);

            if (reMessage.equals("用户名或密码错误，请检查你的登录信息")) {
                re.setStatusCode(203);
                re.setClassName("Message");
                re.setMessage(reMessage);
                re.constractJsonString();
            } else if (reMessage.equals("用户不存在，请检查你的用户名")) {
                re.setStatusCode(203);
                re.setClassName("Message");
                re.setMessage(reMessage);
                re.constractJsonString();
            } else {
                re.setClassObject(reMessage);
                re.setClassName(String.class.toString());
            }
            return new ResponseEntity<HttpJson>(re, HttpStatus.OK);
        }catch (Exception e){
            re.setStatusCode(250);
            re.setMessage("请求属性异常");
            re.constractJsonString();
            return  new ResponseEntity<HttpJson>(re,HttpStatus.BAD_REQUEST);
        }
    }


}
