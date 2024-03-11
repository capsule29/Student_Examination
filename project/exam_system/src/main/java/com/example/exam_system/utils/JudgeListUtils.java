package com.example.exam_system.utils;

import com.example.exam_system.pojo.Judge;
import com.example.exam_system.service.JudgeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JudgeListUtils{
    JudgeService judgeService=new JudgeService();
    TopicTools topicTools=new TopicTools();
    public List<Judge> getJudgeList(int judgeNumber,int courseId){
        List<Judge> judgeList=new ArrayList<>();
        int totalRows = judgeService.countTotalRows();
        Random random=new Random();
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i <judgeNumber ; i++) {
            int rd= random.nextInt(totalRows)+1;
            if(!list.contains(rd)){
                list.add(rd);
            }
        }
        for (Integer integer : list) {
            Judge judge = judgeService.getOneJudgeByJudgeIdAndCourseId(integer,courseId);
            judge.setAnswer("");
            judgeList.add(judge);
        }
        return topicTools.randJudgeList(judgeList);
    }
}
