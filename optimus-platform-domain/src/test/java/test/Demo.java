package test;

import com.github.ooknight.rubik.optimus.archer.platform.entity.Account;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Function;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Group;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Message;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Module;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Permission;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Privilege;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Role;
import com.github.ooknight.rubik.optimus.archer.platform.entity.Setting;
import com.github.ooknight.rubik.optimus.archer.platform.enums.DisplayMode;
import com.github.ooknight.rubik.optimus.archer.platform.schema.PlatformSchema;

import io.ebean.Database;
import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;
import io.ebean.datasource.DataSourceConfig;
import org.junit.Before;
import org.junit.Test;

import static com.github.ooknight.rubik.optimus.archer.platform.schema.PlatformSchema.p;
import static com.github.ooknight.rubik.optimus.archer.platform.schema.PlatformSchema.platform;

public class Demo {

    private Database db;

    @Before
    public void before() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDriver("org.h2.Driver");
        //dsc.setUrl("jdbc:h2:mem:demo_db;INIT=RUNSCRIPT FROM 'classpath:/db-schema.ddl'\\;RUNSCRIPT FROM 'classpath:/db-datum.ddl'");
        dsc.setUrl("jdbc:h2:mem:demo_db;INIT=RUNSCRIPT FROM 'classpath:/db-schema.h2.ddl';");
        dsc.setUsername("sa");
        dsc.setPassword("sa");
        //
        DatabaseConfig dbc = new DatabaseConfig();
        dbc.setDataSourceConfig(dsc);
        //sc.setDatabaseBooleanTrue("1");
        //sc.setDatabaseBooleanFalse("0");
        //dbc.setAutoCommitMode(true);
        //dbc.setDefaultServer(true);
        //dbc.setRegister(true);
        //
        db = DatabaseFactory.create(dbc);
    }

    @Test
    public void test() {
        System.out.println(db.find(Account.class).findList().size());
        System.out.println(db.find(Function.class).findList().size());
        System.out.println(db.find(Group.class).findList().size());
        System.out.println(db.find(Message.class).findList().size());
        System.out.println(db.find(Module.class).findList().size());
        System.out.println(db.find(Permission.class).findList().size());
        System.out.println(db.find(Privilege.class).findList().size());
        System.out.println(db.find(Role.class).findList().size());
        System.out.println(db.find(Setting.class).findList().size());
    }

    @Test
    public void test1() {
        System.out.println(DisplayMode.NONE);
        System.out.println(DisplayMode.MENU);
        System.out.println(DisplayMode.SHORTCUT);
        System.out.println(DisplayMode.MENU_AND_SHORTCUT);
    }

    @Test
    public void test2() {
        Setting setting = new Setting();
        setting.setKey("ACCOUNT_DEFAULT_PASSWORD");
        setting.setValue("123456");
        System.out.println(setting);
        db.insert(setting);
        System.out.println(db.createQuery(Setting.class).findList());
    }

    @Test
    public void test3() {
        System.out.println(PlatformSchema.platform.setting.key.eq("1"));
        System.out.println(PlatformSchema.platform.setting.value);
        db.insert(from("t1", "v1"));
        db.delete(Setting.class, "t0");
        db.update(from("t1", "v1"));
        db.update(Setting.class)
            .set(p(platform.setting.value), "vvv")
            .where().eq(p(platform.setting.key), "kkk")
            .update();
        db.createQuery(Setting.class).where().findList();
    }

    private Setting from(String key, String value) {
        Setting setting = new Setting();
        setting.setKey(key);
        setting.setValue(value);
        return setting;
    }

    @Test
    public void teststsetst() {
        Setting setting = new Setting();
        //setting.setKey(key);
        setting.setValue("123");
        db.delete(setting);
    }
}
