package com.example.exam_system.service;

import com.example.exam_system.mapper.RadioMapper;
import com.example.exam_system.pojo.Judge;
import com.example.exam_system.pojo.Radio;
import com.example.exam_system.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.Optional;

public class RadioService {
    public int countTotalRows() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RadioMapper mapper = sqlSession.getMapper(RadioMapper.class);
        int totalRows = mapper.countTotalRows();
        sqlSession.commit();
        sqlSession.close();
        return totalRows;
    }

    public Radio getOneRadioByRadioIdAndCourseId(int id,int courseId) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RadioMapper mapper = sqlSession.getMapper(RadioMapper.class);
        Radio radio = mapper.getOneRadioById(id,courseId);
        sqlSession.commit();
        sqlSession.close();
        return  radio;
    }

    public int getPageSize(){
        int size=8;
        return size;
    }
    public List<Radio> selectRadioListByPage(int page){
        int size=getPageSize();
        int begin=size*(page-1);
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RadioMapper mapper = sqlSession.getMapper(RadioMapper.class);
        List<Radio> radioList = mapper.selectByPageAndSize(begin, size);
        sqlSession.commit();
        sqlSession.close();
        return  radioList;
    }

    public int getMaxPage(){
        int totalRows = countTotalRows();
        int size=getPageSize();
        int maxPage=(int)(Math.ceil(totalRows*1.0/size));
        return maxPage;
    }

    public boolean addRadio(Radio radio){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RadioMapper mapper = sqlSession.getMapper(RadioMapper.class);
        int i = mapper.insertRadio(radio);
        sqlSession.commit();
        sqlSession.close();
        if(i!=0){
            return true;
        }else{
            return false;
        }
    }

}
