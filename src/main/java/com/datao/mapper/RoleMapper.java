package com.datao.mapper;

import com.datao.pojo.Role;

import java.util.List;

/**
 * Created by 海涛 on 2016/4/28.
 */
public interface RoleMapper {

    List<Role> findRoles(Integer id);

}
