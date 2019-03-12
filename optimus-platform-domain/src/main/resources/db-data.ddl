-- ======== ======== ======== ========
-- setting
-- ======== ======== ======== ========
INSERT INTO e_platform_setting (key_, value_)
VALUES ('ACCOUNT_DEFAULT_PASSWORD', '123456');

-- ======== ======== ======== ========
-- permission
-- ======== ======== ======== ========
INSERT INTO e_platform_permission(id_, name_, code_)
VALUES (111, 'permission.group.read', ''),
       (112, 'permission.group.write', ''),
       (113, 'permission.role.read', ''),
       (114, 'permission.role.write', '');

-- ======== ======== ======== ========
-- module
-- ======== ======== ======== ========
INSERT INTO e_platform_module (id_, name_, icon_, ordinal_)
VALUES (101, 'module.system', 'icon-layers', 1);

-- ======== ======== ======== ========
-- function
-- ======== ======== ======== ========
INSERT INTO e_platform_function (id_, name_, code_, module_id_, parent_, url_, type_, display_, is_lock_, ordinal_)
VALUES (101, 'function.cache.browse', 'cache:browse', 101, 0, '/portal/admin/cache', 1, 1, 1, 101);

-- ======== ======== ======== ========
-- account
-- ======== ======== ======== ========
INSERT INTO e_platform_account (id_, username_, password_, nickname_, type_, salt_)
VALUES (1, 'god', '{noop}123456', '超级管理员', 1, '*'),
       (10, 'admin', '{noop}123456', '管理员', 10, '*');
