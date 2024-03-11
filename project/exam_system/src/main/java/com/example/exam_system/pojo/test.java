package com.example.exam_system.pojo;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
//        Student student = new Student();
////        student.setStudentId("092220121");
//        String jsonString = JSON.toJSONString("");
//        System.out.println(jsonString);
//        Student student1 = JSON.parseObject(jsonString, Student.class);
//        System.out.println(student1);
//        List<String> list1=new ArrayList<>();
//        List<String> list2=new ArrayList<>();
//        list1.add("我");
//        list1.add("不");
//        list1.add("会");
//        list2.add("会");
//        list2.add("不");
//        list2.add("我");
//        System.out.println(list1);
//        System.out.println(list2);
//        System.out.println(list1.containsAll(list2));
        SendGetScore getScore=new SendGetScore();
        getScore.setGetScore(89);
        String jsonString = JSON.toJSONString(getScore);
        System.out.println(jsonString);
        System.out.println((int)(Math.ceil(4*1.0/3)));


    }
}
