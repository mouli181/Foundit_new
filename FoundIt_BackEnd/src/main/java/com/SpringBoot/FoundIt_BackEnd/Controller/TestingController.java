package com.SpringBoot.FoundIt_BackEnd.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class TestingController {

    @GetMapping("/test")
    public String test0(){
        return " Testing Api .......!  ";
    }

    @GetMapping("/user")
    public String test01(){
        return "test...";
    }

}

