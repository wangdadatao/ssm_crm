package com.datao.mapper;

import com.datao.pojo.User;

/**
 * Created by 海涛 on 2016/4/28.
 */
public interface UserMapper {

    User findByTel(String tel);

}
