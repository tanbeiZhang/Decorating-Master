package com.tinymooc.handler.course.controller;

import java.io.BufferedInputStream;

import java.io.OutputStream;

import  java.io.BufferedOutputStream;

import java.util.*;
import javax.servlet.ServletOutputStream;
import java.text.DecimalFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import com.alibaba.asyncload.AsyncLoadConfig;
import com.alibaba.asyncload.AsyncLoadExecutor;
import com.alibaba.asyncload.impl.AsyncLoadEnhanceProxy;
import com.tinymooc.authority.annotation.CheckAuthority;
import com.tinymooc.common.domain.*;
import com.tinymooc.common.tag.pageTag.PageHelper;
import com.tinymooc.handler.attention.service.AttentionService;
import com.tinymooc.handler.course.service.CourseService;
import com.tinymooc.handler.favorite.service.FavoriteService;
import com.tinymooc.handler.label.service.LabelService;
import com.tinymooc.handler.resource.service.ResourceService;
import com.tinymooc.handler.user.service.UserService;
import com.tinymooc.handler.video.service.VideoService;
import com.tinymooc.util.UUIDGenerator;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CourseController {
    private Logger log = LoggerFactory.getLogger(CourseController.class);
    @Autowired
    private VideoService videoService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private LabelService labelService;

    @Autowired
    private UserService userService;

    @Autowired
    private AttentionService attentionService;

    @Autowired
    private FavoriteService  favoriteService;

    private String labels;

    private String previousLabels;

    @RequestMapping("createCoursePage.htm")
    public ModelAndView createCoursePage(HttpServletRequest req, HttpServletResponse res) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DataDic.class)
                .add(Restrictions.eq("dicKey", "专业分类"));
        List<DataDic> dataDicList = (List<DataDic>) courseService.queryAllOfCondition(DataDic.class, detachedCriteria);

        req.setAttribute("dataDicList", dataDicList);
        return new ModelAndView("/course/courseCreate");
    }

    /**
     * 创建裱花
     *
     * @param req
     * @param res
     * @return
     */
    @RequestMapping("createCourse.htm")
    public ModelAndView createCourse(HttpServletRequest req, HttpServletResponse res) {
        // FIXME
        System.out.println("================进入CreateCourse.htm=============");
        User user = (User) req.getSession().getAttribute("user");
        String picUrl = (String)req.getSession().getAttribute("picUrl");
        // FIXME
        System.out.println("=================picUrl="+picUrl);

        String courseTitle = ServletRequestUtils.getStringParameter(req, "courseTitle", "");
        String courseIntro = ServletRequestUtils.getStringParameter(req, "courseIntro", "");
        String type = ServletRequestUtils.getStringParameter(req, "type", "");
        Course course = new Course();
        course.setCourseId(UUIDGenerator.randomUUID());
        course.setApplyDate(new Date());
        course.setCourseIntro(courseIntro);
        course.setCourseState("申请中");
        course.setType(type);
        if (picUrl != null)
            course.setLogoUrl(picUrl);
        else
            course.setLogoUrl("/resource/pic/courseLogo/course1.jpg");
        course.setScanNum(0);
        course.setCourseTitle(courseTitle);
        courseService.save(course);
        UserCourse userCourse = new UserCourse();
        userCourse.setUserCourseId(UUIDGenerator.randomUUID());
        userCourse.setUserPosition("创建者");
        userCourse.setCourse(course);
        userCourse.setUser(user);
        courseService.save(userCourse);
        req.getSession().removeAttribute("picUrl");

        // FIXME
        System.out.println("================结束CreateCourse.htm=============");
        return new ModelAndView("redirect:courseList.htm");
    }


    //裱花搜索
    @RequestMapping("searchCourseFront.htm")
    public ModelAndView searchCourseIndex(HttpServletRequest req, HttpServletResponse res)
    {    List<Course> SlistC = null;

        String path = req.getSession().getServletContext().getRealPath("/");
        System.out.println("路径：" + path);

            System.out.println("开始搜索了吗？");

            String q = req.getParameter("searchValue");
            System.out.println("搜索的内容" + q);
            DecimalFormat df = new DecimalFormat("#0.000");

            if(courseService.createCourseIndex()){

                long begin = new Date().getTime();
                List<Course> list = courseService.getCourses(q);
                System.out.println("输出list数量：" + list.size());
                long end = new Date().getTime();
                double time = (double) (end - begin) / 1000;
                String timeStr = df.format(time);
                DetachedCriteria dc = DetachedCriteria.forClass(Course.class);
                Disjunction disjuncteTitle = Restrictions.disjunction();
                disjuncteTitle.add(Restrictions.eq("courseId","!@#$%^&*()"));
                Set<Course> courseSet =  new HashSet<>();


                //将裱花样式想光的裱花搜索出来
                for(int i =0;i < list.size();i ++)   {
                    Course course = courseService.findById(Course.class,list.get(i).getCourseId()) ;
                    if (course.getCourse() !=null )     {
                        System.out.println( "输出涉及的裱花样式" + course.getCourse().getCourseId())   ;
                        courseSet.add(course.getCourse()) ;
                    }
                    else {
                        System.out.println("输出涉及的裱花" + course.getCourseId());
                        courseSet.add(course) ;
                    }
                }
                List<Course> list2 = new LinkedList<>();
                list2.addAll(courseSet);
                courseSet.clear();

                for(int i =0;i<list2.size();i++) {
                    disjuncteTitle.add(Restrictions.eq("courseId",list2.get(i).getCourseId()));
                }
                dc.add(disjuncteTitle);
                int totalPage=courseService.countTotalPage(dc, 9);
                PageHelper.forPage(totalPage, 9);
                SlistC = (List<Course>)courseService.getByPage(dc,9);

                System.out.println("SlistC.size():"+SlistC.size());

                req.setAttribute("totalTime", timeStr);
                req.setAttribute("searchValue",q);
            }
            return new ModelAndView("/course/searchResultFront","SearchCourselist", SlistC);

    }

    @RequestMapping("createLessonPage.htm")
    public ModelAndView createLessonPage(HttpServletRequest req, HttpServletResponse res) {
        String courseId = ServletRequestUtils.getStringParameter(req, "courseId", "");
        Course course = courseService.findById(Course.class, courseId);
        DetachedCriteria detachedCriteria1 = DetachedCriteria.forClass(UserCourse.class)
                .createCriteria("course")
                .add(Restrictions.eq("course", course));
        List<UserCourse> userCourseList = (List<UserCourse>) courseService.queryAllOfCondition(UserCourse.class, detachedCriteria1);
        // FIXME
        System.out.println("===============userCourseList=" + userCourseList.size());

        int lessons = userCourseList.size();

        req.setAttribute("course", course);
        req.setAttribute("lessons", lessons);
        return new ModelAndView("/course/createLesson");
    }

    @RequestMapping("createLesson.htm")
    public ModelAndView createLesson(HttpServletRequest req, HttpServletResponse res) {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null)
            return new ModelAndView("redirect:login.htm");

       /* AsyncLoadTestService asyncLoadTestService = xxxxx;  //你原先的业务处理主体
        // 初始化config
        AsyncLoadConfig config = new AsyncLoadConfig(3 * 1000l);
        // 初始化executor
        AsyncLoadExecutor executor = new AsyncLoadExecutor(10, 100);
        executor.initital();
        // 初始化proxy
        AsyncLoadEnhanceProxy<AsyncLoadTestService> proxy = new AsyncLoadEnhanceProxy<AsyncLoadTestService>();
        proxy.setService(asyncLoadTestService); //传递你原先的业务对象
        proxy.setConfig(config);
        proxy.setExecutor(executor);

        AsyncLoadTestService service = proxy.getProxy(); //获取到异步并行处理包装过的服务对象*/


        AsyncLoadConfig config = new AsyncLoadConfig(3 * 1000l);
        // 初始化executor
        AsyncLoadExecutor executor = new AsyncLoadExecutor(10, 100);
        executor.initital();
        // 初始化proxy
        AsyncLoadEnhanceProxy<CourseService> proxy = new AsyncLoadEnhanceProxy<CourseService>();
        proxy.setService(courseService);
        proxy.setConfig(config);
        proxy.setExecutor(executor);
        // 执行测试
        CourseService courseService = proxy.getProxy();



        String courseId = ServletRequestUtils.getStringParameter(req, "courseId", "");
        String courseTitle = ServletRequestUtils.getStringParameter(req, "courseTitle", "");
        String courseIntro = ServletRequestUtils.getStringParameter(req, "courseIntro", "");
        String content = ServletRequestUtils.getStringParameter(req, "content", "");
        String lessonNum = ServletRequestUtils.getStringParameter(req, "lessonNum", "");
        Course course = new Course();
        course.setCourseId(UUIDGenerator.randomUUID());
        course.setApplyDate(new Date());
        course.setCourseIntro(courseIntro);
        course.setCourseState("申请中");
        course.setScanNum(0);
        course.setCourseTitle(courseTitle);
        course.setLessonNum(Integer.parseInt(lessonNum));
        course.setCourse(courseService.findById(Course.class, courseId));
        courseService.save(course);

        UserCourse userCourse = new UserCourse();
        userCourse.setUserCourseId(UUIDGenerator.randomUUID());
        userCourse.setUserPosition("创建者");
        userCourse.setCourse(course);
        userCourse.setUser(user);
        courseService.save(userCourse);

        Resource resource = new Resource();
        resource.setResourceId(UUIDGenerator.randomUUID());
        resource.setResourceObject(course.getCourseId());
        courseService.save(resource);

        ImageText imageText = new ImageText();
        imageText.setResourceId(resource.getResourceId());
        imageText.setContent(content);
        imageText.setResource(resource);
        courseService.save(imageText);


        return new ModelAndView("redirect:courseList.htm");
    }

    /**
     * 裱花列表
     *
     * @param req
     * @param res
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("courseList.htm")
    public ModelAndView courseList(HttpServletRequest req, HttpServletResponse res) {
        User user = (User) req.getSession().getAttribute("user");
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserCourse.class)
                .add(Restrictions.eq("user", user))
                .add(Restrictions.eq("userPosition", "创建者"))
                .createCriteria("course")
                .add(Restrictions.isNull("course"));
        int pageSize = 5;
        int totalPage = courseService.countTotalPage(detachedCriteria, pageSize);
        PageHelper.forPage(totalPage, pageSize);
        List<UserCourse> myCreatedCourseList = (List<UserCourse>) courseService.getByPage(detachedCriteria, pageSize);

        int credit = user.getCredit();
        // FIXME
        System.out.println("========credit=====" + credit);

        Level myLevel = userService.getUserLevel(credit);
        System.out.println("===myLevel===" + myLevel.getLv());

        req.setAttribute("myLevel", myLevel);
        req.setAttribute("myCreatedCourseList", myCreatedCourseList);

        return new ModelAndView("/course/myCourse");
    }

    @RequestMapping("courseDetailPage.htm")
    public ModelAndView courseDetailPage(HttpServletRequest req, HttpServletResponse res) {
        // FIXME
        log.info("====================进入courseDetailPage=================");

        String courseId = ServletRequestUtils.getStringParameter(req, "courseId", "");
        Course course = courseService.findById(Course.class, courseId);
        course.setScanNum(course.getScanNum() + 1);
        courseService.update(course);

        User user = (User) req.getSession().getAttribute("user");

        // Test 1
        //查询该裱花的用户裱花信息（能得到创建者信息）
        DetachedCriteria detachedCriteria1 = DetachedCriteria.forClass(UserCourse.class)
                .add(Restrictions.eq("userPosition", "创建者"))
                .add(Restrictions.eq("course", course));
        // currentCourse 针对当前裱花的创建者
        UserCourse currentCourse = courseService.queryAllOfCondition(UserCourse.class, detachedCriteria1).get(0);
        // FIXME
        if (currentCourse.getLearnState() == null) {
            System.out.println("Test 1 if===============currentCourse.getLearnState()=NULL");
        } else {
            System.out.println("Test 1 else===============currentCourse.getLearnState()=" + currentCourse.getLearnState());
        }

        //查询该裱花的所有裱花样式
//		DetachedCriteria criteria2=DetachedCriteria.forClass(UserCourse.class)
//				.add(Restrictions.eq("user", user))
//				.createCriteria("course")
//				.add(Restrictions.eq("course", course))				
//				.addOrder(Order.asc("applyDate"));

        // Test 2
        //按开始学习时间降序排列查询该裱花用户学习状态为"学习中"的用户裱花信息
        DetachedCriteria detachedCriteria2 = DetachedCriteria.forClass(UserCourse.class)
                .add(Restrictions.eq("userPosition", "学员"))
                .add(Restrictions.eq("course", course))
                .add(Restrictions.eq("learnState", "学习中"))
                .addOrder(Order.desc("startDate"));
        // 学员学习中
        List<UserCourse> userLearnCourseList = (List<UserCourse>) courseService.queryAllOfCondition(UserCourse.class, detachedCriteria2);

        // FIXME
        System.out.println("Test 2===============userLearnCourseList .size=" + userLearnCourseList.size());

        // Test 3
        //按开始学习时间降序排列查询该裱花用户学习状态为"已学"的用户裱花信息
        DetachedCriteria detachedCriteria3 = DetachedCriteria.forClass(UserCourse.class)
                .add(Restrictions.eq("userPosition", "学员"))
                .add(Restrictions.eq("course", course))
                .add(Restrictions.eq("learnState", "已学"))
                .addOrder(Order.desc("startDate"));
        // 学员已学
        List<UserCourse> userEndCourseList = (List<UserCourse>) courseService.queryAllOfCondition(UserCourse.class, detachedCriteria3);
        // FIXME
        System.out.println("Test 3===============userEndCourseList.size=" + userEndCourseList.size());
        // 准备学员数据
        int studentNum = userLearnCourseList.size() + userEndCourseList.size();

        // Test 4
        // 查询该裱花创建者其他创建的用户裱花
        DetachedCriteria detachedCriteria4 = DetachedCriteria.forClass(UserCourse.class)
                .add(Restrictions.eq("userPosition", "创建者"))
                .add(Restrictions.eq("user", currentCourse.getUser()));
        // 查询该裱花创建者创建的所有裱花
        List<UserCourse> creatorCourseList = (List<UserCourse>) courseService.queryAllOfCondition(UserCourse.class, detachedCriteria4);
        // 准备裱花数数据
        int creatorCourseNum = creatorCourseList.size();
        // FIXME
        System.out.println("Test 4===============creatorCourseList.size=" + creatorCourseList.size());

        // Test 5
        // 查询关注者为登录用户，被关注者为裱花创建者的关注信息
        // 查询当前用户与创建者的关注信息
        DetachedCriteria detachedCriteria5_1 = DetachedCriteria.forClass(Attention.class)
                .add(Restrictions.eq("userByUserId", user))
                .add(Restrictions.eq("userByAttentionedUserId", currentCourse.getUser()));
        List<Attention> attentionOfCurrentToCreator = (List<Attention>) courseService.queryAllOfCondition(Attention.class, detachedCriteria5_1);
        // 当前用户是否关注创建者
        int isAttention = 0;
        if (!attentionOfCurrentToCreator.isEmpty()) {
            isAttention = 1;
        }
        // 当前用户是否收藏本裱花
        DetachedCriteria detachedCriteria5_2 = DetachedCriteria.forClass(Favorite.class)
                .add(Restrictions.eq("user", user))
                .add(Restrictions.eq("courseId", course.getCourseId()));
        List<Favorite> favoriteOfCurrentCourse = (List<Favorite>) favoriteService.queryAllOfCondition(Favorite.class, detachedCriteria5_2);

        int isFavorite = 0;
        if (!favoriteOfCurrentCourse.isEmpty()) {
            isFavorite = 1;
        }
        // FIXME
        System.out.println("Test 5===============attentionOfCurrentToCreator.size=" + attentionOfCurrentToCreator.size());

        // Test 6-1
        // 查询裱花创建者的关注信息
        // 创建者的粉丝
        DetachedCriteria detachedCriteria6_1 = DetachedCriteria.forClass(Attention.class)
                .add(Restrictions.eq("userByAttentionedUserId", currentCourse.getUser()));
        // 查询创建者的粉丝
        List<Attention> fansOfCreatorList = (List<Attention>) courseService.queryAllOfCondition(Attention.class, detachedCriteria6_1);
        // 准备粉丝数据
        int fansNum = fansOfCreatorList.size();

        // FIXME
        System.out.println("Test 6-1 ===============fansOfCreatorList.size=" + fansOfCreatorList.size());

        // Test 6-2
        // 创建者的关注
        DetachedCriteria detachedCriteria6_2 = DetachedCriteria.forClass(Attention.class)
                .add(Restrictions.eq("userByUserId", currentCourse.getUser()));
        // 查询创建者的关注
        List<Attention> followOfCreatorList = (List<Attention>) courseService.queryAllOfCondition(Attention.class, detachedCriteria6_2);
        // 准备关注数据
        int followNum = followOfCreatorList.size();
        // FIXME
        System.out.println("Test 6-2===============followOfCreatorList =" + followOfCreatorList.size());

        // Test 7
        // 该门裱花的标签
        DetachedCriteria detachedCriteria7 = DetachedCriteria.forClass(LabelObject.class)
                .add(Restrictions.eq("objectType", "course"))
                .add(Restrictions.eq("objectId", currentCourse.getCourse().getCourseId()));
        // 该门裱花的标签
        List<LabelObject> labelObjectList = (List<LabelObject>) courseService.queryAllOfCondition(LabelObject.class, detachedCriteria7);
        // FIXME
        System.out.println("Test 7===============labelObjectList =" + labelObjectList.size());

        // Test 8
        // 该门裱花的评分同时写回Course表的totalMark中
        DetachedCriteria detachedCriteria8 = DetachedCriteria.forClass(Grade.class).add(Restrictions.eq("gradeObject", courseId));
        List<Grade> gradeList = courseService.queryAllOfCondition(Grade.class, detachedCriteria8);
        double userGrade = 0.0;
        if (gradeList.size() != 0) {
            // 页面限制位数
            userGrade = courseService.queryGrade(courseId);
        } else {
            userGrade = 0.0;
        }
        course.setTotalMark(userGrade);
        courseService.update(course);
        // FIXME
        System.out.println("Test 8===============userGrade=" + userGrade);


        // Test 9
        // 查询当前用户针对本裱花的学习状态
        DetachedCriteria detachedCriteria9 = DetachedCriteria.forClass(UserCourse.class)
                .add(Restrictions.eq("user", user))
                .add(Restrictions.eq("course", course))
                .add(Restrictions.eq("userPosition", "学员"));
        String currentCourseState = "开始学习";
        List<UserCourse> tempList = (List<UserCourse>) courseService.queryAllOfCondition(UserCourse.class, detachedCriteria9);
        // FIXME
        System.out.println("Test 9===============tempList.size=" + tempList.size());

        if (!tempList.isEmpty()) {
            currentCourseState = tempList.get(0).getLearnState();
        }

        // FIXME
        System.out.println("==============程序执行到CourseController ->Test9===============");

        // Test 10
        //查询该裱花对应所有的裱花样式及用户学习状态
        DetachedCriteria detachedCriteria10 = DetachedCriteria.forClass(Course.class)
                .add(Restrictions.eq("course", course))
                .addOrder(Order.asc("applyDate"))
                .add(Restrictions.eq("courseState", "批准"));
        List<Course> tempLessonList = (List<Course>) courseService.queryAllOfCondition(Course.class, detachedCriteria10);
        // FIXME
        System.out.println("Test10 ===============tempLessonList.size=" + tempLessonList.size());

        // 裱花样式
        List<UserCourse> lessonList = new ArrayList<UserCourse>();
        for (int i = 0; i < tempLessonList.size(); i++) {
            String currentLessonState = "未学";
            UserCourse newCourse = new UserCourse();
            DetachedCriteria detachedCriteria11 = DetachedCriteria.forClass(UserCourse.class)
                    .add(Restrictions.eq("user", user))
                    .add(Restrictions.eq("course", tempLessonList.get(i)))
                    .add(Restrictions.eq("userPosition", "学员"));
            List<UserCourse> localTempCourseList = (List<UserCourse>) courseService.queryAllOfCondition(UserCourse.class, detachedCriteria11);
            // FIXME
            System.out.println("Test11 ===============localTempCourseList=" + localTempCourseList.size());

            if (!localTempCourseList.isEmpty()) {
                currentLessonState = localTempCourseList.get(0).getLearnState();
            }
            newCourse.setCourse(tempLessonList.get(i));
            newCourse.setLearnState(currentLessonState);
            lessonList.add(newCourse);
        }
        // Test UserCourseList
        // FIXME
        System.out.println("Test 章节===============lessonList=" + lessonList.size());

        // 准备章节数据
        int lessonNum = lessonList.size();
        // Test12
        // FIXME
        System.out.println("Test 12=============lessonNum=" + lessonNum);

        int picSuffix = 0;
        if (currentCourse.getCourse().getCourseId().length() <= 2) {
            picSuffix = Integer.parseInt(currentCourse.getCourse().getCourseId());
        } else {
            picSuffix = Integer.parseInt(currentCourse.getCourse().getCourseId().substring(0, 7), 16) % 10;
        }

        // 封装信息
        req.setAttribute("currentCourse", currentCourse);
        req.setAttribute("fansNum", fansNum);
        req.setAttribute("followNum", followNum);
        req.setAttribute("userGrade", userGrade);
        req.setAttribute("studentNum", studentNum);
        req.setAttribute("creatorCourseNum", creatorCourseNum);
        req.setAttribute("isAttention", isAttention);
        req.setAttribute("isFavorite", isFavorite);
        req.setAttribute("userLearnCourseList", userLearnCourseList);
        req.setAttribute("userEndCourseList", userEndCourseList);
        req.setAttribute("user", user);
        req.setAttribute("lessonNum", lessonNum);
        req.setAttribute("labelList", labelObjectList);
        req.setAttribute("currentCourseState", currentCourseState);
        req.setAttribute("lessonList", lessonList);
        return new ModelAndView("/course/courseDetailPage");
    }

    /**
     * 管理裱花
     *
     * @param req
     * @param res
     * @return
     */
    @RequestMapping("manageCoursePage.htm")
    public ModelAndView manageCoursePage(HttpServletRequest req, HttpServletResponse res) {
        String courseId = ServletRequestUtils.getStringParameter(req, "courseId", "");
        Course course = courseService.findById(Course.class, courseId);
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DataDic.class)
                .add(Restrictions.eq("dicKey", "专业分类"));
        List<DataDic> dataDicList = (List<DataDic>) courseService.queryAllOfCondition(DataDic.class, detachedCriteria);

        req.setAttribute("course", course);
        req.setAttribute("dataDicList", dataDicList);
        return new ModelAndView("/course/manageCourse");
    }

    @RequestMapping("manageCourse.htm")
    public ModelAndView manageCourse(HttpServletRequest req, HttpServletResponse res) {
        String courseTitle = ServletRequestUtils.getStringParameter(req, "courseTitle", "");
        String courseIntro = ServletRequestUtils.getStringParameter(req, "courseIntro", "");
        String type = ServletRequestUtils.getStringParameter(req, "type", "");
        String courseId = ServletRequestUtils.getStringParameter(req, "courseId", "");
        Course course = courseService.findById(Course.class, courseId);
        course.setCourseTitle(courseTitle);
        course.setCourseIntro(courseIntro);
        course.setType(type);
        courseService.update(course);
        return new ModelAndView("/include/outSuccess");
    }


    @RequestMapping("lessonPage.htm")
    public ModelAndView lessonPage(HttpServletRequest req, HttpServletResponse res) {
        // FIXME
        log.info("==================进入lessonPage===============");
        String childrenId = ServletRequestUtils.getStringParameter(req, "childrenId", "");

        // FIXME
        log.info("==================childrenId={}", childrenId);

        User user = (User) req.getSession().getAttribute("user");
        // 裱花样式
        Course lesson = courseService.findById(Course.class, childrenId);

        // FIXME
        log.info("==================lesson.title={}", lesson.getCourseTitle());

        //1查询创建者
        DetachedCriteria detachedCriteria1 = DetachedCriteria.forClass(UserCourse.class)
                .add(Restrictions.eq("course", lesson.getCourse()))
                .add(Restrictions.eq("userPosition", "创建者"));
        List<UserCourse> queryTempList1 = (List<UserCourse>) courseService.queryAllOfCondition(UserCourse.class, detachedCriteria1);
        UserCourse currentCourse = queryTempList1.get(0);

        // 2 查询本裱花学习中
        //按开始学习时间降序排列查询该裱花用户学习状态为"学习中"的用户裱花信息
        DetachedCriteria detachedCriteria2 = DetachedCriteria.forClass(UserCourse.class)
                .add(Restrictions.eq("userPosition", "学员"))
                .add(Restrictions.eq("course", lesson.getCourse()))
                .add(Restrictions.eq("learnState", "学习中"))
                .addOrder(Order.desc("startDate"));
        List<UserCourse> userLearnCourseList = (List<UserCourse>) courseService.queryAllOfCondition(UserCourse.class, detachedCriteria2);

        // 3查询本裱花已学
        //按开始学习时间降序排列查询该裱花用户学习状态为"已学"的用户裱花信息
        DetachedCriteria detachedCriteria3 = DetachedCriteria.forClass(UserCourse.class)
                .add(Restrictions.eq("userPosition", "学员"))
                .add(Restrictions.eq("course", lesson.getCourse()))
                .add(Restrictions.eq("learnState", "已学"))
                .addOrder(Order.desc("startDate"));
        List<UserCourse> userEndCourseList = (List<UserCourse>) courseService.queryAllOfCondition(UserCourse.class, detachedCriteria3);

        // Test 4
        // 查询当前用户与创建者的关注信息
        DetachedCriteria detachedCriteria4 = DetachedCriteria.forClass(Attention.class)
                .add(Restrictions.eq("userByUserId", user))
                .add(Restrictions.eq("userByAttentionedUserId", currentCourse.getUser()));
        List<Attention> attentionOfCurrentToCreator = (List<Attention>) courseService.queryAllOfCondition(Attention.class, detachedCriteria4);
        // 当前用户是否关注创建者
        int isAttention = 0;
        if (!attentionOfCurrentToCreator.isEmpty()) {
            isAttention = 1;
        }
        // FIXME
        System.out.println("Test 4===============attentionOfCurrentToCreator.size=" + attentionOfCurrentToCreator.size());

        // Test 5-1
        // 创建者的粉丝
        DetachedCriteria detachedCriteria5_1 = DetachedCriteria.forClass(Attention.class)
                .add(Restrictions.eq("userByAttentionedUserId", currentCourse.getUser()));
        // 查询创建者的粉丝
        List<Attention> fansOfCreatorList = (List<Attention>) courseService.queryAllOfCondition(Attention.class, detachedCriteria5_1);
        // 准备粉丝数据
        int fansNum = fansOfCreatorList.size();

        // FIXME
        System.out.println("Test 5-1 ===============fansOfCreatorList.size=" + fansOfCreatorList.size());

        // Test 5-2
        // 创建者的关注
        DetachedCriteria detachedCriteria5_2 = DetachedCriteria.forClass(Attention.class)
                .add(Restrictions.eq("userByUserId", currentCourse.getUser()));
        // 查询创建者的关注
        List<Attention> followOfCreatorList = (List<Attention>) courseService.queryAllOfCondition(Attention.class, detachedCriteria5_2);
        // 准备关注数据
        int followNum = followOfCreatorList.size();
        // FIXME
        System.out.println("Test 5-2===============followOfCreatorList =" + followOfCreatorList.size());

        //6-评论信息
        DetachedCriteria detachedCriteria6 = DetachedCriteria.forClass(Comment.class)
                .add(Restrictions.eq("commentObject", childrenId))
                .add(Restrictions.isNull("comment"))
                .addOrder(Order.asc("commentDate"));
        List<Comment> singleCommentList = (List<Comment>) courseService.queryAllOfCondition(Comment.class, detachedCriteria6);

        DetachedCriteria detachedCriteria7 = DetachedCriteria.forClass(Comment.class)
                .add(Restrictions.eq("commentObject", childrenId))
                .add(Restrictions.isNotNull("comment"))
                .addOrder(Order.asc("commentDate"));
        List<Comment> nestedCommentList = (List<Comment>) courseService.queryAllOfCondition(Comment.class, detachedCriteria7);


        // 7-裱花样式资源信息
        DetachedCriteria detachedCriteria8 = DetachedCriteria.forClass(Resource.class)
                .add(Restrictions.eq("resourceObject", childrenId));
        List<Resource> resources = (List<Resource>) courseService.queryAllOfCondition(Resource.class, detachedCriteria8);
        Resource resource = resources.get(0);

        // 8-裱花对应的裱花样式列表
        DetachedCriteria detachedCriteria9 = DetachedCriteria.forClass(Course.class)
                .add(Restrictions.eq("course", currentCourse.getCourse()))
                .addOrder(Order.asc("lessonNum"));
        List<Course> lessonList = (List<Course>) courseService.queryAllOfCondition(Course.class, detachedCriteria9);

        // 9-当前用户对于本裱花样式的学习状态
        DetachedCriteria detachedCriteria10 = DetachedCriteria.forClass(UserCourse.class)
                .add(Restrictions.eq("user", user))
                .add(Restrictions.eq("course", lesson))
                .add(Restrictions.eq("userPosition", "学员"));
        String lessonLearnState = "开始学习";
        List<UserCourse> queryTempList2 = (List<UserCourse>) courseService.queryAllOfCondition(UserCourse.class, detachedCriteria10);
        if (!queryTempList2.isEmpty()) {
            lessonLearnState = queryTempList2.get(0).getLearnState();
        }

        int studentNum = userLearnCourseList.size() + userEndCourseList.size();
        lesson.setScanNum(lesson.getScanNum() + 1);

        // Test14 准备TencentVideoId
        String  resourceObject = courseService.findById(Course.class, childrenId).getCourseId();
        DetachedCriteria detachedCriteria14 = DetachedCriteria.forClass(Resource.class)
                .add(Restrictions.eq("resourceObject", resourceObject));
        Resource temResource = resourceService.queryAllOfCondition(Resource.class, detachedCriteria14).get(0);
        String fileId = videoService.findById(Video.class, temResource.getResourceId()).getTencentVideoId();

        // FIXME
        System.out.println("======================resourceObject="+resourceObject);
        // FIXME
        System.out.println("======================fileId="+fileId);

        // FIXME
        log.info("==============程序执行到此============");
        req.setAttribute("lesson", lesson);
        req.setAttribute("currentCourse", currentCourse);
        req.setAttribute("userLearnCourseList", userLearnCourseList);
        req.setAttribute("userEndCourseList", userEndCourseList);
        req.setAttribute("fansNum", fansNum);
        req.setAttribute("followNum", followNum);
        req.setAttribute("studentNum", studentNum);
        req.setAttribute("isAttention", isAttention);
        req.setAttribute("learning", userLearnCourseList.size());
        req.setAttribute("learned", userEndCourseList.size());
        req.setAttribute("user", user);
        req.setAttribute("singleCommentList", singleCommentList);
        req.setAttribute("nestedCommentList", nestedCommentList);
        req.setAttribute("commentNum", singleCommentList.size() + nestedCommentList.size());
        req.setAttribute("resource", resource);
        req.setAttribute("lessonList", lessonList);
        req.setAttribute("lessonNum", lessonList.size());
        req.setAttribute("lessonLearnState", lessonLearnState);
        return new ModelAndView("/course/lesson", "fileId", fileId);
    }

