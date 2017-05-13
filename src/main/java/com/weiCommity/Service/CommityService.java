package com.weiCommity.Service;

import com.weiCommity.Dao.CommityDao;
import com.weiCommity.Model.CommityInfo;
import com.weiCommity.Model.UserExtend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PackageName com.weiCommity.Service
 * Created by uryuo on 17/5/12.
 */
@Service
public class CommityService {
    final CommityDao commityDao;

    public List<UserExtend> getAllCommityMemList(CommityInfo info) {

        return commityDao.getCommityMemList(info);
    }

    @Autowired
    public CommityService(CommityDao commityDao) {
        this.commityDao = commityDao;
    }
}
