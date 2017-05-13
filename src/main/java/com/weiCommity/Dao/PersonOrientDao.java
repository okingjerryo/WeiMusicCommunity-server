package com.weiCommity.Dao;

import com.weiCommity.Model.Login;
import com.weiCommity.Model.PersonalInfoPersonalOriented;
import com.weiCommity.Model.ProjectInfoPersonalOriented;
import com.weiCommity.Model.UserExtend;
import com.weiCommity.Util.StaticVar;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PackageName com.weiCommity.Dao
 * Created by uryuo on 17/5/12.
 */
@Repository
public class PersonOrientDao extends BaseDao {

    public List<ProjectInfoPersonalOriented> getAllOPProject(Login thisLogin) {
        return (List<ProjectInfoPersonalOriented>) BaseDao.selListFromSQL(StaticVar.getMapperNameSpace() + "sel_getAllOPProject", thisLogin);
    }

    public List<UserExtend> getAllUWorkByUuid(Login thisUser) {
        return (List<UserExtend>) BaseDao.selListFromSQL(StaticVar.getMapperNameSpace() + "sel_AllUWorkByUuid", thisUser);
    }

    public PersonalInfoPersonalOriented getPersonalInfoOne(Login thisUser) {
        return (PersonalInfoPersonalOriented) BaseDao.selOneFromSQL(StaticVar.getMapperNameSpace() + "sel_getPersonalInfoOne", thisUser);
    }
}
