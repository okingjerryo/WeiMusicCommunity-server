package com.weiCommity.Service;

import com.weiCommity.Dao.WorkDao;
import com.weiCommity.Model.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * PackageName com.weiCommity.Service
 * Created by uryuo on 17/5/10.
 */
@Service
public class WorkService {
    private HashMap<String, String> workEntity = new HashMap<String, String>();
    private final WorkDao dao;

    @Autowired
    public WorkService(WorkDao dao) {
        this.dao = dao;
        constractFromBase();
    }

    private void constractFromBase() {
        List<Work> thisWork = dao.getAllWork();
        for (Work thisW : thisWork) {
            workEntity.put(thisW.getWorkFC() + thisW.getWorkSC(), thisW.getWorkId());
        }
    }

    public String getWorkId(String FirstSc, String SecondSc) {
        return workEntity.get(FirstSc + SecondSc);
    }

}
