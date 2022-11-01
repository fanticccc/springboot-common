package com.example.schedule;

import com.example.pojo.Grade;
import com.example.pojo.User;
import com.example.service.GradeService;
import com.example.service.UserService;
import com.example.utils.EmailSender;
import com.example.utils.ReadProperty;
import com.example.utils.SysQueueUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author SongJunBao
 * @Description: 測試注解開啓定時任務 扫描user表检测异常用户(0 未婚 1 已婚 其他都为异常)
 * @Date 2022/1/19 16:33
 * @Version 1.0
 * com.example.schedule
 */
@Slf4j
@Configuration //1.主要用于标记配置类，兼备Component的效
@Transactional(rollbackFor = Exception.class)
public class CheckExcUserScheduleTask {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailSender sender;
    // 管理员邮箱
    private static final String EMAIL_NUMBER = "2548533500@qq.com";

    //发送邮件开关
    @Value("${system-parameters.is_send_email}")
    private String IS_SEND_EMAIL;

    //3.添加定时任务 扫描user表
    @Scheduled(cron = "${system-parameters.scheduling.user}")
    private void task() {
        log.info("Spring Scheduling CheckExcUserScheduleTask start--------:{}", LocalDateTime.now().toLocalTime());
        // sql 不合理，where条件查询直接附带条件：IsMry>1 > 建议修改 --TODO
        List<User> list = userService.selectAll();
        //遍历grade大于1的异常用户 存至队列
        for (User user : list) {
            if (!CollectionUtils.isEmpty(list) && user.getIsMry() > 1) {
                if (StringUtils.isNotEmpty(IS_SEND_EMAIL) && IS_SEND_EMAIL.equals("true")) {
                    sendEmail(user);
                }
                //异常用户 放入队列
                try {
                    SysQueueUtils.setObj(new Grade().setName(user.getName()).setGrade(user.getIsMry()));
                } catch (Exception ex) {
                    log.info("异常用户存队列异常，请检查该用户信息完整性。");
                }
                //删除此异常用户
                userService.deleteUserById(user.getId());
                log.info(" Find ExceptionUser name:{} ", user.getName());
            }
        }
    }

    /**
     * 将异常用户信息 发送邮件通知管理员
     *
     * @param user 异常用户
     */
    private void sendEmail(User user) {
        if (!ObjectUtils.isEmpty(user)) {
            String subject = "Abnormal user information ";
            sender.send(subject, user.toString(), EMAIL_NUMBER);
            log.info("异常用户user:{},发送邮件 告警成功！", user.getName());
        }
    }
}
