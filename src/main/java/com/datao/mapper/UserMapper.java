package com.datao.mapper;

import com.datao.pojo.Role;
import com.datao.pojo.User;

import java.util.List;

/**
 * Created by 海涛 on 2016/4/28.
 */
public interface UserMapper {

    User findByTel(String tel);

    List<User> findAll();

    User findById(Integer userid);
}
