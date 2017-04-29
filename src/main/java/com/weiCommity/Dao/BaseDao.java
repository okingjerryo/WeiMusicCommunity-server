package com.weiCommity.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by uryuo on 17/4/9.
 */
//用于连接
@Repository
public class BaseDao {
    //使用static 类型变为全局变量 保证 Connection 为 最大生存期
    private static String resource = "Config/mybities-config.xml";
    private static InputStream inputStream;
    protected static SqlSessionFactory sqlSessionFactory;
    private static boolean isInit = false;
    public SqlSession session ;
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


    //单元测试
    public static void main(){
        BaseDao conn  = new BaseDao();
        String re = conn.session.selectOne("org.test.Login.selectPerson","okingjerryo");
        System.out.println(re);

    }


}
