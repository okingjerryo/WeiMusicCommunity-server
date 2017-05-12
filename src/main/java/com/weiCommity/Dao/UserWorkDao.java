package com.weiCommity.Dao;

import com.weiCommity.Model.UserTFWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * PackageName com.weiCommity.Dao
 * Created by uryuo on 17/5/10.
 */

@Repository
public class UserWorkDao extends BaseDao {
    final UserTFWork userWork;

    @Autowired
    public UserWorkDao(UserTFWork userWork) {
        this.userWork = userWork;
    }

    //
}
