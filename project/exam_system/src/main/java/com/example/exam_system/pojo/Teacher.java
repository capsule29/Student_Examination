package com.example.exam_system.pojo;

public class Teacher {
    private String uId;//工号
    private String teacherPassword;//密码
    private String name;//教师姓名
    private String permission;//权限 admin/teacher

    @Override
    public String toString() {
        return "Teacher{" +
                "uId='" + uId + '\'' +
                ", teacherPassword='" + teacherPassword + '\'' +
                ", name='" + name + '\'' +
                ", permission='" + permission + '\'' +
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
//    public String getTeacherId() {
//        return teacherId;
//    }
//
//    public void setTeacherId(String teacherId) {
//        this.teacherId = teacherId;
//    }

    public String getTeacherPassword() {
        return teacherPassword;
    }

    public void setTeacherPassword(String teacherPassword) {
        this.teacherPassword = teacherPassword;
    }

//    public String getTeacherName() {
//        return teacherName;
//    }
//
//    public void setTeacherName(String teacherName) {
//        this.teacherName = teacherName;
//    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
