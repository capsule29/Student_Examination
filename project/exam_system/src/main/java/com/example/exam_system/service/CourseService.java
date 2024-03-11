package com.example.exam_system.service;

import com.example.exam_system.mapper.CourseMapper;
import com.example.exam_system.pojo.Course;
import com.example.exam_system.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class CourseService {
    public String selectCourseNameByCourseId(int courseId){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
        String courseName = mapper.selectCourseNameByCourseId(courseId);
        sqlSession.commit();
        sqlSession.close();
        return courseName;
    }
    public boolean addCourse(Course course){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
        int i = mapper.addCourse(course);
        if(i!=0){
            return true;
        }else{
            return  false;
        }
    }

    public List<Course> selectAllCourse(){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
        List<Course> courses = mapper.selectAll();
        sqlSession.commit();
        sqlSession.close();
        return courses;
    }
}
