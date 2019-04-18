package com.baizhi.service.impl;

import com.baizhi.entity.Banner;
import com.baizhi.mapper.BannerMappper;
import com.baizhi.service.BannerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerMappper bannerMappper;

    @Override
    public Map selectAll(int page, int rows) {
        PageHelper.startPage(page, rows);
        PageInfo<Banner> pageInfo = new PageInfo<Banner>(bannerMappper.selectAll());
        Map map = new HashMap();
        map.put("rows", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    @Override
    public void addBanner(Banner banner) {
        bannerMappper.insert(banner);
    }

    @Override
    public void delete(Banner banner) {
        bannerMappper.delete(banner);
    }

    @Override
    public void update(Banner banner) {
        bannerMappper.updateByPrimaryKeySelective(banner);
    }
}
