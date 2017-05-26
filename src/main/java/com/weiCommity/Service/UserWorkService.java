package com.weiCommity.Service;

import com.weiCommity.Dao.UserWorkDao;
import com.weiCommity.Model.ProjectWork;
import com.weiCommity.Model.UserTFWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PackageName com.weiCommity.Service
 * Created by uryuo on 17/5/10.
 */
@Service
public class UserWorkService {
    final UserWorkDao workDao;

    @Autowired
    public UserWorkService(UserWorkDao workDao) {
        this.workDao = workDao;
    }


    public UserTFWork getUserWorkByProjectWork(ProjectWork applyWodk) {
        return workDao.getUserWorkByProjectWork(applyWodk);
    }

    //将用户的UWid从数据库中查询出来
    public UserTFWork getUWWithUUidandWorkId(String creatUUuid, String thisWorkId) {
        UserTFWork work = new UserTFWork();
        //将查询需要的字段组装好
        work.setWorkId(thisWorkId);
        work.setUUuid(creatUUuid);
        return workDao.getUserWorkByWorkIdandUUid(work);
    }
}

