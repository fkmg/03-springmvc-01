package com.sxt.controller;

import com.sxt.bean.Spouse;
import com.sxt.service.SpouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    @RequestMapping("add")
    public String toAddSpouseView(Model model){
        return "spouse/spouseadd";
    }


    @RequestMapping("uplodimage")
    @ResponseBody
    public Integer toUpLoadImage(@RequestParam("userimage") MultipartFile uploadFile,String userdesc){
        try {
            //获取文件名称
            String fileName = uploadFile.getOriginalFilename();
            //文件的上传路径
            String filePath = "E:\\java\\uploadfile\\" + fileName;
            File file = new File(filePath);
            //将文件上传到指定的路径
            uploadFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 200;
    }
}
