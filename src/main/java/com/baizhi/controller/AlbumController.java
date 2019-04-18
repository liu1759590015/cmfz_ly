package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("queryAll")
    @ResponseBody
    public List<Album> queryAll() {
        List<Album> albums = albumService.queryAll();
        System.out.println(albums);
        return albums;
    }

    @RequestMapping("addAlbum")
    @ResponseBody
    public Map addAlbum(Album album, MultipartFile file, HttpServletRequest request) throws IOException {
        String fileName = file.getOriginalFilename();
        String endName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + endName;
        file.transferTo(new File("D:\\webjieduan\\maven\\cmfz_ly\\src\\main\\webapp\\audioCollection\\" + fileName));
        /*File file1=new File(request.getServletContext().getContextPath()+"/index/"+fileName);*/
        album.setImgPath("/audioCollection/" + fileName);
        album.setAmount(0);
        album.setPublishDate(new Date());
        //System.err.println(banner);
        Map map = new HashMap();
        try {
            albumService.insert(album);
            map.put("isInsert", true);
        } catch (Exception e) {
            map.put("isInsert", false);
            e.printStackTrace();
        }
        return map;
    }

}
