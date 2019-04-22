package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "album")
@ExcelTarget(value = "album")
public class Album {
    @Id
    @KeySql(useGeneratedKeys = true)
    @ExcelIgnore
    private Integer id;
    @Excel(name = "专辑标题", needMerge = true)
    private String title;
    @Excel(name = "章节数量", needMerge = true)
    private Integer amount;
    @Excel(name = "专辑图像", type = 2, needMerge = true)
    private String imgPath;
    @Excel(name = "专辑评分", needMerge = true)
    private Integer score;
    @Excel(name = "作者", needMerge = true)
    private String author;
    //播音人
    @Excel(name = "播音人", needMerge = true)
    @Column(name = "board_cast")
    private String boardCast;
    @JSONField(format = "YYYY-MM-dd HH:mm:ss")
    @Excel(name = "发行时间", format = "yyyy年MM月dd日HH时mm分ss秒", width = 50, needMerge = true)
    private Date publishDate;
    @Excel(name = "简介", needMerge = true)
    private String brief;
    @ExcelCollection(name = "持明法洲-----吉祥妙音")
    private List<Chapter> children;
}
