package com.example.service;

import com.example.pojo.Grade;

import java.util.List;

/**
 * @Author SongJunBao
 * @Description:
 * @Date 2022/2/24 15:55
 * @Version 1.0
 * com.example.service
 */

public interface GradeService {

    int addGrade(Grade grade);

    List<Grade> getGradeList();

}
