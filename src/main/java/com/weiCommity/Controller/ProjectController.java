package com.weiCommity.Controller;

import com.weiCommity.Model.CommityInfo;
import com.weiCommity.Model.ProjectDynamic;
import com.weiCommity.Model.ProjectInfo;
import com.weiCommity.Model.ProjectWork;
import com.weiCommity.Service.*;
import com.weiCommity.Util.HttpJson;
import org.joda.time.DateTime;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * PackageName com.weiCommity.Controller
 * Created by uryuo on 17/5/10.
 */
@RestController
@RequestMapping(value = "api/project", produces = "application/json;charset=UTF-8")
public class ProjectController {

    //Project 需要的支持服务
    final ProjectService projectService;
    final UserWorkService userWorkService;
    final MessageBoxService messageBoxService;
    final WorkService findWorkService;
    final ProjectDynamicService projectDynamicService;

    //策划用户创建一个工程
    @RequestMapping("Project/create")
    public ResponseEntity<HttpJson> createNewCProject(@RequestBody String jsonString) {
        HttpJson re = new HttpJson();
        HttpJson inObj = new HttpJson(jsonString, ProjectInfo.class);

        try {
            if (!inObj.getClassName().equals("ProjectInfo:Project-create"))
                throw new JSONException("");


            String pCTimeStr = inObj.getPara("pCTimeStr");
            ProjectInfo info = (ProjectInfo) inObj.getClassObject();
            info.setPCreatTime(new DateTime(pCTimeStr));
            //注册
            String thisPid = projectService.createProject(info);
            //2. 注册自己为策划
            ProjectWork thisWork = new ProjectWork();
            thisWork.setPId(thisPid);
            thisWork.setUJoinTime(info.getPCreatTime());
            thisWork.setUWid(findWorkService.getWorkId("歌曲", "策划"));

            String thisPWId = projectService.setPeopleWorkToProject(thisWork);
            //3.ProjectDy 插入
            ProjectDynamic dynamic = new ProjectDynamic();
            dynamic.setPDType("创建");
            dynamic.setPWId(thisPWId);
            projectDynamicService.setProjectDynWithNOFile(dynamic);
        } catch (JSONException e) {
            re.setStatusCode(250);
            re.setMessage("请求不合法");
            re.constractJsonString();
            return new ResponseEntity<>(re, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            re.setStatusCode(203);
            re.setMessage("服务器出错");
            return new ResponseEntity<>(re, HttpStatus.BAD_GATEWAY);
        }


        return new ResponseEntity<>(re, HttpStatus.OK);
    }

    //获得指定状态的社团全部项目
    @RequestMapping("projectInfo/get")
    public ResponseEntity<HttpJson> getCommityProjectByState(String jsonString) {
        HttpJson re = new HttpJson();
        HttpJson inObj = new HttpJson(jsonString, CommityInfo.class);

        try {
            if (!inObj.getClassName().equals("CommityInfo:getAllProjectInProject"))
                throw new JSONException("");
            CommityInfo info = (CommityInfo) inObj.getClassObject();
            Integer start = Integer.getInteger(inObj.getPara("startState"));
            Integer end = Integer.getInteger(inObj.getPara("endState"));
            ProjectInfo thisPInfo = new ProjectInfo();
            thisPInfo.setCId(info.getCid());
            thisPInfo.setStartState(start);
            thisPInfo.setEndState(end);
            List<ProjectInfo> allProject = projectService.getAllCommityProject(thisPInfo);

        } catch (JSONException e) {
            re.setStatusCode(250);
            re.setMessage("请求异常");
            return new ResponseEntity<>(re, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            re.setStatusCode(203);
            re.setMessage("服务器出现异常");
            return new ResponseEntity<>(re, HttpStatus.BAD_GATEWAY);
        }

        re.constractJsonString();
        return new ResponseEntity<>(re, HttpStatus.OK);
    }

    //根据Pid查询单个Project的详情
    @RequestMapping("projectDetail/getOne")
    ResponseEntity<HttpJson> getProjectDetail(String jsonString) {
        HttpJson re = new HttpJson();
        HttpJson inObj = new HttpJson(jsonString, CommityInfo.class);

        try {
            if (!inObj.getClassName().equals("CommityInfo:getAllProjectInProject"))
                throw new JSONException("");

        } catch (JSONException e) {
            re.setStatusCode(250);
            re.setMessage("请求异常");
            return new ResponseEntity<>(re, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            re.setStatusCode(203);
            re.setMessage("服务器出现异常");
            return new ResponseEntity<>(re, HttpStatus.BAD_GATEWAY);
        }

        re.constractJsonString();
        return new ResponseEntity<>(re, HttpStatus.OK);

    }

    //bean管理
    @Autowired
    public ProjectController(ProjectService projectService,
                             UserWorkService workService, MessageBoxService messageBoxService, WorkService findWorkService, ProjectDynamicService projectDynamicService) {
        this.projectService = projectService;
        this.userWorkService = workService;
        this.messageBoxService = messageBoxService;
        this.findWorkService = findWorkService;
        this.projectDynamicService = projectDynamicService;
    }
}
