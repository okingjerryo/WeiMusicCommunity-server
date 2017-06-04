package com.weiCommity.Service;

import com.weiCommity.Dao.BaseDao;
import com.weiCommity.Dao.ProjectDao;
import com.weiCommity.Model.*;
import com.weiCommity.Util.StaticVar;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * PackageName com.weiCommity.Service
 * Created by uryuo on 17/5/10.
 */
@Service
public class ProjectService {
    final ProjectDao projectDao;
    final MessageBoxService messageBoxService;
    @Autowired
    public ProjectService(ProjectDao projectDao, MessageBoxService messageBoxService) {
        this.projectDao = projectDao;
        this.messageBoxService = messageBoxService;
    }
    //创建社团项目到数据库
    public String createProject(ProjectInfo info) {
        //动态生成新的UUid
        String Pid = UUID.randomUUID().toString();
        info.setPId(Pid);
        //调用Dao层将新数据存储进去
        projectDao.createProject(info);
        return Pid;
    }
    //将该用户注册为策划角色
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

    public ProjectWorkApplyMsg addProjectWorkApply(ProjectWork applyWork) {
        applyWork.setPWId(UUID.randomUUID().toString());
        //存入申请
        projectDao.createUserWorkApply(applyWork);
        //返回一个完整的申请信息视图
        return projectDao.getProjectWorkMsg(applyWork);
    }

    public void applyJoinApplicate(ProjectWork checkerWork, String thisMail) {
        projectDao.applyJoinApplicate(checkerWork);
        //获得自己的当前条目信息
        checkerWork = projectDao.getOneProjectWord(checkerWork);
        //查询其他同样WorkId的成员
        List<ProjectWork> perventMem = projectDao.getOtherWorkApply(checkerWork);
        //每个都走拒绝服务
        for (ProjectWork thisPeople : perventMem) {
            String thisPeopleMail = messageBoxService.getSpeIdRelativeMail(thisPeople.getPWId());
            prventJoinApplicate(thisPeople, thisPeopleMail);
        }
        //更新当前信息
        messageBoxService.editBoxCheckNum(thisMail, 1);
        //回信
        String things = "您好，很高兴收到您的信息，很高兴的通知您，您的加入申请已通过，欢迎加入我们这个项目中~";
        messageBoxService.replyMsgByIdAndThing(thisMail, things);
    }

    public void prventJoinApplicate(ProjectWork checkerWork, String thisMail) {
        //删除当前条目
        projectDao.delThisApply(checkerWork);
        //更新当前信
        messageBoxService.editBoxCheckNum(thisMail, -1);
        //回信
        String things = "您好，很高兴收到您的信息。很遗憾的通知您，因为某些原因 您的加入申请未通过，欢迎选择我们项目的其他工种，或者其他更适合的项目" +
                "对此给你带来不便还请谅解。";
        messageBoxService.replyMsgByIdAndThing(thisMail, things);
    }

    public boolean checkProjectCanDo(ProjectWork checkerWork) {
        int thisProjectCount = projectDao.checkProjectCanDo(checkerWork);
        return thisProjectCount == 4;
    }

    public void setProjectState(ProjectInfo thisInfo, int i) {
        thisInfo.setPState(i);
        projectDao.setProjectState(thisInfo);
    }

    public void delFileOldest(ProjectFile file) throws IOException {
        //1.查出最老的文件路径
        ProjectFile oldest = projectDao.getOldestFile(file);
        //2.删除当前文件
        FileUtils.forceDelete(new File(StaticVar.getToFilePath() + oldest.getPFPath()));
        //3 数据库删除这个记录
        projectDao.delOldest(oldest);
    }

    public String addFiletoUWid(String realFileName, ProjectFile pTarFile, MultipartFile file) throws IOException {
        //查询看所有文件数量是否为5
        int exsitFCount = projectDao.getPersonExistFile(pTarFile);
        if (exsitFCount == 5)
            //清除最久的文件
            delFileOldest(pTarFile);

        //文件插入
        String tarPath = "ProjectSpace/" + pTarFile.getPWId() + "/" + realFileName;
        FileUtils.writeByteArrayToFile(new File(StaticVar.getToFilePath() + tarPath), file.getBytes());
        pTarFile.setPFCreateTime(DateTime.now());
        String rePFId = UUID.randomUUID().toString();
        pTarFile.setPFId(rePFId);
        pTarFile.setPFPath(tarPath);
        projectDao.saveProjectFile(pTarFile);
        return rePFId;
    }

