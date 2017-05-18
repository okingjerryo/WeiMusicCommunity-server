package com.weiCommity.Model;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

/**
 * PackageName com.weiCommity.Model
 * Created by uryuo on 17/5/10.
 */
@Component
public class ProjectWork {
    protected String PWId;
    protected String PId;
    protected String UUuid;
    protected String UWid;
    protected DateTime UJoinTime;
    protected String WorkSC;
    protected int UApply = 0;   //0 申请中 1.已同意 拒绝时会删除该条记录
    protected int PWIsComplete;
    protected String StateName;

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }

    public int getPWIsComplete() {
        return PWIsComplete;
    }

    public void setPWIsComplete(int PWIsComplete) {
        this.PWIsComplete = PWIsComplete;
    }

    public int getUApply() {
        return UApply;
    }

    public void setUApply(int UApply) {
        this.UApply = UApply;
    }

    public String getWorkSC() {
        return WorkSC;
    }

    public void setWorkSC(String workSC) {
        WorkSC = workSC;
    }

    public String getPWId() {
        return PWId;
    }

    public void setPWId(String PWId) {
        this.PWId = PWId;
    }

    public String getPId() {
        return PId;
    }

    public void setPId(String PId) {
        this.PId = PId;
    }

    public String getUUuid() {
        return UUuid;
    }

    public void setUUuid(String UUuid) {
        this.UUuid = UUuid;
    }

    public String getUWid() {
        return UWid;
    }

    public void setUWid(String UWid) {
        this.UWid = UWid;
    }

    public DateTime getUJoinTime() {
        return UJoinTime;
    }

    public void setUJoinTime(DateTime UJoinTime) {
        this.UJoinTime = UJoinTime;
    }
}
