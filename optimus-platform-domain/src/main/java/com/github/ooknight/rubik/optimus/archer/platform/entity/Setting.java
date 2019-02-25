package com.github.ooknight.rubik.optimus.archer.platform.entity;

import com.github.ooknight.rubik.core.entity.OEntity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "e_platform_setting")
public class Setting extends OEntity {

    @Column(name = "key_")
    private String key;
    @Column(name = "value_")
    private String value;
}
