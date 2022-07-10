package com.example.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StreamUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @Author YSTen_SongJunBao
 * @Description: Http請求工具類
 * @Date 2022/4/20 10:21
 * @Version 1.0
 * com.example.utils
 */

public class HttpUtil {

    private static final String  _GET = "GET";

    private static final String  _POST = "POST";

    private static final int  baseTimeout = 30;

    /**
     *
     * @param httpUrl 请求地址
     * @param prams  参数
     * @param character 编码
     * @return
     * @throws Exception
     */
    public static String doPost(String httpUrl, Map<String,String> prams, String character) throws Exception {
        URL url ;
        StringBuilder pramStr = new StringBuilder();
        InputStream in = null ;
        OutputStreamWriter outWriter = null ;
        HttpURLConnection connection = null ;
        if (StringUtils.isEmpty(httpUrl)){
            throw new RuntimeException("request url null ~ ");
        }
        //定义需要访问的地址
         url = new URL(httpUrl);
        //获取连接对象
         connection = (HttpURLConnection)url.openConnection();
        //请求类型
        connection.setRequestMethod(_POST);
        //携带参数
        connection.setDoOutput(true);
        //连接超时时间
        connection.setConnectTimeout(baseTimeout * 1000);
        connection.setReadTimeout(baseTimeout * 2000);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        if (!CollectionUtils.isEmpty(prams)){
            for (Map.Entry<String, String> pram : prams.entrySet()) {
                pramStr.append("&").append(pram.getKey()).append("=").append(pram.getValue());
            }
            //outWriter = new OutputStreamWriter(connection.getOutputStream(),character);
            connection.getOutputStream().write(pramStr.substring(1).toString().getBytes(character));
        }
        //发起请求
        connection.connect();
        //接收返回值
        in = connection.getInputStream();
        String response = StreamUtils.copyToString(in, Charset.forName(character));
        return response ;
    }
}
