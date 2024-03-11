package com.example.exam_system.pojo;

public class SendTPaper {
    private int paperId;//试卷编号
    private String courseName;//课程名

    @Override
    public String toString() {
        return "SendTPaper{" +
                "paperId=" + paperId +
                ", courseName='" + courseName + '\'' +
                ", testName='" + testName + '\'' +
                ", testType='" + testType + '\'' +
                ", examDate='" + examDate + '\'' +
                ", limitTime=" + limitTime +
                ", radioNumber=" + radioNumber +
                ", radioScore=" + radioScore +
                ", multSelectNumber=" + multSelectNumber +
                ", multSelectScore=" + multSelectScore +
                ", judgeNumber=" + judgeNumber +
                ", judgeScore=" + judgeScore +
                ", password='" + password + '\'' +
                ", classId='" + classId + '\'' +
                '}';
    }

    public int getPaperId() {
        return paperId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public int getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(int limitTime) {
        this.limitTime = limitTime;
    }

    public int getRadioNumber() {
        return radioNumber;
    }

    public void setRadioNumber(int radioNumber) {
        this.radioNumber = radioNumber;
    }

    public double getRadioScore() {
        return radioScore;
    }

    public void setRadioScore(double radioScore) {
        this.radioScore = radioScore;
    }

    public int getMultSelectNumber() {
        return multSelectNumber;
    }

    public void setMultSelectNumber(int multSelectNumber) {
        this.multSelectNumber = multSelectNumber;
    }

    public double getMultSelectScore() {
        return multSelectScore;
    }

    public void setMultSelectScore(double multSelectScore) {
        this.multSelectScore = multSelectScore;
    }

    public int getJudgeNumber() {
        return judgeNumber;
    }

    public void setJudgeNumber(int judgeNumber) {
        this.judgeNumber = judgeNumber;
    }

    public double getJudgeScore() {
        return judgeScore;
    }

    public void setJudgeScore(double judgeScore) {
        this.judgeScore = judgeScore;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    private String testName;//考试名称
    private String testType;//考试类型 期中/期末
    private String examDate;//考试日期
    private int limitTime;//考试限制时长
    private int radioNumber;//单选题数量
    private double radioScore;//单选题单题分值
    private int multSelectNumber;//多选题个数
    private double multSelectScore;//多选题单题分值
    private int judgeNumber;//判断题数量
    private double judgeScore;//判断题单题分值
    private String password;//考试密码
    private String classId;//班级编号
}
