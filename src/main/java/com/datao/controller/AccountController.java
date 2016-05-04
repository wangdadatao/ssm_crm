package com.datao.controller;

import com.datao.mapper.UserMapper;
import com.datao.pojo.User;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * Created by 海涛 on 2016/4/28.
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Inject
    private UserMapper userMapper;

    @RequestMapping(method = RequestMethod.GET)
    public String accountShow() {
        return "account/account";
    }

    @RequestMapping(value = "/users.json", method = RequestMethod.GET,
    produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String,Object> showUsers(){

        Map<String,Object> result = Maps.newHashMap();
        List<User> userList = userMapper.findAll();

        result.put("draw",1);
        result.put("recordsTotal",userList.size());
        result.put("recordsFiltered",userList.size());
        result.put("data",userList);

        return result;
    }


}
