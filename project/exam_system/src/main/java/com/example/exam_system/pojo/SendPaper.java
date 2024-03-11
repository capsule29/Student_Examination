package com.example.exam_system.pojo;

public class SendPaper {
    private int paperId;//试卷id
    private String courseName;//课程名称
    private String testName;//考试名称
    private String examDate;//考试日期
    private String testType;//考试类型
    private int limitTime;//考试限制时间
//    private String password;//考试密码

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

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public int getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(int limitTime) {
        this.limitTime = limitTime;
    }

    @Override
    public String toString() {
        return "SendPaper{" +
                "paperId=" + paperId +
                ", courseName='" + courseName + '\'' +
                ", testName='" + testName + '\'' +
                ", examDate='" + examDate + '\'' +
                ", testType='" + testType + '\'' +
                ", limitTime=" + limitTime +
                '}';
    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
}
