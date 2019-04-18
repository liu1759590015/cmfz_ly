package com.baizhi.service;

import com.baizhi.entity.Admin;

import java.util.List;

public interface AdminService {
    //测试环境
    public List<Admin> queryAll();

    //查询用户名，密码，验证登录
    public Admin selectOne(Admin admin);
}
