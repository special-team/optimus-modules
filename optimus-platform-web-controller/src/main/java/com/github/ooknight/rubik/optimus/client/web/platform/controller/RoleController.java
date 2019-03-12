package com.github.ooknight.rubik.optimus.client.web.platform.controller;

import optimus.WEB;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Role;
import com.github.ooknight.rubik.optimus.client.web.platform.PlatformEngine;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

@Controller
public class RoleController {

    @Resource
    private PlatformEngine engine;

    @PreAuthorize("hasPermission('role:create','')")
    @GetMapping(value = "/portal/role/create")
    public String create(Model model) {
        //model.addAttribute("role", Creator.ROLE());
        return "portal/role/create";
    }

    @PreAuthorize("hasPermission('role:create','')")
    @PostMapping(value = "/portal/role/create")
    public String create(Role role) {
        engine.platform().create(role);
        return "redirect:/portal/role/browse";
    }

    @PreAuthorize("hasPermission('role:update','')")
    @GetMapping(value = "/portal/role/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("role", engine.platform().role(id).orElseThrow(WEB::ERROR_HTTP_404));
        return "portal/role/update";
    }

    @PreAuthorize("hasPermission('role:update','')")
    @PostMapping(value = "/portal/role/update/{id}")
    public String update(@PathVariable Long id, Role role) {
        role.setId(id);
        engine.platform().update(role);
        return "redirect:/portal/role/browse";
    }

    @PreAuthorize("hasPermission('role:detail','')")
    @GetMapping(value = "/portal/role/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("role", engine.platform().role(id).orElseThrow(WEB::ERROR_HTTP_404));
        return "portal/role/detail";
    }

    @PreAuthorize("hasPermission('role:browse','')")
    @GetMapping(value = "/portal/role/browse")
    public String browse(Model model) {
        model.addAttribute("roleList", engine.platform().role());
        return "portal/role/browse";
    }
}
