package com.example.exam_system.mapper;

import com.example.exam_system.pojo.MultSelect;
import com.example.exam_system.pojo.Radio;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MultSelectMapper {
    /**
     * 根据传入页码和页面大小进行分页查询
     * @param begin 开始位置
     * @param size 大小
     * @return
     */
    @Select("select * from multselect limit #{begin},#{size}")
    @ResultMap("MultSelectResultMap")
    List<MultSelect> selectByPageAndSize(@Param("begin") int begin, @Param("size") int size);

    /**
     * 插入新的题目
     * @param multSelect
     * @return
     */
    @Insert("insert into multselect values (null,#{courseId},#{question},#{optionA},#{optionB},#{optionC},#{optionD},#{optionE},#{answer})")
    int insertRadio(MultSelect multSelect);

    /**
     * 根据传入的题目id查询该题目的答案
     * @param id
     * @return
     */
    @Select("select answer from multselect where mult_select_id=#{id}")
    String selectAnswerByMultSelectId(@Param("id") int id);

    /**
     * 计算总行数
     * @return
     */
    @Select("select count(*) from multselect")
    int countTotalRows();

    @Select("select * from multselect where mult_select_id=#{id} and course_id=#{courseId}")
    @ResultMap("MultSelectResultMap")
    MultSelect getOneMultSelectById(@Param("id") int id,@Param("courseId") int courseId);
}
