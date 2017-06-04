package com.weiCommity.Service;

import com.weiCommity.Dao.PersonOrientDao;
import com.weiCommity.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PackageName com.weiCommity.Service
 * Created by uryuo on 17/5/12.
 */
@Service
public class PersonOrientedService {
    final PersonOrientDao personOrientDao;

    //获得全部与自己相关的制定区间的Project
    public List<ProjectInfoPersonalOriented> getAllProject(ProjectInfoPersonalOriented thisLogin) {
        return personOrientDao.getAllOPProject(thisLogin);
    }

    @Autowired
    public PersonOrientedService(PersonOrientDao personOrientDao) {
        this.personOrientDao = personOrientDao;
    }


    public List<UserExtend> getAllUWork(Login thisUser) {
        return personOrientDao.getAllUWorkByUuid(thisUser);
    }

    public PersonalInfoPersonalOriented getPersonalInfoOne(Login thisUser) {
        return personOrientDao.getPersonalInfoOne(thisUser);
    }

    public List<CommityInfoPersonalOriented> getAllCommityPO(Login thisUser) {
        return personOrientDao.getAllCommityInfoPO(thisUser);
    }

    public List<MessageBox> getAllMailPPO(MessageBox thisUser) {
        return personOrientDao.getAllMailPPO(thisUser);
    }

    public List<ProjectInfoPersonalOriented> getAllProjectDoing(Login login) {
        return personOrientDao.getAllProjectPO(login);
    }

    public List<UserTFWork> getAllUTFWork(String thisUUid) {
        return personOrientDao.getAllUTFWork(thisUUid);
    }

    public List<CommityMember> getAllCommityMemPO(CommityMember member) {
        return personOrientDao.getAllCommityMemPO(member);
    }

    public List<MessageBox> getAllCommityMsg(CommityMember elem) {
        return personOrientDao.getAllCommityMsg(elem);
    }

    public List<MessageBox> getAllPersonalMsg(MessageBox messageBox) {
        return personOrientDao.getAllPersonalMessage(messageBox);
    }
}
