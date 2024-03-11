package com.example.exam_system.utils;

import com.example.exam_system.pojo.MultSelect;
import com.example.exam_system.pojo.Radio;
import com.example.exam_system.service.MultSelectService;
import com.example.exam_system.service.RadioService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultSelectListUtils{
    MultSelectService multSelectService=new MultSelectService();
    TopicTools topicTools=new TopicTools();
    public List<MultSelect> getMultSelectList(int multSelectNumber,int courseId){
        List<MultSelect> multSelectList=new ArrayList<>();
        int totalRows = multSelectService.countTotalRows();
        Random random=new Random();
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i <multSelectNumber ; i++) {
            int rd= random.nextInt(totalRows)+1;
            if(!list.contains(rd)){
                list.add(rd);
            }
        }
        for (Integer integer : list) {
           MultSelect multSelect = multSelectService.getOneMultSelectByMultSelectIdAndCourseId(integer,courseId);
           multSelect.setAnswer("");
            multSelectList.add(topicTools.randMultSelectOption(multSelect));
        }
        //System.out.println(multSelectList);
        return topicTools.randMultSelectList(multSelectList);
    }
}
