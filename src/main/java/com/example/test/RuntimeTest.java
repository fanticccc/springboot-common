package com.example.test;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @Author： SongJunBao
 * @Date： 2022/9/14 10:59
 * @Description： 执行指令
 * @Version： 1.0
 * @Package: com.example.test
 */
@Slf4j
public class RuntimeTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("请输入指令：");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        System.out.println("正在执行。。。");
        TimeUnit.SECONDS.sleep(3);
        //String command = "ipconfig /all";
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);
            InputStream inputStream = process.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream, Charset.forName("GBK"));
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            log.error("调用服务器执行指令-" + command + "-失败：{}", e.getMessage());
            e.printStackTrace();
        }
    }
}
