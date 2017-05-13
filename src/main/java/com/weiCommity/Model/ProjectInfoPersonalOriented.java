package com.weiCommity.Model;

import org.joda.time.DateTime;

/**
 * PackageName com.weiCommity.Model
 * Created by uryuo on 17/5/12.
 */
public class ProjectInfoPersonalOriented extends ProjectInfo {
    private String thisUUuid;
    private DateTime UJoinTime;
    private String CName;
    private String WordId;
    private String WorkFC;
    private String WorkSC;

    public String getThisUUuid() {
        return thisUUuid;
    }

    public void setThisUUuid(String thisUUuid) {
        this.thisUUuid = thisUUuid;
    }

    public DateTime getUJoinTime() {
        return UJoinTime;
    }

    public void setUJoinTime(DateTime UJoinTime) {
        this.UJoinTime = UJoinTime;
    }

    public String getCName() {
        return CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }

    public String getWordId() {
        return WordId;
    }

    public void setWordId(String wordId) {
        WordId = wordId;
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
}
