package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ablum")
public class Ablum {
    @Id
    @KeySql(useGeneratedKeys = true)
    private String id;
    private String title;
    private Integer amount;
    @Column(name = "img_path")
    private String imgPath;
    private Double score;
    private String author;
    //播音人
    @Column(name = "boardcast")
    private String boardCast;
    @Column(name = "publish_date")
    private Date publishDate;
    private String bref;
}
