package com.example.exam_system.pojo;

import java.util.List;

//带有题目的试卷
public class SendDoPaper {
    private int paperId;//试卷id
    private int limitTime;//限制考试时长
    private List<Radio> radioList;//单选题集合
    private List<MultSelect> multSelectList;//多选题集合
    private List<Judge> judgeList;//判断题集合

    @Override
    public String toString() {
        return "SendDoPaper{" +
                "paeprId=" + paperId +
                ", limitTime=" + limitTime +
                ", radioList=" + radioList +
                ", multSelectList=" + multSelectList +
                ", judgeList=" + judgeList +
                '}';
    }

//    public int getPaeprId() {
//        return paperId;
//    }

//    public void setPaeprId(int paeprId) {

    public int getPaperId() {
        return paperId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }
//        this.paperId = paeprId;
//    }

    public int getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(int limitTime) {
        this.limitTime = limitTime;
    }

    public List<Radio> getRadioList() {
        return radioList;
    }

    public void setRadioList(List<Radio> radioList) {
        this.radioList = radioList;
    }

    public List<MultSelect> getMultSelectList() {
        return multSelectList;
    }

    public void setMultSelectList(List<MultSelect> multSelectList) {
        this.multSelectList = multSelectList;
    }

    public List<Judge> getJudgeList() {
        return judgeList;
    }

    public void setJudgeList(List<Judge> judgeList) {
        this.judgeList = judgeList;
    }
}
