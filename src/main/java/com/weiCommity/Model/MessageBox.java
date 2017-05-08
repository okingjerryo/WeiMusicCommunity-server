package com.weiCommity.Model;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

/***
 * PackageName com.weiCommity.Model
 * 软件信箱类
 * 附加的附件类为 MessageAttach
 * Created by uryuo on 17/5/1.
 */
@Component
public class MessageBox {
    private String MId;
    @DateTimeFormat
    private DateTime MCreateTime;
    private String MSenderId;
    private String MTarId;
    private String MTitle;
    private String MThings;
    private int MIsReaded = 0;
    private int MImportant = 0;
    private int MCheck = -2;
    private String MSpcType;

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

    public String getMSenderId() {
        return MSenderId;
    }

    public void setMSenderId(String MSenderId) {
        this.MSenderId = MSenderId;
    }

    public String getMTarId() {
        return MTarId;
    }

    public void setMTarId(String MTarId) {
        this.MTarId = MTarId;
    }

    public String getMTitle() {
        return MTitle;
    }

    public void setMTitle(String MTitle) {
        this.MTitle = MTitle;
    }

    public String getMThings() {
        return MThings;
    }

    public void setMThings(String MThings) {
        this.MThings = MThings;
    }

    public int getMIsReaded() {
        return MIsReaded;
    }

    public void setMIsReaded(int MIsReaded) {
        this.MIsReaded = MIsReaded;
    }

    public int getMImportant() {
        return MImportant;
    }

    public void setMImportant(int MImportant) {
        this.MImportant = MImportant;
    }

    public int getMCheck() {
        return MCheck;
    }

    public void setMCheck(int MCheck) {
        this.MCheck = MCheck;
    }

    public String getMSpcType() {
        return MSpcType;
    }

    public void setMSpcType(String MSpcType) {
        this.MSpcType = MSpcType;
    }
}
