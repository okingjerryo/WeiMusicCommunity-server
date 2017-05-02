package com.weiCommity.Dao;

import com.weiCommity.Model.CommityInfo;
import com.weiCommity.Model.CommityMember;
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
            info = session.selectOne("org.test.Login.sel_CommityInfoByCName", CName);
        } catch (Exception e) {
            session.close();
            throw e;
        }
        session.close();
        return info.getCid();
    }

    //通过实体 注册一个社团项目
    public void registInfo(CommityInfo info) {
        try {
            session = sqlSessionFactory.openSession();
            session.selectOne("org.test.Login.insert_CommityInfo", info);
        } catch (Exception e) {
            session.close();
            throw e;
        }
        session.close();
    }

    //注册一个用户到制定社团
    public void registUserIntoCommity(CommityMember member) {
        try {
            session = sqlSessionFactory.openSession();
            session.selectOne("org.test.Login.insert_CommityMember", member);
        } catch (Exception e) {
            session.close();
            throw e;
        }
        session.close();
    }

    //查询当前社团人员数量
    public int getCommityMeMCount(String CId) {
        int re;
        try {
            session = sqlSessionFactory.openSession();
            re = session.selectOne("org.test.Login.sel_CommityMemCount", CId);
        } catch (Exception e) {
            session.close();
            throw e;
        }
        session.close();
        return re;
    }

    //社团人数更新为新人数
    public void updateCommityMemCount(String CId, int thisCount) {
        CommityInfo info = new CommityInfo();
        info.setCid(CId);
        info.setCMeMCount(thisCount);
        try {
            session = sqlSessionFactory.openSession();
            session.selectOne("org.test.Login.update_CommmityMeMCount", info);
        } catch (Exception e) {
            session.close();
            throw e;
        }
        session.close();
    }

}
