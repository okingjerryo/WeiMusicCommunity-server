package com.weiCommity.Dao;

import com.weiCommity.Model.ProjectFile;
import com.weiCommity.Util.StaticVar;
import org.springframework.stereotype.Repository;

/**
 * PackageName com.weiCommity.Dao
 * Created by uryuo on 17/5/17.
 */
@Repository
public class FileReviewDao extends BaseDao {

    public int getThisFileNotDealR(ProjectFile thisFile) {
        return (int) BaseDao.selOneFromSQL(StaticVar.getMapperNameSpace() + "sel_FReviewNoDealCount", thisFile);
    }
}
