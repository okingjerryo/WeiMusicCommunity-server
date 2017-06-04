package com.weiCommity.Dao;

import com.weiCommity.Model.ProjectBonus;
import com.weiCommity.Model.ProjectInfo;
import com.weiCommity.Util.StaticVar;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PackageName com.weiCommity.Dao
 * Created by uryuo on 17/6/4.
 */
@Repository
public class BonusDao extends BaseDao {

    public List<ProjectBonus> getAllCommityBonus(ProjectInfo info) {
        return (List<ProjectBonus>) BaseDao.selListFromSQL(StaticVar.getMapperNameSpace() + "sel_getAllCommityBonus", info);
    }

    public ProjectBonus getBonusByCMid(ProjectBonus bonus) {
        return (ProjectBonus) BaseDao.selOneFromSQL(StaticVar.getMapperNameSpace() + "sel_getBonusByCmid", bonus);
    }

    public void setBonus(ProjectBonus bonus) {
        BaseDao.InEdDeOneIntoSql(StaticVar.getMapperNameSpace() + "update_setBonus", bonus);
    }
}
