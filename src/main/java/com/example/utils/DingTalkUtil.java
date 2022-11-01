package com.example.utils;

import com.example.service.PublishMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author SongJunBao
 * @Description:
 * @Date 2022/4/8 17:16
 * @Version 1.0
 * com.exc.song.utils
 */
@Component
public class DingTalkUtil {

    @Autowired
    private PublishMessageService publishMessageService;

    public void send(String content, String atMobile) {
        publishMessageService.sendToDingDingInfo(content, atMobile);
    }

    public static String taskLa(String str) {
        return str;
        //publishMessageService.sendToDingDingInfo(content,atMobile);
    }

}
