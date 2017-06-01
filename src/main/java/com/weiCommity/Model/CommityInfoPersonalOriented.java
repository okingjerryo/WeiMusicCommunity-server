package com.weiCommity.Model;

import org.springframework.stereotype.Component;

/**
 * PackageName com.weiCommity.Model
 * Created by uryuo on 17/5/14.
 */
@Component
public class CommityInfoPersonalOriented extends CommityInfo {
    private String CMid;
    private String UUuid;
    private int Utype;
    private String UJoinTime;
    private String UTypeName;

    public String getUTypeName() {
        return UTypeName;
    }

    public void setUTypeName(String UTypeName) {
        this.UTypeName = UTypeName;
    }

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

    public int getUtype() {
        return Utype;
    }

    public void setUtype(int utype) {
        Utype = utype;
    }

    public String getUJoinTime() {
        return UJoinTime;
    }

    public void setUJoinTime(String UJoinTime) {
        this.UJoinTime = UJoinTime;
    }
}
