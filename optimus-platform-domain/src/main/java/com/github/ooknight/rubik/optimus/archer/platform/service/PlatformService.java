package com.github.ooknight.rubik.optimus.archer.platform.service;

import com.github.ooknight.rubik.core.service.IService;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Account;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Function;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Group;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Module;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Role;

import java.util.List;
import java.util.Optional;

public interface PlatformService extends IService {

    void create(Account account);

    void update(Account account);

    void create(Group group);

    void update(Group group);

    void create(Role role);

    void update(Role role);

    List<Module> menu();

    List<Function> shortcut();

    Optional<String> setting(String key);

    void setting(String key, String value);

    void password(Long accountId, String password);
}
