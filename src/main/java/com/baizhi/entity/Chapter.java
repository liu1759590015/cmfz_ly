package com.baizhi.entity;

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
    private Double size;
    //时长
    private Double duration;
    private Date publishDate;
    @Transient
    private Album album;
}
