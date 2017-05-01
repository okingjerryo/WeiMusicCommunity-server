package com.weiCommity.Service;

import com.weiCommity.Dao.LoginDao;
import com.weiCommity.Dao.RegistDao;
import com.weiCommity.Model.Login;
import com.weiCommity.Model.UserExtend;
import com.weiCommity.Model.UserTFWork;
import com.weiCommity.Util.MD5.MD5String;
import com.weiCommity.Util.StaticVar;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * Created by uryuo on 17/4/17.
 */
@Service
public class RegistService {
    private final LoginDao loginDao;
    private final RegistDao registDao;

    @Autowired
    public RegistService(LoginDao loginDao, RegistDao registDao) {
        this.loginDao = loginDao;
        this.registDao = registDao;
    }

    //注册账户名
    public String registUserWithType(Login thisUser ,String style) throws Exception {
        //生成uuid
        UUID uuid = UUID.randomUUID();
        //密码加密
        MD5String md5 = new MD5String(thisUser.getUPwd());

        thisUser.setUPwd(md5.getCalcString());
        thisUser.setUUuid(uuid.toString());

        //检查重复用户

        String testUserRe;
        testUserRe = loginDao.getUserUUID(thisUser.getUAName(),style);
        if (testUserRe!=null)
            return "-1";//用户名已存在

        //style重填装
        if (style.equals("M")){
            thisUser.setUMail(thisUser.getUAName());
            thisUser.setUAName(null);
        }else if (style.equals("T")){
            thisUser.setUTel(thisUser.getUAName());
            thisUser.setUAName(null);
        }
        boolean reFlag =  loginDao.registUser(thisUser);
        if (!reFlag)
            return "-2"; //注册出现了问题
        return thisUser.getUUuid();
    }

    //用相关类注册常用工种
    public void registFreqTOWByWorkId(String UUuid,List<Object> insertID) throws Exception {

        for (Object thisWId : insertID) {
            UserTFWork thisTF = new UserTFWork();
            thisTF.setIsFreq(1);
            thisTF.setUUuid(UUuid);
            thisTF.setWorkId((String) thisWId);
            thisTF.setUWid(UUID.randomUUID().toString());
            registDao.insertWTByUUuid(thisTF);
        }
    }

    //注册扩展信息，带着头像(用固定编码格式编码)
    public String registExtandInfo(UserExtend userExtend,String imgStr) throws Exception{


        //对头像的存储和处理 路径是UserSpace/UserId/
        String imgPath;
        File tarPath;
        //检测是否传了空值
        if (userExtend.getUHeadImg().isEmpty()){
            imgPath = "CommonSpace/default_personal_image.png";

        }
        else{
            File oraginalPath = new File(userExtend.getUHeadImg());
            imgPath =  "UserSpace/"+userExtend.getUUuid() + "/" + oraginalPath.getName();
            tarPath = new File(StaticVar.getToFilePath() + imgPath);  // 存的时候调用StaticVar 到FilePath路径并且最后带/
            FileUtils.writeByteArrayToFile(tarPath,imgStr.getBytes("ISO-8859-1"));
        }

        userExtend.setUHeadImg(imgPath);
        //调用Dao层存储
        registDao.insertUserExtend(userExtend);
        return imgPath;
    }



}
