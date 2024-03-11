package com.example.exam_system.pojo;

public class SendStudent {
    private String uId;//学号
    private String name;//学生姓名
    private String classId;//班级号

    @Override
    public String toString() {
        return "SendStudent{" +
                "uId='" + uId + '\'' +
                ", name='" + name + '\'' +
                ", classId='" + classId + '\'' +
                '}';
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
}
