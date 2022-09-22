package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author： SongJunBao
 * @Date： 2022/9/15 10:07
 * @Description：
 * @Version： 1.0
 * @Package: com.example.consumer.handler
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String messageId;
    private String messageData;
    private String createTime;
}
