package com.example.exam_system.service;

import com.example.exam_system.mapper.JudgeMapper;
import com.example.exam_system.pojo.Judge;
import com.example.exam_system.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.awt.*;
import java.util.List;

public class JudgeService {
    public int countTotalRows(){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        JudgeMapper mapper = sqlSession.getMapper(JudgeMapper.class);
        int totalRows = mapper.countTotalRows();
        sqlSession.commit();
        sqlSession.close();
        return totalRows;
    }

    public Judge getOneJudgeByJudgeIdAndCourseId(int id,int courseId){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        JudgeMapper mapper = sqlSession.getMapper(JudgeMapper.class);
        Judge judge = mapper.getOneJudgeById(id,courseId);
        sqlSession.commit();
        sqlSession.commit();
        return judge;
    }

    public int getPageSize(){
        int size=8;
        return size;
    }

    public int getMaxPage(){
        int totalRows = countTotalRows();
        int size=getPageSize();
        int maxPage=(int)(Math.ceil(totalRows*1.0/size));
        return maxPage;
    }

    public List<Judge> selectJudgeListByPage(int page) {
//        System.out.println(page);
        int size=getPageSize();
        int begin=(page-1)*size;
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        JudgeMapper mapper = sqlSession.getMapper(JudgeMapper.class);
        List<Judge> judgeList = mapper.selectByPageAndSize(begin, size);
//        System.out.println(judgeList);
        sqlSession.commit();
        sqlSession.close();
        return judgeList;
    }
    public boolean addJudge(Judge judge){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        JudgeMapper mapper = sqlSession.getMapper(JudgeMapper.class);
        int i = mapper.insertJudge(judge);
        sqlSession.commit();
        sqlSession.close();
        if(i!=0){
            return true;
        }else {
            return false;
        }
    }
}
