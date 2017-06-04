package com.weiCommity.Controller;

import com.weiCommity.Model.ProjectBonus;
import com.weiCommity.Model.ProjectInfo;
import com.weiCommity.Service.BonusService;
import com.weiCommity.Util.HttpJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * PackageName com.weiCommity.Controller
 * Created by uryuo on 17/5/17.
 */
@RestController
@RequestMapping(value = "api/bonus", produces = "application/json;charset=UTF-8")
public class BonusController {
    final BonusService bonusService;

    @Autowired
    public BonusController(BonusService bonusService) {
        this.bonusService = bonusService;
    }

    @RequestMapping("listAll")
    ResponseEntity<HttpJson> getAllCommityBounse(@RequestBody String jsonString) {
        return ControllerFreamwork.excecute(jsonString, ProjectInfo.class, "ProjectInfo:getAllCommityBonus", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                ProjectInfo info = (ProjectInfo) inObj.getClassObject();
                List<ProjectBonus> bList = bonusService.getAllCommityBonus(info);
                re.setClassObject(bList);
                return re;
            }
        });
    }
}
