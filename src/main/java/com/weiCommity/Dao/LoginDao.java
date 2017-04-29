package com.weiCommity.Dao;

import com.weiCommity.Model.Login;
import com.weiCommity.Util.MD5.MD5String;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;
import sun.security.rsa.RSASignature;

import java.sql.SQLException;

/**
 * Created by uryuo on 17/4/11.
 */
@Repository
public class LoginDao extends BaseDao {
    @Autowired
    private Login login;
    // 注意 style是 你使用的登录方式 A:使用账户名 M:使用邮件 T:使用手机号
    private Login getLogin(String thisUserName , String style) {
        session = sqlSessionFactory.openSession();
        if (style.equals("A")) {
            login = session.selectOne("org.test.Login.selLoginWithAccName", thisUserName);
        }else if (style.equals("M")){
            login = session.selectOne("org.test.Login.selLoginWithMailName", thisUserName);
        }else if (style.equals("T")){
            login = session.selectOne("org.test.Login.selLoginWithTelName", thisUserName);
        }

        session.close();
        return login;
    }

    public Login getLoginWithUUuid(String UUuid){
        try{
            session = sqlSessionFactory.openSession();
            login = session.selectOne("org.test.Login.selLoginWithUUuid",UUuid);
        }catch (Exception e) {
           throw e;
        }finally {
            session.close();
            return login;
        }
    }
    public String getPwd(String username ,String style){
            getLogin(username,style);
        try {
            return login.getUPwd();
        }catch (Exception e){
            return null;
        }
    }

    public String getUserUUID(String username,String style){

            getLogin(username,style);
        try {
            return login.getUUuid();
        }catch (Exception e){
            return null;
        }
    }

    public boolean registUser(Login thisUser){
        boolean re = true;

        session = sqlSessionFactory.openSession();
        try {
            session.selectOne("org.test.Login.registeUser", thisUser);
        }catch (Exception e){
            re = false;
        }finally {


            session.close();

            return re;
        }
    }

    public void editUser(Login thisUser){
        try{
            session = sqlSessionFactory.openSession();
            session.selectOne("org.test.Login.update_LoginAll",thisUser);
            session.close();
        }catch (Exception e){
            session.close();
            throw e;
        }

    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

}
