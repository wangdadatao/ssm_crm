package com.datao.service;

import com.datao.error.DataAccessException;
import com.datao.error.ForbiddenException;
import com.datao.error.NotFoundException;
import com.datao.mapper.CustomerMapper;
import com.datao.mapper.ProgressFileMapper;
import com.datao.mapper.ProgressMapper;
import com.datao.mapper.UserMapper;
import com.datao.pojo.Customer;
import com.datao.pojo.User;
import com.datao.util.ShiroUtil;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

/**
 * Created by 海涛 on 2016/5/3.
 */
@Named
public class CustomerService {
    @Inject
    private CustomerMapper customerMapper;

    @Inject
    private UserMapper userMapper;

    @Inject
    private ProgressMapper progressMapper;

    @Inject
    private ProgressFileMapper progressFileMapper;


    /**
     * 根据参数查询Customer
     * @param param
     * @return
     */
    public List<Customer> findCustomerlist(Map<String, Object> param) {

        if (ShiroUtil.isManager()) {
            return customerMapper.findAll();
        } else {
            param.put("userid", ShiroUtil.getCurrentUserId());
            return customerMapper.findByParam(param);
        }

    }

    /**
     * 添加新的Customer
     *
     * @param customer
     */
    public void addNewCustomer(Customer customer) {
        customer.setCreateid(ShiroUtil.getCurrentUserId());
        customer.setCreatetime(DateTime.now().toString("yyyy-MM-dd"));
        customer.setProgress(Customer.PROGRESS_NEW);
        customer.setUserid(ShiroUtil.getCurrentUserId());

        customerMapper.addNewCustomer(customer);
    }


    /**
     * 查询Customer的总数量
     *
     * @return
     */
    public Long findCustomerCount() {
        return customerMapper.findCount();
    }


    /**
     * 根据查询方式获取用户的饿总数量
     *
     * @param param
     * @return
     */
    public Long findUserCountByParam(Map<String, Object> param) {

        if (ShiroUtil.isManager()) {
            return customerMapper.findCount();
        } else {
            return customerMapper.findCountByParam(param);
        }
    }

    /**
     * 根据customerID查找该对象
     *
     * @param id
     * @return
     */
    public Customer findCustomerById(Integer id) {

        Customer customer = customerMapper.findByCustomerId(id);

        if (customer == null) {
            throw new NotFoundException();
        }

        if (ShiroUtil.isManager()) {
            return customer;
        } else {
            if (customer.getUserid() == null || customer.getUserid().equals(ShiroUtil.getCurrentUserId())) {
                return customer;
            } else {
                System.out.println("customer的id是：" + customer.getUserid());
                System.out.println("CustomerService101: 权限不足");
                throw new ForbiddenException();
            }
        }

    }

    /**
     * 转交客户
     *
     * @param customerid
     * @param userid
     */
    public void tranCustomer(Integer customerid, Integer userid) {

        Customer customer = customerMapper.findByCustomerId(customerid);

        User user = userMapper.findById(userid);

        if (user == null) {
            throw new NotFoundException("用户不存在");
        } else {
            if (customer == null) {
                throw new NotFoundException("客户不存在");
            } else {
                if (customer.getUserid().equals(ShiroUtil.getCurrentUserId())) {
                    customer.setUserid(user.getId());
                    customer.setMark(customer.getMark() + "<br>" +
                            ShiroUtil.getCurrentUser().getUsername() + "转过来的客户！");
                    customerMapper.update(customer);
                }
            }
        }
    }

    /**
     * 删除客户信息
     *
     * @param customerid
     */
    public void delCustomer(Integer customerid) {

        Customer customer = customerMapper.findByCustomerId(customerid);

        if (customer != null) {
            if (ShiroUtil.isManager()) {
                customerMapper.delCustomer(customer);
                progressFileMapper.delProgressFile(customer.getUserid());
                progressMapper.delProgress(customer.getUserid());

            } else {
                throw new ForbiddenException();
            }
        } else {
            throw new NotFoundException("要删除的资源不存在！");
        }

    }

    /**
     * 公开客户
     *
     * @param customerid
     */
    public void pubCustomer(Integer customerid) {

        Customer customer = customerMapper.findByCustomerId(customerid);

        if (customer == null) {
            throw new DataAccessException("没有找到该客户！");
        } else {
            if (customer.getUserid().equals(ShiroUtil.getCurrentUserId())) {
                customer.setUserid(null);
                customerMapper.pubCustomer(customer);
            } else {
                throw new DataAccessException("权限不足！");
            }
        }
    }


    /**
     * 根据当前登录的用户查找相应的客户
     * @return
     * @param userid
     */
    public List<Customer> findCustomerByUser(Integer userid) {

        return customerMapper.findByUserid(userid);
    }
}
