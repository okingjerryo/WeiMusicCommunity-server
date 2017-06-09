package com.weiCommity.Controller;

import com.weiCommity.Model.*;
import com.weiCommity.Service.PersonOrientedService;
import com.weiCommity.Util.HttpJson;
import com.weiCommity.Util.StaticVar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * PackageName com.weiCommity.Controller
 * Created by uryuo on 17/5/12.
 */
@RestController
@RequestMapping(value = "api/personalO", produces = "application/json;charset=UTF-8")
public class PersonOrientedController {
    final PersonOrientedService personOrientedService;


    //列出所有与自己相关的状态区间的项目
    @RequestMapping("project/get")
    ResponseEntity<HttpJson> getAllPersonalProject(@RequestBody String jsonString) {
        return ControllerFreamwork.excecute(jsonString, Login.class, "ProjectInfoPersonalOriented:getAllPersonalProject", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) {
                ProjectInfoPersonalOriented thisLogin = (ProjectInfoPersonalOriented) inObj.getClassObject();
                List<ProjectInfoPersonalOriented> reProject = personOrientedService.getAllProject(thisLogin);
                re.setClassObject(reProject);
                return re;
            }
        });
    }

    //通过获得全部常用工种
    @RequestMapping("UserTFWork/get")
    public ResponseEntity<HttpJson> getAllUserWorkByUUuid(@RequestBody String jsonString) {
        return ControllerFreamwork.excecute(jsonString, Login.class, "Login:getAllUWByUuid", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                Login thisUser = (Login) inObj.getClassObject();
                List<UserExtend> reWork = personOrientedService.getAllUWork(thisUser);

                return re;
            }
        });
    }


    //列出所有与自己相关属性的邮件 注意注入 MspecialType
    @RequestMapping("personalMail/get")
    ResponseEntity<HttpJson> getAllPersonalMail(@RequestBody String jsonString) {
        return ControllerFreamwork.excecute(jsonString, MessageBox.class, "MessageBox:getAllPersonalMail", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                MessageBox thisUser = (MessageBox) inObj.getClassObject();
                List<MessageBox> reMsg = new ArrayList<>();
                switch (thisUser.getMSenderType()) {
                    case 3:
                        reMsg = personOrientedService.getAllMailPPO(thisUser);

                }

                re.setClassObject(reMsg);
                return re;
            }
        });
    }

    //列出所有与自己相关的个人信息
    @RequestMapping("personalInfoOne/get")
    public ResponseEntity<HttpJson> getPersonalInfoOne(@RequestBody String jsonString) {
        return ControllerFreamwork.excecute(jsonString, Login.class, "Login:getPersonalInfoPO", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                Login thisUser = (Login) inObj.getClassObject();
                PersonalInfoPersonalOriented thisInfo = personOrientedService.getPersonalInfoOne(thisUser);
                re.setPara("birthday", thisInfo.getUBirthday().toString(StaticVar.getDateOnlyFormat()));
                re.setClassObject(thisInfo);
                return re;
            }
        });
    }

    //获取与自己相关的全部社团
    @RequestMapping("personalCommity/get")
    public ResponseEntity<HttpJson> getAllPersonalRCommity(@RequestBody String jsonString) {
        return ControllerFreamwork.excecute(jsonString, Login.class, "Login:getAllPersonalCommityPO", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                Login thisUser = (Login) inObj.getClassObject();
                List<CommityInfoPersonalOriented> usersCommity = personOrientedService.getAllCommityPO(thisUser);
                re.setClassObject(usersCommity);
                return re;
            }
        });
    }

    //获得自己的全部能访问到的项目
    @RequestMapping("personalProjectPO/get")
    public ResponseEntity<HttpJson> getAllProjectRePO(@RequestBody String jsonString) {
        return ControllerFreamwork.execute(jsonString, "form:getAllProjectRPO", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                //获得 UUid 注入 UUid
                String thisUUid = inObj.getPara("UUid");
                //1.PWid查询所有与UUid相关的条目
                ProjectInfoPersonalOriented wannaGet = new ProjectInfoPersonalOriented();
                wannaGet.setThisUUuid(thisUUid);
                wannaGet.setStartState(0);
                wannaGet.setEndState(5);
                List<ProjectInfoPersonalOriented> allProjectPo = personOrientedService.getAllProject(wannaGet);

                //根据不同情况筛选
                List<ProjectInfoPersonalOriented> send = new ArrayList<ProjectInfoPersonalOriented>();
                for (ProjectInfoPersonalOriented thisElem : allProjectPo) {
                    switch (thisElem.getWorkSC()) {
                        case "策划":
                            send.add(thisElem);
                            break;
                        case "歌手":
                            if (thisElem.getPState() == 2)
                                send.add(thisElem);
                            break;
                        case "后期":
                            if (thisElem.getPState() == 2 || thisElem.getPState() == 3)
                                send.add(thisElem);
                            break;
                        case "美工":
                            if (thisElem.getPState() == 3 || thisElem.getPState() == 4)
                                send.add(thisElem);
                            break;
                    }
                }

                re.setClassObject(send);
                return re;
            }
        });
    }

    //列出自己的全部工种
    @RequestMapping("personalAllWork/get")
    public ResponseEntity<HttpJson> getAllWork(@RequestBody String jsonString) {
        return ControllerFreamwork.execute(jsonString, "form:getAllTFWork", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                String thisUUid = inObj.getPara("UUid");
                List<UserTFWork> reList = personOrientedService.getAllUTFWork(thisUUid);
                re.setClassObject(reList);
                return re;
            }
        });
    }

    //列出自己社团可能可以的社团全部信息
    @RequestMapping("personalAllCommityMessage/get")
    public ResponseEntity<HttpJson> getAllCommityMessage(@RequestBody String jsonString) {
        return ControllerFreamwork.execute(jsonString, "form:getAllCommity", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                String thisUUid = inObj.getPara("UUid");
                //获取这个人全部的CommityMem信息
                CommityMember member = new CommityMember();
                member.setUUuid(thisUUid);
                List<CommityMember> myCommityList = personOrientedService.getAllCommityMemPO(member);
                List<MessageBox> messageBoxes = new ArrayList<>();
                for (CommityMember elem : myCommityList) {
                    //如果这个人是管理员以上就将这个社团的信息邮件全部给过去
                    if (elem.getUtype() > 1) {
                        List<MessageBox> thisCMes = personOrientedService.getAllCommityMsg(elem);
                        messageBoxes.addAll(thisCMes);
                    }
                }
                re.setClassObject(messageBoxes);
                return re;
            }
        });
    }

    //列出自己全部未读的个人信息
    @RequestMapping("personalMegPO/get")
    public ResponseEntity<HttpJson> getAllPersonalMessage(@RequestBody String jsonString) {
        return ControllerFreamwork.execute(jsonString, "form:getAllPersonalMsg", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                String thisUUid = inObj.getPara("UUid");
                MessageBox messageBox = new MessageBox();
                messageBox.setMTarId(thisUUid);
                List<MessageBox> list = new ArrayList<>();
                list = personOrientedService.getAllPersonalMsg(messageBox);
                re.setClassObject(list);
                return re;
            }
        });
    }
    @Autowired
    public PersonOrientedController(PersonOrientedService personOrientedService) {
        this.personOrientedService = personOrientedService;
    }


}
