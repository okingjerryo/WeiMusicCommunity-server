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
    private DateTime PWFileTime;
    private String PFId;
    private String PRTitle;
    private String PRThing;
    private int PRIsDeal;
    private DateTime PRDealTime;
    private String RPWId;

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

    public DateTime getPWFileTime() {
        return PWFileTime;
    }

    public void setPWFileTime(DateTime PWFileTime) {
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
