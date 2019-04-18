package com.baizhi.service.impl;

import com.baizhi.entity.Admin;
import com.baizhi.mapper.AdminMapper;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Admin> queryAll() {
        List<Admin> admins = adminMapper.selectAll();
        return admins;
    }

    @Override
    public Admin selectOne(Admin admin) {
        return adminMapper.selectOne(admin);
    }
}
