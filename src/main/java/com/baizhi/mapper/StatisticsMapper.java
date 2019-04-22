package com.baizhi.mapper;

import com.baizhi.entity.China;

import java.util.List;

public interface StatisticsMapper {
    public int selectByDate(int day);

    public List<China> selectBySex(int sex);
}
