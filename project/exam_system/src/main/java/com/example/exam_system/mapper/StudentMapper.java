package com.example.exam_system.mapper;

import com.example.exam_system.pojo.AccountAndPassword;
import com.example.exam_system.pojo.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StudentMapper {
    @Select("select * from student")
    @ResultMap("StudentResultMap")
    List<Student> selectAllStudent();

    @Select("select * from student where class_id=#{classId}")
    @ResultMap("StudentResultMap")
    List<Student> selectStudentByClassId(@Param("classId") String classId);

    @Select("select * from student where student_id=#{account} and student_password=#{password} and state=0")
    @ResultMap("StudentResultMap")
    Student selectByStudentIdAndStudentPassword(@Param("account") String account,@Param("password") String password);

    @Update("update student set state=1 where student_id=#{uId} and state=0")
    int login(String uId);

    @Update("update student set state=0 where student_id=#{uId} and state=1")
    int logout(String uId);

    @Insert("insert into student values (#{uId},#{studentPassword},#{name},#{permission},#{classId},#{state})")
    int addStudent(Student student);

    @Select("select student_name from student where student_id=#{uId}")
    String selectStudentNameByStudentId(@Param("uId") String uId);

    @Select("select class_id from student where student_id=#{uId}")
    String selectClassIdByStudentId(@Param("uId") String uId);
}
