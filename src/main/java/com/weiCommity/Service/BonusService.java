package com.weiCommity.Service;

import com.weiCommity.Dao.BonusDao;
import com.weiCommity.Model.ProjectBonus;
import com.weiCommity.Model.ProjectInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PackageName com.weiCommity.Service
 * Created by uryuo on 17/6/4.
 */
@Service
public class BonusService {
    final BonusDao bonusDao;

    @Autowired
    public BonusService(BonusDao bonusDao) {
        this.bonusDao = bonusDao;
    }

    public List<ProjectBonus> getAllCommityBonus(ProjectInfo info) {
        return bonusDao.getAllCommityBonus(info);
    }

    public ProjectBonus getBonusByCMid(ProjectBonus bonus) {
        return bonusDao.getBonusByCMid(bonus);
    }

    public void setBonus(ProjectBonus bonus) {
        bonusDao.setBonus(bonus);
    }
}
