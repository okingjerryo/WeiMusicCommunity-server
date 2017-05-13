package com.weiCommity.Controller;

import com.weiCommity.Model.CommityInfo;
import com.weiCommity.Model.UserExtend;
import com.weiCommity.Service.CommityService;
import com.weiCommity.Util.HttpJson;
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
@RequestMapping(value = "api/commity", produces = "application/json;charset=UTF-8")
public class CommityContorller {
    final CommityService commityService;


    @RequestMapping("getAllMemList")
    public ResponseEntity<HttpJson> getAllCommityMem(@RequestBody String jsonString) {
        return ControllerFreamwork.excecute(jsonString, CommityInfo.class, "CommityInfo:getAllMemList", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                CommityInfo info = (CommityInfo) inObj.getClassObject();
                List<UserExtend> reList = commityService.getAllCommityMemList(info);
                re.setClassObject(reList);
                return re;
            }
        });
    }


    @Autowired
    public CommityContorller(CommityService commityService) {
        this.commityService = commityService;
    }
}
