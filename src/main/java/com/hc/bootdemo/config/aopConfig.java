package com.hc.bootdemo.config;

import com.hc.bootdemo.myInterface.MyFunction;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @desc
 * @Author：hanchuang
 * @Version 1.0
 * @Date：add on 14:34 2019/6/10
 */
@Configuration
@Aspect
public class aopConfig {

    @Around("@within(org.springframework.web.bind.annotation.RestController) && @annotation(com.hc.bootdemo.myInterface.MyFunction)")
    public Object simpleAop(final ProceedingJoinPoint joinPoint) throws Throwable{

        try {

            System.out.println("方法调用开始==================："+ joinPoint.getTarget());
            // 调用原有的方法
            Long befor = System.currentTimeMillis();
            Object o = joinPoint.proceed();
            Long after = System.currentTimeMillis();
            System.out.println("调用方法结束===================共耗时："+(after-befor)+"毫秒");
            System.out.println("方法返回：return:===================="+o);
            return o;
        } catch (Throwable throwable) {
            throw throwable;
        }
    }

    @Before("@annotation(com.hc.bootdemo.myInterface.MyFunction)")
    public void doBefore(JoinPoint joinPoint){
        String name = getFunctionName(joinPoint);
        System.out.println("进入方法>>>>>>>"+name);
        Object [] args = joinPoint.getArgs();
        System.out.println("方法入参：args:=================="+Arrays.asList(args));
        Long befor = System.currentTimeMillis();
        System.out.println("方法开始时间："+befor);
    }

    @After("@annotation(com.hc.bootdemo.myInterface.MyFunction)")
    public void doAfter(){
        Long after = System.currentTimeMillis();
        System.out.println("方法结束时间："+after);

    }


    private String getFunctionName(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        MyFunction annotation = signature.getMethod().getAnnotation(MyFunction.class);
        return annotation.methodName();

    }

}
