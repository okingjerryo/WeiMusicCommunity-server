package com.weiCommity.Dao;

import com.weiCommity.Model.CommityActive;
import com.weiCommity.Model.CommityInfo;
import com.weiCommity.Model.CommityMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PackageName com.weiCommity.Dao
 * CommityInfo
 * 实体对数据库的访问操作
 * Created by uryuo on 17/5/1.
 */
@Repository
public class CommityManageDao extends BaseDao {
    @Autowired
    private CommityInfo info;
    private final String thisMapperNamespace = "org.test.Login.";


    //通过社团名字获取社团的Cid
    public String getCidByCName(String CName) {
        try {
            session = sqlSessionFactory.openSession();
            info = session.selectOne("org.test.Login.sel_CommityInfoByCName", CName);
        } catch (Exception e) {
            session.close();
            throw e;
        }
        session.close();
        if (info != null)
            return info.getCid();

        return null;
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

    //通过社团id获取全部的用户
    public List<CommityMember> getAllCommityMember(String Cid) {
        List<CommityMember> re;
        try {
            session = sqlSessionFactory.openSession();
            re = session.selectList("org.test.Login.sel_CommityUserByCId", Cid);
        } catch (Exception e) {
            session.close();
            throw e;
        }
        session.close();
        return re;
    }

    //获得当前社团指定UUid 的用户
    public CommityMember getOneCommityMember(String Cid, String UUuid) {
        CommityMember thisMem = new CommityMember();
        thisMem.setUUuid(UUuid);
        thisMem.setCid(Cid);
        CommityMember re;
        try {
            session = sqlSessionFactory.openSession();
            re = session.selectOne("org.test.Login.sel_CommityOneUserByCM", thisMem);
        } catch (Exception e) {
            session.close();
            throw e;
        }
        session.close();
        return re;
    }

    //修改指定用户的权限
    public void setCommmityMemberType(CommityMember member) {
        try {
            session = sqlSessionFactory.openSession();
            session.selectOne("org.test.Login.update_CommityMemType", member);
        } catch (Exception e) {
            session.close();
            throw e;
        }
        session.close();
    }

    //删除制定用户
    public void delCommityUser(CommityMember member) {
        try {
            session = sqlSessionFactory.openSession();
            session.selectOne("org.test.Login.del_CommityMem", member);
        } catch (Exception e) {
            session.close();
            throw e;
        }
        session.close();
    }

    //通过Cid 返回当前社团信息
    public CommityInfo getCommityInfoByCid(String Cid) {
        return (CommityInfo) BaseDao.selOneFromSQL("org.test.Login.sel_CommityInfoByCid", Cid);
    }

    //通过CName 返回当前社团信息
    public CommityInfo getCommmityInfoByCName(String CName) {
        return (CommityInfo) BaseDao.selOneFromSQL("org.test.Login.sel_CommityInfoByCName", CName);
    }

    //更新社团信息 以及通知
    public void editCommityInfo(CommityInfo info) {
        BaseDao.InEdDeOneIntoSql(thisMapperNamespace + "update_CommityInfo", info);
    }
    //发布通知
    public void publishNotice(CommityInfo info) {
        BaseDao.InEdDeOneIntoSql(thisMapperNamespace + "update_CommityNotice", info);
    }

    //发布活动
    public void publishActive(CommityActive active) {
        BaseDao.InEdDeOneIntoSql(thisMapperNamespace + "insert_CommityActive", active);
    }

    public CommityMember getOneUserInCommityByCMid(String CMId) {
        return (CommityMember) BaseDao.selOneFromSQL(thisMapperNamespace + "sel_CommityMemByCMId", CMId);
    }
}
