package com.example.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author SongJunBao
 * @Description: 异常用户
 * @Date 2022/2/24 15:50
 * @Version 1.0
 * com.example.pojo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Grade {
    //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ExcelProperty(value = "id")
    private int id;
    @ExcelProperty(value = "name")
    private String name;
    @ExcelProperty(value = "grade")
    private int grade;
}
