package com.github.ooknight.rubik.optimus.archer.platform.schema;

import com.github.ooknight.rubik.core.schema.AbstractSchema;
import com.github.ooknight.rubik.optimus.archer.platform.entity.query.QAccount;
import com.github.ooknight.rubik.optimus.archer.platform.entity.query.QFunction;
import com.github.ooknight.rubik.optimus.archer.platform.entity.query.QGroup;
import com.github.ooknight.rubik.optimus.archer.platform.entity.query.QMessage;
import com.github.ooknight.rubik.optimus.archer.platform.entity.query.QModule;
import com.github.ooknight.rubik.optimus.archer.platform.entity.query.QPermission;
import com.github.ooknight.rubik.optimus.archer.platform.entity.query.QPrivilege;
import com.github.ooknight.rubik.optimus.archer.platform.entity.query.QRole;
import com.github.ooknight.rubik.optimus.archer.platform.entity.query.QSetting;

public final class PlatformSchema extends AbstractSchema {

    public static final PlatformSchema platform = new PlatformSchema();
    //
    public QAccount account = QAccount.alias();
    public QFunction function = QFunction.alias();
    public QGroup group = QGroup.alias();
    public QMessage message = QMessage.alias();
    public QModule module = QModule.alias();
    public QPermission permission = QPermission.alias();
    public QPrivilege privilege = QPrivilege.alias();
    public QRole role = QRole.alias();
    public QSetting setting = QSetting.alias();
}
