package com.example.exam_system.pojo;

public class Student {
    private String uId;//学号
    private String studentPassword;//密码
    private String name;//学生姓名
    private String permission;//权限
    private String classId;//班级号
    private int state;//状态 0 未登录  1已登录

    //    public String getStudentId() {
//        return studentId;
//    }
//
//    public void setStudentId(String studentId) {
//        this.studentId = studentId;
//    }

    public String getuId() {
        return uId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "uId='" + uId + '\'' +
                ", studentPassword='" + studentPassword + '\'' +
                ", name='" + name + '\'' +
                ", permission='" + permission + '\'' +
                ", classId='" + classId + '\'' +
                ", state=" + state +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

//    public String getStudentName() {
//        return studentName;
//    }
//
//    public void setStudentName(String studentName) {
//        this.studentName = studentName;
//    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
