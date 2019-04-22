package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    Map selectAll(int page, int rows);

    void insert(User user);

    void update(User user);

    void delete(User user);

    List<User> select();
}
