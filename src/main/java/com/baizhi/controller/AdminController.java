package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("queryAll")
    public String queryAll(Map map) {
        List<Admin> admins = adminService.queryAll();
        map.put("list", admins);
        return "index";
    }

    //登录操作
    @RequestMapping("selectOne")
    public String selectOne(Admin admin) {
        Admin admin1 = adminService.selectOne(admin);
        if (admin1 != null) {
            return "main/main";
        } else {
            return "login";
        }
    }
}
