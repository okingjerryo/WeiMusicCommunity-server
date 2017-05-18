package com.weiCommity.Model;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

/**
 * PackageName com.weiCommity.Model
 * Created by uryuo on 17/5/10.
 */
@Component
public class ProjectFile {
    private String PFId;
    private String PFPath;
    private String PWId;
    private DateTime PFCreateTime;
    private String PFNotice;
    private String PFName;
    private String WorkId;
    private boolean isARapir;
    private String PId;
    private int PWIsComplete;
    private String WorkSC;
    private int PFIsUseComplete;

    public int getPFIsUseComplete() {
        return PFIsUseComplete;
    }

    public void setPFIsUseComplete(int PFIsUseComplete) {
        this.PFIsUseComplete = PFIsUseComplete;
    }

    public String getWorkSC() {
        return WorkSC;
    }

    public void setWorkSC(String workSC) {
        WorkSC = workSC;
    }

    public String getPId() {
        return PId;
    }

    public void setPId(String PId) {
        this.PId = PId;
    }

    public int getPWIsComplete() {
        return PWIsComplete;
    }

    public void setPWIsComplete(int PWIsComplete) {
        this.PWIsComplete = PWIsComplete;
    }

    public String getWorkId() {
        return WorkId;
    }

    public void setWorkId(String workId) {
        WorkId = workId;
    }

    public String getPFName() {
        return PFName;
    }

    public void setPFName(String PFName) {
        this.PFName = PFName;
    }

    public boolean isARapir() {
        return isARapir;
    }

    public void setARapir(boolean ARapir) {
        isARapir = ARapir;
    }

    public String getPFId() {
        return PFId;
    }

    public void setPFId(String PFId) {
        this.PFId = PFId;
    }

    public String getPFPath() {
        return PFPath;
    }

    public void setPFPath(String PFPath) {
        this.PFPath = PFPath;
    }

    public String getPWId() {
        return PWId;
    }

    public void setPWId(String PWId) {
        this.PWId = PWId;
    }

    public DateTime getPFCreateTime() {
        return PFCreateTime;
    }

    public void setPFCreateTime(DateTime PFCreateTime) {
        this.PFCreateTime = PFCreateTime;
    }

    public String getPFNotice() {
        return PFNotice;
    }

    public void setPFNotice(String PFNotice) {
        this.PFNotice = PFNotice;
    }
}
