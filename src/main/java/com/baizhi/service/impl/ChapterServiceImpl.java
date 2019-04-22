package com.baizhi.service.impl;

import com.baizhi.entity.Chapter;
import com.baizhi.mapper.ChapterMapper;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterMapper chapterMapper;

    @Override
    public void insert(Chapter chapter) {
        chapterMapper.insertSelective(chapter);
    }

    @Override
    public List<Chapter> selectAll() {
        return chapterMapper.selectAll();
    }
}
