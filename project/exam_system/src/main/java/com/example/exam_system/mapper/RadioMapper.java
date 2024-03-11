package com.example.exam_system.mapper;

import com.example.exam_system.pojo.Radio;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RadioMapper {

    /**
     * 根据传入页码和页面大小进行分页查询
     * @param begin 开始位置
     * @param size 大小
     * @return
     */
    @Select("select * from radio limit #{begin},#{size}")
    @ResultMap("RadioResultMap")
    List<Radio> selectByPageAndSize(@Param("begin") int begin,@Param("size") int size);

    /**
     * 插入新的题目
     * @param radio
     * @return
     */
    @Insert("insert into radio values (null,#{courseId},#{question},#{optionA},#{optionB},#{optionC},#{optionD},#{answer})")
    int insertRadio(Radio radio);

    /**
     * 根据传入的题目id查询该题目的答案
     * @param id
     * @return
     */
    @Select("select answer from radio where radio_id=#{id}")
    String selectAnswerByRadioId(@Param("id") int id);

    /**
     * 计算总行数
     * @return
     */
    @Select("select count(*) from radio")
    int countTotalRows();

    @Select("select * from radio where radio_id=#{id} and course_id=#{courseId}")
    @ResultMap("RadioResultMap")
    Radio getOneRadioById(@Param("id") int id,@Param("courseId") int courseId);
}
