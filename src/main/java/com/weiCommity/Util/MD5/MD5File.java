package com.weiCommity.Util.MD5;

import java.io.FileInputStream;
import java.security.MessageDigest;

/**
 * Created by uryuo on 17/4/14.
 */
public class MD5File {
    // 待计算文件的path
    private String filePath = "";
    public String calc() throws Exception{
        if (filePath.equals("")){
            System.out.println("WARNING:No filePath set!");
            return "";
        }
        MessageDigest md = MessageDigest.getInstance("MD5");
        FileInputStream fis = new FileInputStream(filePath);

        byte[] dataBytes = new byte[1024];


        int nread = 0;
        while ((nread = fis.read(dataBytes)) != -1) {
            md.update(dataBytes, 0, nread);
        };
        byte[] mdbytes = md.digest();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mdbytes.length; i++) {
            sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }

       return sb.toString();

    }
    public MD5File(String filePath) throws Exception {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
