package com.sxt.controller;

import com.sxt.bean.User;
import com.sxt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/toLoginView")
    public String toLoginView(){
        return "user/loginUser";
    }

    @RequestMapping("/land")
    public String toLand(@Validated User user, BindingResult result, HttpServletRequest request, Model model){
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
                flag = userService.landUser(user,request);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(flag){
            return "forward:/spouse/list";
        }
        return "redirect:/myspringMVC3/user/toLoginView";

    }
}
