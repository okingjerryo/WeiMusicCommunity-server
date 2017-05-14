package com.weiCommity.Service;

import com.weiCommity.Dao.MessageBoxDao;
import com.weiCommity.Model.MessageBox;
import com.weiCommity.Model.ProjectWorkApplyMsg;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * PackageName com.weiCommity.Service
 * Created by uryuo on 17/5/10.
 */
@Service
public class MessageBoxService {
    final MessageBoxDao messageBoxDao;

    @Autowired
    public MessageBoxService(MessageBoxDao messageBoxDao) {
        this.messageBoxDao = messageBoxDao;
    }

    public void sendApplyToProjectCreater(ProjectWorkApplyMsg applyMsg) {
        //组装信件
        MessageBox thisMsg = new MessageBox();
        thisMsg.setMId(UUID.randomUUID().toString());
        thisMsg.setMCheck(0);
        thisMsg.setMCreateTime(DateTime.now());
        thisMsg.setMSenderId(applyMsg.getUUuid());
        thisMsg.setMSenderType(3);
        thisMsg.setMSpcId(applyMsg.getPWId());
        thisMsg.setMTarId(applyMsg.getCreatUUuid());
        thisMsg.setMTitle(applyMsg.getCName() + "社团项目：" + applyMsg.getPTitle() + "的" + applyMsg.getWorkSC() + "工种加入申请");
        String MsgTing = "策划你好，我是本社团的" + applyMsg.getWorkSC() + applyMsg.getUNackName() + "。最近在社团中了解到了这个项目，对其中的" +
                applyMsg.getWorkSC() + "部分很感兴趣，想加入该项目与您一同完成。如果确认我可以胜任，希望能准许加入，期待与您一同完成这个优秀的项目。谢谢~~";
        thisMsg.setMThings(MsgTing);
        //发送
        messageBoxDao.sendProjectApply(thisMsg);

    }

    public String getSpeIdRelativeMail(String thisRelativeId) {
        MessageBox thisMsg = messageBoxDao.getRelativeMailByRelativeId(thisRelativeId);
        return thisMsg.getMId();
    }

    public void editBoxCheckNum(String thisMail, int i) {
        MessageBox editMsg = new MessageBox();
        editMsg.setMId(thisMail);
        editMsg.setMCheck(i);
        messageBoxDao.editMessageCheck(editMsg);
    }

    public void replyMsgByIdAndThing(String thisMail, String things) {
        //获取当前Mail的详细信息
        MessageBox msg = new MessageBox();
        msg.setMId(thisMail);
        msg = messageBoxDao.getOneMail(thisMail);

        MessageBox reply = new MessageBox();
        reply.setMId(UUID.randomUUID().toString());
        reply.setMThings(things);
        reply.setMTitle("答复：" + msg.getMTitle());
        reply.setMTarId(msg.getMSenderId());
        reply.setMSenderId(msg.getMTarId());
        reply.setMSenderType(3);
        reply.setMCreateTime(DateTime.now());

        messageBoxDao.sendMessage(reply);
    }
}
