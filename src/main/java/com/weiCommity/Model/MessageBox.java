package com.weiCommity.Model;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

/***
 * PackageName com.weiCommity.Model
 * 软件信箱类
 * Created by uryuo on 17/5/1.
 */
@Component
public class MessageBox {
    private String MId;
    private DateTime MCreateTime;
    private String MSenderId;
    private String MTarId;
    private String MTitle; //特殊事件标题是固定的
    private String MThings;
    private int MIsReaded = 0;
    private int MCheck = -2;//未读 0 拒绝 -1 已读 1（忽略） 同意 2 普通信件 -2
    private String MSpcId; //相关联的事件id
    private int MSenderType = 1;
    private String SenderName;

    public String getSenderName() {
        return SenderName;
    }

    public void setSenderName(String senderName) {
        SenderName = senderName;
    }

    public int getMSenderType() {
        return MSenderType;
    }

    public void setMSenderType(int MSenderType) {
        this.MSenderType = MSenderType;
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

    public int getMCheck() {
        return MCheck;
    }

    public void setMCheck(int MCheck) {
        this.MCheck = MCheck;
    }

    public String getMSpcId() {
        return MSpcId;
    }

    public void setMSpcId(String MSpcId) {
        this.MSpcId = MSpcId;
    }
}
