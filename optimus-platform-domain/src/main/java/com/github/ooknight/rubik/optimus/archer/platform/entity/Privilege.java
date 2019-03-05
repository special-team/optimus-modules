package com.github.ooknight.rubik.optimus.archer.platform.entity;

import com.github.ooknight.rubik.core.entity.UEntity;

import io.ebean.annotation.DbJson;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "e_platform_privilege")
public class Privilege extends UEntity {

    @ManyToOne
    @JoinColumn(name = "group_id_")
    private Group group;
    @ManyToOne
    @JoinColumn(name = "role_id_")
    private Role role;
    @Column(name = "entity_")
    private Class entity;
    @Column(name = "field_")
    private String field;
    @Column(name = "operator_")
    private String operator;
    @Column(name = "value_")
    private String value;
    @DbJson
    @Column(name = "restricted_")
    private Set<String> restricted;
}
