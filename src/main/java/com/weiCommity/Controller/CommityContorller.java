package com.weiCommity.Controller;

import com.weiCommity.Model.*;
import com.weiCommity.Service.CommityService;
import com.weiCommity.Service.MessageBoxService;
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
    final MessageBoxService messageBoxService;

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

    //通过文字对社团的名字进行搜索
    @RequestMapping("searchCommity")
    public ResponseEntity<HttpJson> searchCommity(@RequestBody String jsonString) {
        return ControllerFreamwork.execute(jsonString, "form:searchCommity", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                String findStr = inObj.getPara("search");
                List<CommityInfo> lists = commityService.searchCommity(findStr);
                re.setClassObject(lists);
                return re;
            }
        });
    }

    //社团加入申请 需要Cid和UUid
    @RequestMapping("addCommity")
    public ResponseEntity<HttpJson> addCommity(@RequestBody String jsonString) {
        return ControllerFreamwork.excecute(jsonString, CommityMember.class, "CommityMem:addApply", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                CommityMember member = (CommityMember) inObj.getClassObject();
                //记录插入
                member = commityService.insertMemApply(member);
                //通过Mem查询到想发送信息的数据
                member = commityService.getCMemApplyMsg(member);
                //组装信件
                MessageBox messageBox = new MessageBox();
                messageBox.setMCheck(0);
                messageBox.setMSenderType(2);
                messageBox.setMTitle("社团：" + member.getCName() + "的成员加入申请");
                messageBox.setMThings("尊敬的管理员您好，我是" + member.getUNackName() + "。在最近发现了贵社团，了解后非常想加入进来，故发送申请~ 希望能加入到这个大家庭中");
                messageBox.setMSenderId(member.getUUuid());
                messageBox.setMTarId(member.getCid());
                messageBox.setMSpcId(member.getCMid());
                messageBoxService.sendMail(messageBox);
                return re;
            }
        });
    }

    //同意 或拒绝社团加入申请
    @RequestMapping("applyMemberJoin")
    public ResponseEntity<HttpJson> applyMemJoin(@RequestBody String jsonString) {
        return ControllerFreamwork.excecute(jsonString, MessageBox.class, "Message:applyMemJoin", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                MessageBox messageBox = (MessageBox) inObj.getClassObject();
                String applyStr = inObj.getPara("check");
                CommityMember member = new CommityMember();
                member.setCMid(messageBox.getMSpcId());
                member = commityService.getCommityMem(member);
                if (applyStr.equals("true")) {
                    //同意申请
                    member.setUtype(1);
                    commityService.setCMState(member);
                    //积分表添加
                    ProjectBonus newMem = new ProjectBonus();
                    newMem.setCMid(member.getCMid());
                    commityService.insertNewCBMem(newMem);
                    //发信
                    MessageBox box = new MessageBox();
                    box.setMTarId(messageBox.getMSenderId());
                    box.setMSenderId(messageBox.getMTarId());
                    box.setMThings("很高兴的告诉您，您的加入社团审核成功，欢迎加入我们的大家庭");
                    box.setMTitle("回复：" + messageBox.getMTitle());
                    box.setMSenderType(1);
                    messageBoxService.sendMail(box);
                } else {
                    //删除申请
                    commityService.delJoinApply(member);
                    //发信
                    MessageBox box = new MessageBox();
                    box.setMTarId(messageBox.getMSenderId());
                    box.setMSenderId(messageBox.getMTarId());
                    box.setMThings("很抱歉的告诉您，您的加入社团审核失败");
                    box.setMTitle("回复：" + messageBox.getMTitle());
                    box.setMSenderType(1);
                    messageBoxService.sendMail(box);
                }
                return re;
            }
        });
    }
    @Autowired
    public CommityContorller(CommityService commityService, MessageBoxService messageBoxService) {
        this.commityService = commityService;
        this.messageBoxService = messageBoxService;
    }
}
