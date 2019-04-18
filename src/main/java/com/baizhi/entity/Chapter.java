package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chapter {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String title;
    private Double size;
    //时长
    private Double duration;
    @Column(name = "publish_date")
    private Date publishDate;
    private Ablum ablum;
}
