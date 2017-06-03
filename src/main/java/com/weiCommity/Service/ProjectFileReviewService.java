package com.weiCommity.Service;

import com.weiCommity.Dao.BaseDao;
import com.weiCommity.Dao.ProjectFileReviewDao;
import com.weiCommity.Model.ProjectFile;
import com.weiCommity.Model.ProjectFileReview;
import com.weiCommity.Util.StaticVar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * PackageName com.weiCommity.Service
 * Created by uryuo on 17/6/1.
 */
@Service
public class ProjectFileReviewService {
    final ProjectFileReviewDao projectFileReviewDao;

    public int getOneFileAllReview(ProjectFile elem) {
        return projectFileReviewDao.getOneFileAllReview(elem);
    }


    public int getOneFileDealReview(ProjectFile elem) {
        return projectFileReviewDao.getOneFileDealReview(elem);
    }

    @Autowired
    public ProjectFileReviewService(ProjectFileReviewDao projectFileReviewDao) {
        this.projectFileReviewDao = projectFileReviewDao;
    }

    public void insertNewComment(ProjectFileReview thisReview) {
        thisReview.setPRId(UUID.randomUUID().toString());
        projectFileReviewDao.insertComment(thisReview);
    }

    public List<ProjectFileReview> getAllByPFid(ProjectFile file) {
        return (List<ProjectFileReview>) BaseDao.selListFromSQL(StaticVar.getMapperNameSpace() + "sel_getAllCommentpFid", file);
    }

    public void setNoProblem(ProjectFileReview thisPFR) {
        projectFileReviewDao.setNoProblem(thisPFR);
    }

    public void delComment(ProjectFileReview projectFileReview) {
        projectFileReviewDao.delComment(projectFileReview);
    }
}
