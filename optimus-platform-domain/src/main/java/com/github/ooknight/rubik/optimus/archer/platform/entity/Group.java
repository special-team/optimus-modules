package com.github.ooknight.rubik.optimus.archer.platform.entity;

import com.github.ooknight.rubik.core.entity.UEntity;

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
@Table(name = "e_platform_group")
public class Group extends UEntity {

    @Column(name = "name_")
    private String name;
    @Column(name = "short_name_")
    private String shortName;
    @ManyToOne
    @JoinColumn(name = "superior_id_")
    private Group superior;
}
