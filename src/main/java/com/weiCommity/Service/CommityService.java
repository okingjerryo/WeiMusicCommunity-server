package com.weiCommity.Service;

import com.weiCommity.Dao.CommityDao;
import com.weiCommity.Model.CommityInfo;
import com.weiCommity.Model.CommityMember;
import com.weiCommity.Model.ProjectBonus;
import com.weiCommity.Model.UserExtend;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * PackageName com.weiCommity.Service
 * Created by uryuo on 17/5/12.
 */
@Service
public class CommityService {
    final CommityDao commityDao;

    public List<UserExtend> getAllCommityMemList(CommityInfo info) {

        return commityDao.getCommityMemList(info);
    }

    @Autowired
    public CommityService(CommityDao commityDao) {
        this.commityDao = commityDao;
    }

    public List<CommityInfo> searchCommity(String findStr) {
        return commityDao.searchCommity(findStr);
    }

    public CommityMember insertMemApply(CommityMember member) {
        member.setCMid(UUID.randomUUID().toString());
        member.setUJoinTime(new DateTime());
        commityDao.insertMemApply(member);
        return member;
    }

    public CommityMember getCMemApplyMsg(CommityMember member) {
        return commityDao.getOneCMemApplyMsg(member);
    }

    public void delJoinApply(CommityMember member) {
        commityDao.delJoinApply(member);
    }

    public CommityMember getCommityMem(CommityMember member) {
        return commityDao.getCommityMem(member);
    }

    public void setCMState(CommityMember member) {
        commityDao.setCMState(member);
    }

    public void insertNewCBMem(ProjectBonus newMem) {
        newMem.setCBId(UUID.randomUUID().toString());
        commityDao.insertCBMem(newMem);
    }

    public boolean isMemExist(CommityMember member) {
        return commityDao.getCommityMemWithCidandUUid(member) == null;
    }
}
