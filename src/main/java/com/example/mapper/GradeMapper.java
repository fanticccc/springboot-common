package com.example.mapper;

import com.example.pojo.Grade;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author SongJunBao
 * @Description:
 * @Date 2022/2/24 15:57
 * @Version 1.0
 * com.example.mapper
 */
@Repository
public interface GradeMapper {

    int addGrade(Grade grade);

    List<Grade> getGradeList();

}
