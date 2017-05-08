package com.weiCommity.Model;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

/**
 * Created by uryuo on 17/4/19.
 */
@Component
public class UserTFWork {
    private String UWid;
    private String UUuid;
    private String WorkId;
    private String WorkFC;
    private String WorkSC;
    private int isFreq;

    private DateTime delayTime;
    private int status;



    //getter and setter
    public String getUWid() {
        return UWid;
    }

    public void setUWid(String UWid) {
        this.UWid = UWid;
    }

    public String getUUuid() {
        return UUuid;
    }

    public void setUUuid(String UUuid) {
        this.UUuid = UUuid;
    }


    public String getWorkId() {
        return WorkId;
    }

    public void setWorkId(String workId) {
        WorkId = workId;
    }

    public String getWorkFC() {
        return WorkFC;
    }

    public void setWorkFC(String workFC) {
        WorkFC = workFC;
    }

    public String getWorkSC() {
        return WorkSC;
    }

    public void setWorkSC(String workSC) {
        WorkSC = workSC;
    }

    public int getIsFreq() {
        return isFreq;
    }

    public void setIsFreq(int isFreq) {
        this.isFreq = isFreq;
    }

    public DateTime getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(DateTime delayTime) {
        this.delayTime = new DateTime(delayTime);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
