package com.weiCommity.Model;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by uryuo on 17/5/1.
 */
@Component
public class CommityActive {
    private String CAId;
    private String Cid;
    private LocalDate CAStartTime;
    private LocalDate CAEndTime;
    private String CATitle;
    private String CAThings;
    private String CACreatUserId;
    private int CAImportent = 0;
    private String CAImgPath;

    public String getCAId() {
        return CAId;
    }

    public void setCAId(String CAId) {
        this.CAId = CAId;
    }

    public String getCid() {
        return Cid;
    }

    public void setCid(String cid) {
        Cid = cid;
    }

    public LocalDate getCAStartTime() {
        return CAStartTime;
    }

    public void setCAStartTime(LocalDate CAStartTime) {
        this.CAStartTime = CAStartTime;
    }

    public LocalDate getCAEndTime() {
        return CAEndTime;
    }

    public void setCAEndTime(LocalDate CAEndTime) {
        this.CAEndTime = CAEndTime;
    }

    public String getCATitle() {
        return CATitle;
    }

    public void setCATitle(String CATitle) {
        this.CATitle = CATitle;
    }

    public String getCAThings() {
        return CAThings;
    }

    public void setCAThings(String CAThings) {
        this.CAThings = CAThings;
    }

    public String getCACreatUserId() {
        return CACreatUserId;
    }

    public void setCACreatUserId(String CACreatUserId) {
        this.CACreatUserId = CACreatUserId;
    }

    public int getCAImportent() {
        return CAImportent;
    }

    public void setCAImportent(int CAImportent) {
        this.CAImportent = CAImportent;
    }

    public String getCAImgPath() {
        return CAImgPath;
    }

    public void setCAImgPath(String CAImgPath) {
        this.CAImgPath = CAImgPath;
    }
}
