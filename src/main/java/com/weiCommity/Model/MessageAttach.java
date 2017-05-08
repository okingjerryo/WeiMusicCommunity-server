package com.weiCommity.Model;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

/**
 * 附件实体 相比于数据库的实体 多了一个使用 ISO-8859-1编码的 ImgObj的类
 * Created by uryuo on 17/5/1.
 */

@Component
public class MessageAttach {
    private String MAId;
    private String MId;
    private DateTime MCreateTime;
    private String MAFileName;
    private String MAFilePath;
    private int MAIsReaseved;
    private String ImgObj;

    public String getImgObj() {
        return ImgObj;
    }

    public void setImgObj(String imgObj) {
        ImgObj = imgObj;
    }

    public String getMAId() {
        return MAId;
    }

    public void setMAId(String MAId) {
        this.MAId = MAId;
    }

    public String getMId() {
        return MId;
    }

    public void setMId(String MId) {
        this.MId = MId;
    }

    public DateTime getMCreateTime() {
        return MCreateTime;
    }

    public void setMCreateTime(DateTime MCreateTime) {
        this.MCreateTime = new DateTime(MCreateTime);
    }

    public String getMAFileName() {
        return MAFileName;
    }

    public void setMAFileName(String MAFileName) {
        this.MAFileName = MAFileName;
    }

    public String getMAFilePath() {
        return MAFilePath;
    }

    public void setMAFilePath(String MAFilePath) {
        this.MAFilePath = MAFilePath;
    }

    public int getMAIsReaseved() {
        return MAIsReaseved;
    }

    public void setMAIsReaseved(int MAIsReaseved) {
        this.MAIsReaseved = MAIsReaseved;
    }
}
