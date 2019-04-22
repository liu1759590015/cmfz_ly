package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ChapterService;
import com.baizhi.utils.AudioUtil;
import com.baizhi.utils.FileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @Autowired
    private AlbumService albumService;

    @RequestMapping("addChapter")
    @ResponseBody
    public Map addChapter(Chapter chapter, MultipartFile file, HttpSession session) {
        //System.out.println(chapter);
        String realPath = session.getServletContext().getRealPath("/radio");
        /*//获取到文件上传的目录
        String realPath = session.getServletContext().getRealPath("/");
        String dir = realPath + "audio";
        File file = new File(dir);
        if (!file.exists()){
            file.mkdir();
        }*/
        String fileName = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(fileName);
        String newName = UUID.randomUUID().toString() + "." + extension;
        Map map = new HashMap();
        File file1 = null;
        try {
            file1 = new File(realPath, newName);
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //手动导入jar包到本地仓库
        /*mvn install:install-file -Dfile=D:\后期阶段\jar\jave-1.0.2.jar -DgroupId=com.baizhi -DartifactId=jave -Dversion=1.0.2.RELEASE -Dpackaging=jar*/
        //获取时长
        Long duration = AudioUtil.getDuration(file1);
        String dateTime = FileUtil.formatDateTime(duration);
        chapter.setDuration(dateTime);
        String size = FileUtil.getPrintSize(file.getSize());
        chapter.setSize(size);
        chapter.setPublishDate(new Date());
        chapter.setRadioPath("/radio/" + newName);
        chapter.setId(UUID.randomUUID().toString());
        try {
            chapterService.insert(chapter);
            Album album = albumService.selectById(chapter.getAlbumId());
            album.setAmount(album.getAmount() + 1);
            albumService.update(album);
            map.put("isInsert", true);
        } catch (Exception e) {
            map.put("isInsert", false);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("download")
    public void downLoad(Chapter chapter, HttpSession session, HttpServletResponse response) {
        //获取文件的路径
        String realPath = session.getServletContext().getRealPath("/");
        String filePath = realPath + chapter.getRadioPath();
        System.out.println(filePath);
        File file = new File(filePath);
        //修改下载时的名字
        String extension = FilenameUtils.getExtension(chapter.getRadioPath());
        String oldName = chapter.getTitle() + "." + extension;
        //下载
        //设置响应的编码
        String encode = null;
        try {
            encode = URLEncoder.encode(oldName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //设置响应头
        response.setHeader("Content-Disposition", "attachment;fileName=" + encode);
        //设置响应类型
        response.setContentType("audio/mpeg");

        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

