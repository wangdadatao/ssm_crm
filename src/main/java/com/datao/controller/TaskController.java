package com.datao.controller;

import com.datao.dto.ShowMessage;
import com.datao.error.DataAccessException;
import com.datao.pojo.Customer;
import com.datao.pojo.Task;
import com.datao.service.CustomerService;
import com.datao.service.TaskService;
import com.datao.util.ShiroUtil;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * Created by 海涛 on 2016/5/5.
 */
@Controller
@RequestMapping("/task")
public class TaskController {

    @Inject
    private TaskService taskService;

    @Inject
    private CustomerService customerService;

    /**
     * 进入待办页面
     *
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showTasks(Model model) {
        List<Task> tasks = taskService.findAllTask();
        List<Customer> customers = customerService.findCustomerByUser(ShiroUtil.getCurrentUserId());
        System.out.println(customers.size());
        model.addAttribute("customers", customers);
        model.addAttribute("tasks", tasks);
        return "task/task";
    }

    /**
     * 添加新的待办事物
     *
     * @param task
     * @return
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addTask(Task task, RedirectAttributes redirectAttributes) {

        taskService.addTask(task);

        redirectAttributes.addFlashAttribute("message",
                new ShowMessage(ShowMessage.SUCCESS, "添加待办成功！"));
        return "redirect:/task";
    }

    @RequestMapping(value = "/status", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> changeStatus(Integer taskid) {
        Map<String, Object> result = Maps.newHashMap();

        try {
            Task task = taskService.changeStatus(taskid);
            result.put("status", "success");
            if(task.ifDone()){
                result.put("ifdone","true");
            }else{
                result.put("ifdone","false");
            }

        } catch (DataAccessException e) {
            result.put("status", "error");
            result.put("errorMessage", e.getMessage());
        }
        return result;
    }
}
