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
import com.github.ooknight.rubik.prototype.authority.SessionUserType;

import io.ebean.Database;
import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;
import io.ebean.datasource.DataSourceConfig;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import static test.Demo.OM.om;

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
    public void testInsert() {
        db.insert(om.account("test", "123456", SessionUserType.NORMAL));
        //db.insert(function);
        db.insert(om.group("test"));
        //db.insert(message);
        //db.insert(module);
        db.insert(om.permission("test", "test"));
        //db.insert(privilege);
        db.insert(om.role("test"));
        db.insert(om.setting("test", "123"));
    }

    @Test
    public void testQuery() {
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
    public void test3() {
        db.insert(om.account("test", "123456", SessionUserType.NORMAL));
        System.out.println(db.find(Account.class).findList());
        System.out.println(db.createSqlQuery("select * from e_platform_account").findList());
        //db.insert(from("t1", "v1"));
        //db.delete(Setting.class, "t0");
        //db.update(from("t1", "v1"));
        //db.update(Setting.class)
        //    .set(p(platform.setting.value), "vvv")
        //    .where().eq(p(platform.setting.key), "kkk")
        //    .update();
        //db.createQuery(Setting.class).where().findList();
    }

    @Mapper
    public interface OM {

        OM om = Mappers.getMapper(OM.class);

        Account account(String username, String password, SessionUserType type);

        Group group(String name);

        Permission permission(String name, String code);

        Role role(String name);

        Setting setting(String key, String value);
    }
}
