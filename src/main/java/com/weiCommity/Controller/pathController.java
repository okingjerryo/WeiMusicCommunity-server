package com.weiCommity.Controller;

import com.weiCommity.Util.HttpJson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * Created by uryuo on 17/4/26.
 */
@RestController
@RequestMapping("tomcat")
public class pathController {
    @RequestMapping(produces = "appliction/json")
    public HttpJson getPath(String a){
         HttpJson re = new HttpJson();
        File test = null;
        try {
            test = ResourceUtils.getFile("classpath:/pic/default_personal_image.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(test.getAbsolutePath());
         re.setClassObject(test);
         return re;
    }
}
