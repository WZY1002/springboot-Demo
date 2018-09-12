package com.myproject.myspring.controller;

import com.myproject.myspring.annocation.MyAutowried;
import com.myproject.myspring.annocation.MyContorller;
import com.myproject.myspring.annocation.MyRequestMapping;
import com.myproject.myspring.annocation.MyRequestParam;
import com.myproject.myspring.service.SpringDemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MyContorller()
@MyRequestMapping("/springdemo")
public class SpringDemo {
    @MyAutowried
    private SpringDemoService springDemoService;

    @MyRequestMapping("/sayhellow")
    public void HellowController(HttpServletRequest request, HttpServletResponse response, @MyRequestParam("name") String json){

    }
}
