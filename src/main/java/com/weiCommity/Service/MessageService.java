package com.weiCommity.Service;

import com.weiCommity.Dao.MessageDao;
import com.weiCommity.Model.MessageBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PackageName com.weiCommity.Service
 * Created by uryuo on 17/5/31.
 */
@Service
public class MessageService {
    final
    MessageDao messageDao;

    public MessageBox getMailTime(MessageBox messageBox) {
        return messageDao.getMailTime(messageBox);
    }

    @Autowired
    public MessageService(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

}
