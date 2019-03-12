package com.github.ooknight.rubik.optimus.archer.platform.service;

import com.github.ooknight.rubik.core.service.IService;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Account;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Function;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Group;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Module;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Role;
import com.github.ooknight.rubik.optimus.archer.platform.entity.query.QAccount;
import com.github.ooknight.rubik.optimus.archer.platform.entity.query.QGroup;
import com.github.ooknight.rubik.optimus.archer.platform.entity.query.QRole;

import java.util.List;
import java.util.Optional;

public interface PlatformService extends IService {

    /* ========= ========= ========= ========= ========= ========= ========= ========= ========= account */

    void create(Account account);

    void update(Account account);

    List<Account> account();

    Optional<Account> account(Long id);

    QAccount createAccountQuery();

    /* ========= ========= ========= ========= ========= ========= ========= ========= ========= group */

    void create(Group group);

    void update(Group group);

    List<Group> group();

    Optional<Group> group(Long id);

    QGroup createGroupQuery();

    /* ========= ========= ========= ========= ========= ========= ========= ========= ========= role */

    void create(Role role);

    void update(Role role);

    List<Role> role();

    Optional<Role> role(Long id);

    QRole createRoleQuery();

    /* ========= ========= ========= ========= ========= ========= ========= ========= ========= others */

    List<Module> menu();

    List<Function> shortcut();

    Optional<String> setting(String key);

    void setting(String key, String value);

    void password(Long account, String password);
}
