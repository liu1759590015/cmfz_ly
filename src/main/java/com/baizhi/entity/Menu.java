package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "menu")
public class Menu {
    private Integer id;
    private String title;
    @Column(name = "icon_cls")
    private String iconCls;
    @Column(name = "parent_id")
    private Integer parentId;
    @Column(name = "jsp_url")
    private String jspUrl;
    @Transient
    private List<Menu> list;
}
