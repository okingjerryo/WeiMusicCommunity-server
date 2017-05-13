package com.weiCommity.Controller;

import com.alibaba.fastjson.TypeReference;
import com.weiCommity.Model.Login;
import com.weiCommity.Model.UserExtend;
import com.weiCommity.Model.UserTFWork;
import com.weiCommity.Service.UserInfoManage;
import com.weiCommity.Util.HttpJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

/**
 * @discribe 用户个人信息修改管理的servlet
 * @requsetMapping /api/uinfoManage
 * Created by uryuo on 17/4/24.
 */
@RestController
@RequestMapping(value = "api/uinfoManage",produces = "application/json")
public class UserInfoManageController {

    private final UserInfoManage userInfoManage;

    @Autowired
    public UserInfoManageController(UserInfoManage userInfoManage) {
        this.userInfoManage = userInfoManage;
    }

    /**
     * @form get-sercurity-info
     * @input UserUUid
     * @param jsonString
     * @return editStatus
     */

    @RequestMapping(value = "security/get")
    public ResponseEntity<HttpJson> getUserSecurity(@RequestBody String jsonString){
        HttpJson re = new HttpJson();
        HttpJson inObj = new HttpJson(jsonString);
        try{
            if (!inObj.getClassName().equals("form:GetSercurity"))
                throw  new JSONException("");

            String UUuid = inObj.getPara("UUuid");
            re = userInfoManage.getUserSercurityInfoByUUuid(UUuid);
        }catch (JSONException e){
            re.setStatusCode(250);
            re.setMessage("请求表单异常");
            re.constractJsonString();
            return new ResponseEntity<HttpJson>(re,HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            re.setStatusCode(203);
            re.setMessage("服务器出现异常，请稍后再试");
            re.constractJsonString();
            return new ResponseEntity<HttpJson>(re,HttpStatus.BAD_GATEWAY);
        }
        re.constractJsonString();
        return new ResponseEntity<HttpJson>(re, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "security/edit")
    public ResponseEntity<HttpJson> editUserSecutity(@RequestBody String jsonString){
        HttpJson re =new HttpJson();
        HttpJson inObj = new HttpJson(jsonString, Login.class);

        try{
            if (!inObj.getClassName().equals("Login:security-edit"))
                throw new JSONException("");

            Login thisUser = (Login) inObj.getClassObject();
            userInfoManage.editUserSercurity(thisUser);
            re.constractJsonString();

        }catch (JSONException e){
            re.setStatusCode(250);
            re.setMessage("请求表单异常");
            re.constractJsonString();
            return new ResponseEntity<HttpJson>(re,HttpStatus.BAD_REQUEST);

        }catch (Exception e){
            re.setStatusCode(203);
            re.setMessage("服务器发生异常，请稍后再试");
            re.constractJsonString();
            return new ResponseEntity<HttpJson>(re,HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity<HttpJson>(re,HttpStatus.ACCEPTED);

    }

    //客户端获取用户扩展信息
    @RequestMapping("userExtend/get")
    public ResponseEntity<HttpJson> getUserExtendInfoByUUuid(@RequestBody String jsonString){
        HttpJson re = new HttpJson();
        HttpJson inObj = new HttpJson(jsonString);
        try {
            if (inObj.getClassName().equals("form:userExtend-get"))
                throw new JSONException("");

            String UUuid = inObj.getPara("UUuid");
            re = userInfoManage.getUserExtendByUUuid(UUuid);
            re.constractJsonString();
        }catch (JSONException e){
            re.setStatusCode(250);
            re.setMessage("请求表单异常");
            re.constractJsonString();
            return new ResponseEntity<HttpJson>(re,HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            re.setStatusCode(203);
            re.setMessage("服务器发生异常，请稍后再试");
            re.constractJsonString();
            return  new ResponseEntity<HttpJson>(re,HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity<HttpJson>(re,HttpStatus.ACCEPTED);
    }

    //客户端修改用户的扩展信息 注意客户端需要传送获取到的ImgPath作为信息
    //UserExtend中的UserHeadImg 存图的编码信息，然后lastImgPath参数存上次从服务器获取的信息，thisImgName 存当前在Android上的信息，如果未修改这两个path都是传服务器之前的信息
    @RequestMapping(value = "userExtend/edit")
    public ResponseEntity<HttpJson> editUserExtendInfo(@RequestBody String jsonString){
        HttpJson re = new HttpJson();
        HttpJson inObj = new HttpJson(jsonString, UserExtend.class);

        try{
            if (!inObj.getClassName().equals("UserExtend:UserExtend-edit"))
                throw new JSONException("");
            String lastImgPath = inObj.getPara("lastImgPath");
            UserExtend userExtend = (UserExtend) inObj.getClassObject();
            String thisImgName = inObj.getPara("thisImgName");
            File thisImg = new File(thisImgName);
            String thisImgPath = userInfoManage.editUserExtend(userExtend,lastImgPath,thisImg);
        }catch (JSONException e){
            re.setMessage("您的请求不合法");
            re.setStatusCode(250);
            re.constractJsonString();
            return new ResponseEntity<HttpJson>(re,HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            re.setStatusCode(203);
            re.setMessage("服务器出现异常");
            re.constractJsonString();
            return new ResponseEntity<HttpJson>(re,HttpStatus.BAD_GATEWAY);
        }
        re.constractJsonString();
        return new ResponseEntity<HttpJson>(re,HttpStatus.ACCEPTED);

    }
    //对这个用户所有工种的获取
    @RequestMapping(value = "userTFWork/get")
    public ResponseEntity<HttpJson> getUserTFWorkByUUuid(@RequestBody String jsonString ){
        HttpJson re = new HttpJson();
        HttpJson inObj = new HttpJson(jsonString);

        try{
            if (!inObj.getClassName().equals("form:userTFWork-get"))
                throw new JSONException("");

            String UUuid = inObj.getPara("UUuid");

            List<UserTFWork> reWork = userInfoManage.getUserTFWorkByUUuid(UUuid);
            re.setClassObject(reWork);
            re.setClassName("List<UserTFWork>");
            re.constractJsonString();
        }catch (JSONException e){
            re.setMessage("您的请求不合法");
            re.setStatusCode(250);
            re.constractJsonString();
            return new ResponseEntity<HttpJson>(re,HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            re.setStatusCode(203);
            re.setMessage("服务器出现异常");
            re.constractJsonString();
            return new ResponseEntity<HttpJson>(re,HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity<HttpJson>(re,HttpStatus.ACCEPTED);
    }

    //对客户端发来的对工种序列按顺序修改 注意 传过来时候把需要数据库做的操作放到fistSC中 e.g."增""改""删"
    @RequestMapping("userTFWork/edit")

    public ResponseEntity<HttpJson> editUserWork (String jsonString){
        HttpJson re = new HttpJson();
        HttpJson inObj = new HttpJson(jsonString, new TypeReference<List<UserTFWork>>() {
        });
        try {
            if (!inObj.getClassName().equals("List<UserTFWork>:userTFWork-edit"))
                throw new JSONException("");
            JSONArray jsonArray = (JSONArray) inObj.getClassObject();
            List<Object> thisQueue = jsonArray.toList();
            userInfoManage.editUserTFWorkByEditList(thisQueue);
            re.constractJsonString();
        } catch (JSONException e) {
            re.setMessage("您的请求不合法");
            re.setStatusCode(250);
            re.constractJsonString();
            return new ResponseEntity<HttpJson>(re, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            re.setStatusCode(203);
            re.setMessage("服务器出现异常");
            re.constractJsonString();
        }

        return new ResponseEntity<HttpJson>(re, HttpStatus.ACCEPTED);
    }


    //通过获得全部常用工种



}
