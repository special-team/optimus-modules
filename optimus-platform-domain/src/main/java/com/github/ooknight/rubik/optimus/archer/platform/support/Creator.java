package com.github.ooknight.rubik.optimus.archer.platform.support;

import com.github.ooknight.rubik.optimus.archer.platform.entity.Account;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Group;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Role;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 用于创建对象, 替代 `new` 和 `set`
 * 使页面字段有默认值，主要用户新增 (create) 页面
 * 所有默认值都在此类中处理, Controller 和 HTML 中不用再处理
 * 例:
 * model.addAttribute("account", creator.account());
 **/
@Mapper
public interface Creator {

    Creator creator = Mappers.getMapper(Creator.class);

    default Account account() {
        return new Account();
    }

    default Group group() {
        return new Group();
    }

    default Role role() {
        return new Role();
    }
}
