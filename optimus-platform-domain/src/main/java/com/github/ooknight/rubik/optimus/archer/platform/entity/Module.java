package com.github.ooknight.rubik.optimus.archer.platform.entity;

import com.github.ooknight.rubik.core.entity.UEntity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "e_platform_module")
public class Module extends UEntity {

    @Column(name = "name_")
    private String name;
    @Column(name = "icon_")
    private String icon;
    @Column(name = "ordinal_")
    private Integer ordinal;
    @OneToMany(mappedBy = "module")
    private List<Function> function;
}
