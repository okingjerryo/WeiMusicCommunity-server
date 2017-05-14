package com.weiCommity.Model;

/**
 * PackageName com.weiCommity.Model
 * Created by uryuo on 17/5/14.
 */
public class CommityInfoPersonalOriented extends CommityInfo {
    private String CMid;
    private String UUuid;
    private String Utype;
    private String UJoinTime;

    public String getCMid() {
        return CMid;
    }

    public void setCMid(String CMid) {
        this.CMid = CMid;
    }

    public String getUUuid() {
        return UUuid;
    }

    public void setUUuid(String UUuid) {
        this.UUuid = UUuid;
    }

    public String getUtype() {
        return Utype;
    }

    public void setUtype(String utype) {
        Utype = utype;
    }

    public String getUJoinTime() {
        return UJoinTime;
    }

    public void setUJoinTime(String UJoinTime) {
        this.UJoinTime = UJoinTime;
    }
}
