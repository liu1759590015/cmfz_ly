package com.baizhi.entity;

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
@Table(name = "ablum")
public class Album {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String title;
    private Integer amount;
    private String imgPath;
    private Double score;
    private String author;
    //播音人
    @Column(name = "boardcast")
    private String boardCast;
    private Date publishDate;
    private String bref;
    private List<Chapter> chapters;
}
