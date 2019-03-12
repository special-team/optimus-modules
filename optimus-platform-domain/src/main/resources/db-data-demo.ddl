-- ======== ======== ======== ========
-- module
-- ======== ======== ======== ========
INSERT INTO e_platform_module (id_, name_, icon_, ordinal_)
VALUES (101, 'module.system', 'icon-layers', 1);

-- ======== ======== ======== ========
-- function
-- ======== ======== ======== ========
INSERT INTO e_platform_function (id_, name_, code_, module_id_, parent_, url_, type_, display_, locked_, ordinal_)
VALUES (101, 'function.cache.browse', 'cache:browse', 101, 0, '/portal/admin/cache', 1, 1, FALSE, 101);

-- ======== ======== ======== ========
-- group
-- ======== ======== ======== ========
INSERT INTO e_platform_group (id_, name_, short_name_)
VALUES (10, '魏国', '魏'),
       (20, '蜀国', '蜀'),
       (30, '吴国', '吴');

-- ======== ======== ======== ========
-- role
-- ======== ======== ======== ========
INSERT INTO e_platform_role (id_, name_)
VALUES (10, '君主'),
       (20, '文官'),
       (30, '武官');

-- ======== ======== ======== ========
-- account
-- ======== ======== ======== ========
INSERT INTO e_platform_account (id_, username_, password_, nickname_, role_id_, group_id_, type_)
VALUES (1011, '曹操', '{noop}123456', '孟德', 10, 10, 11),
       (1021, '刘备', '{noop}123456', '玄德', 10, 20, 11),
       (1031, '孙权', '{noop}123456', '仲谋', 10, 30, 11);

-- ======== ======== ======== ========
-- role permission
-- ======== ======== ======== ========
INSERT INTO e_platform_role_permission (role_id_, permission_id_)
VALUES (10, 111);

-- ======== ======== ======== ========
-- account permission
-- ======== ======== ======== ========
INSERT INTO e_platform_account_permission(account_id_, permission_id_)
VALUES (1011, 112),
       (1011, 113);

