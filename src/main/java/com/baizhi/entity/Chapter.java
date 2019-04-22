package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chapter {
    /*@GeneratedValue(generator = "UUID")*/
    @Id
    @Excel(name = "编号")
    private String id;
    @Excel(name = "title")
    private String title;
    @Excel(name = "章节大小")
    private String size;
    //时长
    @Excel(name = "章节时长")
    private String duration;
    @Excel(name = "发行时间", format = "yyyy年MM月dd日HH时mm分ss秒", width = 50)
    @JSONField(format = "YYYY-MM-dd HH:mm:ss")
    private Date publishDate;

    private String radioPath;
    @Transient
    private Album album;
    private Integer albumId;
}
