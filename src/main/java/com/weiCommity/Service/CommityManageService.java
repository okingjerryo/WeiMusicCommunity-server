package com.weiCommity.Service;

import com.weiCommity.Dao.CommityManageDao;
import com.weiCommity.Model.CommityInfo;
import com.weiCommity.Model.CommityMember;
import com.weiCommity.Util.StaticVar;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
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

}
