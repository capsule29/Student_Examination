package com.example.exam_system.utils;
import com.example.exam_system.pojo.Radio;
import com.example.exam_system.service.RadioService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RadioListUtils{
    RadioService radioService=new RadioService();
    TopicTools topicTools=new TopicTools();
    public List<Radio> getRadioList(int radioNumber,int courseId){
        List<Radio> radioList=new ArrayList<>();
        int totalRows = radioService.countTotalRows();
        Random random=new Random();
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i <radioNumber ; i++) {
            int rd= random.nextInt(totalRows)+1;
            if(!list.contains(rd)){
                list.add(rd);
            }
        }
        for (Integer integer : list) {
             Radio radio = radioService.getOneRadioByRadioIdAndCourseId(integer,courseId);
             radio.setAnswer("");
            radioList.add(topicTools.randRadioOption(radio));

        }
        return topicTools.randRadioList(radioList);
    }
}
