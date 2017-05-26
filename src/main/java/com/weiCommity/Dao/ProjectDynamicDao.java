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
    //从数据库将组装动态信息需要的关联信息获取出来
    public ProjectDynamic getProjectDynAfterInView(ProjectDynamic dynamic) {
        return (ProjectDynamic) BaseDao.selOneFromSQL(StaticVar.getMapperNameSpace() + "sel_ProjectDynAfterIn", dynamic);
    }
    //更新项目动态字段的内容
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
