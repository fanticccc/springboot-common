package com.example.controller;

import com.example.constant.Result;
import com.example.constant.ResultStatus;
import com.example.service.PublishMessageService;
import com.example.utils.DingTalkUtil;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * @Author SongJunBao
 * @Description: 测试发送钉钉消息
 * @Date 2022/4/8 17:36
 * @Version 1.0
 * com.exc.song.controller
 */
@RequestMapping("ding")
@Api(tags = "钉钉消息",value = "钉钉消息")
@RestController
public class DingTalkTestController {

    @Autowired
    private PublishMessageService publishMessageService;
    @Autowired
    private DingTalkUtil dingTalkUtil;
    private static final Map m = Maps.newConcurrentMap();

    @GetMapping("talk")
    @ApiOperation(value = "发送钉钉消息",notes = "发送钉钉消息" )
    public String talk(String str) {
        return DingTalkUtil.taskLa("发送消息：" + str);
    }

    @GetMapping("send1")
    @ApiOperation(value = "发送钉钉消息",notes = "发送钉钉消息" )
    public void sendMsg1(String str) {
        dingTalkUtil.send("dingding-test1-song-p1-" + str, null);
    }

    @GetMapping("send2")
    @ApiOperation(value = "发送钉钉消息",notes = "发送钉钉消息" )
    public void sendMsg2(String str) {
        publishMessageService.sendToDingDingInfo("dingding-test2-song:" + str, null);
    }



    public static void main(String[] args) {
       Result resultStatus = new Result();
        System.out.println(resultStatus);
    }
}
