package com.weiCommity.Dao;


import com.ctc.wstx.sw.EncodingXmlWriter;
import com.weiCommity.Model.UserExtend;
import com.weiCommity.Model.UserTFWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Min;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by uryuo on 17/4/19.
 */
@Repository
public class RegistDao extends BaseDao {
    @Autowired
    UserExtend userExtend;

    //常用工种的注册 只插入，使用UUuid作为主键检索对象
    public void insertWTByUUuid(UserTFWork thisWork) throws Exception{
        try{
        session = sqlSessionFactory.openSession();
        session.selectOne("org.test.Login.insertFreqTOWork",thisWork);
        }catch (Exception e){
            throw e;
        }finally {
            session.close();
        }
    }

    //对人的个人信息进行注册插入 注意对文件的写入是在Service 层实现的
    public void insertUserExtend(UserExtend extend) throws Exception{
        try {
            session = sqlSessionFactory.openSession();
            session.selectOne("org.test.Login.regist_insertUserExtend",extend);
        }catch (Exception e){
            throw e;
        }finally {
            session.close();
        }

    }

    //对个人扩展信息进行获取 使用uuid作为参数
    public UserExtend getUserExtendByUUuid(String thisUUid){
        try {
            session = sqlSessionFactory.openSession();
            userExtend = session.selectOne("",thisUUid);

        }catch (Exception e){
            session.close();
            throw e;
        }
        session.close();
        return userExtend;
    }
    //UserExtend 信息存入库
    public void editUserExtend(UserExtend userExtend){
        try{
            session= sqlSessionFactory.openSession();
            session.selectOne("",userExtend);

        }catch (Exception e){
            session.close();
            throw  e;
        }
        session.close();
    }

    //查询当前用户的工种，返回全部工种
    public List<UserTFWork> getAllTFWorkByUUid(String UUuid){
        List<UserTFWork> re;
        try{
            session= sqlSessionFactory.openSession();
            re = session.selectList("org.test.Login.sel_userTFWorkByUUuid",UUuid);
        }catch (Exception e){
            session.close();
            throw e;
        }
        session.close();

        return re;
    }

    //对指定工种id进行修改更新
    public void editTFWork(UserTFWork userTFWork){
        try {
            session = sqlSessionFactory.openSession();
            session.selectOne("update_ALineofTFWork",userTFWork);
        }catch (Exception e){
            e.printStackTrace();
            session.close();
            throw e;
        }
        session.close();
    }

    //对指定工种条目进行删除
    public void delTFWork(String UWid){
        try {
            session = sqlSessionFactory.openSession();
            session.selectOne("org.test.Login.delete_ALineofTFWork",UWid);
        }catch (Exception e){
            e.printStackTrace();
            session.close();
            throw e;
        }
        session.close();

    }

}
