package com.weiCommity.Model;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by uryuo on 17/5/1.
 */
@Component
public class CommityMember {
    private String CMid;
    private String Cid;
    private String UUuid;
    private LocalDate UJoinTime;
    private int Utype = 0;

    public String getCMid() {
        return CMid;
    }

    public void setCMid(String CMid) {
        this.CMid = CMid;
    }

    public String getCid() {
        return Cid;
    }

    public void setCid(String cid) {
        Cid = cid;
    }

    public String getUUuid() {
        return UUuid;
    }

    public void setUUuid(String UUuid) {
        this.UUuid = UUuid;
    }

    public LocalDate getUJoinTime() {
        return UJoinTime;
    }

    public void setUJoinTime(LocalDate UJoinTime) {
        this.UJoinTime = UJoinTime;
    }

    public int getUtype() {
        return Utype;
    }

    public void setUtype(int utype) {
        Utype = utype;
    }
}
