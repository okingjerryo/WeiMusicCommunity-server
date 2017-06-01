package com.weiCommity.Dao;

import com.weiCommity.Model.*;
import com.weiCommity.Util.StaticVar;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PackageName com.weiCommity.Dao
 * Created by uryuo on 17/5/10.
 */
@Repository
public class ProjectDao extends BaseDao {
    //项目信息进入数据库
    public void createProject(ProjectInfo info) {
        BaseDao.InEdDeOneIntoSql(StaticVar.getMapperNameSpace() + "insert_ProjectCreate", info);
    }
    //注册项目角色
    public void insertProjectWork(ProjectWork thisWork) {
        BaseDao.InEdDeOneIntoSql(StaticVar.getMapperNameSpace() + "insert_ProjectWork", thisWork);
    }

    public List<ProjectInfo> getCProjectWithState(ProjectInfo thisCid) {
        List<ProjectInfo> re = (List<ProjectInfo>) BaseDao.selListFromSQL(StaticVar.getMapperNameSpace() + "sel_getAllCommityProject", thisCid);
        return re;
    }

    public ProjectInfo getOnePDetail(ProjectInfo info) {
        return (ProjectInfo) BaseDao.selOneFromSQL(StaticVar.getMapperNameSpace() + "sel_OneProjectDetail", info);
    }

    public void createUserWorkApply(ProjectWork applyWork) {
        BaseDao.InEdDeOneIntoSql(StaticVar.getMapperNameSpace() + "insert_ProjectWrokApply", applyWork);
    }

    public ProjectWorkApplyMsg getProjectWorkMsg(ProjectWork applyWork) {
        return (ProjectWorkApplyMsg) BaseDao.selOneFromSQL(StaticVar.getMapperNameSpace() + "sel_ProjectWorkApplyMsg", applyWork);
    }

    public void applyJoinApplicate(ProjectWork checkerWork) {
        BaseDao.InEdDeOneIntoSql(StaticVar.getMapperNameSpace() + "update_applyPJoinApply", checkerWork);
    }

    public List<ProjectWork> getOtherWorkApply(ProjectWork checkerWork) {
        return (List<ProjectWork>) BaseDao.selListFromSQL(StaticVar.getMapperNameSpace() + "sel_OtherWorkApply", checkerWork);
    }

    public ProjectWork getOneProjectWord(ProjectWork checkerWork) {
        return (ProjectWork) BaseDao.selOneFromSQL(StaticVar.getMapperNameSpace() + "selOneProjectWork", checkerWork);
    }

    public void delThisApply(ProjectWork checkerWork) {
        BaseDao.InEdDeOneIntoSql(StaticVar.getMapperNameSpace() + "del_ProjectMemApply", checkerWork);
    }

    public int checkProjectCanDo(ProjectWork checkerWork) {
        return (int) BaseDao.selOneFromSQL(StaticVar.getMapperNameSpace() + "sel_checkProjectCanDo", checkerWork);
    }

    public void setProjectState(ProjectInfo thisInfo) {
        BaseDao.InEdDeOneIntoSql(StaticVar.getMapperNameSpace() + "update_ProjectState", thisInfo);
    }

    public int getPersonExistFile(ProjectFile pTarFile) {
        return (int) BaseDao.selOneFromSQL(StaticVar.getMapperNameSpace() + "sel_getExistFileCount", pTarFile);
    }

    public ProjectFile getOldestFile(ProjectFile file) {
        List<ProjectFile> files = (List<ProjectFile>) BaseDao.selListFromSQL(StaticVar.getMapperNameSpace() + "sel_getFileOldest", file);
        return files.get(0);
    }

    public void delOldest(ProjectFile oldest) {
        BaseDao.InEdDeOneIntoSql(StaticVar.getMapperNameSpace() + "del_oldestFile", oldest);
    }

    public void saveProjectFile(ProjectFile pTarFile) {
        BaseDao.InEdDeOneIntoSql(StaticVar.getMapperNameSpace() + "insert_ProjectFile", pTarFile);
    }

    public ProjectWork getPersonalPW(ProjectWork work) {
        return (ProjectWork) BaseDao.selOneFromSQL(StaticVar.getMapperNameSpace() + "sel_PersonalPW", work);
    }

    public List<ProjectFile> getAllPFile(ProjectWork projectWork) {
        return (List<ProjectFile>) BaseDao.selListFromSQL(StaticVar.getMapperNameSpace() + "sel_getAllProjectFile", projectWork);
    }

    public List<ProjectWorkDetail> getAllProjectWork(ProjectInfo thisInfo) {
        return (List<ProjectWorkDetail>) BaseDao.selListFromSQL(StaticVar.getMapperNameSpace() + "sel_getAllProjectWork", thisInfo);
    }

    public void editProjectInfo(ProjectInfo thisInfo) {
        BaseDao.InEdDeOneIntoSql(StaticVar.getMapperNameSpace() + "update_ProjectInfo", thisInfo);
    }
}
