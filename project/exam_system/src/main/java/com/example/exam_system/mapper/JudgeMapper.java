package com.example.exam_system.mapper;

import com.example.exam_system.pojo.Judge;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface JudgeMapper {

    @Select("select * from judge limit #{begin},#{size}")
     @ResultMap("JudgeResultMap")
    List<Judge>selectByPageAndSize(@Param("begin") int begin,@Param("size") int size);

    @Insert("insert into judge values (null,#{courseId},#{question},#{optionA},#{optionB},#{answer})")
    int insertJudge(Judge judge);

    @Select("select answer from judge where judge_id=#{id}")
    String selectAnswerByJudgeId(int id);

    @Select("select count(*) from judge")
    int countTotalRows();

    @Select("select * from judge where judge_id=#{id} and course_id=#{courseId}")
    @ResultMap("JudgeResultMap")
    Judge getOneJudgeById(@Param("id") int id,@Param("courseId") int courseId);
}
