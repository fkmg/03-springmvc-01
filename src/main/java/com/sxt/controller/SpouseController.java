package com.sxt.controller;

import com.sxt.bean.Spouse;
import com.sxt.service.SpouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/spouse")
public class SpouseController {
    
    @Autowired
    private SpouseService spouseService;

    @RequestMapping("list")
    public String spouseList(Model model){
        System.out.println("hello world");
        List<Spouse> list = spouseService.listSpouse();
        model.addAttribute("spouselist",list);
        return "spouse/spouselist";
    }

}
