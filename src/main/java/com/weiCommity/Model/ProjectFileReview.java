package com.weiCommity.Model;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

/**
 * PackageName com.weiCommity.Model
 * Created by uryuo on 17/5/16.
 */
@Component
public class ProjectFileReview {
    private String PRId;
    private String PWFileTime;
    private String PFId;
    private String PRTitle;
    private String PRThing;
    private int PRIsDeal;
    private DateTime PRDealTime;
    private String RPWId;
    private int RPIsRepair;

    private String WorkSC;
    private String FIlePWid;
    private String thisUserPWId;

    public String getWorkSC() {
        return WorkSC;
    }

    public void setWorkSC(String workSC) {
        WorkSC = workSC;
    }

    public String getFIlePWid() {
        return FIlePWid;
    }

    public void setFIlePWid(String FIlePWid) {
        this.FIlePWid = FIlePWid;
    }

    public String getThisUserPWId() {
        return thisUserPWId;
    }

    public void setThisUserPWId(String thisUserPWId) {
        this.thisUserPWId = thisUserPWId;
    }

    public int getRPIsRepair() {
        return RPIsRepair;
    }

    public void setRPIsRepair(int RPIsRepair) {
        this.RPIsRepair = RPIsRepair;
    }

    private String UNackName;

    public String getUNackName() {
        return UNackName;
    }

    public void setUNackName(String UNackName) {
        this.UNackName = UNackName;
    }

    public String getPRId() {
        return PRId;
    }

    public void setPRId(String PRId) {
        this.PRId = PRId;
    }

    public String getPWFileTime() {
        return PWFileTime;
    }

    public void setPWFileTime(String PWFileTime) {
        this.PWFileTime = PWFileTime;
    }

    public String getPFId() {
        return PFId;
    }

    public void setPFId(String PFId) {
        this.PFId = PFId;
    }

    public String getPRTitle() {
        return PRTitle;
    }

    public void setPRTitle(String PRTitle) {
        this.PRTitle = PRTitle;
    }

    public String getPRThing() {
        return PRThing;
    }

    public void setPRThing(String PRThing) {
        this.PRThing = PRThing;
    }

    public int getPRIsDeal() {
        return PRIsDeal;
    }

    public void setPRIsDeal(int PRIsDeal) {
        this.PRIsDeal = PRIsDeal;
    }

    public DateTime getPRDealTime() {
        return PRDealTime;
    }

    public void setPRDealTime(DateTime PRDealTime) {
        this.PRDealTime = PRDealTime;
    }

    public String getRPWId() {
        return RPWId;
    }

    public void setRPWId(String RPWId) {
        this.RPWId = RPWId;
    }
}
