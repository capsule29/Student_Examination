package com.example.exam_system.service;

import com.example.exam_system.mapper.JudgeMapper;
import com.example.exam_system.mapper.MultSelectMapper;
import com.example.exam_system.mapper.PaperMapper;
import com.example.exam_system.mapper.RadioMapper;
import com.example.exam_system.pojo.*;
import com.example.exam_system.utils.JudgeListUtils;
import com.example.exam_system.utils.MultSelectListUtils;
import com.example.exam_system.utils.RadioListUtils;
import com.example.exam_system.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PaperService {
    //根据班级号查询试卷
    List<Paper> selectPaperByClassId(String classId){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PaperMapper mapper = sqlSession.getMapper(PaperMapper.class);
        List<Paper> paperList = mapper.selectPaperByClassId(classId);
        sqlSession.commit();
        sqlSession.close();
        return paperList;
    }
    //试卷根据试卷号和密码进行验证
    public Paper selectPaperByPaerIdAndPassword(Paper paper){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PaperMapper mapper = sqlSession.getMapper(PaperMapper.class);
        Paper paper1 = mapper.selectPaerByPaperIdAndPassword(paper.getPaperId(), paper.getPassword());
        sqlSession.commit();
        sqlSession.close();
        return paper1;
    }

    //获取带有题目的试卷
    public SendDoPaper getPaper(Paper paper){
        JudgeListUtils judgeListUtils=new JudgeListUtils();
        RadioListUtils radioListUtils=new RadioListUtils();
        MultSelectListUtils multSelectListUtils=new MultSelectListUtils();
        SendDoPaper sendDoPaper=new SendDoPaper();
        sendDoPaper.setLimitTime(paper.getLimitTime());
        sendDoPaper.setPaperId(paper.getPaperId());
        List<Judge> judgeList=judgeListUtils.getJudgeList(paper.getJudgeNumber(),paper.getCourseId());
        List<Radio> radioList=radioListUtils.getRadioList(paper.getRadioNumber(),paper.getCourseId());
        List<MultSelect> multSelectList=multSelectListUtils.getMultSelectList(paper.getMultSelectNumber(),paper.getCourseId());
        sendDoPaper.setJudgeList(judgeList);
        sendDoPaper.setRadioList(radioList);
        sendDoPaper.setMultSelectList(multSelectList);
        return sendDoPaper;
    }

    //根据试卷id获取试卷信息
    public Paper getPaperInfoByPaperId(int paperId){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PaperMapper mapper = sqlSession.getMapper(PaperMapper.class);
        Paper paper = mapper.selectPaperByPaperId(paperId);
        sqlSession.commit();
        sqlSession.close();
        return paper;
    }
    //计算单选题所得分数
    public double calRadioScore(List<Radio> radioList,double radioScore){
//        System.out.println(radioList);
        if(radioList==null){
            return 0;
        }
        double radioTotalScore=0;
        int count=0;
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        for (Radio radio : radioList) {
            SqlSession sqlSession = sqlSessionFactory.openSession();
            RadioMapper mapper = sqlSession.getMapper(RadioMapper.class);
            String standardAnswer = mapper.selectAnswerByRadioId(radio.getRadioId());
            sqlSession.commit();
            sqlSession.close();
            if(standardAnswer.equals(radio.getAnswer())){
                count++;
//                radioTotalScore+=radioScore;
            }
        }
        radioTotalScore=count*radioScore;
//        System.out.println(radioScore);
//        System.out.println("r"+count);
        return radioTotalScore;
    }
    public List<String> getMultSelectAnswerList(String answer){
        List<String> answerList=new ArrayList<>();
        String[] split = answer.split(",");
        for (String s : split) {
            answerList.add(s);
        }
        return answerList;
    }
    //计算多选题所得分数
    public double calMultSelectScore(List<GetMultSelectFromWeb> multSelectList,double multSelectScore){
        double multSelectTotalScore=0;
//        System.out.println(multSelectList);
        int count=0;
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        for (GetMultSelectFromWeb getMultSelectFromWeb : multSelectList) {
            SqlSession sqlSession = sqlSessionFactory.openSession();
            MultSelectMapper mapper = sqlSession.getMapper(MultSelectMapper.class);
            String s = mapper.selectAnswerByMultSelectId(getMultSelectFromWeb.getMultSelectId());
            sqlSession.commit();
            sqlSession.close();
            List<String> standardAnswer = getMultSelectAnswerList(s);
            List<String> getAnswer = Arrays.asList(getMultSelectFromWeb.getAnswer());
            if(getAnswer.containsAll(standardAnswer)){
                count++;
//                multSelectTotalScore+=multSelectScore;
                }
        }
        multSelectTotalScore=count*multSelectScore;
//        System.out.println(multSelectScore);
//        System.out.println("m"+count);
        return multSelectTotalScore;
    }
    //计算判断题所得分数
    public double calJudgeScore(List<Judge> judgeList,double judgeScore){
        double judgeTotalScore=0;
//        System.out.println(judgeList);
        if(judgeList==null){
            return 0;
        }
        int count=0;
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        for (Judge judge : judgeList) {
            SqlSession sqlSession = sqlSessionFactory.openSession();
            JudgeMapper mapper = sqlSession.getMapper(JudgeMapper.class);
            String standardAnswer = mapper.selectAnswerByJudgeId(judge.getJudgeId());
            sqlSession.commit();
            sqlSession.close();
            if(standardAnswer.equals(judge.getAnswer())){
                count++;
//                judgeTotalScore+=judgeScore;
            }
        }
        judgeTotalScore=count*judgeScore;
//        System.out.println(judgeTotalScore);
//        System.out.println("j"+count);
        return judgeTotalScore;
    }
    //计算整张试卷的分数
    public double calTotalScore(GetPaperFromWeb getPaperFromWeb){
        Paper paper = getPaperInfoByPaperId(getPaperFromWeb.getPaperId());
        double totalScore=0;
        //计算单选题所获分数
        double radioScore=calRadioScore(getPaperFromWeb.getRadioList(),paper.getRadioScore());
        //计算所选题所获分数
        double multSelectScore=calMultSelectScore(getPaperFromWeb.getMultSelectList(),paper.getMultSelectScore());
        //计算判断题所获分数
        double judgeScore = calJudgeScore(getPaperFromWeb.getJudgeList(), paper.getJudgeScore());
        totalScore=radioScore+multSelectScore+judgeScore;
        GradeService gradeService=new GradeService();
        Grade grade=new Grade();
        grade.setMyGrade(totalScore);
        grade.setCourseId(paper.getCourseId());
        grade.setStudentId(getPaperFromWeb.getuId());
        grade.setPaperId(paper.getPaperId());
        gradeService.addGrade(grade);
        return totalScore;
    }
    public String selectTestTypeByPaperId(int id){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PaperMapper mapper = sqlSession.getMapper(PaperMapper.class);
        String testType = mapper.selectTestTypeByPaperId(id);
        sqlSession.commit();
        sqlSession.close();
        return testType;
    }
    public boolean addPaper(Paper paper){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PaperMapper mapper = sqlSession.getMapper(PaperMapper.class);
        int i = mapper.addPaper(paper);
        sqlSession.commit();
        sqlSession.close();
        if(i!=0){
            return true;
        }else{
            return false;
        }
    }

    public List<SendTPaper> getAllPaper(){
        List<SendTPaper> sendTPaperList=new ArrayList<>();
        CourseService courseService = new CourseService();
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PaperMapper mapper = sqlSession.getMapper(PaperMapper.class);
        List<Paper> paperList = mapper.selectAllPaper();
        sqlSession.commit();
        sqlSession.close();
        for (Paper paper : paperList) {
            SendTPaper sendTPaper=new SendTPaper();
            sendTPaper.setPaperId(paper.getPaperId());
            sendTPaper.setCourseName(courseService.selectCourseNameByCourseId(paper.getCourseId()));
            sendTPaper.setTestName(paper.getTestName());
            sendTPaper.setTestType(paper.getTestType());
            sendTPaper.setExamDate(paper.getExamDate());
            sendTPaper.setLimitTime(paper.getLimitTime());
            sendTPaper.setRadioNumber(paper.getRadioNumber());
            sendTPaper.setRadioScore(paper.getRadioScore());
            sendTPaper.setMultSelectNumber(paper.getMultSelectNumber());
            sendTPaper.setMultSelectScore(paper.getMultSelectScore());
            sendTPaper.setJudgeNumber(paper.getJudgeNumber());
            sendTPaper.setJudgeScore(paper.getJudgeScore());
            sendTPaper.setPassword(paper.getPassword());
            sendTPaper.setClassId(paper.getClassId());
            sendTPaperList.add(sendTPaper);
        }
        return sendTPaperList;
    }
}
