package com.datao.mapper;

import com.datao.pojo.Customer;

import java.util.List;
import java.util.Map;

/**
 * Created by 海涛 on 2016/5/3.
 */
public interface CustomerMapper {

    //查找所有的客户
    List<Customer> findAll();

    //根据用户id查找对应的客户
    List<Customer> findByUserid(Integer userid);

    //添加新用户
    void addNewCustomer(Customer customer);

    //统计数量
    Long findCount();

    //统计数量
    Integer findUsersCount(Integer userid);

    //根据参数查找对象
    List<Customer> findByParam(Map<String, Object> param);

    //根据参数 统计数量
    Long findCountByParam(Map<String, Object> param);

    //根据id查找
    Customer findByCustomerId(Integer id);

    //更新客户
    void update(Customer customer);

    //删除客户
    void delCustomer(Customer customer);

    //设置成公众的客户
    void pubCustomer(Customer customer);
}
