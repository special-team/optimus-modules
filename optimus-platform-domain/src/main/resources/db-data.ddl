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
-- account
-- ======== ======== ======== ========
INSERT INTO e_platform_account (id_, username_, password_, nickname_, type_)
VALUES (1, 'god', '{noop}123456', '超级管理员', 1),
       (10, 'admin', '{noop}123456', '管理员', 10);
