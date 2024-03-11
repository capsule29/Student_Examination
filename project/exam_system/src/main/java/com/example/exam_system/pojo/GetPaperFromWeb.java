package com.example.exam_system.pojo;

import java.util.List;

public class GetPaperFromWeb {
    private int paperId;//试卷编号
    private  String uId; //学号
    private List<Radio> radioList;//单选题
    private List<Judge> judgeList;//判断题
    private List<GetMultSelectFromWeb> multSelectList;//多选题

    @Override
    public String toString() {
        return "GetPaperFromWeb{" +
                "paperId=" + paperId +
                ", uId='" + uId + '\'' +
                ", radioList=" + radioList +
                ", judgeList=" + judgeList +
                ", multSelectList=" + multSelectList +
                '}';
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public int getPaperId() {
        return paperId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    public List<Radio> getRadioList() {
        return radioList;
    }

    public void setRadioList(List<Radio> radioList) {
        this.radioList = radioList;
    }

    public List<Judge> getJudgeList() {
        return judgeList;
    }

    public void setJudgeList(List<Judge> judgeList) {
        this.judgeList = judgeList;
    }

    public List<GetMultSelectFromWeb> getMultSelectList() {
        return multSelectList;
    }

    public void setMultSelectList(List<GetMultSelectFromWeb> multSelectList) {
        this.multSelectList = multSelectList;
    }
}
