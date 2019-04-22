package com.baizhi.service;

import com.baizhi.entity.Chapter;

import java.util.List;

public interface ChapterService {
    void insert(Chapter chapter);

    List<Chapter> selectAll();
}
