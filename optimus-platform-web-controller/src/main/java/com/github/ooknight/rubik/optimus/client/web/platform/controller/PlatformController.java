package com.github.ooknight.rubik.optimus.client.web.platform.controller;

import com.github.ooknight.rubik.core.client.web.security.annotation.U;
import com.github.ooknight.rubik.optimus.client.web.platform.PlatformEngine;
import com.github.ooknight.rubik.prototype.authority.SessionUser;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class PlatformController {

    @Resource
    private PlatformEngine engine;

    @GetMapping("/")
    public String root() {
        return "redirect:/portal/dashboard";
    }

    @RequestMapping("/login")
    public String login(Model model, String error) {
        if (error != null) {
            model.addAttribute("message", "无效的用户名或密码");
        }
        return "login";
    }

    //@PreAuthorize("hasPermission('dashboard',T(org.ufox.rubik.core.client.web.security.access.Permission).READ)")
    @GetMapping("/portal/dashboard")
    public String dashboard() {
        return "portal/dashboard";
    }

    @GetMapping("/portal/profile")
    public String profile() {
        return "portal/profile";
    }

    @PostMapping("/portal/password")
    public String password(@RequestParam("npassword") String npassword, @U SessionUser su) {
        engine.platform().password(su.uid(), npassword);
        return "redirect:/portal/profile";
    }

    /*
    @GetMapping("/portal/admin/cache")
    public String cache(Model model) {
        model.addAttribute("info", cacheAgent.info());
        return "portal/admin/cache";
    }

    @PostMapping(value = "/portal/admin/cache", params = "operation=clear")
    public String cache() {
        cacheAgent.clear();
        return "portal/admin/cache";
    }
    */

    @GetMapping("/portal/debug")
    public String debug() {
        return "portal/debug";
    }
}
