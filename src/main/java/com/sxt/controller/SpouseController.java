package com.sxt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/spouse")
public class SpouseController {

    @RequestMapping("list")
    public String spouseList(){
        System.out.println("hello world");

        return "spouse/spouselist";
    }

}
