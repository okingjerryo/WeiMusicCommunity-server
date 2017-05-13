package com.weiCommity.Dao;

import com.weiCommity.Model.ProjectFile;
import com.weiCommity.Model.ProjectInfo;
import com.weiCommity.Model.ProjectWork;
import com.weiCommity.Util.StaticVar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PackageName com.weiCommity.Dao
 * Created by uryuo on 17/5/10.
 */
@Repository
public class ProjectDao extends BaseDao {
    final ProjectFile file;
    final ProjectInfo info;
    final ProjectWork work;

    @Autowired
    public ProjectDao(ProjectFile file, ProjectInfo info, ProjectWork work) {
        this.file = file;
        this.info = info;
        this.work = work;
    }

    public void createProject(ProjectInfo info) {
        BaseDao.InEdDeOneIntoSql(StaticVar.getMapperNameSpace() + "insert_ProjectCreate", info);
    }

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
}
