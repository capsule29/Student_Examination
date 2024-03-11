package com.example.exam_system.service;

import com.example.exam_system.mapper.TeacherMapper;
import com.example.exam_system.pojo.AccountAndPassword;
import com.example.exam_system.pojo.Teacher;
import com.example.exam_system.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class TeacherService {
    public Teacher login(AccountAndPassword accountAndPassword){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher teacher = mapper.login(accountAndPassword);
        return teacher;
    }
}
