package com.example.exam_system.pojo;

public class SendGradeToTeacher {
    private String name;
    private String utId;
    private String classId;
    private String courseName;
    private String testType;

    @Override
    public String toString() {
        return "SendGrade{" +
                "name='" + name + '\'' +
                ", utId='" + utId + '\'' +
                ", classId='" + classId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", testType='" + testType + '\'' +
                ", grade=" + grade +
                '}';
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    private double grade;

    public String getUtId() {
        return utId;
    }

    public void setUtId(String utId) {
        this.utId = utId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
