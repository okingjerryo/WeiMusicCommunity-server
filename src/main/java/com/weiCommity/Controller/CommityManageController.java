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
    @RequestMapping(value = "delCommityUser")
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

    //通过社团id 或者 社团名字获取社团信息（都有的话 以Cid为优先）
    @RequestMapping(value = "commityInfo/get")
    public ResponseEntity<HttpJson> getCommityInfo(@RequestBody String jsonString) {
        HttpJson inObj = new HttpJson(jsonString);
        HttpJson re = new HttpJson();
        try {
            if (inObj.getClassName().equals("CommityMember:CommityInfo-get"))
                throw new JSONException("");

            CommityInfo info = (CommityInfo) inObj.getClassObject();
            CommityInfo reInfo;
            if (info.getCid() != null)
                reInfo = manageService.getCommityInfoByCid(info.getCid());
            else if (info.getCName() != null)
                reInfo = manageService.getCommityInfoByCName(info.getCName());
            else
                throw new JSONException("缺少必要信息");

            re.setClassName("CommityInfo:thisCommityInfo");
            re.setClassObject(reInfo);
        } catch (JSONException e) {
            re.setStatusCode(250);
            re.setMessage("请求不合法" + e.getMessage());
            return new ResponseEntity<>(re, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            re.setStatusCode(203);
            re.setMessage("服务器出现异常，请稍后再试");
            return new ResponseEntity<>(re, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(re, HttpStatus.OK);
    }

    //管理层修改公告 注意 要特别注入UUuid 类里面要提供 Cid 新的Notice
    @RequestMapping(value = "notice/publish")
    public ResponseEntity<HttpJson> publishCommityNotice(@RequestBody String jsonString) {
        HttpJson inObj = new HttpJson(jsonString);
        HttpJson re = new HttpJson();
        try {
            if (inObj.getClassName().equals("CommityInfo:notice-publish"))
                throw new JSONException("");


            String opUUuid = inObj.getPara("UUuid");
            CommityInfo sender = (CommityInfo) inObj.getClassObject(); //要发布的东西
            CommityMember opMem = manageService.getOneUserInCommity(opUUuid, sender.getCid());
            if (opMem.getUtype() < 2)
                throw new JSONException("没有足够的权限");

            //发布公告
            manageService.publishCommityNotices(sender);

        } catch (JSONException e) {
            re.setStatusCode(250);
            re.setMessage("您的操作失败" + e.getMessage());
            return new ResponseEntity<>(re, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            re.setStatusCode(203);
            re.setMessage("服务器出现异常，请稍后再试");
            return new ResponseEntity<>(re, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(re, HttpStatus.OK);
    }

    //管理员发布活动 在chuan'guo'la

}
