package com.github.ooknight.rubik.optimus.client.web.platform;

import com.github.ooknight.rubik.optimus.archer.platform.service.PlatformService;
import com.github.ooknight.rubik.optimus.archer.platform.service.SecurityUserService;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class PlatformEngine {

    @Resource
    private PlatformService platform;
    @Resource
    private SecurityUserService security;

    public PlatformService platform() {
        return platform;
    }

    public SecurityUserService security() {
        return security;
    }
}
