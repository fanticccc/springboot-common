package com.example.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author song
 * @Description: controller 结果模板
 * @Date 2022/1/26 14:51
 * @Version 1.0
 * com.example.pojo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Result<T> {
    /**
     * 结果编码
     */
    private int code;
    /**
     * 结果描述
     */
    private String message;
    /**
     * 数据
     */
    private T content;
}
