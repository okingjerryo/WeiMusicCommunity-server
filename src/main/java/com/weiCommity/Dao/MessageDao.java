package com.weiCommity.Dao;

import com.weiCommity.Model.MessageBox;
import com.weiCommity.Util.StaticVar;
import org.springframework.stereotype.Repository;

/**
 * PackageName com.weiCommity.Dao
 * Created by uryuo on 17/5/31.
 */
@Repository
public class MessageDao extends BaseDao {

    public MessageBox getMailTime(MessageBox messageBox) {
        return (MessageBox) BaseDao.selOneFromSQL(StaticVar.getMapperNameSpace() + "sel_messageTime", messageBox);
    }
}
