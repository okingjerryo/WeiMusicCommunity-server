package com.weiCommity.Dao;

import com.weiCommity.Model.CommityInfo;
import com.weiCommity.Model.CommityMember;
import com.weiCommity.Model.ProjectBonus;
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

    public List<CommityInfo> searchCommity(String findStr) {
        return (List<CommityInfo>) BaseDao.selListFromSQL(StaticVar.getMapperNameSpace() + "sel_searchCommity", findStr);
    }

    public void insertMemApply(CommityMember member) {
        BaseDao.InEdDeOneIntoSql(StaticVar.getMapperNameSpace() + "insert_CommiytMemApply", member);
    }

    public CommityMember getOneCMemApplyMsg(CommityMember member) {
        return (CommityMember) BaseDao.selOneFromSQL(StaticVar.getMapperNameSpace() + "sel_CMemApplyMsg", member);
    }

    public void delJoinApply(CommityMember member) {
        BaseDao.InEdDeOneIntoSql(StaticVar.getMapperNameSpace() + "del_CommityMemApply", member);
    }

    public CommityMember getCommityMem(CommityMember member) {
        return (CommityMember) BaseDao.selOneFromSQL(StaticVar.getMapperNameSpace() + "sel_CommityMem", member);
    }

    public void setCMState(CommityMember member) {
        BaseDao.InEdDeOneIntoSql(StaticVar.getMapperNameSpace() + "update_CMState", member);
    }

    public void insertCBMem(ProjectBonus newMem) {
        BaseDao.InEdDeOneIntoSql(StaticVar.getMapperNameSpace() + "insert_CBMem", newMem);
    }
}
