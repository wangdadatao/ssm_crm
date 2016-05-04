package com.datao.controller;

import com.datao.dto.ShowMessage;
import com.datao.service.UserService;
import com.datao.error.DataAccessException;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;

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

    /**
     * 登录
     *
     * @param tel
     * @param password
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String userLog(String tel, String password, RedirectAttributes redirectAttributes) {

        try {
            userService.userLog(tel, password);
            return "redirect:/home";
        } catch (AuthenticationException e) {
            redirectAttributes.addFlashAttribute("Message", new ShowMessage(ShowMessage.ERROR, "帐号或密码错误！"));
            return "redirect:/";
        } catch (DataAccessException e) {
            redirectAttributes.addFlashAttribute("Message", new ShowMessage(ShowMessage.DANGER, e.getMessage()));
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homeShow() {
        return "home";
    }

    /**
     * 安全退出
     *
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("Message", new ShowMessage(ShowMessage.SUCCESS, "安全退出成功"));
        userService.logOut();
        return "redirect:/";
    }


}
