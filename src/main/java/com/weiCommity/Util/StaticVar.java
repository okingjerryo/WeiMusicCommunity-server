package com.weiCommity.Util;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by uryuo on 17/4/24.
 */
public class StaticVar {
    private static String toFilePath = "";

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
