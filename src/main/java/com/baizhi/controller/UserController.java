package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("selectAll")
    @ResponseBody
    public Map selectAll(int page, int rows) {
        Map map = userService.selectAll(page, rows);
        return map;
    }

    @RequestMapping("insert")
    @ResponseBody
    public Map insert(User user, MultipartFile file, HttpSession session) {
        String realPath = session.getServletContext().getRealPath("/");
        //获取到文件上传的目录
        String dir = realPath + "user";
        File file1 = new File(dir);
        if (!file1.exists()) {
            file1.mkdir();
        }
        String fileName = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(fileName);
        String newName = UUID.randomUUID().toString() + "." + extension;
        File file2 = null;
        try {
            file2 = new File(dir, newName);
            file.transferTo(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map map = new HashMap();
        user.setHeadImg("/user/" + newName);
        user.setStatus(0);
        user.setCreateDate(new Date());
        try {
            userService.insert(user);
            map.put("isInsert", true);
        } catch (Exception e) {
            map.put("isInsert", false);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("update")
    @ResponseBody
    public Map update(User user, MultipartFile file, HttpSession session) {
        if (file != null) {
            String realPath = session.getServletContext().getRealPath("/");
            //获取到文件上传的目录
            String dir = realPath + "user";
            File file1 = new File(dir);
            if (!file1.exists()) {
                file1.mkdir();
            }
            String fileName = file.getOriginalFilename();
            String extension = FilenameUtils.getExtension(fileName);
            String newName = UUID.randomUUID().toString() + "." + extension;
            File file2 = null;
            try {
                file2 = new File(dir, newName);
                file.transferTo(file2);
                user.setHeadImg("/user/" + newName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Map map = new HashMap();
        try {
            userService.update(user);
            map.put("isUpdate", true);
        } catch (Exception e) {
            map.put("isUpdate", false);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("delete")
    @ResponseBody
    public Map delete(User user, HttpSession session) {
        Map map = new HashMap();
        try {
            String realPath = session.getServletContext().getRealPath("");
            File file = new File(realPath + user.getHeadImg());
            file.delete();
            userService.delete(user);
            map.put("isDelete", true);
        } catch (Exception e) {
            map.put("isDelete", false);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("deriveUser")
    public void deriveUser(HttpServletResponse response) {
        List<User> users = userService.select();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户", "用户表"),
                User.class, users);
        OutputStream out = null;
        try {
            response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode("用户导出文件.xls", "UTF-8"))));
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
