package com.weiCommity.Controller;

import com.weiCommity.Model.*;
import com.weiCommity.Service.MessageBoxService;
import com.weiCommity.Service.ProjectFileReviewService;
import com.weiCommity.Service.ProjectService;
import com.weiCommity.Util.HttpJson;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * PackageName com.weiCommity.Controller
 * Created by uryuo on 17/5/17.
 */
@RestController
@RequestMapping(value = "api/fileReview", produces = "application/json;charset=UTF-8")
public class ProjectFileReviewController {
    final ProjectFileReviewService fileReviewService;
    final ProjectService projectService;
    final MessageBoxService messageBoxService;
    List<String> FanWorkFlow = new ArrayList<>();

    @Autowired
    public ProjectFileReviewController(ProjectFileReviewService fileReviewService, ProjectService projectService, MessageBoxService messageBoxService) {
        this.fileReviewService = fileReviewService;
        this.projectService = projectService;

        FanWorkFlow.add("美工");
        FanWorkFlow.add("后期");
        FanWorkFlow.add("歌手");
        this.messageBoxService = messageBoxService;
    }

    //对指定文件进行评论
    @RequestMapping("review/add")
    ResponseEntity<HttpJson> addCommentNormal(@RequestBody String jsonString) {
        return ControllerFreamwork.excecute(jsonString, ProjectFileReview.class, "ProjectFileReview:addComNormal", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                ProjectFileReview thisReview = (ProjectFileReview) inObj.getClassObject();
                fileReviewService.insertNewComment(thisReview);
                return re;
            }
        });
    }

    //策划评论 相当于返工
    @RequestMapping("reviewCe/add")
    ResponseEntity<HttpJson> addCommentCe(@RequestBody String jsonString) {
        return ControllerFreamwork.excecute(jsonString, ProjectFileReview.class, "ProjectFileReview:addComCe", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                ProjectFileReview thisReview = (ProjectFileReview) inObj.getClassObject();
                fileReviewService.insertNewComment(thisReview);
                //开始返工
                ProjectFile thisPeFile = new ProjectFile();
                thisPeFile.setPFId(thisReview.getPFId());
                thisPeFile = projectService.getProjectFileDetailOne(thisPeFile);
                int state = 5;
                for (String elem : FanWorkFlow) {
                    state--;
                    //把这个项目相关工种的已经完成的文件取消
                    ProjectFile thisStateFile = new ProjectFile();
                    thisStateFile.setPId(thisPeFile.getPId());
                    thisStateFile.setWorkSC(elem);
                    thisStateFile = projectService.getPFByWorkandPid(thisStateFile);
                    projectService.setProjectFileCancal(thisStateFile);
                    if (elem.equals(thisPeFile.getWorkSC())) {
                        //把这个项目的状态变更
                        ProjectInfo info = new ProjectInfo();
                        info.setPId(thisPeFile.getPId());
                        projectService.setProjectState(info, state);
                        break;
                    }

                }
                //发送通知信
                String thisUuid = inObj.getPara("thisUUuid");
                ProjectWorkDetail detail = new ProjectWorkDetail();
                detail.setWorkSC(thisReview.getWorkSC());
                detail.setPId(thisPeFile.getPId());
                ProjectInfo info = new ProjectInfo();
                info.setPId(thisPeFile.getPId());
                info = projectService.getProjectInfoOne(info);
                info = projectService.getProjectInfoOne(info);
                //查这个人的uuid
                detail = projectService.getProjectWorkWithWorkSC(detail);
                MessageBox box = new MessageBox();
                box.setMTarId(detail.getUUuid());
                box.setMSenderId(thisUuid);
                box.setMTitle("您的项目：" + info.getPTitle() + "的" + detail.getWorkSC() + "阶段被返工");
                box.setMThings("很抱歉，由于某些原因，" + box.getMTitle() + "。请你及时检查相关提交文件。并提交重新修改");
                box.setMSenderType(3);
                box.setMCheck(-2);
                messageBoxService.sendMail(box);
                return re;
            }
        });


    }

    //通过PFid获取到全部评论
    @RequestMapping("review/getAll")
    ResponseEntity<HttpJson> getAllComment(@RequestBody String jsonString) {
        return ControllerFreamwork.excecute(jsonString, ProjectFile.class, "ProjectFile:getAllComment", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                ProjectFile file = (ProjectFile) inObj.getClassObject();
                List<ProjectFileReview> listRe = fileReviewService.getAllByPFid(file);
                re.setClassObject(listRe);
                return re;
            }
        });
    }
    //删除指定评论号的评论

    //将评论的状态置为不认为有问题
    @RequestMapping("noProblem/set")
    ResponseEntity<HttpJson> setNoProblem(@RequestBody String jsonString) {
        return ControllerFreamwork.excecute(jsonString, ProjectFileReview.class, "ProjectFileReview:noProblem", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                ProjectFileReview thisPFR = (ProjectFileReview) inObj.getClassObject();
                thisPFR.setPRDealTime(new DateTime());
                fileReviewService.setNoProblem(thisPFR);
                return re;
            }
        });
    }

    @RequestMapping("comment/del")
    ResponseEntity<HttpJson> delComment(@RequestBody String jsonString) {
        return ControllerFreamwork.excecute(jsonString, ProjectFileReview.class, "ProjectFileReview:delComment", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                ProjectFileReview projectFileReview = (ProjectFileReview) inObj.getClassObject();
                fileReviewService.delComment(projectFileReview);
                return re;
            }
        });
    }
}
