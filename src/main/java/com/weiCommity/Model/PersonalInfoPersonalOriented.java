package com.weiCommity.Model;

import org.joda.time.DateTime;

/**
 * PackageName com.weiCommity.Model
 * Created by uryuo on 17/5/12.
 */
public class PersonalInfoPersonalOriented extends UserExtend {

    private String UANmae;
    private String UTel;
    private String UMail;


    public String getUANmae() {
        return UANmae;
    }

    public void setUANmae(String UANmae) {
        this.UANmae = UANmae;
    }

    public String getUTel() {
        return UTel;
    }

    public void setUTel(String UTel) {
        this.UTel = UTel;
    }

    public String getUMail() {
        return UMail;
    }

    public void setUMail(String UMail) {
        this.UMail = UMail;
    }

    public void setFriendIteam(String UUuid, String ImageUrl, String UNackName, String sign) {
        this.UUuid = UUuid;
        this.UHeadImg = ImageUrl;
        this.USign = sign;
        this.UNackName = UNackName;

    }

    public void setUserinf(String UUuid, String ImageUrl, String UNackName, String gender,
                           String phone, DateTime UBirthday, String sign, String UANmae, String UMail) {

        this.UUuid = UUuid;
        this.UHeadImg = ImageUrl;
        this.USign = sign;
        this.UNackName = UNackName;
        this.USex = gender;
        this.UTel = phone;
        this.UBirthday = UBirthday;
        this.UANmae = UANmae;
        this.UMail = UMail;
    }


}
