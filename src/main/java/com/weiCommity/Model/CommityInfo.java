package com.weiCommity.Model;

import org.joda.time.DateTime;

/**
 * 社团信息实体，用于存常用需要的舒团信息
 * Created by uryuo on 17/5/1.
 */
public class CommityInfo {
    protected String Cid;
    protected String CName;

    protected DateTime CCreateTime;
    protected String Cintroduce;
    protected String CTag;
    protected String CHeadImg;
    protected int CIsNMin;
    protected String CNMDemand;
    protected int CMeMCount;
    protected String CNotice;
    protected String CImgObj;
    protected DateTime CNoteCTime;

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
