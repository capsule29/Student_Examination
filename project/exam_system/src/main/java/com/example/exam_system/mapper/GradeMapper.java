package com.example.exam_system.mapper;

import com.example.exam_system.pojo.Grade;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GradeMapper {
    @Insert("insert into my_grade values (null,#{studentId},#{courseId},#{myGrade},#{paperId})")
    int addGrade(Grade grade);

    @Select("select * from my_grade,course where my_grade.course_id=course.course_id")
    @ResultMap("GradeResultMap")
    List<Grade> selectAllGrade();

    @Select("select * from my_grade where student_id=#{studentId}")
    @ResultMap("GradeResultMap")
    List<Grade> selectAllGradeByStudentId(@Param("studentId") String stduentId);
}
