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
@Table(name = "e_platform_message")
public class Message extends UEntity {

    @Column(name = "title_")
    private String title;
    @Column(name = "content_")
    private String content;
    @ManyToOne
    @JoinColumn(name = "from_")
    private Account from;
    @Column(name = "to_")
    private String to;
}
