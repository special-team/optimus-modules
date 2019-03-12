package com.github.ooknight.rubik.optimus.kernel.platform.service.impl;

import com.github.ooknight.rubik.optimus.archer.platform.entity.Account;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Function;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Group;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Module;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Role;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Setting;
import com.github.ooknight.rubik.optimus.archer.platform.entity.query.QAccount;
import com.github.ooknight.rubik.optimus.archer.platform.entity.query.QFunction;
import com.github.ooknight.rubik.optimus.archer.platform.entity.query.QGroup;
import com.github.ooknight.rubik.optimus.archer.platform.entity.query.QModule;
import com.github.ooknight.rubik.optimus.archer.platform.entity.query.QRole;
import com.github.ooknight.rubik.optimus.archer.platform.entity.query.QSetting;
import com.github.ooknight.rubik.optimus.archer.platform.enums.DisplayMode;
import com.github.ooknight.rubik.optimus.archer.platform.service.PlatformService;

import io.ebean.Database;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static com.github.ooknight.rubik.core.schema.AbstractSchema.p;
import static com.github.ooknight.rubik.optimus.archer.platform.schema.PlatformSchema.platform;

@Service
public class PlatformServiceImpl implements PlatformService {

    @Resource
    private Database db;

    /* ========= ========= ========= ========= ========= ========= ========= ========= ========= account */

    @Override
    public void create(Account account) {
        db.insert(account);
    }

    @Override
    public void update(Account account) {
        db.update(account);
    }

    @Override
    public List<Account> account() {
        return db.createQuery(Account.class).findList();
    }

    @Override
    public Optional<Account> account(Long id) {
        return db.createQuery(Account.class).setId(id).findOneOrEmpty();
    }

    @Override
    public QAccount createAccountQuery() {
        return new QAccount();
    }

    /* ========= ========= ========= ========= ========= ========= ========= ========= ========= group */

    @Override
    public void create(Group group) {
        db.insert(group);
    }

    @Override
    public void update(Group group) {
        db.update(group);
    }

    @Override
    public List<Group> group() {
        return db.find(Group.class).findList();
    }

    @Override
    public Optional<Group> group(Long id) {
        return db.createQuery(Group.class).setId(id).findOneOrEmpty();
    }

    @Override
    public QGroup createGroupQuery() {
        return new QGroup();
    }

    /* ========= ========= ========= ========= ========= ========= ========= ========= ========= role */

    @Override
    public void create(Role role) {
        db.insert(role);
    }

    @Override
    public void update(Role role) {
        db.update(role);
    }

    @Override
    public List<Role> role() {
        return db.createQuery(Role.class).findList();
    }

    @Override
    public Optional<Role> role(Long id) {
        return db.createQuery(Role.class).setId(id).findOneOrEmpty();
    }

    @Override
    public QRole createRoleQuery() {
        return new QRole();
    }

    /* ========= ========= ========= ========= ========= ========= ========= ========= ========= others */

    @Cacheable("menu")
    @Override
    public List<Module> menu() {
        return new QModule()
            .function.filterMany(new QFunction().display.in(DisplayMode.MENU, DisplayMode.MENU_AND_SHORTCUT).getExpressionList())
            .ordinal.asc().function.ordinal.asc().findList();
    }

    @Cacheable("shortcut")
    @Override
    public List<Function> shortcut() {
        return new QFunction().display.in(DisplayMode.SHORTCUT, DisplayMode.MENU_AND_SHORTCUT).ordinal.asc().findList();
    }

    @Cacheable("setting")
    @Override
    public Optional<String> setting(String key) {
        return new QSetting().key.equalTo(key).findOneOrEmpty().map(Setting::getValue);
    }

    @CacheEvict(value = "setting", key = "#key")
    @Override
    public void setting(String key, String value) {
        new QSetting().key.eq(key).asUpdate().set(p(platform.setting.value), value).update();
    }

    @Override
    public void password(Long accountId, String password) {
        new QAccount().id.eq(accountId).asUpdate().set(p(platform.account.password), password).update();
    }
}
