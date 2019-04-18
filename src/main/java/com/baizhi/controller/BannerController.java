package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("selectAll")
    @ResponseBody
    public Map selectAll(int page, int rows) {
        //System.err.println("1123");
        Map map = bannerService.selectAll(page, rows);
        //System.out.println(map);
        return map;
    }

    @RequestMapping("addBanner")
    @ResponseBody
    public Map addBanner(Banner banner, MultipartFile file, HttpServletRequest request) throws IOException {
        String fileName = file.getOriginalFilename();
        String endName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + endName;
        file.transferTo(new File("D:\\webjieduan\\maven\\cmfz_ly\\src\\main\\webapp\\index\\" + fileName));
        /*File file1=new File(request.getServletContext().getContextPath()+"/index/"+fileName);*/
        banner.setImgPath("/index/" + fileName);
        banner.setCreateDate(new Date());
        banner.setStatus("on");
        //System.err.println(banner);
        Map map = new HashMap();
        try {
            bannerService.addBanner(banner);
            map.put("isInsert", true);
        } catch (Exception e) {
            map.put("isInsert", false);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("deleteBanner")
    @ResponseBody
    public Map deleteBanner(Banner banner) {
        Map map = new HashMap();
        try {
            File file = new File("D:\\webjieduan\\maven\\cmfz_ly\\src\\main\\webapp" + banner.getImgPath());
            file.delete();
            bannerService.delete(banner);
            map.put("isDelete", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("isDelete", false);
        }
        return map;
    }

    @RequestMapping("updateBanner")
    @ResponseBody
    public Map updateBanner(Banner banner, MultipartFile file) throws IOException {
        if (file != null) {
            System.out.println(file);
            String fileName = file.getOriginalFilename();
            System.err.println(fileName);
            String endName = fileName.substring(fileName.lastIndexOf("."));
            fileName = UUID.randomUUID() + endName;
            file.transferTo(new File("D:\\webjieduan\\maven\\cmfz_ly\\src\\main\\webapp\\index\\" + fileName));
            /*File file1=new File(request.getServletContext().getContextPath()+"/index/"+fileName);*/
            banner.setImgPath("/index/" + fileName);
        }
        Map map = new HashMap();
        try {
            bannerService.update(banner);
            map.put("isUpdate", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("isUpdate", false);
        }
        return map;
    }
}
