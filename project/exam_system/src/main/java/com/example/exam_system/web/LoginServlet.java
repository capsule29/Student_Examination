package com.example.exam_system.web;

import com.alibaba.fastjson.JSON;
import com.example.exam_system.pojo.AccountAndPassword;
import com.example.exam_system.pojo.Student;
import com.example.exam_system.pojo.Teacher;
import com.example.exam_system.service.StudentService;
import com.example.exam_system.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends BaseServlet{
    StudentService studentService=new StudentService();
    TeacherService teacherService=new TeacherService();
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        AccountAndPassword accountAndPassword = JSON.parseObject(s, AccountAndPassword.class);
        if(accountAndPassword!=null) {
            Student student = studentService.login(accountAndPassword);
            if (student == null) {
                Teacher teacher = teacherService.login(accountAndPassword);
                String jsonString = JSON.toJSONString(teacher);
                response.getWriter().write(jsonString);
            } else {
                String jsonString = JSON.toJSONString(student);
                response.getWriter().write(jsonString);
            }
        }
    }
}
