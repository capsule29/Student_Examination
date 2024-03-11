package com.example.exam_system.mapper;

import com.example.exam_system.pojo.Paper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PaperMapper {
    @Insert("insert into paper values (null,#{courseId},#{testName},#{testType},#{examDate},#{limitTime},#{radioNumber}" +
            ",#{radioScore},#{multSelectNumber},#{multSelectScore},#{judgeNumber},#{judgeScore},#{password},#{classId})")
    int addPaper(Paper paper);

    @Select("select * from paper where paper_id=#{id}")
    @ResultMap("PaperResultMap")
    Paper selectPaperByPaperId(@Param("id") int id);

    @Select("select * from paper where class_id=#{classId}")
    @ResultMap("PaperResultMap")
    List<Paper> selectPaperByClassId(@Param("classId") String classId);

    @Select("select * from paper where paper_id=#{paperId} and password=#{password}")
    @ResultMap("PaperResultMap")
    Paper selectPaerByPaperIdAndPassword(@Param("paperId") int paperId,@Param("password") String password);

    @Select("select test_type from paper where paper_id=#{id}")
    String selectTestTypeByPaperId(@Param("id") int id);

    @Select("select * from paper")
    @ResultMap("PaperResultMap")
    List<Paper>selectAllPaper();
}
