package com.github.ooknight.rubik.optimus.kernel.platform.service.impl;

import com.github.ooknight.rubik.core.query.QueryEngine;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Account;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Function;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Group;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Module;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Role;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Setting;
import com.github.ooknight.rubik.optimus.archer.platform.entity.query.QAccount;
import com.github.ooknight.rubik.optimus.archer.platform.entity.query.QFunction;
import com.github.ooknight.rubik.optimus.archer.platform.entity.query.QModule;
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

import static com.github.ooknight.rubik.core.schema.AbstractSchema.column;
import static com.github.ooknight.rubik.optimus.archer.platform.schema.PlatformSchema.platform;
import static com.github.ooknight.rubik.optimus.archer.platform.schema.PlatformSchema.setting;

@Service
public class PlatformServiceImpl implements PlatformService {

    @Resource
    private Database db;

    @Override
    public void create(Account account) {
        db.insert(account);
    }

    @Override
    public void update(Account account) {
        db.update(account);
    }

    @Override
    public void create(Group group) {
        db.insert(group);
    }

    @Override
    public void update(Group group) {
        db.update(group);
    }

    @Override
    public void create(Role role) {
        db.insert(role);
    }

    @Override
    public void update(Role role) {
        db.update(role);
    }

    @Cacheable("menu")
    @Override
    public List<Module> menu() {
        return QueryEngine.QUERY(QModule.class)
            .function.filterMany(QueryEngine.QUERY(QFunction.class).display.in(DisplayMode.MENU, DisplayMode.MENU_AND_SHORTCUT).getExpressionList())
            .ordinal.asc().function.ordinal.asc().findList();
    }

    @Cacheable("shortcut")
    @Override
    public List<Function> shortcut() {
        return QueryEngine.QUERY(QFunction.class).display.in(DisplayMode.SHORTCUT, DisplayMode.MENU_AND_SHORTCUT).ordinal.asc().findList();
    }

    @Cacheable("setting")
    @Override
    public Optional<String> setting(String key) {
        return new QSetting().select(setting.value).key.equalTo(key).findOneOrEmpty().map(Setting::getValue);
    }

    @CacheEvict(value = "setting", key = "#key")
    @Override
    public void setting(String key, String value) {
        db.update(Setting.class).set(column(platform.setting.value), value).where().eq(column(platform.setting.key), key).update();
    }

    @Override
    public void changeAccountPassword(Long accountId, String password) {
        db.update(Account.class).set(QAccount.alias().password.toString(), password).where().idEq(accountId).update();
    }
}
