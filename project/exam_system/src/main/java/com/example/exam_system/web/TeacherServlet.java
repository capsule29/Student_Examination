package com.example.exam_system.web;

import com.alibaba.fastjson.JSON;
import com.example.exam_system.pojo.*;
import com.example.exam_system.service.*;
import com.example.exam_system.utils.TrueOrFalse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/teacher/*")
public class TeacherServlet extends BaseServlet{
    JudgeService judgeService=new JudgeService();
    RadioService radioService=new RadioService();
    MultSelectService multSelectService=new MultSelectService();
    GradeService gradeService=new GradeService();
    TrueOrFalse trueOrFalse=new TrueOrFalse();
    PaperService paperService=new PaperService();
    StudentService studentService=new StudentService();
    ClassService classService=new ClassService();
    CourseService courseService=new CourseService();
    /**
     * 分页查询判断题
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
   public void selectJudgeByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
       BufferedReader reader = request.getReader();
       String s = reader.readLine();
       GetPage getPage = JSON.parseObject(s, GetPage.class);
//       System.out.println(getPage);
       if(getPage!=null){
           List<Judge> judgeList = judgeService.selectJudgeListByPage(getPage.getPage());
           String jsonString = JSON.toJSONString(judgeList);
           response.setContentType("text/json;charset=utf-8");
           response.getWriter().write(jsonString);
       }
   }

    /**
     * 分页查询单选题
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectRadioByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        GetPage getPage = JSON.parseObject(s, GetPage.class);
        if(getPage!=null){
//            List<MultSelect> multSelectList = multSelectService.selectMultSelectListByPage(getPage.getPage());
            List<Radio> radioList = radioService.selectRadioListByPage(getPage.getPage());
            response.setContentType("text/json;charset=utf-8");
            String jsonString = JSON.toJSONString(radioList);
            response.getWriter().write(jsonString);
        }
    }

    /**
     * 分页查询多选题
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectMultSelectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        GetPage getPage = JSON.parseObject(s, GetPage.class);
        if(getPage!=null){
            List<MultSelect> multSelectList = multSelectService.selectMultSelectListByPage(getPage.getPage());
            response.setContentType("text/json;charset=utf-8");
            String jsonString = JSON.toJSONString(multSelectList);
            response.getWriter().write(jsonString);
        }
    }

    /**
     * 查询所有学生的成绩
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectAllGrade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<SendGradeToTeacher> allGradeToTeacher = gradeService.getAllGradeToTeacher();
        response.setContentType("text/json;charset=utf-8");
        String jsonString = JSON.toJSONString(allGradeToTeacher);
        response.getWriter().write(jsonString);
    }

    /**
     * 添加单选题
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addRadio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setCharacterEncoding("utf-8");
       response.setContentType("text/json;charset=utf-8");
        String s = request.getReader().readLine();
        Radio radio = JSON.parseObject(s, Radio.class);
        if(radio!=null) {
            boolean b = radioService.addRadio(radio);
            if(b){
                trueOrFalse.sendMessageTrue(request, response);
            }else{
                trueOrFalse.sendMessageFalse(request, response);
            }
        }
    }

    /**
     * 添加多选题
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addMultSelect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        String s = request.getReader().readLine();
        MultSelect multSelect = JSON.parseObject(s, MultSelect.class);
        if(multSelect!=null){
            boolean b = multSelectService.addMultSelect(multSelect);
            if(b){
                trueOrFalse.sendMessageTrue(request, response);
            }else{
                trueOrFalse.sendMessageFalse(request, response);
            }
        }
    }

    /**
     * 添加判断题
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addJudge(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        String s = request.getReader().readLine();
        Judge judge = JSON.parseObject(s, Judge.class);
        if(judge!=null){
            boolean b = judgeService.addJudge(judge);
            if(b){
                trueOrFalse.sendMessageTrue(request, response);
            }else{
                trueOrFalse.sendMessageFalse(request, response);
            }
        }
    }

    /**
     * 创建试卷
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void createPaper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setCharacterEncoding("utf-8");
       response.setContentType("text/json;charset=utf-8");
        String s = request.getReader().readLine();
//        System.out.println(s);
        Paper paper = JSON.parseObject(s, Paper.class);
        if(paper!=null) {
            int totalQuestionNumber = paper.getJudgeNumber() + paper.getRadioNumber() + paper.getMultSelectNumber();
            double score = 100 * 1.0 / totalQuestionNumber;
            paper.setJudgeScore(score);
            paper.setRadioScore(score);
            paper.setMultSelectScore(score);
            boolean b = paperService.addPaper(paper);
            if(b){
                trueOrFalse.sendMessageTrue(request, response);
            }else {
                trueOrFalse.sendMessageFalse(request, response);
            }
        }
    }

    /**
     * 添加学生
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setCharacterEncoding("utf-8");
       response.setContentType("text/json/charset=utf-8");
        String s = request.getReader().readLine();
        Student student = JSON.parseObject(s, Student.class);
        if(student!=null){
            boolean b = studentService.addStudent(student);
            if(b){
                trueOrFalse.sendMessageTrue(request, response);
            }else{
                trueOrFalse.sendMessageFalse(request, response);
            }
        }
    }

    /**
     * 添加班级
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json/charset=utf-8");
        String s = request.getReader().readLine();
        ClassInfo classInfo = JSON.parseObject(s, ClassInfo.class);
        if(classInfo!=null){
            boolean b = classService.addClass(classInfo);
            if(b){
                trueOrFalse.sendMessageTrue(request, response);
            }else{
                trueOrFalse.sendMessageFalse(request, response);
            }
        }
    }

    /**
     * 添加课程
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json/charset=utf-8");
        String s = request.getReader().readLine();
        Course course = JSON.parseObject(s, Course.class);
        if(course!=null){
            boolean b = courseService.addCourse(course);
            if(b){
                trueOrFalse.sendMessageTrue(request, response);
            }else{
                trueOrFalse.sendMessageFalse(request, response);
            }
        }
    }

    /**
     * 查询所有学生
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectAllStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/json;charset=utf-8");
        List<SendStudent> sendStudentList = studentService.selectAllStudent();
        String jsonString = JSON.toJSONString(sendStudentList);
        response.getWriter().write(jsonString);
    }

    /**
     * 查询所有班级
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectAllClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/json;charset=utf-8");
        List<ClassInfo> classInfos = classService.selectAllClass();
        String jsonString = JSON.toJSONString(classInfos);
        response.getWriter().write(jsonString);
    }

    /**
     * 查询所有科目
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectAllCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/json;charset=utf-8");
        List<Course> courses = courseService.selectAllCourse();
        String jsonString = JSON.toJSONString(courses);
        response.getWriter().write(jsonString);
    }

    /**
     * 查询所有试卷
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectAllPaper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/json;charset=utf-8");
        List<SendTPaper> allPaper = paperService.getAllPaper();
        String jsonString = JSON.toJSONString(allPaper);
        response.getWriter().write(jsonString);
    }

}
