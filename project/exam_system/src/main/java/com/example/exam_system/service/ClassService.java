package com.example.exam_system.service;

import com.example.exam_system.mapper.ClassInfoMapper;
import com.example.exam_system.pojo.ClassInfo;
import com.example.exam_system.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ClassService {
    public boolean addClass(ClassInfo classInfo){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassInfoMapper mapper = sqlSession.getMapper(ClassInfoMapper.class);
        int i = mapper.addClass(classInfo);
        if(i!=0){
            return true;
        }else{
            return false;
        }
    }
    public List<ClassInfo> selectAllClass(){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ClassInfoMapper mapper = sqlSession.getMapper(ClassInfoMapper.class);
        List<ClassInfo> classInfos = mapper.selectAllClass();
        sqlSession.commit();
        sqlSession.close();
        return classInfos;
    }
}
