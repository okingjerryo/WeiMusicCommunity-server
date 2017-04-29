package com.weiCommity.Service;

import com.weiCommity.Dao.LoginDao;
import com.weiCommity.Model.Login;
import com.weiCommity.Util.MD5.MD5String;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by uryuo on 17/4/15.
 */
@Service
public class LoginService {
    @Autowired
    private LoginDao dao;

    public String LoginWithStyle(String user ,String password,String style) throws Exception {
        String re = "密码错误，请检查你的密码";
        String thisPwd = dao.getPwd(user,style);
        if (thisPwd == null)
                re= "用户不存在，请检查你的用户名";
        else {

            MD5String pwdWithMD = new MD5String(password);
            try {
                if (thisPwd.equals(pwdWithMD.getCalcString())) //注意要使用calc
                    re = dao.getUserUUID(user,style);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        return re;

    }


    public LoginDao getDao() {
        return dao;
    }

    public void setDao(LoginDao dao) {
        this.dao = dao;
    }

}
