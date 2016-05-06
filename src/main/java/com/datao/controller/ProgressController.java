package com.datao.controller;

import com.datao.dto.ShowMessage;
import com.datao.pojo.Customer;
import com.datao.pojo.Progress;
import com.datao.pojo.User;
import com.datao.service.CustomerService;
import com.datao.service.ProgressService;
import com.datao.service.UserService;
import com.datao.util.Page;
import com.datao.util.ShiroUtil;
import com.datao.util.ToUTF8;
import com.google.inject.spi.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by 海涛 on 2016/5/4.
 */
@Controller
@RequestMapping("/progress")
public class ProgressController {

    @Inject
    private UserService userService;
    @Inject
    private CustomerService customerService;
    @Inject
    private ProgressService progressService;

    /**
     * 跟进首页
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showProgress(@RequestParam(required = false, defaultValue = "") String userid,
                               @RequestParam(required = false, defaultValue = "") String progress,
                               @RequestParam(required = false, defaultValue = "") String date,
                               @RequestParam(required = false, defaultValue = "") String context,
                               @RequestParam(required = false, defaultValue = "1") String p,
                               Model model) {
        progress = ToUTF8.toUTF8(progress);
        date = ToUTF8.toUTF8(date);
        context = ToUTF8.toUTF8(context);

        List<User> userList = userService.finAllUser();
        List<Customer> customerList = customerService.findCustomerByUser(ShiroUtil.getCurrentUserId());


        Page<Progress> page = progressService.findByParam(userid, progress, date, context, p);

        model.addAttribute("userList", userList);
        model.addAttribute("page", page);
        model.addAttribute("customerList", customerList);

        model.addAttribute("userid", userid);
        model.addAttribute("progress", progress);
        model.addAttribute("date", date);
        model.addAttribute("context", context);

        return "progress/progress";

    }

    /**
     * @param progress
     * @param file
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String save(Progress progress, @RequestParam MultipartFile[] file, RedirectAttributes redirectAttributes) {

        progressService.saveNewProgress(progress, file);

        redirectAttributes.addFlashAttribute("message", new ShowMessage(ShowMessage.SUCCESS, "添加成功"));
        return "redirect:/progress";
    }
}