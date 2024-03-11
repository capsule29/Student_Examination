package com.example.exam_system.utils;

import com.example.exam_system.pojo.Judge;
import com.example.exam_system.pojo.MultSelect;
import com.example.exam_system.pojo.Radio;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TopicTools {
    public Radio randRadioOption(@org.jetbrains.annotations.NotNull Radio radio) {
        Random random = new Random(100);
        List<String> optionList = new ArrayList<>();
        optionList.add(radio.getOptionA());
        optionList.add(radio.getOptionB());
        optionList.add(radio.getOptionC());
        optionList.add(radio.getOptionD());
        Collections.shuffle(optionList, random);
        radio.setOptionA(optionList.get(0));
        radio.setOptionB(optionList.get(1));
        radio.setOptionC(optionList.get(2));
        radio.setOptionD(optionList.get(3));
        return radio;
    }

    public List<Radio> randRadioList(List<Radio> radioList) {
        Random random = new Random(100);
        Collections.shuffle(radioList, random);
        return radioList;
    }

    public MultSelect randMultSelectOption(@org.jetbrains.annotations.NotNull MultSelect multSelect) {
        Random random = new Random(100);
        List<String> optionList = new ArrayList<>();
        optionList.add(multSelect.getOptionA());
        optionList.add(multSelect.getOptionB());
        optionList.add(multSelect.getOptionC());
        optionList.add(multSelect.getOptionD());
        optionList.add(multSelect.getOptionE());
        Collections.shuffle(optionList, random);
        multSelect.setOptionA(optionList.get(0));
        multSelect.setOptionB(optionList.get(1));
        multSelect.setOptionC(optionList.get(2));
        multSelect.setOptionD(optionList.get(3));
        multSelect.setOptionE(optionList.get(4));
        return multSelect;
    }

    public List<MultSelect> randMultSelectList(List<MultSelect> multSelectList){
        Random random = new Random(100);
        Collections.shuffle(multSelectList,random);
        return multSelectList;
    }

    public List<Judge> randJudgeList(List<Judge> judgeList){
        Random random=new Random(100);
        Collections.shuffle(judgeList,random);
        return judgeList;
    }
}
