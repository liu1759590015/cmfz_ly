package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ChapterService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ChapterService chapterService;
    @RequestMapping("queryAll")
    @ResponseBody
    public List<Album> queryAll() {
        List<Album> albums = albumService.queryAll();
        //System.out.println(albums);
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
        album.setScore(5);
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

    @RequestMapping("selectAlbum")
    @ResponseBody
    public List<Album> selectAlbum() {
        List<Album> albums = albumService.selectAll();
        //System.err.println("专辑"+albums);
        return albums;
    }

    @RequestMapping("deriveAlbum")
    public void DeriveAlbum(HttpSession session, HttpServletResponse response) {
        List<Album> albums = albumService.queryAll();
        String path = session.getServletContext().getRealPath("/");
        for (Album album : albums) {
            album.setImgPath(path + album.getImgPath());
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("专辑章节", "章节"),
                Album.class, albums);
        OutputStream out = null;
        try {
            response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode("专辑导出文件.xls", "UTF-8"))));
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            out = response.getOutputStream();
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
