package com.baizhi.service.impl;

import com.baizhi.entity.China;
import com.baizhi.mapper.StatisticsMapper;
import com.baizhi.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private StatisticsMapper statisticsMapper;

    @Override
    public int selectByDate(int day) {
        return statisticsMapper.selectByDate(day);
    }

    @Override
    public List<China> selectBySex(int sex) {
        return statisticsMapper.selectBySex(sex);
    }

}
