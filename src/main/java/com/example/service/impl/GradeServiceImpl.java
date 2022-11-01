package com.example.service.impl;

import com.example.mapper.GradeMapper;
import com.example.pojo.Grade;
import com.example.service.GradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @Author SongJunBao
 * @Description:
 * @Date 2022/2/24 15:56
 * @Version 1.0
 * com.example.service.impl
 */
@Slf4j
@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;


    @Override
    public int addGrade(Grade grade) {
        return gradeMapper.addGrade(grade);
    }
}
