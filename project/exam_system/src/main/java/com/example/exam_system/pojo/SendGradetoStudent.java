package com.example.exam_system.pojo;

public class SendGradetoStudent {
    private String courseName;
    private String testType;
    private double grade;

    @Override
    public String toString() {
        return "SendGradetoStudent{" +
                "courseName='" + courseName + '\'' +
                ", testType='" + testType + '\'' +
                ", grade=" + grade +
                '}';
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
