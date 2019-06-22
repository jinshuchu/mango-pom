package com.louis.mango.admin.controller;

import com.louis.mango.admin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public Object hello(){
        return "hello world";
    }

//    @RequestMapping(value = "/user/findAll", method = RequestMethod.GET)
//    public Object findAll(){
//        return sysUserService.findAll();



}


