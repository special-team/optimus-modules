package com.github.ooknight.rubik.optimus.archer.platform.schema;

import com.github.ooknight.rubik.core.schema.AbstractSchema;
import com.github.ooknight.rubik.optimus.archer.platform.entity.query.QSetting;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class PlatformSchema extends AbstractSchema {

    public final PlatformSchema platform = new PlatformSchema();
    //
    public QSetting setting = QSetting.alias();
}
