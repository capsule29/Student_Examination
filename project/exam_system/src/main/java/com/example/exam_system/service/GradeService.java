package com.example.exam_system.service;

import com.example.exam_system.mapper.GradeMapper;
import com.example.exam_system.pojo.Grade;
import com.example.exam_system.pojo.SendGradeToTeacher;
import com.example.exam_system.pojo.SendGradetoStudent;
import com.example.exam_system.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GradeService {
    public void addGrade(Grade grade){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        GradeMapper mapper = sqlSession.getMapper(GradeMapper.class);
        int i = mapper.addGrade(grade);
        sqlSession.commit();
        sqlSession.close();
    }
    public List<SendGradetoStudent> getStudentAllScore(String studentId){
        List<SendGradetoStudent> sendGradetoStudentList=new ArrayList<>();
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        GradeMapper mapper = sqlSession.getMapper(GradeMapper.class);
        List<Grade> grades = mapper.selectAllGradeByStudentId(studentId);
        sqlSession.commit();
        sqlSession.close();
        for (Grade grade : grades) {
            SendGradetoStudent sendGradetoStudent=new SendGradetoStudent();
            sendGradetoStudent.setGrade(grade.getMyGrade());
            sendGradetoStudent.setCourseName(new CourseService().selectCourseNameByCourseId(grade.getCourseId()));
            sendGradetoStudent.setTestType(new PaperService().selectTestTypeByPaperId(grade.getPaperId()));
            sendGradetoStudentList.add(sendGradetoStudent);
        }
        return sendGradetoStudentList;
    }
    public List<SendGradeToTeacher> getAllGradeToTeacher(){
        List<SendGradeToTeacher> sendGradeToTeacherList =new ArrayList<>();
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        GradeMapper mapper = sqlSession.getMapper(GradeMapper.class);
        List<Grade> grades = mapper.selectAllGrade();
        for (Grade grade : grades) {
            SendGradeToTeacher sendGradeToTeacher = new SendGradeToTeacher();
            sendGradeToTeacher.setGrade(grade.getMyGrade());
            sendGradeToTeacher.setTestType(new PaperService().selectTestTypeByPaperId(grade.getPaperId()));
            sendGradeToTeacher.setName(new StudentService().selectStudentNameByStudentId(grade.getStudentId()));
            sendGradeToTeacher.setClassId(new StudentService().selectClassIdByStudentId(grade.getStudentId()));
            sendGradeToTeacher.setUtId(grade.getStudentId());
            sendGradeToTeacher.setCourseName(new CourseService().selectCourseNameByCourseId(grade.getCourseId()));
            sendGradeToTeacherList.add(sendGradeToTeacher);
        }
        return sendGradeToTeacherList;
    }
}
