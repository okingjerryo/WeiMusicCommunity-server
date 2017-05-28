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


    //列出所有与自己相关的邮件
    @RequestMapping("personalMail/get")
    ResponseEntity<HttpJson> getAllPersonalMail(@RequestBody String jsonString) {
        return ControllerFreamwork.excecute(jsonString, Login.class, "Login:getAllPersonalMail", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                Login thisUser = (Login) inObj.getClassObject();
                List<MessageBox> reMsg = personOrientedService.getAllMailPO(thisUser);
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

    @Autowired
    public PersonOrientedController(PersonOrientedService personOrientedService) {
        this.personOrientedService = personOrientedService;
    }


}
