package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @KeySql(useGeneratedKeys = true)
    @Excel(name = "编号")
    private Integer id;
    @Excel(name = "名字")
    private String name;
    @Excel(name = "法号")
    private String dharma;
    @Excel(name = "性别")
    private Integer sex;
    @Excel(name = "省份")
    private String province;
    @Excel(name = "城市")
    private String city;
    @Excel(name = "签名")
    private String sign;
    @Excel(name = "状态")
    private Integer status;
    @Excel(name = "手机号")
    private String phone;
    @Excel(name = "密码")
    private String password;
    @Excel(name = "盐值")
    private String salt;
    @Excel(name = "图片")
    private String headImg;
    @JSONField(format = "YYYY-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @Excel(name = "注册时间", format = "yyyy年MM月dd日HH时mm分ss秒")
    private Date createDate;
}
