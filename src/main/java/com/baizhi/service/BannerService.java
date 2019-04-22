package com.baizhi.service;

import com.baizhi.entity.Banner;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Map;

public interface BannerService {
    Map selectAll(int page, int rows);

    void addBanner(Banner banner);

    void delete(Banner banner);

    void update(Banner banner);

    Workbook deriveExcel();
}
