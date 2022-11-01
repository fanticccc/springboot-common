package com.example.annotation;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Author SongJunBao
 * @Description: 实现日志打印
 * @Date 2022/5/19 14:33
 * @Version 1.0
 * com.example.annotation
 */
@Aspect
@Component
public class SysLogAspect {

    @Pointcut("@annotation(com.example.annotation.SysLog)")
    public void logPointCut() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();

        // 方法返回结果
        Object result = joinPoint.proceed();
        // 执行时长
        long time = System.currentTimeMillis() - beginTime;
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // 获取自定义注解上的值
        SysLog sysLog = method.getAnnotation(SysLog.class);
        String sysLogValue = sysLog.value();
        String syslogDesc = sysLog.desc();

        // 获取调用的类名、方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();

        //方法入参
        Object[] args = joinPoint.getArgs();
        System.out.println("注解上的值：" + (StringUtils.isEmpty(sysLogValue) ? syslogDesc : sysLogValue));
        System.out.println("执行的类和方法：" + className + "." + methodName);
        System.out.println("执行时长：" + time + "ms");
        System.out.println("执行结果：" + result);
        return result;
    }
}
