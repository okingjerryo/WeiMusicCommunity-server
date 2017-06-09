package com.weiCommity.Controller;

import com.weiCommity.Model.ProjectBonus;
import com.weiCommity.Model.ProjectInfo;
import com.weiCommity.Service.BonusService;
import com.weiCommity.Util.HttpJson;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        HttpJson re = new HttpJson();
        HttpJson inObj = new HttpJson(jsonString, ProjectInfo.class);
        try {
            //对不属于本Controller的请求进行排他

            if (!inObj.getClassName().equals("ProjectInfo:ListAll"))
                throw new JSONException("");
            //执行每个Controller特有的逻辑
            ProjectInfo info = (ProjectInfo) inObj.getClassObject();
            List<ProjectBonus> bList = bonusService.getAllCommityBonus(info);
            re.setClassObject(bList);
            //Controller执行结束后re发送端的json串进行组装
            re.constractJsonString();
            //请求不合法抛出异常
        } catch (JSONException e) {
            re.setStatusCode(250);
            re.setMessage("请求不合法" + e.getMessage());
            re.constractJsonString();
            return new ResponseEntity<>(re, HttpStatus.BAD_REQUEST);
            //服务器抛出异常
        } catch (Exception e) {
            re.setStatusCode(203);
            re.setMessage("服务器出现异常，请稍后再试");
            re.constractJsonString();
            return new ResponseEntity<HttpJson>(re, HttpStatus.BAD_GATEWAY);
        }
        //没有抛出异常则将数据发送给客户端
        return new ResponseEntity<HttpJson>(re, HttpStatus.OK);

    }
}
