package com.baizhi.service;

import com.baizhi.entity.China;

import java.util.List;

public interface StatisticsService {
    int selectByDate(int day);

    List<China> selectBySex(int sex);

}
