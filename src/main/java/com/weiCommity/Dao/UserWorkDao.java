package com.weiCommity.Dao;

import com.weiCommity.Model.ProjectWork;
import com.weiCommity.Model.UserTFWork;
import com.weiCommity.Util.StaticVar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * PackageName com.weiCommity.Dao
 * Created by uryuo on 17/5/10.
 */

@Repository
public class UserWorkDao extends BaseDao {
    final UserTFWork userWork;


    public UserTFWork getUserWorkByProjectWork(ProjectWork applyWodk) {
        return (UserTFWork) BaseDao.selOneFromSQL(StaticVar.getMapperNameSpace() + "sel_UserWorkbyProjectWork", applyWodk);
    }

    //bean
    @Autowired
    public UserWorkDao(UserTFWork userWork) {
        this.userWork = userWork;
    }

    public UserTFWork getUserWorkByWorkIdandUUid(UserTFWork work) {
        return (UserTFWork) BaseDao.selOneFromSQL(StaticVar.getMapperNameSpace() + "sel_UserWorkByUUidandWid", work);
    }
}
