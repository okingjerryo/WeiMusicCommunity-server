package com.weiCommity.Model;

import org.springframework.stereotype.Component;

/**
 * PackageName com.weiCommity.Model
 * Created by uryuo on 17/5/10.
 */
@Component
public class Work {
    private String WorkId;
    private String WorkFC;
    private String WorkSC;
    private String WorkType;

    public String getWorkId() {
        return WorkId;
    }

    public void setWorkId(String workId) {
        WorkId = workId;
    }

    public String getWorkFC() {
        return WorkFC;
    }

    public void setWorkFC(String workFC) {
        WorkFC = workFC;
    }

    public String getWorkSC() {
        return WorkSC;
    }

    public void setWorkSC(String workSC) {
        WorkSC = workSC;
    }

    public String getWorkType() {
        return WorkType;
    }

    public void setWorkType(String workType) {
        WorkType = workType;
    }
}
