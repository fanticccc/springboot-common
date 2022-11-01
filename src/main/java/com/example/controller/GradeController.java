package com.example.controller;

import com.example.constant.ResultStatus;
import com.example.pojo.Grade;
import com.example.constant.Result;
import com.example.service.GradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @Author SongJunBao
 * @Description:
 * @Date 2022/2/24 16:01
 * @Version 1.0
 * com.example.controller
 */
@RestController
@Slf4j
@Api(tags = "等级管理",value = "等级管理")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @GetMapping("addGrade")
    @CrossOrigin
    @ApiOperation(value = "查询用户等级信息",notes = "查询用户等级信息" )
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public Result<Grade> addGrade(@ApiParam(value = "id") @RequestParam(value = "id", required = false) Integer id,
                                  @ApiParam(value = "真实姓名") @RequestParam(value = "name", required = false) String name,
                                  @ApiParam(value = "等级") @RequestParam(value = "grade", required = false) Integer grade) {
        int a = gradeService.addGrade(new Grade().setId(id).setName(name).setGrade(grade));
        if (a == 0) {
            Result result = new Result(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMsg(), null);
            return result;
        }
        return new Result(ResultStatus.FAIL.getCode(), ResultStatus.FAIL.getMsg(), null);
    }
}
