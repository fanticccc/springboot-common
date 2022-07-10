package com.example.constant;

import lombok.Data;

/**
 * @Author YSTen_SongJunBao
 * @Description: 邮件
 * @Date 2022/5/5 15:05
 * @Version 1.0
 * com.example.utils
 */
@Data
public class Email {
    /**
     * 收件人
     */
    private String mailTo;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;
    /**
     * 邮件抄送人
     */
    private String mailCc;

}
