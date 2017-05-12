package com.weiCommity.Model;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

/**
 * PackageName com.weiCommity.Model
 * Created by uryuo on 17/5/10.
 */
@Component
public class ProjectWork {
    private String PWId;
    private String PId;
    private String UUuid;
    private String UWid;
    private DateTime UJoinTime;

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
