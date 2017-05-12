package com.weiCommity.Model;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

/**
 * PackageName com.weiCommity.Model
 * 项目详情类
 * Created by uryuo on 17/5/10.
 */
@Component
public class ProjectInfo {
    private String PId;
    private String CId;
    private String CreatUUuid;
    private DateTime PEndTime;
    private String PTitle;
    private String PIntroduce;
    private DateTime PCreatTime;
    private int PState;
    private String PType; //中文记录

    private String UNackName;
    private String StateName;
    private Integer startState = 0;
    private Integer endState = 6;

    public Integer getStartState() {
        return startState;
    }

    public void setStartState(Integer startState) {
        this.startState = startState;
    }

    public Integer getEndState() {
        return endState;
    }

    public void setEndState(Integer endState) {
        this.endState = endState;
    }

    public String getUNackName() {
        return UNackName;
    }

    public void setUNackName(String UNackName) {
        this.UNackName = UNackName;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }

    public String getPId() {
        return PId;
    }

    public void setPId(String PId) {
        this.PId = PId;
    }

    public String getCId() {
        return CId;
    }

    public void setCId(String CId) {
        this.CId = CId;
    }

    public String getCreatUUuid() {
        return CreatUUuid;
    }

    public void setCreatUUuid(String creatUUuid) {
        CreatUUuid = creatUUuid;
    }

    public DateTime getPEndTime() {
        return PEndTime;
    }

    public void setPEndTime(DateTime PEndTime) {
        this.PEndTime = PEndTime;
    }

    public String getPTitle() {
        return PTitle;
    }

    public void setPTitle(String PTitle) {
        this.PTitle = PTitle;
    }

    public String getPIntroduce() {
        return PIntroduce;
    }

    public void setPIntroduce(String PIntroduce) {
        this.PIntroduce = PIntroduce;
    }

    public DateTime getPCreatTime() {
        return PCreatTime;
    }

    public void setPCreatTime(DateTime PCreatTime) {
        this.PCreatTime = PCreatTime;
    }

    public int getPState() {
        return PState;
    }

    public void setPState(int PState) {
        this.PState = PState;
    }

    public String getPType() {
        return PType;
    }

    public void setPType(String PType) {
        this.PType = PType;
    }
}