//文件下载
    @RequestMapping("download.htm")
    public ResponseEntity<byte[]> download(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String fileId = ServletRequestUtils.getStringParameter(req, "fileId", "");
        String DDMName = ServletRequestUtils.getStringParameter(req, "DDMName", "");


        System.out.println("fileId 路径" + fileId);
        String path = req.getSession().getServletContext().getRealPath("/");
        System.out.println("路径：" + path);
        path = path +"/" +  fileId;
      //  String path="D:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\springMVC\\WEB-INF\\upload\\图片10（定价后）.xlsx";
        File file=new File(path);
        HttpHeaders headers = new HttpHeaders();
        String fileName=new String((DDMName+".gcode").getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);


       /* //ÉèÖÃÏàÓ¦ÀàÐÍapplication/octet-stream
        resp.setContentType("application/x-msdownload");
        //ÉèÖÃÍ·ÐÅÏ¢
        resp.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\"");
        InputStream inputStream = new FileInputStream(file);
        ServletOutputStream ouputStream = resp.getOutputStream();
        byte b[] = new byte[1024];
        int n ;
        while((n = inputStream.read(b)) != -1){
            ouputStream.write(b,0,n);
        }
        //¹Ø±ÕÁ÷¡¢ÊÍ·Å×ÊÔ´
        ouputStream.close();
        inputStream.close();
*/

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }


    @RequestMapping("printFileDownload.htm")
    public ModelAndView printFileDownload(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        try{
            String path = req.getSession().getServletContext().getRealPath("/");
            System.out.println("路径：" + path);
            path = path + "/upfiles/folder/20151220/145059321186741.jpg";
            File file = new File(path);
            String filename = file.getName();
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            resp.reset();

            // 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
            resp.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.replaceAll(" ", "").getBytes("utf-8"),"iso8859-1"));
            resp.addHeader("Content-Length", "" + file.length());
            OutputStream os = new BufferedOutputStream(resp.getOutputStream());
            resp.setContentType("multipart/form-data");
            os.write(buffer);// 输出文件
            os.flush();
            os.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return new ModelAndView("");
    }

    @CheckAuthority(name = "回复话题")
    @RequestMapping("createReply.htm")
    public ModelAndView createReply(HttpServletRequest req, HttpServletResponse res) {
        String courseTimeId = ServletRequestUtils.getStringParameter(req, "courseTimeId", "");
        String content = ServletRequestUtils.getStringParameter(req, "content", "");
        String parentId = ServletRequestUtils.getStringParameter(req, "parentId", "");
        User user = (User) req.getSession().getAttribute("user");

        Comment comment = new Comment();
        comment.setCommentId(UUIDGenerator.randomUUID());
        comment.setCommentDate(new Date());
        comment.setCommentContent(content);
        comment.setCommentObject(courseTimeId);
        comment.setUser(user);
        comment.setType("裱花样式");
        if (!parentId.equals(null)) {
            comment.setComment(courseService.findById(Comment.class, parentId));
        }
        courseService.save(comment);
        return new ModelAndView("redirect:lessonPage.htm?childrenId=" + courseTimeId);

    }

    @RequestMapping("startLearn.htm")
    public ModelAndView startLearn(HttpServletRequest req, HttpServletResponse res) {
        // 本裱花样式学习状态
        log.info("==============进入startLearn===========");
        String courseId = ServletRequestUtils.getStringParameter(req, "courseId", "");
        User user = (User) req.getSession().getAttribute("user");
        Course course = courseService.findById(Course.class, courseId);
        UserCourse userCourse = new UserCourse();
        userCourse.setCourse(course);
        userCourse.setLearnState("学习中");
        userCourse.setStartDate(new Date());
        userCourse.setUser(user);
        userCourse.setUserCourseId(UUIDGenerator.randomUUID());
        userCourse.setUserPosition("学员");
        courseService.save(userCourse);
        return new ModelAndView("redirect:lessonPage.htm?childrenId=" + courseId);
    }

    @RequestMapping("startStudy.htm")
    public ModelAndView startStudy(HttpServletRequest req, HttpServletResponse res) {
        // 本裱花的学习状态
        String courseId = ServletRequestUtils.getStringParameter(req, "courseId", "");
        User user = (User) req.getSession().getAttribute("user");
        Course course = courseService.findById(Course.class, courseId);
        UserCourse userCourse = new UserCourse();
        userCourse.setCourse(course);
        userCourse.setLearnState("学习中");
        userCourse.setStartDate(new Date());
        userCourse.setUser(user);
        userCourse.setUserCourseId(UUIDGenerator.randomUUID());
        userCourse.setUserPosition("学员");
        courseService.save(userCourse);
        return new ModelAndView("redirect:courseDetailPage.htm?courseId=" + courseId);

    }

    @RequestMapping("haveLeaned.htm")
    public ModelAndView haveLeaned(HttpServletRequest req, HttpServletResponse res) {
        String courseId = ServletRequestUtils.getStringParameter(req, "courseId", "");
        User user = (User) req.getSession().getAttribute("user");
        Course course = courseService.findById(Course.class, courseId);
        DetachedCriteria criteria = DetachedCriteria.forClass(UserCourse.class)
                .add(Restrictions.eq("user", user))
                .add(Restrictions.eq("course", course))
                .add(Restrictions.eq("userPosition", "学员"));
        List<UserCourse> courses = (List<UserCourse>) courseService.queryAllOfCondition(UserCourse.class, criteria);
        UserCourse userCourse = courses.get(0);
        userCourse.setLearnState("已学");
        courseService.update(userCourse);
        return new ModelAndView("redirect:lessonPage.htm?childrenId=" + courseId);
    }
}


