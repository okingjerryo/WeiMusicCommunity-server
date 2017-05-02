package com.weiCommity.Model;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * 社团人员列表与权限实体（用的view中最大的字段数量作为内部的成员变量，实际中按需使用）
 * Created by uryuo on 17/5/1.
 */
@Component
public class CommityMember {
    private String CMid;
    private String Cid;
    private String UUuid;
    private LocalDate UJoinTime;
    private int Utype = 0;
    //存view 里面的Type的名字 给客户端方便
    private String UTypeName;
    private String UNackName;
    private String CName;

    public String getCName() {
        return CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }

    public String getUNackName() {
        return UNackName;
    }

    public void setUNackName(String UNackName) {
        this.UNackName = UNackName;
    }

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
