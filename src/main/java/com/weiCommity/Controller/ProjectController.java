package com.weiCommity.Controller;

import com.weiCommity.Model.*;
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
    final FileReviewService fileReviewService;

    //策划用户创建一个工程
    @RequestMapping("Project/create")
    public ResponseEntity<HttpJson> createNewCProject(@RequestBody String jsonString) {
        HttpJson re = new HttpJson();
        HttpJson inObj = new HttpJson(jsonString, ProjectInfo.class);

        try {
            if (!inObj.getClassName().equals("ProjectInfo:Project-create"))
                throw new JSONException("");

            ProjectInfo info = (ProjectInfo) inObj.getClassObject();
            info.setPCreatTime(DateTime.now());
            //注册
            String thisPid = projectService.createProject(info);
            //2. 注册自己为策划
            ProjectWork thisWork = new ProjectWork();
            thisWork.setPId(thisPid);
            thisWork.setUJoinTime(info.getPCreatTime());
            String thisWorkId = findWorkService.getWorkId("歌曲", "策划");
            UserTFWork thisUWClass = userWorkService.getUWWithUUidandWorkId(info.getCreatUUuid(), thisWorkId);
            thisWork.setUWid(thisUWClass.getUWid());
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
        re.setStatusCode(400);
        re.constractJsonString();
        return new ResponseEntity<>(re, HttpStatus.OK);
    }

    //获得指定状态的社团全部项目
    @RequestMapping("projectInfo/get")
    public ResponseEntity<HttpJson> getCommityProjectByState(@RequestBody String jsonString) {
        HttpJson re = new HttpJson();
        HttpJson inObj = new HttpJson(jsonString, CommityInfo.class);

        try {
            if (!inObj.getClassName().equals("CommityInfo:getAllProjectInProject"))
                throw new JSONException("");
            CommityInfo info = (CommityInfo) inObj.getClassObject();
            int start = Integer.parseInt(inObj.getPara("startState"));
            int end = Integer.parseInt(inObj.getPara("endState"));
            ProjectInfo thisPInfo = new ProjectInfo();
            thisPInfo.setCId(info.getCid());
            thisPInfo.setStartState(start);
            thisPInfo.setEndState(end);
            List<ProjectInfo> allProject = projectService.getAllCommityProject(thisPInfo);
            re.setClassObject(allProject);
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
    ResponseEntity<HttpJson> getProjectDetail(@RequestBody String jsonString) {
        HttpJson re = new HttpJson();
        HttpJson inObj = new HttpJson(jsonString, ProjectInfo.class);

        try {
            if (!inObj.getClassName().equals("ProjectInfo:getOnePDetail"))
                throw new JSONException("");

            ProjectInfo info = (ProjectInfo) inObj.getClassObject();

            ProjectInfo reInfo = projectService.getOnePDetail(info);
            re.setClassObject(reInfo);

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

    //使用指定工种向指定项目发送项目加入申请
    @RequestMapping("joinProject/application")
    ResponseEntity<HttpJson> projectAddapplication(@RequestBody String jsonString) {
        return ControllerFreamwork.excecute(jsonString, ProjectWork.class, "ProjectWork:applicateJoinProject", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                //1.从数据库取出当前的UWid
                ProjectWork applyWork = (ProjectWork) inObj.getClassObject();
                //UWid get
                UserTFWork applyUWork = userWorkService.getUserWorkByProjectWork(applyWork);
                //2.置当前用户状态为申请中
                applyWork.setUWid(applyUWork.getUWid());
                //返回一个从数据库读出的ProjectWorkApplyMsg视图
                ProjectWorkApplyMsg applyMsg = projectService.addProjectWorkApply(applyWork);
                //3.书写Mail并发送给策划
                messageBoxService.sendApplyToProjectCreater(applyMsg);
                return re;
            }
        });
    }

    //策划工种对指定请求进行审核 传回来时候注意要给予PWId
    @RequestMapping("joinProject/check")
    ResponseEntity<HttpJson> checkJoinApply(@RequestBody String jsonnString) {
        return ControllerFreamwork.excecute(jsonnString, ProjectWork.class, "ProjectWork:joinCheck", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {

                //需要的参数
                String checkStr = inObj.getPara("checkResult");
                String thisMail = inObj.getPara("mailMid");
                ProjectWork CheckerWork = (ProjectWork) inObj.getClassObject();
                //成功
                if (checkStr.equals("true")) {
                    //置状态为同意
                    projectService.applyJoinApplicate(CheckerWork, thisMail);

                    //查看加入的人数是否满足需求
                    boolean canStart = projectService.checkProjectCanDo(CheckerWork);
                    if (canStart) {
                        //设置项目状态为待传文件
                        ProjectInfo thisInfo = new ProjectInfo();
                        thisInfo.setPId(CheckerWork.getPId());
                        projectService.setProjectState(thisInfo, 1);
                    }
                } else { //失败 给每一个人发邮件
                    projectService.prventJoinApplicate(CheckerWork, thisMail);
                }
                return re;
            }
        });
    }

    //根据UUid+Pid 返回这个人的ProjectWOrk
    @RequestMapping("projectwork/get")
    ResponseEntity<HttpJson> getPersonalPWId(@RequestBody String jsonString) {
        return ControllerFreamwork.excecute(jsonString, ProjectWork.class, "ProjectWork:getPersonalPWork", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                ProjectWork work = (ProjectWork) inObj.getClassObject();
                ProjectWork reWork = projectService.getPersonalPW(work);
                re.setClassObject(reWork);
                return re;
            }
        });
    }

    //通过PWId 获取这个人全部历史文件
    @RequestMapping("AllFile/get")
    ResponseEntity<HttpJson> getAllFile(@RequestBody String jsonString) {
        return ControllerFreamwork.excecute(jsonString, ProjectWork.class, "ProjectWork:getAllUserProjectFile", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                ProjectWork projectWork = (ProjectWork) inObj.getClassObject();
                List<ProjectFile> allFile = projectService.getAllPFile(projectWork);
                //组装name 和状态
                for (ProjectFile thisFile : allFile) {
                    String thisPath = thisFile.getPFPath();
                    String name = thisPath.substring(thisPath.lastIndexOf("/") + 1, thisPath.lastIndexOf("."));
                    thisFile.setPFName(name);

                    //查状态
                    boolean fileflag = fileReviewService.getThisFileIsReadytoEnd(thisFile);
                    thisFile.setARapir(fileflag);
                }
                re.setClassObject(allFile);
                return re;
            }
        });
    }

    //获得文件的详细信息
//    @RequestMapping("oneFileDetail/get"){
//
//    }

    //返回自己可以访问的PWid（策划 全部 其他 自己的上一层）

    //完成自己的阶段


    //策划审核 同意进入下一阶段 以及不同意

    //策划终审 最后更新积分








    //bean管理
    @Autowired
    public ProjectController(ProjectService projectService,
                             UserWorkService workService, MessageBoxService messageBoxService, WorkService findWorkService, ProjectDynamicService projectDynamicService, FileReviewService fileReviewService) {
        this.projectService = projectService;
        this.userWorkService = workService;
        this.messageBoxService = messageBoxService;
        this.findWorkService = findWorkService;
        this.projectDynamicService = projectDynamicService;
        this.fileReviewService = fileReviewService;
    }
}