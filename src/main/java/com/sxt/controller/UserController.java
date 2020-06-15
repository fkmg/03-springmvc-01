package com.sxt.controller;

import com.sxt.bean.Spouse;
import com.sxt.bean.User;
import com.sxt.service.SpouseService;
import com.sxt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SpouseService spouseService;

    @RequestMapping("/toLoginView")
    public String toLoginView(){
        return "user/loginUser";
    }

    @RequestMapping("/land")
    public String toLand(@Validated User user, BindingResult result, HttpServletRequest request, HttpServletResponse response, Model model){
        boolean flag = false;
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError objectError : errors) {
                System.out.println(objectError.getCode());
                System.out.println(objectError.getDefaultMessage());
                model.addAttribute("errors", errors);
            }
        }else {
            try {
                flag = userService.landUser(user,request,response);
            } catch (SQLException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if(flag){
            //登录成功
            List<Spouse> list = spouseService.listSpouse();
            model.addAttribute("spouselist",list);
            return "spouse/spouselist";
        }
        return "redirect:/user/toLoginView";

    }
}
