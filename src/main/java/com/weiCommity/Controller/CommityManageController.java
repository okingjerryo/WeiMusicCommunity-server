package com.weiCommity.Controller;

import com.weiCommity.Model.CommityInfo;
import com.weiCommity.Service.CommityManageService;
import com.weiCommity.Util.HttpJson;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * PackageName com.weiCommity.Controller
 * 社团管理类Conntroller 让有权限的用户可以对社团进行一些操作
 * Created by uryuo on 17/5/1.
 */
@RestController
@RequestMapping(value = "api/commity")
public class CommityManageController {
    private final CommityManageService manageService;

    @Autowired
    public CommityManageController(CommityManageService manageService) {
        this.manageService = manageService;
    }

    //社团注册
    //1.社团基本信息注入    2.人员表中当前用户入库 设置权限为（4.社长）  返回成功 or 社团的id
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
            String thisNewCId;
        } catch (JSONException e) {
            re.setStatusCode(250);
            re.setMessage("注册请求失败" + e.getMessage());
            return new ResponseEntity<HttpJson>(re, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            re.setStatusCode(203);
            re.setMessage("服务器出现异常，请稍后再试");
            return new ResponseEntity<HttpJson>(re, HttpStatus.BAD_GATEWAY);

        }
        return new ResponseEntity<HttpJson>(re, HttpStatus.ACCEPTED);
    }


}
