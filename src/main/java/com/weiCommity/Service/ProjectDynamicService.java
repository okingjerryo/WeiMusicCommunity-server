package com.weiCommity.Service;

import com.weiCommity.Dao.ProjectDynamicDao;
import com.weiCommity.Model.ProjectDynamic;
import com.weiCommity.Model.ProjectFile;
import com.weiCommity.Util.StaticVar;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * PackageName com.weiCommity.Service
 * Created by uryuo on 17/5/11.
 */
@Service
public class ProjectDynamicService {
    final ProjectDynamicDao dynamicDao;

    public void setProjectDynWithNOFile(ProjectDynamic dynamic) {
        dynamic.setPDId(UUID.randomUUID().toString());
        dynamic.setTime(DateTime.now());
        //插入 然后执行文字更新
        dynamicDao.insertDynamic(dynamic);
        ProjectDynamic inputDyn = getProjectDyWithNOFile(dynamic);
        constrctPDyWithNOFile(inputDyn);
    }


    //组装动态字段进入数据库
    private void constrctPDyWithNOFile(ProjectDynamic inputDyn) {
        String DyWord = "在" + inputDyn.getTime().toString(StaticVar.getDateFormat()) + " ," + inputDyn.getWorkSC() + inputDyn.getUNackName() +
                inputDyn.getPDType() + "了项目:" + inputDyn.getPTitle();
        inputDyn.setWord(DyWord);
        dynamicDao.setDynWord(inputDyn);
    }


    //从数据库反向取出数据
    private ProjectDynamic getProjectDyWithNOFile(ProjectDynamic dynamic) {
        return dynamicDao.getProjectDynAfterInView(dynamic);
    }

    @Autowired
    public ProjectDynamicService(ProjectDynamicDao dynamicDao) {
        this.dynamicDao = dynamicDao;
    }

    public void setProjectFileDynamic(ProjectFile pTarFile) {
        //dynmic 插入
        ProjectDynamic projectDynamic = new ProjectDynamic();
        projectDynamic.setTime(DateTime.now());
        projectDynamic.setPDId(UUID.randomUUID().toString());
        projectDynamic.setPWId(pTarFile.getPWId());
        projectDynamic.setPDType("更新");
        projectDynamic.setPFId(pTarFile.getPFId());
        dynamicDao.insertDynamic(projectDynamic);
        //获取当前视图
        ProjectDynamic afterDyn = dynamicDao.getProjectDynWFAfterInView(projectDynamic);
        //构造word
        constrctPDyWithFile(afterDyn);
    }

    private void constrctPDyWithFile(ProjectDynamic inputDyn) {
        String DyWord = "在" + inputDyn.getTime().toString(StaticVar.getDateFormat()) + " ," + inputDyn.getWorkSC() + inputDyn.getUNackName() +
                inputDyn.getPDType() + "了项目:" + inputDyn.getPTitle() + "的文件，并备注了：" + inputDyn.getPFNotice();
        inputDyn.setWord(DyWord);
        dynamicDao.setDynWord(inputDyn);
    }
}
