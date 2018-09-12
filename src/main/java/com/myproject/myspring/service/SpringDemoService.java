package com.myproject.myspring.service;

import com.myproject.myspring.annocation.MyService;

@MyService
public class SpringDemoService {

    public  String SayHellow(String name){
        return "Hellow" +name;
    }
}
