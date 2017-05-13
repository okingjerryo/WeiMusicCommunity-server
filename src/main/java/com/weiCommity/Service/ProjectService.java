package com.weiCommity.Service;

import com.weiCommity.Dao.ProjectDao;
import com.weiCommity.Model.ProjectInfo;
import com.weiCommity.Model.ProjectWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * PackageName com.weiCommity.Service
 * Created by uryuo on 17/5/10.
 */
@Service
public class ProjectService {
    final ProjectDao projectDao;

    @Autowired
    public ProjectService(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public String createProject(ProjectInfo info) {
        String Pid = UUID.randomUUID().toString();
        info.setPId(Pid);
        projectDao.createProject(info);
        return Pid;
    }

    public String setPeopleWorkToProject(ProjectWork thisWork) {
        String PWId = UUID.randomUUID().toString();
        thisWork.setPWId(PWId);
        projectDao.insertProjectWork(thisWork);
        return PWId;
    }


    public List<ProjectInfo> getAllCommityProject(ProjectInfo thisCid) {
        List<ProjectInfo> getRe = projectDao.getCProjectWithState(thisCid);

        return getRe;
    }

    public ProjectInfo getOnePDetail(ProjectInfo info) {
        return projectDao.getOnePDetail(info);
    }
}
