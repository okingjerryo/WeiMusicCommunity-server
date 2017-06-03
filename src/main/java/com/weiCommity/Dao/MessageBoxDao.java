package com.weiCommity.Dao;

import com.weiCommity.Model.MessageBox;
import com.weiCommity.Util.StaticVar;
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

    public void sendProjectApply(MessageBox thisMsg) {
        BaseDao.InEdDeOneIntoSql(StaticVar.getMapperNameSpace() + "insert_ProjectWorkApply", thisMsg);
    }

    public MessageBox getRelativeMailByRelativeId(String thisRelativeId) {
        return (MessageBox) BaseDao.selOneFromSQL(StaticVar.getMapperNameSpace() + "sel_MsgByRelativeId", thisRelativeId);
    }

    public void editMessageCheck(MessageBox thisMail) {
        BaseDao.InEdDeOneIntoSql(StaticVar.getMapperNameSpace() + "update_MsgCheck", thisMail);
    }

    public MessageBox getOneMail(String thisMail) {
        return (MessageBox) BaseDao.selOneFromSQL(StaticVar.getMapperNameSpace() + "sel_getOneMail", thisMail);
    }

    public void sendMessage(MessageBox reply) {
        BaseDao.InEdDeOneIntoSql(StaticVar.getMapperNameSpace() + "insert_SendMessage", reply);
    }

    public void setMailIsReaded(MessageBox message) {
        BaseDao.InEdDeOneIntoSql(StaticVar.getMapperNameSpace() + "update_setMailIsReaded", message);
    }
}
