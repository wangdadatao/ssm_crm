package com.datao.controller;

import com.datao.pojo.User;
import com.datao.service.UserService;
import com.datao.util.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by 海涛 on 2016/4/28.
 */
@Controller
public class HomeController {

    @Inject
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/user/log",method = RequestMethod.POST)
    public String userLog(User user, Model model, HttpServletRequest request){

        try{
            userService.userLog(user,request);
        }catch(DataAccessException e){
            model.addAttribute("errorMessage",e.getMessage());
            return "index";
        }

        return "redirect:/home";
    }

    @RequestMapping(value = "/home" , method = RequestMethod.GET)
    public String homeShow(){
        return "home";
    }


}
