package com.weiCommity.Model;

import org.springframework.stereotype.Component;

/**
 * PackageName com.weiCommity.Model
 * 好友类
 * 在查询时采用 UFUuid 与 USUuid 分别检索一遍来确认好友关系是否存在
 * Created by uryuo on 17/5/1.
 */
@Component
public class UserFriend {
    private String UFId;
    private String UFUuid;
    private String USUuid;
    private int UFStatus = 0;

    public String getUFId() {
        return UFId;
    }

    public void setUFId(String UFId) {
        this.UFId = UFId;
    }

    public String getUFUuid() {
        return UFUuid;
    }

    public void setUFUuid(String UFUuid) {
        this.UFUuid = UFUuid;
    }

    public String getUSUuid() {
        return USUuid;
    }

    public void setUSUuid(String USUuid) {
        this.USUuid = USUuid;
    }

    public int getUFStatus() {
        return UFStatus;
    }

    public void setUFStatus(int UFStatus) {
        this.UFStatus = UFStatus;
    }
}
