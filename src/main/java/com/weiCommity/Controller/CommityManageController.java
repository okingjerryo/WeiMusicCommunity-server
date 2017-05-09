package com.weiCommity.Controller;

import com.weiCommity.Model.CommityActive;
import com.weiCommity.Model.CommityInfo;
import com.weiCommity.Model.CommityMember;
import com.weiCommity.Service.CommityManageService;
import com.weiCommity.Util.HttpJson;
import org.joda.time.DateTime;
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


    //社团注册 获得当前社团名是否存在
    @RequestMapping(value = "isNameExist")
    public ResponseEntity<HttpJson> isCommityNameExist(@RequestBody String jsonString) {
        HttpJson re = new HttpJson();
        HttpJson inObj = new HttpJson(jsonString);
        try {
            if (!inObj.getClassName().equals("form:isCommityNameExist"))
                throw new JSONException("");

            String targetName = inObj.getPara("CName");
            CommityInfo info = new CommityInfo();
            info.setCName(targetName);
            String reIn = manageService.isCommmityIn(info);
            if (reIn == null)
                reIn = "不存在";

            re.setPara("Cid", reIn);
            re.constractJsonString();
        } catch (JSONException e) {
            re.setStatusCode(250);
            re.setMessage("请求失败" + e.getMessage());
            re.constractJsonString();
            return new ResponseEntity<>(re, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            re.setStatusCode(203);
            re.setMessage("服务器出现异常 请稍后再试");
            re.constractJsonString();
            return new ResponseEntity<>(re, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(re, HttpStatus.OK);
    }

    //社团注册-基本
    //1.社团基本信息注入    2.人员表中当前用户入库 设置权限为（4.社长）
    //需要另外注入 创建社团的用户UUuid
    //返回 创建社团的Cid
    @RequestMapping(value = "regist/common")
    public ResponseEntity<HttpJson> registCommityCommon(@RequestBody String jsonString) {
        HttpJson re = new HttpJson();
        HttpJson inObj = new HttpJson(jsonString, CommityInfo.class);
        try {
            if (!inObj.getClassName().equals("CommityInfo:commityRegist-common"))
                throw new JSONException("");

            CommityInfo registInfo = (CommityInfo) inObj.getClassObject();

//            //1.检查待注册社团名字是否存在
//            String checkCommityName = manageService.isCommmityIn(registInfo);
//            if (!checkCommityName.isEmpty())
//                throw new JSONException("您要注册的社团名已存在");

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
            re.constractJsonString();
            return new ResponseEntity<>(re, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            re.setStatusCode(203);
            re.setMessage("服务器出现异常，请稍后再试");
            re.constractJsonString();
            return new ResponseEntity<>(re, HttpStatus.BAD_GATEWAY);

        }
        re.constractJsonString();
        return new ResponseEntity<>(re, HttpStatus.ACCEPTED);
    }

    //列出当前所有社团用户以及权限
    @RequestMapping(value = "userInCommity/getALL")
    public ResponseEntity<HttpJson> getCommityAllMem(@RequestBody String jsonString) {
        HttpJson inObj = new HttpJson(jsonString, CommityMember.class);
        HttpJson re = new HttpJson();
        try {
            if (!inObj.getClassName().equals("CommityMember:CommityMem-get"))
                throw new JSONException("");
            CommityMember inMem = (CommityMember) inObj.getClassObject();

            List<CommityMember> send = manageService.getAllUserInCommity(inMem.getCid());
            re.setClassName("List<CommityMember>");
            re.setClassObject(send);
            re.constractJsonString();
        } catch (JSONException e) {
            re.setStatusCode(250);
            re.setMessage("请求不合法");
            re.constractJsonString();
            return new ResponseEntity<>(re, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            re.setStatusCode(203);
            re.setMessage("服务器出现异常，请稍后再试");
            re.constractJsonString();
            return new ResponseEntity<>(re, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(re, HttpStatus.OK);
    }

    //社长副社长对用户权限的修改
    @RequestMapping(value = "usertype/edit")
    public ResponseEntity<HttpJson> setCommityMemType(@RequestBody String jsonString) {
        HttpJson inObj = new HttpJson(jsonString, CommityMember.class);
        HttpJson re = new HttpJson();
        try {
            if (!inObj.getClassName().equals("CommityMember:CommityMember-set"))
                throw new JSONException("请求不合法");

            String thisEditUser = inObj.getPara("thisUUid");
            String thisJoinTime = inObj.getPara("UJoinTime");
            CommityMember member = (CommityMember) inObj.getClassObject();
            member.setUJoinTime(new DateTime(thisJoinTime));
            manageService.setUserTypeIdentityByMUUuid(thisEditUser, member);
            re.constractJsonString();
        } catch (JSONException e) {
            re.setStatusCode(250);
            re.setMessage("成员职位变更失败," + e.getMessage());
            re.constractJsonString();
            return new ResponseEntity<>(re, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            re.setStatusCode(203);
            re.setMessage("服务器出现异常，请稍后再试");
            re.constractJsonString();
            return new ResponseEntity<>(re, HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity<>(re, HttpStatus.ACCEPTED);
    }

    //成员踢出 （暂时只有社长副社长有这个权限）
    @RequestMapping(value = "delCommityUser")
    public ResponseEntity<HttpJson> kickUserOutOfCommity(@RequestBody String jsonString) {
        HttpJson inObj = new HttpJson(jsonString, CommityMember.class);
        HttpJson re = new HttpJson();
        try {
            if (!inObj.getClassName().equals("CommityMember:CommityMember-kick"))
                throw new JSONException("请求不合法");
            String opreationUser = inObj.getPara("UUuid");
            CommityMember delMem = (CommityMember) inObj.getClassObject();

            manageService.delUserInCommity(delMem);
            re.constractJsonString();
        } catch (JSONException e) {
            re.setStatusCode(250);
            re.setMessage("您的操作失败," + e.getMessage());
            re.constractJsonString();
            return new ResponseEntity<>(re, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            re.setStatusCode(203);
            re.setMessage("服务器出现异常，请稍后再试");
            re.constractJsonString();
            return new ResponseEntity<>(re, HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity<>(re, HttpStatus.ACCEPTED);
    }

    //通过CMId获得一条成员信息
    @RequestMapping(value = "getCMem/CMid")
    public ResponseEntity<HttpJson> getCommityMemByCMid(@RequestBody String jsonString) {
        HttpJson re = new HttpJson();
        HttpJson inObj = new HttpJson(jsonString, CommityMember.class);
        try {
            if (!inObj.getClassName().equals("CommityMember:CommityMem-getOne"))
                throw new JSONException("");

            CommityMember member = (CommityMember) inObj.getClassObject();
            String thisCMid = member.getCMid();
            CommityMember reInfo = manageService.getOneUserInCommity(thisCMid);
            re.setPara("UJoinTime", reInfo.getUJoinTime().toString());
            re.setClassObject(reInfo);
        } catch (JSONException e) {
            re.setStatusCode(250);
            re.setMessage("请求不合法");
            re.constractJsonString();
            return new ResponseEntity<>(re, HttpStatus.OK);

        } catch (Exception e) {
            re.setStatusCode(203);
            re.setMessage("服务器出错，请稍后再试");
            re.constractJsonString();
            return new ResponseEntity<>(re, HttpStatus.OK);
        }
        re.constractJsonString();
        return new ResponseEntity<>(re, HttpStatus.OK);
    }

    //通过社团id 或者 社团名字获取社团信息（都有的话 以Cid为优先）（这里已经有公告了）
    @RequestMapping(value = "commityInfo/get")
    public ResponseEntity<HttpJson> getCommityInfo(@RequestBody String jsonString) {
        HttpJson inObj = new HttpJson(jsonString, CommityInfo.class);
        HttpJson re = new HttpJson();
        try {
            if (!inObj.getClassName().equals("Commityinfo:CommityInfo-get"))
                throw new JSONException("");

            CommityInfo info = (CommityInfo) inObj.getClassObject();
            CommityInfo reInfo = new CommityInfo();
            if (info.getCid() != null)
                reInfo = manageService.getCommityInfoByCid(info.getCid());
            else if (info.getCName() != null)
                reInfo = manageService.getCommityInfoByCName(info.getCName());
            else
                throw new JSONException("缺少必要信息");

            re.setClassName("CommityInfo:thisCommityInfo");
            if (reInfo.getCNoteCTime() != null)
                re.setPara("CNCreatTime", reInfo.getCNoteCTime().toString());
            else
                re.setPara("CNCreatTime", "");
            re.setPara("CCreatTime", reInfo.getCCreateTime().toString());
            re.setClassObject(reInfo);
            re.constractJsonString();
        } catch (JSONException e) {
            re.setStatusCode(250);
            re.setMessage("请求不合法" + e.getMessage());
            re.constractJsonString();
            return new ResponseEntity<>(re, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            re.setStatusCode(203);
            re.setMessage("服务器出现异常，请稍后再试");
            re.constractJsonString();
            return new ResponseEntity<>(re, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(re, HttpStatus.OK);
    }

    //管理层发布公告 注意 要特别注入UUuid 类里面要提供 Cid 新的Notice
    @RequestMapping(value = "notice/publish")
    public ResponseEntity<HttpJson> publishCommityNotice(@RequestBody String jsonString) {
        HttpJson inObj = new HttpJson(jsonString, CommityInfo.class);
        HttpJson re = new HttpJson();
        try {
            if (!inObj.getClassName().equals("CommityInfo:notice-publish"))
                throw new JSONException("");


            String opUUuid = inObj.getPara("UUuid");
            String CNCTime = inObj.getPara("CNCTime");
            CommityInfo sender = (CommityInfo) inObj.getClassObject(); //要发布的东西
            sender.setCNoteCTime(new DateTime(CNCTime));
            CommityMember opMem = manageService.getOneUserInCommity(sender.getCid(), opUUuid);
            if (opMem.getUtype() < 2)
                throw new JSONException("没有足够的权限");

            //发布公告
            manageService.publishCommityNotices(sender);
            re.constractJsonString();
        } catch (JSONException e) {
            re.setStatusCode(250);
            re.setMessage("您的操作失败" + e.getMessage());
            re.constractJsonString();
            return new ResponseEntity<>(re, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            re.setStatusCode(203);
            re.setMessage("服务器出现异常，请稍后再试");
            re.constractJsonString();
            return new ResponseEntity<>(re, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(re, HttpStatus.OK);
    }

    //管理员发布活动 在默认情况下开始时间为现在 注意 有活动海报的存储
    @RequestMapping(value = "activity/publish")
    public ResponseEntity<HttpJson> addCommityActivity(@RequestBody String jsonString) {
        HttpJson inObj = new HttpJson(jsonString);
        HttpJson re = new HttpJson();
        try {
            if (inObj.getClassName().equals("CommityActivity:active-publish"))
                throw new JSONException("");

            CommityActive active = (CommityActive) inObj.getClassObject(); //要发布的东西
            CommityMember opMem = manageService.getOneUserInCommity(active.getCACreatUserId(), active.getCid());
            if (opMem.getUtype() < 2)
                throw new JSONException("没有足够的权限");

            //发布活动
            manageService.publishCommityActivity(active);
            re.constractJsonString();
        } catch (JSONException e) {
            re.setStatusCode(250);
            re.setMessage("您的操作失败" + e.getMessage());
            re.constractJsonString();
            return new ResponseEntity<>(re, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            re.setStatusCode(203);
            re.setMessage("服务器出现异常，请稍后再试");
            re.constractJsonString();
            return new ResponseEntity<>(re, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(re, HttpStatus.OK);
    }

    //管理员对公告和社团信息的修改
    @RequestMapping(value = "comomityInfo/edit")
    public ResponseEntity<HttpJson> editCommityInfo(@RequestBody String jsonString) {
        HttpJson inObj = new HttpJson(jsonString, CommityInfo.class);
        HttpJson re = new HttpJson();
        try {
            if (!inObj.getClassName().equals("CommityInfo:publish"))
                throw new JSONException("");
            //权限的检查
            String thisOp = inObj.getPara("UUuid");
            String lastPath = inObj.getPara("lastPath");
            String CCreatTime = inObj.getPara("CCreatTime");
            CommityInfo info = (CommityInfo) inObj.getClassObject(); //要发布的东西
            info.setCCreateTime(new DateTime(CCreatTime));
            CommityMember opMem = manageService.getOneUserInCommity(info.getCid(), thisOp);
            if (opMem.getUtype() < 2)
                throw new JSONException("没有足够的权限");

            //修改信息
            manageService.editCommityInfo(info, lastPath);
            re.constractJsonString();
        } catch (JSONException e) {
            re.setStatusCode(250);
            re.setMessage("您的操作失败" + e.getMessage());
            re.constractJsonString();
            return new ResponseEntity<>(re, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            re.setStatusCode(203);
            re.setMessage("服务器出现异常，请稍后再试");
            re.constractJsonString();
            return new ResponseEntity<>(re, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(re, HttpStatus.OK);
    }

    //获得一个社员的Member信息
    @RequestMapping(value = "commityMember/get")
    public ResponseEntity<HttpJson> getCommityMem(@RequestBody String jsonString) {
        HttpJson inObj = new HttpJson(jsonString);
        HttpJson re = new HttpJson();

        try {
            if (!inObj.getClassName().equals("form:getOneCommityMem"))
                throw new JSONException("请求地址有错");
            String find = inObj.getPara("UUuid");
            String Cid = inObj.getPara("Cid");
            CommityMember thisMember = manageService.getOneUserInCommity(Cid, find);
            re.setClassObject(thisMember);
        } catch (JSONException e) {
            re.setStatusCode(250);
            re.setMessage("请求异常" + e.getMessage());
            re.resolveJsonString();
            return new ResponseEntity<>(re, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            re.setStatusCode(203);
            re.setMessage("服务器异常：" + e.getMessage());
            re.constractJsonString();
            new ResponseEntity<>(re, HttpStatus.BAD_GATEWAY);
        }

        re.constractJsonString();
        return new ResponseEntity<>(re, HttpStatus.OK);
    }
    //获得活动（可以根据时间）
    //public ResponseEntity<HttpJson> getCommityActivity()
    //修改一个活动


}
