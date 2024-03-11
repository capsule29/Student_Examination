package com.example.exam_system.mapper;

import com.example.exam_system.pojo.AccountAndPassword;
import com.example.exam_system.pojo.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeacherMapper {
    @Select("select * from teacher")
    @ResultMap("TeacherResultMap")
    List<Teacher>selectAllTeacher();

    @Select("select * from teacher where teacher_id=#{account} and teacher_password=#{password}")
    @ResultMap("TeacherResultMap")
    Teacher login(AccountAndPassword accountAndPassword);

    @Insert("insert into teacher values (#{uId},#{teacherPassword},#{name},#{permission})")
    int addTeacher(Teacher teacher);
}
