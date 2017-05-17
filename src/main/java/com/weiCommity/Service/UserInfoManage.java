package com.weiCommity.Service;

import com.weiCommity.Dao.LoginDao;
import com.weiCommity.Dao.RegistDao;
import com.weiCommity.Model.Login;
import com.weiCommity.Model.PersonalInfoPersonalOriented;
import com.weiCommity.Model.UserExtend;
import com.weiCommity.Model.UserTFWork;
import com.weiCommity.Util.HttpJson;
import com.weiCommity.Util.StaticVar;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.List;

/**
 * Created by uryuo on 17/4/25.
 */
@Service
public class UserInfoManage {
    final LoginDao loginDao;
    final RegistDao registDao;

    @Autowired
    public UserInfoManage(LoginDao loginDao, RegistDao registDao) {
        this.loginDao = loginDao;
        this.registDao = registDao;
    }

    public HttpJson getUserSercurityInfoByUUuid(String UUuid){
        HttpJson re = new HttpJson();
        Login UserSecurity = loginDao.getLoginWithUUuid(UUuid);
        re.setClassName("Model:Login");
        re.setClassObject(UserSecurity);
        return re;
    }

    public void editUserSercurity(Login thisEdit){
        loginDao.setLogin(thisEdit);
    }

    public HttpJson getUserExtendByUUuid(String UUUid){
        HttpJson re =new HttpJson();
        UserExtend userExtend = registDao.getUserExtendByUUuid(UUUid);

        re.setClassName("Model:UserExtend");
        re.setClassObject(userExtend);
        return re;
    }

    public String editUserExtend(PersonalInfoPersonalOriented userExtend, String lastImgPath, File newFileName) throws Exception {
        String tarImg = lastImgPath;
        //传的文件出现变化则更新文件信息
        if (!lastImgPath.equals(newFileName.getPath())) {
            File lastImg = ResourceUtils.getFile(StaticVar.getToFilePath() + lastImgPath);
            //FileUtils.forceDelete(lastImg);
            tarImg = "UserSpace" + "/" + userExtend.getUUuid() + "/" + newFileName.getName();

            File newImg = ResourceUtils.getFile(StaticVar.getToFilePath() + tarImg);
            FileUtils.writeByteArrayToFile(newImg, userExtend.getUImgObj().getBytes("ISO-8859-1"));
        }
        userExtend.setUHeadImg(tarImg);
        registDao.editUserExtend(userExtend);

        return tarImg;
    }

    public List<UserTFWork> getUserTFWorkByUUuid(String thisUUid){
        return registDao.getAllTFWorkByUUid(thisUUid);
    }

    public void editUserTFWorkByEditList(List<Object> thisQue) throws Exception {
        for (int i = 0; i < thisQue.size(); i++) {
            UserTFWork thisUE = (UserTFWork) thisQue.get(i);
            if (thisUE.getWorkFC().equals("增"))
                registDao.insertWTByUUuid(thisUE);
            else if (thisUE.getWorkFC().equals("改"))
                registDao.editTFWork(thisUE);
            else if (thisUE.getWorkFC().equals("删"))
                registDao.delTFWork(thisUE.getUWid());
        }
    }
}
