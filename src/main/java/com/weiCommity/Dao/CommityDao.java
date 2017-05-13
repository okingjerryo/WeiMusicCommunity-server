package com.weiCommity.Dao;

import com.weiCommity.Model.CommityInfo;
import com.weiCommity.Model.UserExtend;
import com.weiCommity.Util.StaticVar;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PackageName com.weiCommity.Dao
 * Created by uryuo on 17/5/12.
 */
@Repository
public class CommityDao extends BaseDao {

    public List<UserExtend> getCommityMemList(CommityInfo info) {
        return (List<UserExtend>) BaseDao.selListFromSQL(StaticVar.getMapperNameSpace() + "sel_getAllCommityMemL", info);
    }
}
