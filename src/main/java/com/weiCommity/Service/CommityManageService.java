package com.weiCommity.Service;

import com.weiCommity.Dao.CommityManageDao;
import com.weiCommity.Model.CommityActive;
import com.weiCommity.Model.CommityInfo;
import com.weiCommity.Model.CommityMember;
import com.weiCommity.Util.StaticVar;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * PackageName com.weiCommity.Service
 * 这里面具有全部的社团权限管理服务
 * Created by uryuo on 17/5/1.
 */
@Service
public class CommityManageService {
    @Autowired
    private CommityManageDao commityManageDao;



    //通过社团信息实体(名字)查询是否存在当前社团在数据库中 存在的话返回 Cid
    public String isCommmityIn(CommityInfo thisinfo) {
        String re = "";
        re = commityManageDao.getCidByCName(thisinfo.getCName());
        return re;
    }

    //注册 社团的常用信息 返回注册成功的Cid;
    public String registCommon(CommityInfo info) throws Exception {
        String re;

        //注册一个新的Cid
        info.setCid(UUID.randomUUID().toString());
        //注册一个当前时间
        info.setCCreateTime(new DateTime(DateTime.now()));
        //图片进存储空间
        String tarBackPath = "CommitySpace/" + info.getCid() + "/" + new File(info.getCHeadImg()).getName();
        File tarPath = new File(StaticVar.getToFilePath() + tarBackPath);
        FileUtils.writeByteArrayToFile(tarPath, info.getCImgObj().getBytes(StaticVar.getDecodeFileSet()));
        //设置图片位置
        info.setCHeadImg(tarBackPath);
        //入库
        commityManageDao.registInfo(info);
        re = info.getCid();
        return re;
    }

    //注册 将将制定用户根据制定权限注册到社团
    public void registUserIntoCommity(CommityMember member) {
        //注册用户
        DateTime time = new DateTime(DateTime.now());
        member.setUJoinTime(new DateTime(DateTime.now()));
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


            //通过dao修改权限
            commityManageDao.setCommmityMemberType(editMem);

    }

    //删除 制定UUid 的成员
    public void delUserInCommity(CommityMember delMem) {
        commityManageDao.delCommityUser(delMem);
        //用户数-1
        int MemCount = commityManageDao.getCommityMeMCount(delMem.getCid());
        //用户数量增加1
        commityManageDao.updateCommityMemCount(delMem.getCid(), MemCount - 1);
    }

    //======= 社团信息的查看 注意这里面获取的信息全部是单一的，这是面向社团 不是面向用户的服务
    //通过Cid 获取相关社团信息
    //要从磁盘获取图像。。。。
    public CommityInfo getCommityInfoByCid(String Cid) throws IOException {
        CommityInfo re = commityManageDao.getCommityInfoByCid(Cid);
        //把社团的头像文件获取下来
        File imgPath = new File(StaticVar.getToFilePath() + re.getCHeadImg());
        String imgEncode = FileUtils.readFileToString(imgPath, StaticVar.getDecodeFileSet());
        re.setCImgObj(imgEncode);
        return re;
    }

    //通过CName 获取相关社团信息
    public CommityInfo getCommityInfoByCName(String CName) throws IOException {

        CommityInfo re = commityManageDao.getCommmityInfoByCName(CName);
        File imgPath = new File(StaticVar.getToFilePath() + re.getCHeadImg());
        String imgEncode = FileUtils.readFileToString(imgPath, StaticVar.getDecodeFileSet());
        re.setCImgObj(imgEncode);
        return re;
    }


    //发布公告
    public void publishCommityNotices(CommityInfo info) {
        commityManageDao.publishNotice(info);
    }

    //修改社团相关信息 以及公告
    public void editCommityInfo(CommityInfo info, String lastPath) throws IOException {
        //检查图像有没有变化
        if (!(info.getCHeadImg().equals(lastPath))) {
            //删除之前的图
            FileUtils.forceDelete(new File(StaticVar.getToFilePath() + lastPath));
            //存新图
            String fileName = new File(info.getCHeadImg()).getName();
            String filePathStr = "CommitySpace/" + info.getCid() + "/" + fileName;

            File tarNewFile = new File(StaticVar.getToFilePath() + filePathStr);
            FileUtils.writeByteArrayToFile(tarNewFile, info.getCImgObj().getBytes(StaticVar.getDecodeFileSet()));
            info.setCHeadImg(filePathStr);
        }
        commityManageDao.editCommityInfo(info);

    }

    //发布活动
    public void publishCommityActivity(CommityActive active) throws IOException {
        //首先把相关图片存档
        String fileName = new File(active.getCAImgPath()).getName();
        String filePathStr = "CommitySpace/" + active.getCid() + "/active/" + fileName;

        if (active.getCAEndTime() == null)
            throw new JSONException("活动结束时间未设定");
        if (active.getCAImgPath() != null) {
            File tarSPath = new File(StaticVar.getToFilePath() + filePathStr);
            FileUtils.writeByteArrayToFile(tarSPath, active.getCAImgObj().getBytes(StaticVar.getDecodeFileSet()));
            active.setCAImgPath(filePathStr);
        }

        active.setCAId(UUID.randomUUID().toString());

        if (active.getCAStartTime() == null)
            active.setCAStartTime(new DateTime(DateTime.now()));

        commityManageDao.publishActive(active);
    }


    public CommityMember getOneUserInCommity(String thisCMid) {
        return commityManageDao.getOneUserInCommityByCMid(thisCMid);
    }

    public void addPeopleCount(String cid) {
        int MemCount = commityManageDao.getCommityMeMCount(cid);
        //用户数量增加1
        commityManageDao.updateCommityMemCount(cid, MemCount + 1);
    }
}
