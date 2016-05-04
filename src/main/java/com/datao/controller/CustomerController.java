package com.datao.controller;

import com.datao.error.NotFoundException;
import com.datao.pojo.Customer;
import com.datao.pojo.User;
import com.datao.service.CustomerService;
import com.datao.error.DataAccessException;
import com.datao.service.UserService;
import com.datao.util.ToUTF8;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by 海涛 on 2016/5/3.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Inject
    private CustomerService customerService;

    @Inject
    private UserService userService;

    /**
     * customer主页
     *
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String customerShow(RedirectAttributes redirectAttributes) {
        return "customer/customer";
    }

    /**
     * 返回json
     * 查找该用户的所有客户
     *
     * @return
     */
    @RequestMapping(value = "/list.json", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> customerList(HttpServletRequest request) {
        Map<String, Object> resultMap = Maps.newHashMap();

        String draw = request.getParameter("draw");
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer length = Integer.valueOf(request.getParameter("length"));
        String orderColumnIndex = request.getParameter("order[0][column]");
        String orderType = request.getParameter("order[0][dir]");
        String orderColumnName = request.getParameter("columns[" + orderColumnIndex + "][name]");

        String searchName = request.getParameter("seaName");
        String searchTel = request.getParameter("seaTel");
        String searchState = request.getParameter("seaState");


        Map<String, Object> param = Maps.newHashMap();
        param.put("start", start);
        param.put("length", length);
        if (StringUtils.isNotEmpty(searchName)) {
            param.put("seaName", "%" + ToUTF8.toUTF8(searchName) + "%");
        }
        if (StringUtils.isNotEmpty(searchTel)) {
            param.put("seaTel", "%" + ToUTF8.toUTF8(searchTel) + "%");
        }
        if (StringUtils.isNotEmpty(searchState)) {
            param.put("seaState", ToUTF8.toUTF8(searchState));
        }
        if (orderColumnName == null || orderType == null) {
            param.put("orderColumn", "id");
            param.put("orderType", "desc");
        } else {
            param.put("orderColumn", orderColumnName);
            param.put("orderType", orderType);
        }

        List<Customer> userList = customerService.findCustomerlist(param);
        Long count = customerService.findCustomerCount();
        Long filteredCount = customerService.findUserCountByParam(param);

        resultMap.put("draw", draw);
        resultMap.put("recordsTotal", count); //总记录数
        resultMap.put("recordsFiltered", filteredCount); //过滤出来的数量
        resultMap.put("data", userList);

        return resultMap;
    }

    /**
     * 添加新用户
     *
     * @param customer
     * @return
     */
    @RequestMapping(value = "/addcustomer", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addCustomer(Customer customer) {
        Map<String, Object> result = Maps.newHashMap();

        try {
            customerService.addNewCustomer(customer);
            result.put("status", "success");
        } catch (DataAccessException e) {
            result.put("status", "error");
            result.put("errorMessage", e.getMessage());
        }
        return result;
    }

    /**
     * 展示某个客户的详细信息
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/show/{id:\\d+}", method = RequestMethod.GET)
    public String showById(@PathVariable Integer id, Model model) {
        Customer customer = customerService.findCustomerById(id);
        List<User> users = userService.finAllUser();

        model.addAttribute("users", users);
        model.addAttribute("customer", customer);

        return "customer/show";

    }

    /**
     * 转交客户
     *
     * @param customerid
     * @param userid
     * @return
     */
    @RequestMapping(value = "/tran/{customerid:\\d+}/{userid:\\d+}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> tranCustomer(@PathVariable Integer customerid, @PathVariable Integer userid) {
        Map<String, Object> result = Maps.newHashMap();

        try {
            customerService.tranCustomer(customerid, userid);
            result.put("status", "success");
        } catch (NotFoundException e) {
            result.put("status", "error");
            result.put("errorMessage", e.getMessage());
        }
        return result;
    }

    /**
     * 删除客户
     *
     * @param customerid
     * @return
     */
    @RequestMapping(value = "/del/{customerid:\\d+}", method = RequestMethod.GET)
    public String delCustomer(@PathVariable Integer customerid, RedirectAttributes redirectAttributes) {

        customerService.delCustomer(customerid);

        redirectAttributes.addFlashAttribute("message", "删除成功！");
        return "redirect:/customer";

    }

    @RequestMapping(value = "/public/{customerid:\\d+}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> pubCustomer(@PathVariable Integer customerid) {

        Map<String, Object> result = Maps.newHashMap();
        try {
            customerService.pubCustomer(customerid);
            result.put("status", "success");
        } catch (DataAccessException e) {
            result.put("status", "error");
            result.put("errorMessage", e.getMessage());
        }
        return result;
    }


}
