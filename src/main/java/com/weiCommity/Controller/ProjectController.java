package com.weiCommity.Controller;

import com.weiCommity.Model.*;
import com.weiCommity.Service.*;
import com.weiCommity.Util.HttpJson;
import com.weiCommity.Util.StaticVar;
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
    final ProjectFileReviewService projectFileReviewService;
    final BonusService bonusService;

    //策划用户创建一个工程 注意本Controller编写时还为使用Controller框架
    //指定servlet请求地址
    @RequestMapping("Project/create")
    public ResponseEntity<HttpJson> createNewCProject(@RequestBody String jsonString) {
        //定义传入和输出数据
        HttpJson re = new HttpJson();
        HttpJson inObj = new HttpJson(jsonString, ProjectInfo.class);

        try {
            //请求不符合就抛出异常
            if (!inObj.getClassName().equals("ProjectInfo:Project-create"))
                throw new JSONException("");
            //将客户端提交的项目信息Bean从Json中取出
            ProjectInfo info = (ProjectInfo) inObj.getClassObject();
            info.setPCreatTime(DateTime.now());
            //1.注册项目
            String thisPid = projectService.createProject(info);
            //2. 注册自己为策划
            ProjectWork thisWork = new ProjectWork();
            thisWork.setPId(thisPid);
            thisWork.setUJoinTime(info.getPCreatTime());
            //通过数据库Work表把选定工种的WorkId查出来
            String thisWorkId = findWorkService.getWorkId("歌曲", "策划");
            //通过Uid和WorkId从数据库查询到这个用户的UWId 这个是成员类必须的字段
            UserTFWork thisUWClass = userWorkService.getUWWithUUidandWorkId(info.getCreatUUuid(), thisWorkId);
            thisWork.setUWid(thisUWClass.getUWid());
            thisWork.setUApply(1);
            //项目表插入当前用户为策划
            String thisPWId = projectService.setPeopleWorkToProject(thisWork);
            //3.ProjectDy 动态生成项目动态 并插入
            //设置当前的项目动态类型为创建
            ProjectDynamic dynamic = new ProjectDynamic();
            dynamic.setPDType("创建");
            dynamic.setPWId(thisPWId);
            //将项目动态插入数据库
            projectDynamicService.setProjectDynWithNOFile(dynamic);

        } catch (JSONException e) {
            re.setStatusCode(250);
            re.setMessage("请求不合法");
            re.constractJsonString();
            return new ResponseEntity<>(re, HttpStatus.BAD_REQUEST);
        //在服务器处理数据时发生了意外错误
        } catch (Exception e) {
            re.setStatusCode(203);
            re.setMessage("服务器出错");
            return new ResponseEntity<>(re, HttpStatus.BAD_GATEWAY);
        }
        //组装返回状态 因为只需要让客户端知道插入成功即可，所以只设置了返回码为400 成功
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
            re.setPara("creatTime", reInfo.getPCreatTime().toString());

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
                applyWork.setUJoinTime(new DateTime());
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

                    CheckerWork = projectService.getProjectWorkDetail(CheckerWork);
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

    //根据Pid返回一个项目的成员清单
    @RequestMapping("AllProjectWork/get")
    ResponseEntity<HttpJson> getAllProjectWork(@RequestBody String jsonString) {
        return ControllerFreamwork.excecute(jsonString, ProjectInfo.class, "ProjectInfo:getAllProjectWrok", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                ProjectInfo thisInfo = (ProjectInfo) inObj.getClassObject();
                List<ProjectWorkDetail> reList = projectService.getAllProjectWork(thisInfo);
                re.setClassObject(reList);
                return re;
            }
        });
    }

    //对已有的可以修改的信息进行更新
    @RequestMapping("ProjectInfo/edit")
    ResponseEntity<HttpJson> editProjectInfo(@RequestBody String jsonString) {
        return ControllerFreamwork.excecute(jsonString, ProjectInfo.class, "ProjectInfo:editProjectInfo", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                ProjectInfo thisInfo = (ProjectInfo) inObj.getClassObject();
                projectService.editProjectInfo(thisInfo);
                return re;
            }
        });
    }
    // 返回自己可以访问的PWid（策划 全部 其他 自己的上一层）

    //完成自己的阶段


    //策划审核 同意进入下一阶段 以及不同意

    //策划终审 最后更新积分


    //获得一个项目的CreateTIme
    @RequestMapping("ProjectTimeStr/get")
    ResponseEntity<HttpJson> getPCTime(@RequestBody String jsonString) {
        return ControllerFreamwork.execute(jsonString, "form:getPTimeStr", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                String thisPid = inObj.getPara("Pid");
                ProjectInfo project = new ProjectInfo();
                project.setPId(thisPid);
                project = projectService.getProjectInfoOne(project);
                String timeStr = project.getPCreatTime().toString();
                re.setPara("timeStr", timeStr);
                return re;
            }
        });

    }

    //策划获取当前最新的上传的伴奏文件
    @RequestMapping("upFile/getLatest")
    ResponseEntity<HttpJson> getUpFileLatestCe(@RequestBody String jsonString) {
        return ControllerFreamwork.execute(jsonString, "form:CeGetLatest", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                String Pid = inObj.getPara("Pid");
                String UUid = inObj.getPara("Uuid");
                //获得当前用户的Pwid
                ProjectWork projectWork = new ProjectWork();
                projectWork.setPId(Pid);
                projectWork.setUUuid(UUid);
                projectWork = projectService.getProjectWorkOne(projectWork);
                List<ProjectFile> files = projectService.getAllPFile(projectWork);
                //返回第一个 就是最新的
                if (files.size() != 0)
                    re.setClassObject(files.get(0));
                else
                    re.setClassObject(new ProjectFile());
                return re;
            }
        });
    }

    //更新歌词
    @RequestMapping("lrc/update")
    ResponseEntity<HttpJson> LrcUpdate(@RequestBody String jsonString) {
        return ControllerFreamwork.excecute(jsonString, ProjectInfo.class, "ProjectInfo:updatePInfo", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                ProjectInfo info = (ProjectInfo) inObj.getClassObject();
                projectService.updateLrc(info);
                return re;
            }
        });
    }

    //更新状态为歌手开始
    @RequestMapping("finishState/one")
    ResponseEntity<HttpJson> finishfirstState(@RequestBody String jsonString) {
        return ControllerFreamwork.excecute(jsonString, ProjectFile.class, "ProjectFile:finishState", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                ProjectFile thisFile = (ProjectFile) inObj.getClassObject();
                //1 设置当前文件为ProjectComplete
                projectService.setFileComplete(thisFile);
                //2.获得Pid
                ProjectWorkDetail CeDetail = new ProjectWorkDetail();
                CeDetail.setPId(thisFile.getPId());
                CeDetail.setWorkSC("策划");
                CeDetail = projectService.getOnePDetailByPFid(CeDetail);
                //3.更新ProjectWork
                CeDetail.setPWIsComplete(1);
                projectService.setPWorkComplete(CeDetail);
                //2.更新Pid状态
                ProjectInfo thisInfo = new ProjectInfo();
                thisInfo.setPId(CeDetail.getPId());
                projectService.setProjectState(thisInfo, 2);
                //给歌手发信息
                //2.获得歌手的PWId
                ProjectWorkDetail detail = new ProjectWorkDetail();
                detail.setWorkSC("歌手");
                detail.setPId(thisInfo.getPId());

                detail = projectService.getProjectWorkWithWorkSC(detail);
                thisInfo = projectService.getProjectInfoOne(thisInfo);
                //给歌手发信息
                MessageBox messageBox = new MessageBox();
                messageBox.setMSenderType(3);
                messageBox.setMTitle("项目：" + thisInfo.getPTitle() + "到了您的阶段");
                messageBox.setMThings("您好，您加入的" + messageBox.getMTitle() + "请及时制作并上传您的阶段文件");
                messageBox.setMTarId(detail.getUUuid());
                messageBox.setMSenderId(thisInfo.getCreatUUuid());
                messageBoxService.sendMail(messageBox);
                return re;
            }
        });
    }

    //获得全部的用户文件 根据PFid 查询
    @RequestMapping("AllProjectFile/get")
    ResponseEntity<HttpJson> getAllPWProjectFile(@RequestBody String jsonString) {
        return ControllerFreamwork.excecute(jsonString, ProjectWork.class, "ProjectWork:getAllPFile", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                ProjectWork work = (ProjectWork) inObj.getClassObject();
                //1.获取全部文件
                List<ProjectFile> files = projectService.getMyWorkFile(work);
                //注意 需要设置isAllRepair
                for (ProjectFile elem : files) {
                    //设置歌曲名字
                    String thisPath = elem.getPFPath();
                    String name = thisPath.substring(thisPath.lastIndexOf("/") + 1, thisPath.lastIndexOf("."));
                    DateTime dateTime = new DateTime(name);
                    name = dateTime.toString(StaticVar.getDateFormat());
                    elem.setPFName(name);
                    //检查这个文件是否可以提交
                    boolean isS = checkThisFileCanSubmit(elem);
                    elem.setARapir(isS);
                }
                re.setClassObject(files);
                return re;
            }
        });
    }

    private boolean checkThisFileCanSubmit(ProjectFile elem) {
        //检查
        int allReview = projectFileReviewService.getOneFileAllReview(elem);
        int dealReview = projectFileReviewService.getOneFileDealReview(elem);
        return allReview == dealReview;
    }

    //向策划发布完成策划申请
    @RequestMapping("CompleteState/Apply")
    ResponseEntity<HttpJson> sendApplytoCe(@RequestBody String jsonString) {
        return ControllerFreamwork.excecute(jsonString, ProjectFile.class, "ProjectFile:applytoComplete", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                ProjectFile upDateFile = (ProjectFile) inObj.getClassObject();
                upDateFile.setPFIsUseComplete(1);
                //更新当前状态
                projectService.setFileApply(upDateFile);
                //给策划发信息通知
                ProjectWork thisWork = new ProjectWork();
                thisWork.setPWId(upDateFile.getPWId());
                ProjectWorkDetail ceWork = new ProjectWorkDetail();
                //1.获取当前人的PWork
                thisWork = projectService.getProjectWorkByPWId(thisWork);
                ceWork.setPId(thisWork.getPId());
                ceWork.setWorkSC("策划");
                ceWork = projectService.getProjectWorkWithWorkSC(ceWork);
                ProjectInfo info = new ProjectInfo();
                info.setPId(ceWork.getPId());
                info = projectService.getProjectInfoOne(info);
                //给策划发信息
                MessageBox messageBox = new MessageBox();
                messageBox.setMSenderId(thisWork.getUUuid());
                messageBox.setMTarId(ceWork.getUUuid());
                messageBox.setMSenderType(3);
                messageBox.setMTitle("您的项目" + info.getPTitle() + thisWork.getWorkSC() + "阶段即将完成");
                messageBox.setMThings("策划您好，很高兴的告诉您" + messageBox.getMTitle() + "。请进入相关项目检查提交文件,及时审核");
                messageBox.setMSpcId(upDateFile.getPFId()); //特殊文件设置为待审核文件的PFId
                messageBox.setMCheck(0);
                //进入美工阶段 至项目状态为审查阶段
                if (thisWork.getWorkSC().equals("美工")) {
                    ProjectInfo thisInfo = new ProjectInfo();
                    thisInfo.setPId(thisWork.getPId());
                    projectService.setProjectState(thisInfo, 5);
                    //对项目的最后信息进行修正
                    messageBox.setMTitle("离成功只剩最后一步：您的项目" + info.getPTitle() + "即将全部完成");
                    messageBox.setMThings("策划您好，很高兴的告诉您" + messageBox.getMTitle() + "。请进入相关项目检查全部工种的文件,进行最终的审核。");
                    messageBox.setMCheck(-2);
                }
                messageBoxService.sendMail(messageBox);

                return re;
            }
        });
    }

    //策划获得当前全部可以看到的完成状态文件
    @RequestMapping("CeFile/get")
    ResponseEntity<HttpJson> getAllCeFile(@RequestBody String jsonString) {
        return ControllerFreamwork.excecute(jsonString, ProjectInfoPersonalOriented.class, "ProjectInfoPO:getAllCeFile", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                ProjectInfoPersonalOriented thisInfo = (ProjectInfoPersonalOriented) inObj.getClassObject();
                List<ProjectFile> files = projectService.getAllProjectCeFile(thisInfo);
                for (ProjectFile elem : files) {
                    String thisPath = elem.getPFPath();
                    String name = thisPath.substring(thisPath.lastIndexOf("/") + 1, thisPath.lastIndexOf("."));
                    DateTime dateTime = new DateTime(name);
                    name = elem.getWorkSC() + " 提交时间：" + dateTime.toString(StaticVar.getDateSmallFormat());
                    elem.setARapir(true);
                    elem.setPFName(name);
                }
                re.setClassObject(files);
                return re;
            }
        });
    }

    //对阶段完成的审核
    @RequestMapping("SteteComplete/check")
    ResponseEntity<HttpJson> StateCompleteCheck(@RequestBody String jsonString) {
        return ControllerFreamwork.excecute(jsonString, MessageBox.class, "MessageBox:StateCompleteCheck", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                String checkRe = inObj.getPara("checkRe");
                MessageBox message = (MessageBox) inObj.getClassObject();
                //设置本信息已读
                messageBoxService.setMailisReaded(message);
                if (checkRe.equals("true")) {
                    String pId = projectService.setPFStateComplete(true, message.getMSpcId());
                    //更新阶段
                    ProjectInfo thisInfo = new ProjectInfo();
                    thisInfo.setPId(pId);
                    thisInfo = projectService.getProjectInfoOne(thisInfo);
                    projectService.setProjectState(thisInfo, thisInfo.getPState() + 1);
                    //给下个角色发信息
                    String nextWorkSC = getNextWorkSCByMailTitle(message.getMTitle());
                    ProjectWorkDetail ceWork = new ProjectWorkDetail();
                    ceWork.setPId(pId);
                    ceWork.setWorkSC(nextWorkSC);
                    ceWork = projectService.getProjectWorkWithWorkSC(ceWork);

                    //发信息
                    MessageBox newMessage = new MessageBox();
                    newMessage.setMCheck(-2);
                    newMessage.setMSenderType(3);
                    newMessage.setMTitle("项目：" + thisInfo.getPTitle() + "到了您的阶段");
                    newMessage.setMThings("您好，您加入的" + newMessage.getMTitle() + "。请及时制作并上传您的阶段文件");
                    newMessage.setMTarId(ceWork.getUUuid());
                    newMessage.setMSenderId(message.getMTarId());
                    messageBoxService.sendMail(newMessage);

                } else {
                    //设置当前PFid的Complete为否
                    projectService.setPFStateComplete(false, message.getMSpcId());
                    //回信
                    MessageBox newMessage = new MessageBox();
                    newMessage.setMSenderId(message.getMTarId());
                    newMessage.setMTarId(message.getMSenderId());
                    newMessage.setMTitle("审核结果:" + message.getMTitle());
                    newMessage.setMThings("您好，很高兴收到您提交的文件。很遗憾的告诉您，由于某些原因您的阶段审核没有通过，请及时对相关文件进行修改");
                    newMessage.setMSenderType(3);
                    newMessage.setMCheck(-2);
                    messageBoxService.sendMail(newMessage);
                }

                return re;
            }
        });
    }

    //完成项目
    //设置美工状态
    //设置项目状态
    //社团积分相加
    @RequestMapping("SteteComplete/set")
    ResponseEntity<HttpJson> StateComplete(@RequestBody String jsonString) {
        return ControllerFreamwork.execute(jsonString, "form:StateComplete", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                String Pid = inObj.getPara("Pid");
                //美工完成
                ProjectFile detail = new ProjectFile();
                detail.setPId(Pid);
                detail.setWorkSC("美工");
                detail = projectService.getPFByWorkandPid(detail);
                //把美工也设置为完成
                String pId = projectService.setPFStateComplete(true, detail.getPFId());
                ProjectInfo thisInfo = new ProjectInfo();
                thisInfo.setPId(pId);
                thisInfo = projectService.getProjectInfoOne(thisInfo);
                projectService.setProjectState(thisInfo, 6);
                //社团积分相加
                List<ProjectWorkDetail> list = projectService.getAllProjectWork(thisInfo);
                for (ProjectWorkDetail elem : list) {
                    //查CMid
                    CommityMember member = new CommityMember();
                    member.setUUuid(elem.getUUuid());
                    member.setCid(elem.getCId());
                    member = projectService.getMemByCidAndUUid(member);
                    //获得当前积分
                    ProjectBonus bonus = new ProjectBonus();
                    bonus.setCMid(member.getCMid());
                    bonus = bonusService.getBonusByCMid(bonus);
                    //更新积分
                    int thisScore = bonus.getCBonus();
                    bonus.setCBonus(thisScore + elem.getWorkBonus());
                    bonusService.setBonus(bonus);
                }
                return re;
            }
        });
    }

    @RequestMapping("getAllDynWord")
    ResponseEntity<HttpJson> getAllProjectDyn(@RequestBody String jsonString) {
        return ControllerFreamwork.execute(jsonString, "form:getAllPDyWord", new ControllerFreamwork.ControllerFuntion() {
            @Override
            public HttpJson thisControllerDoing(HttpJson inObj, HttpJson re) throws Exception {
                String Pid = inObj.getPara("Pid");
                List<String> dynList = projectService.getAllPDyn(Pid);
                String ReStr = "";
                for (String elem : dynList) {
                    ReStr += elem + "\n";
                }
                re.setPara("reStr", ReStr);
                return re;
            }
        });
    }

    private String getNextWorkSCByMailTitle(String mTitle) {
        if (mTitle.contains("歌手"))
            return "后期";
        else if (mTitle.contains("后期"))
            return "美工";
        return null;
    }


    //bean管理
    @Autowired
    public ProjectController(ProjectService projectService,
                             UserWorkService workService, MessageBoxService messageBoxService, WorkService findWorkService, ProjectDynamicService projectDynamicService, FileReviewService fileReviewService, ProjectFileReviewService projectFileReviewService, BonusService bonusService) {
        this.projectService = projectService;
        this.userWorkService = workService;
        this.messageBoxService = messageBoxService;
        this.findWorkService = findWorkService;
        this.projectDynamicService = projectDynamicService;
        this.fileReviewService = fileReviewService;
        this.projectFileReviewService = projectFileReviewService;
        this.bonusService = bonusService;
    }
}
