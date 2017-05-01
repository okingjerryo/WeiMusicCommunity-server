package com.weiCommity.Dao;

import com.weiCommity.Model.CommityInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * PackageName com.weiCommity.Dao
 * CommityInfo
 * 实体对数据库的访问操作
 * Created by uryuo on 17/5/1.
 */
@Repository
public class CommityManageDao extends BaseDao {

    private CommityInfo info;

    @Autowired
    public CommityManageDao(CommityInfo info) {
        this.info = info;
    }


    //通过社团名字获取社团的Cid
    public String getInfoByCName(String CName) {
        try {
            session = sqlSessionFactory.openSession();
            info = session.selectOne("sel_CommityInfoByCName", CName);
        } catch (Exception e) {
            session.close();
            throw e;
        }
        session.close();
        return info.getCid();
    }


}
