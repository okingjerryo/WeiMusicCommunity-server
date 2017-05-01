package com.weiCommity.Controller;

import com.weiCommity.Model.UserExtend;
import com.weiCommity.Util.HttpJson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PackageName com.weiCommity.Controller
 * Created by uryuo on 17/5/1.
 */
//给 Paw获得Json 的Controler
@RestController
@RequestMapping(value = "/api/getjson")
public class PathController {
    @RequestMapping(produces = "application/json")
    public ResponseEntity<HttpJson> getPathTest(@RequestBody String test) {
        HttpJson inObj = new HttpJson(test);
        HttpJson json = new HttpJson();
        UserExtend ue = new UserExtend();
        json.setClassObject(ue);
        json.setClassName(String.class.toString());

        return new ResponseEntity<HttpJson>(json, HttpStatus.ACCEPTED);
    }
}
