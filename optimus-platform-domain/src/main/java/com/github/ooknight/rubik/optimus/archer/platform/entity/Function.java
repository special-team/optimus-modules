package com.github.ooknight.rubik.optimus.archer.platform.entity;

import com.github.ooknight.rubik.core.entity.UEntity;
import com.github.ooknight.rubik.optimus.archer.platform.enums.DisplayMode;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "e_platform_function")
public class Function extends UEntity {

    @Column(name = "name_")
    private String name;
    @Column(name = "code_")
    private String code;
    @ManyToOne
    @JoinColumn(name = "module_id_")
    private Module module;
    @ManyToOne
    @JoinColumn(name = "parent_")
    private Function parent;
    @Column(name = "url_")
    private String url;
    @Column(name = "icon_")
    private String icon;
    @Column(name = "type_")
    private String type;
    @Column(name = "display_")
    private DisplayMode display;
    @Column(name = "is_lock_")
    private Integer isLock;
    @Column(name = "ordinal_")
    private Integer ordinal;
}
