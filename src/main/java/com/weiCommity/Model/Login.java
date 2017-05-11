package com.weiCommity.Model;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by uryuo on 17/4/11.
 */
@Component
public class Login {
   private String UUuid;
   private String UPwd;
   private String UAName;
   private String UTel;
   private String UMail;
   private String UAccStatus = "0";


    public void RegistWithFlag(String UPwd, String registName,String flag) {
        this.UUuid = UUID.randomUUID().toString();
        this.UPwd = UPwd;
        if (flag.equals("account")) {
            this.UAName = registName;
        }else if(flag.equals("tel")){
            this.UTel = registName;
        }else if (flag.equals("mail")){
            this.UMail = registName;
        }
    }

    public Login() {
        super();
    }

    //getter & setter
    public String getUUuid() {
        return UUuid;
    }

    public void setUUuid(String UUuid) {
        this.UUuid = UUuid;
    }

    public String getUPwd() {
        return UPwd;
    }

    public void setUPwd(String UPwd) {
        this.UPwd = UPwd;
    }

    public String getUAName() {
        return UAName;
    }

    public void setUAName(String UAName) {
        this.UAName = UAName;
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

    public String getUAccStatus() {
        return UAccStatus;
    }

    public void setUAccStatus(String UAccStatus) {
        this.UAccStatus = UAccStatus;
    }
}
