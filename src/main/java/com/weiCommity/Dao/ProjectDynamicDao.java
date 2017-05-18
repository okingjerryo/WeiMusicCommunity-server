package com.weiCommity.Dao;

import com.weiCommity.Model.ProjectDynamic;
import com.weiCommity.Util.StaticVar;
import org.springframework.stereotype.Repository;

/**
 * PackageName com.weiCommity.Dao
 * Created by uryuo on 17/5/11.
 */
@Repository
public class ProjectDynamicDao extends BaseDao {

    public ProjectDynamic getProjectDynAfterInView(ProjectDynamic dynamic) {
        return (ProjectDynamic) BaseDao.selOneFromSQL(StaticVar.getMapperNameSpace() + "sel_ProjectDynAfterIn", dynamic);
    }

    public void setDynWord(ProjectDynamic dyWord) {
        BaseDao.InEdDeOneIntoSql(StaticVar.getMapperNameSpace() + "insert_DynWord", dyWord);
    }

    public void insertDynamic(ProjectDynamic dynamic) {
        BaseDao.InEdDeOneIntoSql(StaticVar.getMapperNameSpace() + "insert_Dyn", dynamic);
    }

    public ProjectDynamic getProjectDynWFAfterInView(ProjectDynamic projectDynamic) {
        return (ProjectDynamic) BaseDao.selOneFromSQL(StaticVar.getMapperNameSpace() + "sel_ProejectDynWFAfterIn", projectDynamic);
    }
}