    public ProjectWork getPersonalPW(ProjectWork work) {
        return projectDao.getPersonalPW(work);
    }

    public List<ProjectFile> getAllPFile(ProjectWork projectWork) {
        return projectDao.getAllPFile(projectWork);
    }

    public ProjectWorkApplyMsg getProjectWorkDetail(ProjectWork pw) {
        return projectDao.getProjectWorkMsg(pw);
    }

    public List<ProjectFile> getAllPFileComplet(ProjectWorkApplyMsg thisWork) {
        return (List<ProjectFile>) BaseDao.selListFromSQL(StaticVar.getMapperNameSpace() + "sel_AllPComplateFile", thisWork);
    }

    public List<ProjectFile> getPFileWithWork(ProjectWorkApplyMsg thisWork) {
        return (List<ProjectFile>) BaseDao.selListFromSQL(StaticVar.getMapperNameSpace() + "sel_ProjectFileWithWork", thisWork);
    }

    public List<ProjectWorkDetail> getAllProjectWork(ProjectInfo thisInfo) {
        return projectDao.getAllProjectWork(thisInfo);
    }

    public void editProjectInfo(ProjectInfo thisInfo) {
        projectDao.editProjectInfo(thisInfo);
    }

    public ProjectInfo getProjectInfoOne(ProjectInfo project) {
        return projectDao.getProjectInfoOne(project);
    }

    public ProjectWork getProjectWorkOne(ProjectWork projectWork) {
        return projectDao.getProjectWorkOne(projectWork);
    }

    public void updateLrc(ProjectInfo info) {
        projectDao.updateLrc(info);
    }

    public void setFileComplete(ProjectFile thisFile) {
        projectDao.setProjectFile(thisFile);
    }

    public ProjectWorkDetail getProjectWorkWithWorkSC(ProjectWorkDetail detail) {
        return projectDao.getPWorkWithWorkSC(detail);
    }

    public ProjectWorkDetail getOnePDetailByPFid(ProjectWorkDetail thisFile) {
        return projectDao.getOneDetailPFid(thisFile);
    }

    public void setPWorkComplete(ProjectWorkDetail ceDetail) {
        projectDao.setPWrokComplete(ceDetail);
    }

    public List<ProjectFile> getMyWorkFile(ProjectWork work) {
        return projectDao.getMyWorkFile(work);
    }

    public void setFileApply(ProjectFile upDateFile) {
        upDateFile.setPFIsUseComplete(1);
        projectDao.setFileApply(upDateFile);
    }

    public ProjectWork getProjectWorkByPWId(ProjectWork thisWork) {
        return projectDao.getProjectWorkByPWId(thisWork);
    }

    public List<ProjectFile> getAllProjectCeFile(ProjectInfoPersonalOriented thisInfo) {
        return projectDao.getAllPCeFile(thisInfo);
    }


    //返回ProjectId
    public String setPFStateComplete(boolean b, String mSpcId) {
        ProjectFile thisFile = new ProjectFile();
        thisFile.setPFId(mSpcId);
        if (b) {
            thisFile = projectDao.getProjectFileWithPFID(thisFile);
            thisFile.setPWIsComplete(1);
            projectDao.updatePWByPFDetail(thisFile);
            return thisFile.getPId();
        } else {
            thisFile.setPFIsUseComplete(0);
            projectDao.setFileApply(thisFile);
        }
        return "";
    }

    public ProjectFile getProjectFileDetailOne(ProjectFile thisPeFile) {
        return projectDao.getOnePFDetailOne(thisPeFile);
    }

    public void setProjectFileCancal(ProjectFile thisPeFile) {
        projectDao.setProjectFileCancal(thisPeFile);
        projectDao.setProjectFileCancal2(thisPeFile);
    }

    public ProjectFile getPFByWorkandPid(ProjectFile thisStateFile) {
        return projectDao.getPFByWorkandPid(thisStateFile);
    }

    public CommityMember getMemByCidAndUUid(CommityMember member) {
        return projectDao.getMemByCidAndUuid(member);
    }
}
