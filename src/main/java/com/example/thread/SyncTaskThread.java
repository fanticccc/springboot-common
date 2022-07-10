package com.example.thread;

import com.example.pojo.Grade;
import com.example.service.GradeService;
import com.example.utils.EmailSender;
import com.example.utils.SysQueueUtils;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

/**
 * @Author YSTen_SongJunBao
 * @Description:
 * @Date 2022/2/24 17:08
 * @Version 1.0
 * com.example.thread //implements ApplicationContextAware
 */
@Slf4j
public class SyncTaskThread extends Thread  {

    private GradeService gradeService ;

 public SyncTaskThread(GradeService gradeService){
     this.gradeService = gradeService ;
 }

    @Override
    @SneakyThrows
    public void run() {
        log.info("Method:SyncTaskThread,run ============= starting ===========");
        while (true){
            try {
                Grade obj = SysQueueUtils.getObj();
                doTask(obj);
            }catch (Exception ex){
                log.error("【SyncTaskThread----】取user保存至grade表出现异常:{}",ex.getMessage());
            }
        }
    }

    private void doTask(Grade grade){
        //将异常用户存入grade表
       // GradeService gradeService = (GradeService) getBean("gradeService");
        gradeService.addGrade(grade);
    }

/*    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext ;
    }

    public static ApplicationContext getApplicationContent(){
        return context ;
    }

    public static Object getBean(String name){
        return getApplicationContent().getBean(name);
    }*/
}
