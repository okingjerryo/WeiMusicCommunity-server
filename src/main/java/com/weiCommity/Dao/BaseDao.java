package com.weiCommity.Dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 主要的连接Dao 里面用于打开数据库的链接
 * 使用Mybatis 作为持久化工具
 * Created by uryuo on 17/4/9.
 */
//用于连接
@Repository
public class BaseDao {
    //使用static 类型变为全局变量 保证 Connection 为 最大生存期
    private static String resource = "Config/mybities-config.xml";
    private static InputStream inputStream;
    static SqlSessionFactory sqlSessionFactory;
    private static boolean isInit = false;
    static SqlSession session;
    //初始init
    BaseDao() {
        if (!isInit) {
            try {
                inputStream = Resources.getResourceAsStream(resource);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
                System.out.println("getConnectionSuccful");
                isInit = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("already init");
        }
    }


    //查询
    static Object selOneFromSQL(String myBatisClass, Object inObj) {
        Object returnObj;
        try {
            session = sqlSessionFactory.openSession();
            returnObj = session.selectOne(myBatisClass, inObj);
        } catch (Exception e) {
            session.close();
            throw e;
        }
        session.close();
        return returnObj;
    }

    public static List<?> selListFromSQL(String myBatisClass, Object inObj) {
        List<?> returnObj;
        try {
            session = sqlSessionFactory.openSession();
            returnObj = session.selectList(myBatisClass, inObj);
        } catch (Exception e) {
            session.close();
            throw e;
        }
        session.close();
        return returnObj;
    }

    //插入 删除 修改
    public static void InEdDeOneIntoSql(String myBatisClass, Object inObj) {
        try {
            session = sqlSessionFactory.openSession();
            session.selectOne(myBatisClass, inObj);
        } catch (Exception e) {
            session.close();
            throw e;
        }
        session.close();
    }




}
