package com.weiCommity.Service;

import com.weiCommity.Dao.MessageBoxDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
