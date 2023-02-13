package com.example.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @ExcelProperty(value = "id")
    private int id;
    @ExcelProperty(value = "userName")
    private String userName;
    @ExcelProperty(value = "name")
    private String name;
    @ExcelProperty(value = "age")
    private int age;
    @ExcelProperty(value = "sex")
    private String sex;
    @ExcelProperty(value = "isMry")
    private int isMry;
}
