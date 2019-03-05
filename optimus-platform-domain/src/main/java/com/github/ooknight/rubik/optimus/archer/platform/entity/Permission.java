package com.github.ooknight.rubik.optimus.archer.platform.entity;

import com.github.ooknight.rubik.core.entity.UEntity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "e_platform_permission")
public class Permission extends UEntity {

    @Column(name = "name_")
    private String name;
    @Column(name = "code_")
    private String code;
}
