package com.weiCommity.Model;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

/**
 * PackageName com.weiCommity.Model
 * Created by uryuo on 17/5/10.
 */
@Component
public class ProjectDynamic {
    private String PDId;
    private DateTime Time;
    private String Word;
    private String PWId;
    private String PFId;
    private String PDType;
    //pdwithNoF
    //在Time ，WorkSC UNackNamePDType了项目PTitle （的文件 （文件名） 并备注了 （备注））
    private String WorkSC;
    private String UNackName;
    private String PTitle;
    private String PFNotice;
    private String PFPath;

    public String getPFPath() {
        return PFPath;
    }

    public void setPFPath(String PFPath) {
        this.PFPath = PFPath;
    }

    public String getPFNotice() {
        return PFNotice;
    }

    public void setPFNotice(String PFNotice) {
        this.PFNotice = PFNotice;
    }

    public String getWorkSC() {
        return WorkSC;
    }

    public void setWorkSC(String workSC) {
        WorkSC = workSC;
    }

    public String getUNackName() {
        return UNackName;
    }

    public void setUNackName(String UNackName) {
        this.UNackName = UNackName;
    }

    public String getPTitle() {
        return PTitle;
    }

    public void setPTitle(String PTitle) {
        this.PTitle = PTitle;
    }

    public String getPDId() {
        return PDId;
    }

    public void setPDId(String PDId) {
        this.PDId = PDId;
    }

    public DateTime getTime() {
        return Time;
    }

    public void setTime(DateTime time) {
        Time = time;
    }

    public String getWord() {
        return Word;
    }

    public void setWord(String word) {
        Word = word;
    }

    public String getPWId() {
        return PWId;
    }

    public void setPWId(String PWId) {
        this.PWId = PWId;
    }

    public String getPFId() {
        return PFId;
    }

    public void setPFId(String PFId) {
        this.PFId = PFId;
    }

    public String getPDType() {
        return PDType;
    }

    public void setPDType(String PDType) {
        this.PDType = PDType;
    }
}
