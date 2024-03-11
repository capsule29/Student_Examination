package com.example.exam_system.web;

import com.alibaba.fastjson.JSON;
import com.example.exam_system.mapper.PaperMapper;
import com.example.exam_system.mapper.StudentMapper;
import com.example.exam_system.pojo.*;
import com.example.exam_system.service.GradeService;
import com.example.exam_system.service.PaperService;
import com.example.exam_system.service.StudentService;
import com.example.exam_system.utils.TrueOrFalse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/student/*")
public class StudentServlet extends BaseServlet{
    StudentService studentService=new StudentService();
    PaperService paperService=new PaperService();
    GradeService gradeService=new GradeService();
    TrueOrFalse trueOrFalse=new TrueOrFalse();

    /**
     * 查询所有代考信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void serachAllTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Student student = JSON.parseObject(s, Student.class);
        if(student!=null) {
            List<SendPaper> sendPaperList = studentService.selectAllTest(student);
            response.setContentType("text/json;charset=utf-8");
            String jsonString = JSON.toJSONString(sendPaperList);
            response.getWriter().write(jsonString);
        }
    }

    /**
     * 考试考试，为学生发布试题
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void startTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Paper paper = JSON.parseObject(s, Paper.class);
        if(paper!=null){
            //这是根据试卷id以及试卷密码在数据库查询到的试卷
            Paper paper1 = paperService.selectPaperByPaerIdAndPassword(paper);
            if(paper1!=null){
                SendDoPaper sendDoPaper = paperService.getPaper(paper1);
                String jsonString = JSON.toJSONString(sendDoPaper);
                response.setContentType("text/json;charset=utf-8");
                response.getWriter().write(jsonString);
            }else {
                String jsonString = JSON.toJSONString("");
                response.getWriter().write(jsonString);
            }
        }
    }

    /**
     * 提交试卷
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void submitTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        GetPaperFromWeb getPaperFromWeb = JSON.parseObject(s, GetPaperFromWeb.class);
        if(getPaperFromWeb!=null){
            double totalScore = paperService.calTotalScore(getPaperFromWeb);
            SendGetScore getScore=new SendGetScore();
            getScore.setGetScore(totalScore);
            String jsonString = JSON.toJSONString(getScore);
            response.getWriter().write(jsonString);
        }
    }

    /**
     * 查询我的考试成绩
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public  void selectMyGrade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        String s = request.getReader().readLine();
        Student student = JSON.parseObject(s, Student.class);
        if(student!=null){
            List<SendGradetoStudent> studentAllScore = gradeService.getStudentAllScore(student.getuId());
            String jsonString = JSON.toJSONString(studentAllScore);
            response.getWriter().write(jsonString);
        }
    }

    /**
     * 退出登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void  logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        String s = request.getReader().readLine();
        Student student = JSON.parseObject(s, Student.class);
        if(student!=null){
            boolean logout = studentService.logout(student);
            if(logout){
                trueOrFalse.sendMessageTrue(request, response);
            }else{
                trueOrFalse.sendMessageFalse(request, response);
            }
        }
    }
}
