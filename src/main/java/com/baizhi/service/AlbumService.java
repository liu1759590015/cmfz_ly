package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.List;

public interface AlbumService {
    public List<Album> queryAll();

    public void insert(Album album);

    public List<Album> selectAll();

    public Album selectById(int id);

    public void update(Album album);
}
