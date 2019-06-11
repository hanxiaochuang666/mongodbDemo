package com.hc.bootdemo.myInterface;


import java.lang.annotation.*;

/**
 * @desc
 * @Author：hanchuang
 * @Version 1.0
 * @Date：add on 15:31 2019/6/10
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyFunction {

    String methodName();
}
