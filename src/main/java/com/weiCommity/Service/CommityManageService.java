package com.weiCommity.Service;

import com.weiCommity.Dao.CommityManageDao;
import com.weiCommity.Model.CommityInfo;
import com.weiCommity.Model.CommityMember;
import com.weiCommity.Util.StaticVar;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * PackageName com.weiCommity.Service
 * 这里面具有全部的社团权限管理服务
 * Created by uryuo on 17/5/1.
 */
@Service
public class CommityManageService {
    private final CommityManageDao commityManageDao;

    @Autowired
    public CommityManageService(CommityManageDao commityManageDao) {
        this.commityManageDao = commityManageDao;
    }

    //通过社团信息实体(名字)查询是否存在当前社团在数据库中 存在的话返回 Cid
    public String isCommmityIn(CommityInfo thisinfo) {
        String re;
        re = commityManageDao.getInfoByCName(thisinfo.getCNAame());
        return re;
    }

    //注册 社团的常用信息 返回注册成功的Cid;
    public String registCommon(CommityInfo info) throws Exception {
        String re;

        //注册一个新的Cid
        info.setCid(UUID.randomUUID().toString());
        //图片进存储空间
        String tarBackPath = "CommitySpace/" + info.getCid() + "/" + new File(info.getCHeadImg()).getName();
        File tarPath = new File(StaticVar.getToFilePath() + tarBackPath);
        FileUtils.writeByteArrayToFile(tarPath, info.getCImgObj().getBytes(StaticVar.getDecodeFileSet()));
        //设置图片位置
        info.setCHeadImg(tarBackPath);
        //入库
        commityManageDao.registInfo(info);
        re = tarBackPath;
        return re;
    }

    //注册 将将制定用户根据制定权限注册到社团
    public void registUserIntoCommity(CommityMember member) {
        //注册用户
        commityManageDao.registUserIntoCommity(member);
        //查询当前社团内用户数
        int MemCount = commityManageDao.getCommityMeMCount(member.getCid());
        //用户数量增加1
        commityManageDao.updateCommityMemCount(member.getCid(), MemCount + 1);
    }

    //查看 用户查看当前社团的成员以及权限
    public List<CommityMember> getAllUserInCommity(String Cid) {
        return commityManageDao.getAllCommityMember(Cid);
    }

    public CommityMember getOneUserInCommity(String Cid, String UUuid) {
        return commityManageDao.getOneCommityMember(Cid, UUuid);
    }

    //更新 制定社团成员的权限 并进行权限检查
    public void setUserTypeIdentityByMUUuid(String UUuid, CommityMember editMem) throws JSONException {
        try {

            if (editMem.getUtype() < 3)
                throw new IllegalAccessException("您不具有修改人员权限的权限");
            //获取到当前用户的修改权限
            CommityMember thisEditMemCM = commityManageDao.getOneCommityMember(editMem.getCid(), UUuid);
            //获取要修改用户的原权限
            CommityMember tarEditMemCM = commityManageDao.getOneCommityMember(editMem.getCid(), editMem.getUUuid());
            //要修改的人员的自身权限要比自己小
            if (!(thisEditMemCM.getUtype() > tarEditMemCM.getUtype()))
                throw new IllegalAccessError("您自身的权限不足，无法修改目标权限");
            //要修改的人员修改后权限要比自己小
            if (!(thisEditMemCM.getUtype() > editMem.getUtype()))
                throw new IllegalAccessException("您将修改的权限高于自身能修改的权限");

            //通过dao修改权限
            commityManageDao.setCommmityMemberType(editMem);
            //不合法的修改权限抛出异常
        } catch (IllegalAccessException e) {
            throw new JSONException(e.getMessage());
        }

    }

    //删除 制定UUid 的成员
    public void delUserInCommity(CommityMember delMem) {
        commityManageDao.delCommityUser(delMem);
    }





}
