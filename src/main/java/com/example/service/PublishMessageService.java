package com.example.service;

/**
 * @Author YSTen_SongJunBao
 * @Description:
 * @Date 2022/4/1 10:46
 * @Version 1.0
 * com.ysten.cos.utils
 */

public interface PublishMessageService {
    /**
     * 发送钉钉消息
     * @param content
     * @param atMobile
     */
    void sendToDingDingInfo(String content, String atMobile);
}
