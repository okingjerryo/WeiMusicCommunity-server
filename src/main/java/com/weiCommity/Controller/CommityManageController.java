package com.weiCommity.Controller;

import com.weiCommity.Model.CommityInfo;
import com.weiCommity.Model.CommityMember;
import com.weiCommity.Service.CommityManageService;
import com.weiCommity.Util.HttpJson;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


/**
 * PackageName com.weiCommity.Controller
 * 社团管理类Conntroller 让有权限的用户可以对社团进行一些操作
 * Created by uryuo on 17/5/1.
 */
@RestController
@RequestMapping(value = "api/commity", produces = "application/json;charset=UTF-8")
public class CommityManageController {
    private final CommityManageService manageService;

    @Autowired
    public CommityManageController(CommityManageService manageService) {
        this.manageService = manageService;
    }

    //社团注册-基本
    //1.社团基本信息注入    2.人员表中当前用户入库 设置权限为（4.社长）
    //需要另外注入 创建社团的用户UUuid
    //返回 创建社团的Cid
    @RequestMapping(value = "regist/common")
    public ResponseEntity<HttpJson> registCommityCommon(@RequestBody String jsonString) {
        HttpJson re = new HttpJson();
        HttpJson inObj = new HttpJson(jsonString);
        try {
            if (!inObj.getClassName().equals("CommityInfo:commityRegist-common"))
                throw new JSONException("");

            CommityInfo registInfo = (CommityInfo) inObj.getClassObject();

            //1.检查待注册社团名字是否存在
            String checkCommityName = manageService.isCommmityIn(registInfo);
            if (!checkCommityName.isEmpty())
                throw new JSONException("您要注册的社团名已存在");

            //2.将这个社团的信息注册到数据库中
            String CId = manageService.registCommon(registInfo);

            //3.生成当前用户为社长
            CommityMember member = new CommityMember();
            member.setCid(CId);
            member.setCMid(UUID.randomUUID().toString());
            member.setUUuid(inObj.getPara("UUuid"));    //需要特别注入
            member.setUtype(4); // 权限为社长
            //4.将这个用户更新到社团表里
            manageService.registUserIntoCommity(member);

            //返回Cid
            re.setMessage(CId);
        } catch (JSONException e) {
            re.setStatusCode(250);
            re.setMessage("注册请求失败" + e.getMessage());
            return new ResponseEntity<>(re, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            re.setStatusCode(203);
            re.setMessage("服务器出现异常，请稍后再试");
            return new ResponseEntity<>(re, HttpStatus.BAD_GATEWAY);

        }
        return new ResponseEntity<>(re, HttpStatus.ACCEPTED);
    }

    //列出当前所有社团用户以及权限
    @RequestMapping(value = "userInCommity/get")
    public ResponseEntity<HttpJson> getCommityMem(@RequestBody String jsonString) {
        HttpJson inObj = new HttpJson(jsonString);
        HttpJson re = new HttpJson();
        try {
            if (inObj.getClassName().equals("CommityMember:CommityMem-get"))
                throw new JSONException("");
            CommityMember inMem = (CommityMember) inObj.getClassObject();

            List<CommityMember> send = manageService.getAllUserInCommity(inMem.getCid());
            re.setClassName("List<CommityMember>");
            re.setClassObject(send);
        } catch (JSONException e) {
            re.setStatusCode(250);
            re.setMessage("请求不合法");
            return new ResponseEntity<>(re, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            re.setStatusCode(203);
            re.setMessage("服务器出现异常，请稍后再试");
            return new ResponseEntity<>(re, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(re, HttpStatus.OK);
    }

    //社长副社长对用户权限的修改
    @RequestMapping(value = "UserInCommity/editType")
    public ResponseEntity<HttpJson> setCommityMemType(@RequestBody String jsonString) {
        HttpJson inObj = new HttpJson(jsonString);
        HttpJson re = new HttpJson();
        try {
            if (inObj.getClassName().equals("CommityMember:CommityMember-set"))
                throw new JSONException("请求不合法");

            String thisEditUser = inObj.getPara("thisUUid");
            CommityMember member = (CommityMember) inObj.getClassObject();
            manageService.setUserTypeIdentityByMUUuid(thisEditUser, member);
        } catch (JSONException e) {
            re.setStatusCode(250);
            re.setMessage("成员职位变更失败," + e.getMessage());
            return new ResponseEntity<>(re, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            re.setStatusCode(203);
            re.setMessage("服务器出现异常，请稍后再试");
            return new ResponseEntity<>(re, HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity<>(re, HttpStatus.ACCEPTED);
    }

    //成员踢出 （暂时只有社长副社长有这个权限）
    public ResponseEntity<HttpJson> kickUserOutOfCommity(@RequestBody String jsonString) {
        HttpJson inObj = new HttpJson(jsonString);
        HttpJson re = new HttpJson();
        try {
            if (inObj.getClassName().equals("CommityMember:CommityMember-kick"))
                throw new JSONException("请求不合法");
            String opreationUser = inObj.getPara("UUuid");
            CommityMember delMem = (CommityMember) inObj.getClassObject();
            //获取当前用户权限等级
            CommityMember opMem = manageService.getOneUserInCommity(delMem.getCid(), opreationUser);
            if (opMem.getUtype() < 3)
                throw new JSONException("权限不足");
            //权限检查完毕后就可以删除了
            manageService.delUserInCommity(delMem);

        } catch (JSONException e) {
            re.setStatusCode(250);
            re.setMessage("您的操作失败," + e.getMessage());
            return new ResponseEntity<>(re, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            re.setStatusCode(203);
            re.setMessage("服务器出现异常，请稍后再试");
            return new ResponseEntity<>(re, HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity<>(re, HttpStatus.ACCEPTED);
    }

    //获取当前社团信息


}
