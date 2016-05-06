package com.datao.service;

import com.datao.error.DataAccessException;
import com.datao.mapper.TaskMapper;
import com.datao.pojo.Task;
import com.datao.util.ShiroUtil;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by 海涛 on 2016/5/5.
 *
 */
@Named
public class TaskService {

    @Inject
    private TaskMapper taskMapper;

    public List<Task> findAllTask() {

        return taskMapper.findAllTask(ShiroUtil.getCurrentUserId());

    }

    /**
     * 添加新的待办
     * @param task
     */
    public void addTask(Task task) {
        task.setCreatetime(DateTime.now().toString("yyyy-MM-dd HH:mm"));
        task.setUserid(ShiroUtil.getCurrentUserId());
        task.setStatus("false");

        taskMapper.addTask(task);
    }

    /**
     * 改变待办事物的状态
     * @param id taskid
     */
    public Task changeStatus(Integer id) {

        Task task = taskMapper.findById(id);

        if(task == null){
            throw new DataAccessException("该事件不存在!");
        }else{
            if(!task.getUserid().equals(ShiroUtil.getCurrentUserId())){
                throw new DataAccessException("无权改变该事件");
            }else{
               if(task.ifDone()){
                   task.setStatus("false");
               }else{
                   task.setStatus("true");
               }

                task.setDonetime(DateTime.now().toString("yyyy-MM-dd HH:mm"));

                taskMapper.upTask(task);
            }
        }
        return task;
    }
}
