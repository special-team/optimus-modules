package com.github.ooknight.rubik.optimus.client.web.platform.controller;

import optimus.WEB;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Account;
import com.github.ooknight.rubik.optimus.client.web.platform.PlatformEngine;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

@Controller
public class UserController {

    @Resource
    private PlatformEngine engine;

    @PreAuthorize("hasPermission('user:create','')")
    @GetMapping(value = "/portal/user/create")
    public String create(Model model) {
        //model.addAttribute("account", Creator.ACCOUNT());
        return "portal/user/create";
    }

    @PreAuthorize("hasPermission('user:create','')")
    @PostMapping(value = "/portal/user/create")
    public String create(Account account) {
        account.setPassword(engine.platform().setting("").orElse(null));
        engine.platform().create(account);
        return "redirect:/portal/user/browse";
    }

    @PreAuthorize("hasPermission('user:update','')")
    @GetMapping(value = "/portal/user/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("account", engine.platform().account(id).orElseThrow(WEB::ERROR_HTTP_404));
        return "portal/user/update";
    }

    @PreAuthorize("hasPermission('user:update','')")
    @PostMapping(value = "/portal/user/update/{id}")
    public String update(@PathVariable Long id, Account account) {
        account.setId(id);
        engine.platform().update(account);
        return "redirect:/portal/user/browse";
    }

    @PreAuthorize("hasPermission('user:detail','')")
    @GetMapping(value = "/portal/user/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("account", engine.platform().createAccountQuery().group.fetch().role.fetch().setId(id).findOneOrEmpty().orElseThrow(WEB::ERROR_HTTP_404));
        return "portal/user/detail";
    }

    @PreAuthorize("hasPermission('user:browse','')")
    @GetMapping(value = "/portal/user/browse")
    public String browse(Model model) {
        model.addAttribute("userList", engine.platform().createAccountQuery().group.fetch().role.fetch().findList());
        return "portal/user/browse";
    }
}
