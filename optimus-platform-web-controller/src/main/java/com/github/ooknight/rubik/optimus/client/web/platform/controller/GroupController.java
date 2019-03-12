package com.github.ooknight.rubik.optimus.client.web.platform.controller;

import optimus.WEB;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Group;
import com.github.ooknight.rubik.optimus.client.web.platform.PlatformEngine;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

@Controller
public class GroupController {

    @Resource
    private PlatformEngine engine;

    @PreAuthorize("hasPermission('group:create','')")
    @GetMapping(value = "/portal/group/create")
    public String create(Model model) {
        //model.addAttribute("group", Creator.GROUP());
        return "portal/group/create";
    }

    @PreAuthorize("hasPermission('group:create','')")
    @PostMapping(value = "/portal/group/create")
    public String create(Group group) {
        engine.platform().create(group);
        return "redirect:/portal/group/browse";
    }

    @PreAuthorize("hasPermission('group:update','')")
    @GetMapping(value = "/portal/group/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("group", engine.platform().group(id).orElseThrow(WEB::ERROR_HTTP_404));
        return "portal/group/update";
    }

    @PreAuthorize("hasPermission('group:update','')")
    @PostMapping(value = "/portal/group/update/{id}")
    public String update(@PathVariable Long id, Group group) {
        group.setId(id);
        engine.platform().update(group);
        return "redirect:/portal/group/browse";
    }

    @PreAuthorize("hasPermission('group:detail','')")
    @GetMapping(value = "/portal/group/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("group", engine.platform().group(id).orElseThrow(WEB::ERROR_HTTP_404));
        return "portal/group/detail";
    }

    @PreAuthorize("hasPermission('group:browse','')")
    @GetMapping("/portal/group/browse")
    public String browse(Model model) {
        model.addAttribute("groupList", engine.platform().group());
        return "portal/group/browse";
    }
}
