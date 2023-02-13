package com.example.controller;

import cn.hutool.core.date.StopWatch;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: SongJunBao@pukkasoft.cn
 * @Date: 2023/1/9 16:42
 * @Package: com.example.controller
 * @Description: -测试EasyExcel 2.2.6 版本
 */
@RestController
@RequestMapping("excel")
public class EasyExcelTestController {

    @Autowired
    private UserService userService;
    @GetMapping("do")
    public void writeExcel(HttpServletResponse response) throws IOException {
        StopWatch watch = new StopWatch();
        watch.start();
        List<User> userList = userService.selectAll();
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).needHead(Boolean.TRUE).build();
        writeData(excelWriter,User.class,1,"用户页1",userList);
        writeData(excelWriter,User.class,2,"用户页2",userList);
        writeData(excelWriter,User.class,3,"用户页3",userList);
        writeData(excelWriter,User.class,4,"用户页4",userList);
        writeData(excelWriter,User.class,5,"用户页5",userList);
        writeData(excelWriter,User.class,6,"用户页6",userList);
        writeData(excelWriter,User.class,7,"用户页7",userList);
        writeData(excelWriter,User.class,8,"用户页8",userList);
        writeData(excelWriter,User.class,9,"用户页9",userList);
        writeData(excelWriter,User.class,10,"用户页10",userList);
        excelWriter.finish();
        watch.stop();
        System.out.println("1耗时："+watch.getTotalTimeMillis());
    }
    @GetMapping("doo")
    public void writeExcel2(HttpServletResponse response) throws IOException, InterruptedException {
        StopWatch watch = new StopWatch();
        List<User> users = userService.selectAll();
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        ExcelWriter  excelWriter = EasyExcel.write(response.getOutputStream()).needHead(Boolean.TRUE).build();
        CountDownLatch latch = new CountDownLatch(3);
        watch.start();
        new Thread(()->{
            writeData(excelWriter,User.class,1,"用户页1",users);
            writeData(excelWriter,User.class,2,"用户页2",users);
            writeData(excelWriter,User.class,3,"用户页3",users);
            System.out.println("线程一执行完成。。。。");
            latch.countDown();
        },"线程1").start();
        new Thread(()->{
            writeData(excelWriter,User.class,4,"用户页4",users);
            writeData(excelWriter,User.class,5,"用户页5",users);
            writeData(excelWriter,User.class,6,"用户页6",users);
            System.out.println("线程二执行完成。。。。");
            latch.countDown();
        },"线程2").start();
        new Thread(()->{
            writeData(excelWriter,User.class,7,"用户页7",users);
            writeData(excelWriter,User.class,8,"用户页8",users);
            writeData(excelWriter,User.class,9,"用户页9",users);
            writeData(excelWriter,User.class,10,"用户页10",users);
            System.out.println("线程三执行完成。。。。");
            latch.countDown();
        },"线程3").start();
        latch.await();
        excelWriter.finish();
        watch.stop();
        System.out.println("2耗时："+watch.getTotalTimeMillis());
    }
    /**
     * 写数据
     * @param  excelWriter excelWriter
     * @param clazz 类型
     * @param sheetNo sheet页
     * @param sheetName sheet名称
     * @param data 数据
     */
    private void writeData(ExcelWriter excelWriter , Class clazz , int sheetNo , String sheetName , List<?> data){
        WriteTable table = EasyExcel.writerTable(sheetNo).needHead(Boolean.TRUE).head(clazz).build();
        WriteSheet sheet = new WriteSheet();
        sheet.setSheetNo(sheetNo);
        sheet.setSheetName(sheetName);
        excelWriter.write(data,sheet,table);
    }
}
