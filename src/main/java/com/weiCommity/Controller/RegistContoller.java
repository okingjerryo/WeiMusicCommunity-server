package com.weiCommity.Controller;

import com.alibaba.fastjson.TypeReference;
import com.weiCommity.Model.Login;
import com.weiCommity.Model.UserExtend;
import com.weiCommity.Service.RegistService;
import com.weiCommity.Util.HttpJson;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * 注册用的servlet Contorller
 * Created by uryuo on 17/4/19.
 */
@RestController
@RequestMapping("api/regist")
public class RegistContoller {
    @Autowired
    RegistService registService;

    //注册事件-注册常用信息
    @RequestMapping(value = "common",produces = "application/json;charset=UTF-8")
    public ResponseEntity<HttpJson> Regist(@RequestBody String inObj){

        HttpJson re = new HttpJson(), input = new HttpJson(inObj);
        String thisFormType = input.getClassName();
        if (!thisFormType.equals("form:regist")){
            re.setStatusCode(250);
            re.setMessage("不匹配的请求");
            return new ResponseEntity<HttpJson>(re, HttpStatus.BAD_REQUEST);
        }
        String getRegistType = input.getPara("style");
        String getUserName = input.getPara("username");
        String getUserPwd = input.getPara("password");

        Login login = new Login();
        login.setUPwd(getUserPwd);
        login.setUAName(getUserName);

        String flag = "-2";
        try {
            flag = registService.registUserWithType(login,getRegistType);
        } catch (Exception e) {
            e.printStackTrace();
            re.setMessage("服务器出错");
            re.setStatusCode(202);
            re.constractJsonString();
            return new ResponseEntity<HttpJson>(re,HttpStatus.BAD_GATEWAY);
        }

        if (flag.equals("-1")){
            re.setMessage("用户已存在，请检查用户名");
            re.setStatusCode(201);
        }else if(flag.equals("-2")){
            re.setStatusCode(202);
            re.setMessage("服务器太忙，请稍后再试");
        }else{
            re.setMessage("UserUUid");
            re.setClassObject(String.class.toString());
            re.setClassObject(flag);
        }

        re.constractJsonString();

        return new ResponseEntity<HttpJson>(re,HttpStatus.ACCEPTED);
    }
    //注册事件-常用工种设定(只插入)
    /**
     * 注意传来的数据：List<String>
     **/
    @RequestMapping(value = "freq",produces = "application/json; charset=UTF-8")
    public ResponseEntity<HttpJson> registFreqTyOWorkWithUUid(@RequestBody String jsonString){
        HttpJson re = new HttpJson();
        HttpJson inObj = new HttpJson(jsonString, new TypeReference<List<String>>() {
        });
        try{
            if (!inObj.getClassName().equals("List<String>:regist-freq"))
                throw new IOException();
            String thisUUuid = inObj.getPara("UUuid");

            JSONArray JsonList = (JSONArray) inObj.getClassObject();
            List<Object> list = JsonList.toList();

            registService.registFreqTOWByWorkId(thisUUuid,list);
            re.constractJsonString();
        }catch (IOException e){

            re.setStatusCode(250);
            re.setMessage("请求不合法");
            re.constractJsonString();
            return new ResponseEntity<HttpJson>(re,HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            re.setStatusCode(202);
            re.setMessage("服务器处理出现异常");
            re.constractJsonString();
            return new ResponseEntity<HttpJson>(re,HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<HttpJson>(re,HttpStatus.OK);
    }

    /**注册阶段3-用户部分基本信息设置
     *
     * @className String[ISO-8859-1]
     * @form regist-userExtend
     * @param jsonString
     * @return 状态
     */
    @RequestMapping(value = "/uextend",produces = "application/json; charset=UTF-8")
    public ResponseEntity<HttpJson> registExtendingInformation(@RequestBody String jsonString){
        HttpJson re = new HttpJson();
        HttpJson inObj = new HttpJson(jsonString);
        try{
            if (!inObj.getClassName().equals("String[ISO-8859-1]:regist-userExtend"))
                throw new IOException();
            UserExtend userExtend = new UserExtend();
            userExtend.setUUuid(inObj.getPara("UUuid"));
            userExtend.setUHeadImg(inObj.getPara("UHeadImg"));
            userExtend.setUBirthday(new DateTime(inObj.getPara("UBirthday")));
            userExtend.setUNackName(inObj.getPara("UNackName"));
            userExtend.setUSex(inObj.getPara("USex"));
            String imgStr = (String) inObj.getClassObject();
            String tarImgPath = registService.registExtandInfo(userExtend,imgStr);
            //返回图像的服务器访问地址
            re.setMessage(tarImgPath);
            re.constractJsonString();
        }catch(IOException e){
            re.setMessage("提交的请求不匹配");
            re.setStatusCode(250);
            re.constractJsonString();
            return new ResponseEntity<HttpJson>(re,HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            re.setStatusCode(202);
            re.setMessage("服务器处理异常");
            re.constractJsonString();
            return  new ResponseEntity<HttpJson>(re,HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity<HttpJson>(re,HttpStatus.ACCEPTED);
    }
}
