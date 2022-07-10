package com.example.constant;

import lombok.Getter;

/**
 * @Author YSTen_SongJunBao
 * @Description: 结果状态
 * @Date 2022/5/12 10:33
 * @Version 1.0
 * com.example.common
 */
@Getter
public enum ResultStatus {

    SUCCESS(1,"成功"),
    FAIL(0,"失败");

    private int code;
    private String msg;

    ResultStatus(int code ,String msg){
        this.code=code;
        this.msg = msg ;
    }
}
