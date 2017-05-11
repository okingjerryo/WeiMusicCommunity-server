package com.weiCommity.Util.MD5;

import java.security.MessageDigest;

/**
 * Created by uryuo on 17/4/14.
 */
public class MD5String {
    private String calcString="";

    public String calc() throws Exception{
        if (calcString.equals("")){
            System.out.println("WARNING:No filePath set!");
            return "";
        }

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(calcString.getBytes());

        byte byteData[] = md.digest();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();

    }

    public MD5String(String calcString) throws Exception{
        this.calcString = calcString;
        this.calcString = this.calc();
    }

    public String getCalcString() {
        return calcString;
    }

    public void setCalcString(String calcString) {
        this.calcString = calcString;
    }

    public static void main(String args[]){


    }
}
