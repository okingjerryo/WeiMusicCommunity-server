package com.weiCommity.Model;

import com.weiCommity.Util.StaticVar;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

/**
 * PackageName com.weiCommity.Model
 * Created by uryuo on 17/5/12.
 */
@Component
public class ProjectInfoPersonalOriented extends ProjectInfo {
    private String thisUUuid;
    private DateTime UJoinTime;
    private String CName;
    private String WordId;
    private String WorkFC;
    private String WorkSC;
    private String UJoinTimeStr;
    private String StateName;
    private String PWId;
    private int PWIsComplete;
    private int thisWorkStateId;

    public int getPWIsComplete() {
        return PWIsComplete;
    }

    public void setPWIsComplete(int PWIsComplete) {
        this.PWIsComplete = PWIsComplete;
    }

    public int getThisWorkStateId() {
        return thisWorkStateId;
    }

    public void setThisWorkStateId(int thisWorkStateId) {
        this.thisWorkStateId = thisWorkStateId;
    }

    public String getPWId() {
        return PWId;
    }

    public void setPWId(String PWId) {
        this.PWId = PWId;
    }

    public String getWorkSC() {
        return WorkSC;
    }

    public void setWorkSC(String workSC) {
        WorkSC = workSC;
    }

    @Override
    public String getStateName() {
        return StateName;
    }

    @Override
    public void setStateName(String stateName) {
        StateName = stateName;
    }

    public String getUJoinTimeStr() {
        return UJoinTimeStr;
    }

    public void setUJoinTimeStr(String UJoinTimeStr) {
        this.UJoinTimeStr = UJoinTimeStr;
    }

    public void setUJoinTimeStr() {
        this.UJoinTimeStr = UJoinTime.toString(StaticVar.getDateFormat());
    }

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
        this.WordId = wordId;
    }

    public String getWorkFC() {
        return WorkFC;
    }

    public void setWorkFC(String workFC) {
        this.WorkFC = workFC;
    }


}
