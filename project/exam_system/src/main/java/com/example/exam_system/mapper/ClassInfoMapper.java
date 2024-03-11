package com.example.exam_system.mapper;

import com.example.exam_system.pojo.ClassInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ClassInfoMapper {
    @Select("select * from class")
    @ResultMap("ClassInfoResultMap")
    List<ClassInfo> selectAllClass();

    @Select("select class_name from class where class_id=#{classId}")
    String selectClassNameByClassId(@Param("classId") String classId);

    @Select("select class_id from class where class_name=#{className}")
    String selectClassIdByClassName(@Param("className") String className);

    @Insert("insert into class values (#{classId},#{className})")
    int addClass(ClassInfo classInfo);
}

