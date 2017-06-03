package com.weiCommity.Dao;

import com.weiCommity.Model.ProjectFile;
import com.weiCommity.Model.ProjectFileReview;
import com.weiCommity.Util.StaticVar;
import org.springframework.stereotype.Repository;

/**
 * PackageName com.weiCommity.Dao
 * Created by uryuo on 17/6/1.
 */
@Repository
public class ProjectFileReviewDao extends BaseDao {
    public int getOneFileAllReview(ProjectFile elem) {
        return (int) BaseDao.selOneFromSQL(StaticVar.getMapperNameSpace() + "sel_ProjectFReviewCount", elem);
    }

    public int getOneFileDealReview(ProjectFile elem) {
        return (int) BaseDao.selOneFromSQL(StaticVar.getMapperNameSpace() + "sel_ProjectFRDealCount", elem);
    }

    public void insertComment(ProjectFileReview thisReview) {
        BaseDao.InEdDeOneIntoSql(StaticVar.getMapperNameSpace() + "insert_newCommentFile", thisReview);
    }

    public void setNoProblem(ProjectFileReview thisPFR) {
        BaseDao.InEdDeOneIntoSql(StaticVar.getMapperNameSpace() + "update_noProblem", thisPFR);
    }

    public void delComment(ProjectFileReview projectFileReview) {
        BaseDao.InEdDeOneIntoSql(StaticVar.getMapperNameSpace() + "del_Comment", projectFileReview);
    }
}
