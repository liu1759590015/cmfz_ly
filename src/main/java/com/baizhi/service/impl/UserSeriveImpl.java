package com.baizhi.service.impl;

import com.baizhi.entity.User;
import com.baizhi.mapper.UserMapper;
import com.baizhi.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserSeriveImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Map selectAll(int page, int rows) {
        PageHelper.startPage(page, rows);
        PageInfo<User> pageInfo = new PageInfo<User>(userMapper.selectAll());
        Map map = new HashMap();
        map.put("rows", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    @Override
    public void insert(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void delete(User user) {
        userMapper.delete(user);
    }

    @Override
    public List<User> select() {
        return userMapper.selectAll();
    }
}
