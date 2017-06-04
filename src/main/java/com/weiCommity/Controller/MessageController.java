package com.weiCommity.Controller;

import com.weiCommity.Model.MessageBox;
import com.weiCommity.Service.MessageService;
import com.weiCommity.Util.HttpJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PackageName com.weiCommity.Controller
 * Created by uryuo on 17/5/31.
 */
@RestController
@RequestMapping(value = "api/message", produces = "application/json;charset=UTF-8")
public class MessageController {

    final MessageService messageService;

    @RequestMapping("mesageTime/get")
    public ResponseEntity<HttpJson> getAllCommityMem(@RequestBody String jsonString) {
        return ControllerFreamwork.excecute(jsonString, MessageBox.class, "MessageBox:getMessageSTime", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                MessageBox messageBox = (MessageBox) inObj.getClassObject();
                MessageBox reBox = messageService.getMailTime(messageBox);
                String mailTimeStr = reBox.getMCreateTime().toString();
                re.setPara("mTime", mailTimeStr);
                return re;
            }
        });
    }

    //设置当前信息为已读

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }
}

