package com.example.exam_system.mapper;

import com.example.exam_system.pojo.Course;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CourseMapper {
    @Insert("insert into course values (null,#{courseName})")
    int addCourse(Course course);


    @Select("select course_name from course where course_id=#{id}")
    String selectCourseNameByCourseId(@Param("id") int id);


    @Select("select course_id from course where course_name=#{name}")
    int selectCourseIdByCourseName(@Param("name") String name);

    @Select("select * from course ")
    @ResultMap("CourseResultMap")
    List<Course> selectAll();
}
