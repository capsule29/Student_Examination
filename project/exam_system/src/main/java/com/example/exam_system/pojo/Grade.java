package com.example.exam_system.pojo;

public class Grade {
    private int gradeId;//试卷编号
    private String studentId;//学号
    private int paperId;//试卷编号
    private int courseId;//课程编号
    private double myGrade;//成绩

    @Override
    public String toString() {
        return "Grade{" +
                "gradeId=" + gradeId +
                ", studentId='" + studentId + '\'' +
                ", paperId=" + paperId +
                ", courseId=" + courseId +
                ", myGrade=" + myGrade +
                '}';
    }

    public int getPaperId() {
        return paperId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public double getMyGrade() {
        return myGrade;
    }

    public void setMyGrade(double myGrade) {
        this.myGrade = myGrade;
    }
}
