package com.weiCommity.Service;

import com.weiCommity.Dao.PersonOrientDao;
import com.weiCommity.Model.Login;
import com.weiCommity.Model.PersonalInfoPersonalOriented;
import com.weiCommity.Model.ProjectInfoPersonalOriented;
import com.weiCommity.Model.UserExtend;
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
    public List<ProjectInfoPersonalOriented> getAllProject(Login thisLogin) {
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
}
