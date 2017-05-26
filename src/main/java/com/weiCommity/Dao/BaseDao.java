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
        //判断SessionFactory是否初始化过，初始化过后就不重复初始化，保证系统的运行效率。
        if (!isInit) {
            try {
                //读入Mybatis配置文件
                inputStream = Resources.getResourceAsStream(resource);
                //通过配置文件构建sql数据工厂
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
            //从数据工厂生成一个会话记录
            session = sqlSessionFactory.openSession();
            //从MyBatisMapper查询出一条记录返回
            returnObj = session.selectOne(myBatisClass, inObj);
            //如果中间出错，及时把会话关闭然后将错误抛出
        } catch (Exception e) {
            session.close();
            throw e;
        }
        session.close();
        return returnObj;
    }

    public static List<?> selListFromSQL(String myBatisClass, Object inObj) {
        //这里的?是个站位符，其具体的类会在编译器得到，也就是在被继承的子类调用时得到
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
