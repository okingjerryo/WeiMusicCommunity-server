package com.weiCommity.Model;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

/**
 * Created by uryuo on 17/5/1.
 */
@Component
public class CommityActive {
    private String CAId;
    private String Cid;

    private DateTime CAStartTime;

    private DateTime CAEndTime;
    private String CATitle;
    private String CAThings;
    private String CACreatUserId;
    private String CAImgPath;
    private String CAImgObj;

    public String getCAImgObj() {
        return CAImgObj;
    }

    public void setCAImgObj(String CAImgObj) {
        this.CAImgObj = CAImgObj;
    }

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

    public DateTime getCAStartTime() {
        return CAStartTime;
    }

    public void setCAStartTime(DateTime CAStartTime) {
        this.CAStartTime = new DateTime(CAStartTime);
    }

    public DateTime getCAEndTime() {
        return CAEndTime;
    }

    public void setCAEndTime(DateTime CAEndTime) {
        this.CAEndTime = new DateTime(CAEndTime);
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


    public String getCAImgPath() {
        return CAImgPath;
    }

    public void setCAImgPath(String CAImgPath) {
        this.CAImgPath = CAImgPath;
    }


}
