package com.weiCommity.Model;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

/**
 * PackageName com.weiCommity.Model
 * Created by uryuo on 17/5/13.
 */
@Component
public class ProjectWorkApplyMsg extends ProjectWork {

    private String CName;
    private String PTitle;
    private String PType;
    private DateTime PCreatTime;
    private String UUuid;
    private String UNackName;
    private String UHeadImg;
    private String CreatUUuid;

    public String getCreatUUuid() {
        return CreatUUuid;
    }

    public void setCreatUUuid(String creatUUuid) {
        CreatUUuid = creatUUuid;
    }

    public String getCName() {
        return CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }

    public String getPTitle() {
        return PTitle;
    }

    public void setPTitle(String PTitle) {
        this.PTitle = PTitle;
    }

    public String getPType() {
        return PType;
    }

    public void setPType(String PType) {
        this.PType = PType;
    }

    public DateTime getPCreatTime() {
        return PCreatTime;
    }

    public void setPCreatTime(DateTime PCreatTime) {
        this.PCreatTime = PCreatTime;
    }

    @Override
    public String getUUuid() {
        return UUuid;
    }

    @Override
    public void setUUuid(String UUuid) {
        this.UUuid = UUuid;
    }

    public String getUNackName() {
        return UNackName;
    }

    public void setUNackName(String UNackName) {
        this.UNackName = UNackName;
    }

    public String getUHeadImg() {
        return UHeadImg;
    }

    public void setUHeadImg(String UHeadImg) {
        this.UHeadImg = UHeadImg;
    }
}
