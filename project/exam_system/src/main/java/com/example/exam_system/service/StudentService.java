package com.example.exam_system.service;

import com.example.exam_system.mapper.StudentMapper;
import com.example.exam_system.pojo.*;
import com.example.exam_system.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    CourseService courseService=new CourseService();
    PaperService paperService=new PaperService();
    public Student login(AccountAndPassword accountAndPassword){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectByStudentIdAndStudentPassword(accountAndPassword.getAccount(),accountAndPassword.getPassword());
        sqlSession.commit();
        if(student!=null){
            int login = mapper.login(student.getuId());
            sqlSession.commit();
        }
        sqlSession.close();
        return student;
    }
    public String selectClassIdByStudentId(String stduentId){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        String classId = mapper.selectClassIdByStudentId(stduentId);
        sqlSession.commit();
        sqlSession.close();
        return classId;
    }

    public List<SendPaper> selectAllTest(Student student) {
        List<SendPaper> sendPaperList=new ArrayList<>();
        String classId = selectClassIdByStudentId(student.getuId());
//        System.out.println(classId);
        List<Paper> paperList = paperService.selectPaperByClassId(classId);
//        System.out.println(paperList);
        for (Paper paper : paperList) {
            SendPaper sendPaper=new SendPaper();
            sendPaper.setPaperId(paper.getPaperId());
            sendPaper.setCourseName(courseService.selectCourseNameByCourseId(paper.getCourseId()));
            sendPaper.setTestName(paper.getTestName());
            sendPaper.setExamDate(paper.getExamDate());
            sendPaper.setTestType(paper.getTestType());
            sendPaper.setLimitTime(paper.getLimitTime());
//            sendPaper.setPassword(paper.getPassword());
            sendPaperList.add(sendPaper);
        }
        return sendPaperList;
    }
    public String selectStudentNameByStudentId(String studentId){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        String studentName = mapper.selectStudentNameByStudentId(studentId);
        sqlSession.commit();
        sqlSession.close();
        return studentName;
    }
    public boolean addStudent(Student student){
        student.setStudentPassword("123456");
        student.setState(0);
        student.setPermission("student");
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        int i = mapper.addStudent(student);
        sqlSession.commit();
        sqlSession.close();
        if(i!=0){
            return true;
        }else{
            return  false;
        }
    }
    public boolean logout(Student student){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        int logout = mapper.logout(student.getuId());
        sqlSession.commit();
        sqlSession.close();
        if(logout!=0){
            return true;
        }else{
            return false;
        }
    }

    public List<SendStudent> selectAllStudent(){
        List<SendStudent> sendStudentList=new ArrayList<>();
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectAllStudent();
        sqlSession.commit();
        sqlSession.close();
        for (Student student : students) {
            SendStudent sendStudent=new SendStudent();
            sendStudent.setuId(student.getuId());
            sendStudent.setClassId(student.getClassId());
            sendStudent.setName(student.getName());
            sendStudentList.add(sendStudent);
        }
        return sendStudentList;
    }
}
