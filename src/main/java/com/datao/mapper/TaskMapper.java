package com.datao.mapper;

import com.datao.pojo.Task;

import java.util.List;

/**
 * Created by 海涛 on 2016/5/5.
 */
public interface TaskMapper {

    //查询所有该用户的待办
    List<Task> findAllTask(Integer userid);

    void addTask(Task task);

    Task findById(Integer id);

    void upTask(Task task);
}
