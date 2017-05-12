package com.weiCommity.Service;

import com.weiCommity.Dao.ProjectDynamicDao;
import com.weiCommity.Model.ProjectDynamic;
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
        ProjectDynamic inputDyn = getProjectDyWithNOFile(dynamic);
        constrctPDyWithNOFile(inputDyn);
    }


    //组装动态字段进入数据库
    private void constrctPDyWithNOFile(ProjectDynamic inputDyn) {
        String DyWord = "在" + inputDyn.getTime() + " ," + inputDyn.getWorkSC() + inputDyn.getUNackName() +
                inputDyn.getPDType() + "了项目" + inputDyn.getPTitle();
        dynamicDao.setDynWord(DyWord);
    }


    //从数据库反向取出数据
    private ProjectDynamic getProjectDyWithNOFile(ProjectDynamic dynamic) {
        return dynamicDao.getProjectDynAfterInView(dynamic);
    }

    @Autowired
    public ProjectDynamicService(ProjectDynamicDao dynamicDao) {
        this.dynamicDao = dynamicDao;
    }
}
