package com.weiCommity.Dao;

import com.weiCommity.Model.Work;
import com.weiCommity.Util.StaticVar;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * PackageName com.weiCommity.Dao
 * Created by uryuo on 17/5/10.
 */
@Repository
public class WorkDao extends BaseDao {

    public List<Work> getAllWork() {
        List<Work> allWork = new ArrayList<>();
        try {
            session = sqlSessionFactory.openSession();
            session.selectList(StaticVar.getMapperNameSpace() + "sel_getAllWork");
        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            throw e;
        }
        session.close();
        return allWork;
    }

}
