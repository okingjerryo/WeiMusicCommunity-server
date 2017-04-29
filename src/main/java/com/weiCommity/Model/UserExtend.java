package com.weiCommity.Model;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by uryuo on 17/4/22.
 */
@Component
public class UserExtend {
    private String UUuid;
    private String UNackName;
    private String USex;
    private LocalDate UBirthday;
    private String UTag;
    private String USign;
    private String UHeadImg;

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

    public LocalDate getUBirthday() {
        return UBirthday;
    }

    public void setUBirthday(String UBirthday) {
        String [] re = UBirthday.split("/");
        int []intDate = new int[3];
        for (int i=0;i<re.length;i++){
            intDate[i] = Integer.parseInt(re[i]);
        }

        LocalDate thisBirthday = LocalDate.of(intDate[0],intDate[1],intDate[2]);
        this.UBirthday = thisBirthday;
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
