package com.weiCommity.Dao;

import com.weiCommity.Model.MessageBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * PackageName com.weiCommity.Dao
 * Created by uryuo on 17/5/10.
 */
@Repository
public class MessageBoxDao {
    final MessageBox messageBox;

    @Autowired
    public MessageBoxDao(MessageBox messageBox) {
        this.messageBox = messageBox;
    }
}
