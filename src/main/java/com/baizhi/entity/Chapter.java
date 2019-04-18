package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chapter {
    @GeneratedValue(generator = "UUID")
    @Id
    private String id;
    private String title;
    private String size;
    //时长
    private String duration;
    @JSONField(format = "YYYY-MM-dd HH:mm:ss")
    private Date publishDate;
    private String radioPath;
    @Transient
    private Album album;
}
