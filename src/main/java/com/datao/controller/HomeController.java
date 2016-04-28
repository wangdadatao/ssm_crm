package com.datao.controller;

import com.datao.pojo.User;
import com.datao.service.UserService;
import com.datao.util.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String userLog(String tel, String password, RedirectAttributes redirectAttributes) {

        try {
            userService.userLog(tel, password);
            return "redirect:/home";

        } catch (DataAccessException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "帐号或密码错误！");
            return "redirect:/";
        }


    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homeShow() {
        return "home";
    }


}
