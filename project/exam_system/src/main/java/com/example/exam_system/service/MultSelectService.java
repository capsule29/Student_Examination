package com.example.exam_system.service;

import com.example.exam_system.mapper.MultSelectMapper;
import com.example.exam_system.pojo.MultSelect;
import com.example.exam_system.pojo.Radio;
import com.example.exam_system.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class MultSelectService {

    public int countTotalRows() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MultSelectMapper mapper = sqlSession.getMapper(MultSelectMapper.class);
        int totalRows = mapper.countTotalRows();
        sqlSession.commit();
        sqlSession.close();
        return totalRows;
    }

    public MultSelect getOneMultSelectByMultSelectIdAndCourseId(int id,int courseId) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MultSelectMapper mapper = sqlSession.getMapper(MultSelectMapper.class);
        MultSelect multSelect = mapper.getOneMultSelectById(id,courseId);
        sqlSession.commit();
        sqlSession.close();
        return multSelect;
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

    public List<MultSelect> selectMultSelectListByPage(int page){
        int size=getPageSize();
        int begin;
        begin=(page-1)*size;
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MultSelectMapper mapper = sqlSession.getMapper(MultSelectMapper.class);
        List<MultSelect> multSelectList = mapper.selectByPageAndSize(begin, size);
        sqlSession.commit();
        sqlSession.close();
        return multSelectList;
    }
    public boolean addMultSelect(MultSelect multSelect){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MultSelectMapper mapper = sqlSession.getMapper(MultSelectMapper.class);
        int i = mapper.insertRadio(multSelect);
        sqlSession.commit();
        sqlSession.close();
        if(i!=0){
            return true;
        }else{
            return false;
        }
    }
}
