package com.github.ooknight.rubik.optimus.archer.platform.entity;

import com.github.ooknight.rubik.core.entity.UEntity;
import com.github.ooknight.rubik.prototype.authority.SessionUserType;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "e_platform_account")
public class Account extends UEntity {

    @Column(name = "username_")
    private String username;
    @Column(name = "password_")
    private String password;
    @Column(name = "nickname_")
    private String nickname;
    @Column(name = "certificate_type_")
    private String certificateType;
    @Column(name = "certificate_number_")
    private String certificateNumber;
    @Column(name = "mobile_")
    private String mobile;
    @Column(name = "email_")
    private String email;
    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id_")
    private Role role;
    @ManyToOne
    @JoinColumn(name = "group_id_")
    private Group group;
    @Column(name = "type_")
    @Enumerated(EnumType.STRING)
    private SessionUserType type;
}
