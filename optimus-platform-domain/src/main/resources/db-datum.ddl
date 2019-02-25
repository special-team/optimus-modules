-- ======== ======== ======== ========
-- setting
-- ======== ======== ======== ========
INSERT INTO e_platform_setting (key_, value_)
VALUES ('ACCOUNT_DEFAULT_PASSWORD', '123456');

-- ======== ======== ======== ========
-- setting
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
VALUES (100, 'module.system', 'icon-layers', 1);

-- ======== ======== ======== ========
-- function
-- ======== ======== ======== ========
-- @formatter:off
INSERT INTO e_platform_function (id_, name_, code_, module_id_, parent_, url_, type_, display_, is_lock_, ordinal_)
VALUES (101, 'function.cache.browse', 'cache:browse', 100, 0, '/portal/admin/cache', 1, 1, 1, 111),
       (111, 'function.group.browse', 'group:browse', 100, 0, '/portal/group/browse', 1, 1, 1, 111),
       (112, 'function.group.create', 'group:create', 100, 0, '/portal/group/create', 1, 0, 1, 112),
       (113, 'function.group.update', 'group:update', 100, 0, '/portal/group/update', 1, 0, 1, 113),
       (114, 'function.group.detail', 'group:detail', 100, 0, '/portal/group/detail', 1, 0, 1, 114),
       (121, 'function.role.browse', 'role:browse', 100, 0, '/portal/role/browse', 1, 1, 1, 121),
       (122, 'function.role.create', 'role:create', 100, 0, '/portal/role/create', 1, 0, 1, 122),
       (123, 'function.role.update', 'role:update', 100, 0, '/portal/role/update', 1, 0, 1, 123),
       (124, 'function.role.detail', 'role:detail', 100, 0, '/portal/role/detail', 1, 0, 1, 124),
       (131, 'function.user.browse', 'user:browse', 100, 0, '/portal/user/browse', 1, 1, 1, 131),
       (132, 'function.user.create', 'user:create', 100, 0, '/portal/user/create', 1, 0, 1, 132),
       (133, 'function.user.update', 'user:update', 100, 0, '/portal/user/update', 1, 0, 1, 133),
       (134, 'function.user.detail', 'user:detail', 100, 0, '/portal/user/detail', 1, 0, 1, 134);
-- @formatter:on

-- ======== ======== ======== ========
-- account
-- ======== ======== ======== ========
INSERT INTO e_platform_account (id_, username_, password_, nickname_, type_, salt_)
VALUES (1, 'god', '{noop}123456', '超级管理员', 1, '*'),
       (10, 'admin', '{noop}123456', '管理员', 10, '*');
