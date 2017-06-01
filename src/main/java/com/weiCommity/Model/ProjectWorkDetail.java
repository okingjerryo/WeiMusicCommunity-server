package com.weiCommity.Model;

import org.springframework.stereotype.Component;

/**
 * PackageName com.weiCommity.Model
 * Created by uryuo on 17/5/31.
 */
@Component
public class ProjectWorkDetail extends ProjectWork {
    private String WorkSC;
    private int WorkBonus;
    private String UHeadImg;
    private String UNackName;

    @Override
    public String getWorkSC() {
        return WorkSC;
    }

    @Override
    public void setWorkSC(String workSC) {
        WorkSC = workSC;
    }

    public int getWorkBonus() {
        return WorkBonus;
    }

    public void setWorkBonus(int workBonus) {
        WorkBonus = workBonus;
    }

    public String getUHeadImg() {
        return UHeadImg;
    }

    public void setUHeadImg(String UHeadImg) {
        this.UHeadImg = UHeadImg;
    }

    public String getUNackName() {
        return UNackName;
    }

    public void setUNackName(String UNackName) {
        this.UNackName = UNackName;
    }
}
