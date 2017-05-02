package com.weiCommity.Util;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 存储常用需要的全局变量，例如路径、对路径的解码格式等等
 * Created by uryuo on 17/4/24.
 */
public class StaticVar {
    private static String toFilePath = "";
    private final static String decodeFileSet = "ISO-8859-1";

    public static String getDecodeFileSet() {
        return decodeFileSet;
    }

    public static String getToFilePath() {
        if (toFilePath.isEmpty()) {
            try {
                File tarPath = ResourceUtils.getFile("classpath:/FileSpace/CommonSpace/default_personal_image.png");
                toFilePath = tarPath.getParentFile().getParentFile().toString() + "/";
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return toFilePath;
    }
}
