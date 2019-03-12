package com.github.ooknight.rubik.optimus.client.web.platform;

import com.github.ooknight.rubik.optimus.archer.platform.service.PlatformService;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class PlatformEngine {

    @Resource
    private PlatformService platform;

    public PlatformService platform() {
        return platform;
    }
}
