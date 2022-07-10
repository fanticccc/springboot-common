package com.example.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
/**
 * @Author YSTen_SongJunBao
 * @Description:
 * @Date 2022/4/20 11:07
 * @Version 1.0
 * com.example.utils
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        String url = "http://way.jd.com/jisuapi/query4";
        String mobile = "15656112757";
        HashMap <String , String> map = new HashMap <String,String>();
        map.put("shouji" , mobile);
        map.put("appkey","2eab09fc0de0178f9505e9f7efdb43d0");
            try {
                String response = HttpUtil.doPost(url, map,"UTF-8");
                log.info("result:{}",response);
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
    }
}
