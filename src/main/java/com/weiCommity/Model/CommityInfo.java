package com.weiCommity.Model;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

/**
 * 社团信息实体，用于存常用需要的舒团信息
 * Created by uryuo on 17/5/1.
 */
@Component
public class CommityInfo {
    private String Cid;
    private String CName;

    private DateTime CCreateTime;
    private String Cintroduce;
    private String CTag;
    private String CHeadImg;
    private int CIsNMin;
    private String CNMDemand;
    private int CMeMCount;
    private String CNotice;
    private String CImgObj;
    private DateTime CNoteCTime;

    public DateTime getCCreateTime() {
        return CCreateTime;
    }

    public void setCCreateTime(DateTime CCreateTime) {
        this.CCreateTime = CCreateTime;
    }

    public DateTime getCNoteCTime() {
        return CNoteCTime;
    }

    public void setCNoteCTime(DateTime CNoteCTime) {
        this.CNoteCTime = CNoteCTime;
    }

    public String getCImgObj() {
        return CImgObj;
    }

    public void setCImgObj(String CImgObj) {
        this.CImgObj = CImgObj;
    }

    public String getCid() {
        return Cid;
    }

    public void setCid(String cid) {
        Cid = cid;
    }

    public String getCName() {
        return CName;
    }

    public void setCName(String CNAame) {
        this.CName = CNAame;
    }



    public String getCintroduce() {
        return Cintroduce;
    }

    public void setCintroduce(String cintroduce) {
        Cintroduce = cintroduce;
    }

    public String getCTag() {
        return CTag;
    }

    public void setCTag(String CTag) {
        this.CTag = CTag;
    }

    public String getCHeadImg() {
        return CHeadImg;
    }

    public void setCHeadImg(String CHeadImg) {
        this.CHeadImg = CHeadImg;
    }

    public int getCIsNMin() {
        return CIsNMin;
    }

    public void setCIsNMin(int CIsNMin) {
        this.CIsNMin = CIsNMin;
    }

    public String getCNMDemand() {
        return CNMDemand;
    }

    public void setCNMDemand(String CNMDemand) {
        this.CNMDemand = CNMDemand;
    }

    public int getCMeMCount() {
        return CMeMCount;
    }

    public void setCMeMCount(int CMeMCount) {
        this.CMeMCount = CMeMCount;
    }

    public String getCNotice() {
        return CNotice;
    }

    public void setCNotice(String CNotice) {
        this.CNotice = CNotice;
    }
}
