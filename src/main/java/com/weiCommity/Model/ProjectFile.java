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
