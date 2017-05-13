package com.weiCommity.Model;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

/**
 * Created by uryuo on 17/4/22.
 */
@Component
public class UserExtend {
    protected String UUuid;
    protected String UNackName;
    protected String USex;
    protected DateTime UBirthday;
    protected String UTag;
    protected String USign;
    protected String UHeadImg;

    public String getUUuid() {
        return UUuid;
    }

    public void setUUuid(String UUuid) {
        this.UUuid = UUuid;
    }

    public String getUNackName() {
        return UNackName;
    }

    public void setUNackName(String UNackName) {
        this.UNackName = UNackName;
    }

    public String getUSex() {
        return USex;
    }

    public void setUSex(String USex) {
        this.USex = USex;
    }

    public DateTime getUBirthday() {
        return UBirthday;
    }

    public void setUBirthday(DateTime UBirthday) {
        this.UBirthday = UBirthday;
    }

    public String getUTag() {
        return UTag;
    }

    public void setUTag(String UTag) {
        this.UTag = UTag;
    }

    public String getUSign() {
        return USign;
    }

    public void setUSign(String USign) {
        this.USign = USign;
    }

    public String getUHeadImg() {
        return UHeadImg;
    }

    public void setUHeadImg(String UHeadImg) {
        this.UHeadImg = UHeadImg;
    }

}
