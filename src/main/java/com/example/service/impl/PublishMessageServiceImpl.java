package com.example.service.impl;

import com.example.constant.Constant;
import com.example.service.PublishMessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author song
 * @Description: 处理、发送钉钉
 * @Date 2022/4/1 10:46
 * @Version 1.0
 */
@Service
@Slf4j
public class PublishMessageServiceImpl implements PublishMessageService {

    @Value("${dingding-parmeters.dingding_mobiles}")
    private String DINGDING_MOBILES_NUMBER;

    @Value("${dingding-parmeters.dingding_api_url}")
    private String DINGDING_API_URL;

    @Resource
    private RestTemplate restTemplate;

    @Override
    public void sendToDingDingInfo(String content, String atMobile) {

        try {
            Map<String, Object> paramObj = new HashMap<>();
            paramObj.put("msgtype", "text");
            Map<String, Object> textObj = new HashMap<>();
            textObj.put("content", content);
            paramObj.put("text", textObj);
            Map<String, Object> atObj = new HashMap<>();
            atObj.put("isAtAll", false);

            List<String> atMobiles = new ArrayList<>();
            if (StringUtils.isNotBlank(DINGDING_MOBILES_NUMBER)) {
                atMobiles = splitToString(DINGDING_MOBILES_NUMBER, Constant.DEFAULT_MULTI_COMMA_SPLIT);
            }
            if (StringUtils.isNotBlank(atMobile)) {
                atMobiles.add(atMobile);
            }
            if (!CollectionUtils.isEmpty(atMobiles)) {
                atObj.put("atMobiles", atMobiles);
            } else {
                atObj.put("isAtAll", true);
            }
            paramObj.put("at", atObj);
            Long timestamp = System.currentTimeMillis();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity httpEntity = new HttpEntity<>(paramObj, headers);
            String dingdingUrl = String.format("%s&timestamp=%s", DINGDING_API_URL, timestamp);
            String result = restTemplate.postForObject(dingdingUrl, httpEntity, String.class);
            log.debug("Method:sendToDingDingInfo,result:{}", result);
        } catch (Exception e) {
            log.error("Method:sendToDingDingInfo,e:{}", e);
        }
    }

    private List<String> splitToString(String source, String separator) {
        if (StringUtils.isEmpty(source)) {
            return Collections.emptyList();
        }
        List<String> list = new ArrayList<>();
        separator = StringUtils.isBlank(separator) ? Constant.DEFAULT_MULTI_COMMA_SPLIT : separator;

        String[] ids = source.split(separator);
        for (String s : ids) {
            if (StringUtils.isNotBlank(s)) {
                list.add(s);
            }
        }
        return list;
    }
}
