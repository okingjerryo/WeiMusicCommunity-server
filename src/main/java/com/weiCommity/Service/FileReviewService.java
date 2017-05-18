package com.weiCommity.Service;

import com.weiCommity.Dao.FileReviewDao;
import com.weiCommity.Model.ProjectFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PackageName com.weiCommity.Service
 * Created by uryuo on 17/5/17.
 */
@Service
public class FileReviewService {
    final FileReviewDao fileReviewDao;


    @Autowired
    public FileReviewService(FileReviewDao fileReviewDao) {
        this.fileReviewDao = fileReviewDao;
    }

    public boolean getThisFileIsReadytoEnd(ProjectFile thisFile) {
        int count = fileReviewDao.getThisFileNotDealR(thisFile);
        return count == 0;
    }
}
