package com.hc.bootdemo.controller;

import com.hc.bootdemo.myInterface.MyFunction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @desc
 * @Author：hanchuang
 * @Version 1.0
 * @Date：add on 14:47 2019/6/10
 */
@RestController
public class testController {

    @GetMapping(value = "/hello")
    public ResponseEntity<String> getHello(@RequestParam(value = "val") String val){
        System.out.println("方法getHello正在执行");
        return new ResponseEntity<>("成功！",HttpStatus.OK);
    }

    @MyFunction(methodName = "teacher")
    @GetMapping(value = "/teacher")
    public String getTeacher(@RequestParam(value = "id") String id){

        System.out.println("方法getTeacher正在执行");
        return "张老师";
    }
}
