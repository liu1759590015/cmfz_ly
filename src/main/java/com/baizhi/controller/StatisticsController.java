package com.baizhi.controller;

import com.baizhi.entity.China;
import com.baizhi.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//统计
@Controller
@RequestMapping("statistics")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping("activeUser")
    @ResponseBody
    public Map activeUser() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("intervals", new String[]{"7天", "15天", "30天", "90天"});
        List list = new ArrayList();
        list.add(statisticsService.selectByDate(7));
        list.add(statisticsService.selectByDate(15));
        list.add(statisticsService.selectByDate(30));
        list.add(statisticsService.selectByDate(90));
        map.put("counts", list);
        return map;
    }

    @RequestMapping("distribution")
    @ResponseBody
    public Map distribution1(int sex) {
        Map map = new HashMap();
        List<China> china = statisticsService.selectBySex(sex);
        map.put("china", china);
        return map;
    }
}
