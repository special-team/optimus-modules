DROP TABLE IF EXISTS e_platform_setting;
DROP TABLE IF EXISTS e_platform_permission;
DROP TABLE IF EXISTS e_platform_role;
DROP TABLE IF EXISTS e_platform_group;
DROP TABLE IF EXISTS e_platform_account;
DROP TABLE IF EXISTS e_platform_role_permission;
DROP TABLE IF EXISTS e_platform_account_permission;
DROP TABLE IF EXISTS e_platform_privilege;
DROP TABLE IF EXISTS e_platform_module;
DROP TABLE IF EXISTS e_platform_function;
DROP TABLE IF EXISTS e_platform_message;

-- ======== ======== ======== ========
-- config
-- ======== ======== ======== ========
CREATE TABLE e_platform_setting (
    id_           BIGINT AUTO_INCREMENT NOT NULL,
    config_key_   VARCHAR(64)           NULL COMMENT '配置KEY',
    config_value_ VARCHAR(64)           NULL COMMENT '配置VALUE',
    deleted_      BOOLEAN DEFAULT FALSE NOT NULL,
    created_      DATETIME              NULL,
    updated_      DATETIME              NULL,
    CONSTRAINT setting_pk PRIMARY KEY (id_),
    CONSTRAINT setting_uk_config_key UNIQUE (config_key_)
);

-- ======== ======== ======== ========
-- permission
-- ======== ======== ======== ========
CREATE TABLE e_platform_permission (
    id_      BIGINT AUTO_INCREMENT NOT NULL,
    name_    VARCHAR(50)           NOT NULL COMMENT '资源名称',
    code_    VARCHAR(50)           NULL COMMENT '资源编码',
    deleted_ BOOLEAN DEFAULT FALSE NOT NULL,
    created_ DATETIME              NULL,
    updated_ DATETIME              NULL,
    CONSTRAINT permission_pk PRIMARY KEY (id_),
);

-- ======== ======== ======== ========
-- role
-- ======== ======== ======== ========
CREATE TABLE e_platform_role (
    id_      BIGINT AUTO_INCREMENT NOT NULL,
    name_    VARCHAR(150)          NOT NULL COMMENT '角色名称',
    deleted_ BOOLEAN DEFAULT FALSE NOT NULL,
    created_ DATETIME              NULL,
    updated_ DATETIME              NULL,
    CONSTRAINT role_pk PRIMARY KEY (id_),
    CONSTRAINT role_uk_name UNIQUE (name_)
);

-- ======== ======== ======== ========
-- group
-- ======== ======== ======== ========
CREATE TABLE e_platform_group (
    id_          BIGINT AUTO_INCREMENT NOT NULL,
    name_        VARCHAR(50)           NOT NULL COMMENT '名称',
    short_name_  VARCHAR(50)           NULL COMMENT '简称',
    superior_id_ BIGINT                NULL COMMENT '上级',
    deleted_     BOOLEAN DEFAULT FALSE NOT NULL,
    created_     DATETIME              NULL,
    updated_     DATETIME              NULL,
    CONSTRAINT group_pk PRIMARY KEY (id_),
    CONSTRAINT group_uk_name UNIQUE (name_),
    CONSTRAINT group_fk_superior FOREIGN KEY (superior_id_) REFERENCES e_platform_group (id_)
);

-- ======== ======== ======== ========
-- account
-- ======== ======== ======== ========
CREATE TABLE e_platform_account (
    id_                 BIGINT AUTO_INCREMENT NOT NULL,
    username_           VARCHAR(200)          NOT NULL COMMENT '用户名',
    password_           VARCHAR(200)          NOT NULL COMMENT '密码',
    nickname_           VARCHAR(200)          NULL COMMENT '昵称',
    certificate_type_   INT                   NULL COMMENT '证件类型',
    certificate_number_ VARCHAR(50)           NULL COMMENT '证件号码',
    mobile_             VARCHAR(50)           NULL COMMENT '手机',
    email_              VARCHAR(50)           NULL COMMENT '邮箱',
    role_id_            BIGINT                NULL COMMENT '角色',
    group_id_           BIGINT                NULL COMMENT '组',
    type_               INT                   NOT NULL COMMENT '用户标识[1:NORMAL|9:GOD]',
    salt_               VARCHAR(32)           NOT NULL COMMENT 'SALT',
    deleted_            BOOLEAN DEFAULT FALSE NOT NULL,
    created_            DATETIME              NULL,
    updated_            DATETIME              NULL,
    CONSTRAINT account_pk PRIMARY KEY (id_),
    CONSTRAINT account_uk_username UNIQUE (username_),
    CONSTRAINT account_fk_role FOREIGN KEY (role_id_) REFERENCES e_platform_role (id_),
    CONSTRAINT account_fk_group FOREIGN KEY (group_id_) REFERENCES e_platform_group (id_)
);

-- ======== ======== ======== ========
-- role permission
-- ======== ======== ======== ========
CREATE TABLE e_platform_role_permission (
    role_id_       BIGINT NOT NULL COMMENT '角色',
    permission_id_ BIGINT NOT NULL COMMENT '权限',
    CONSTRAINT role_permission_pk PRIMARY KEY (role_id_, permission_id_),
    CONSTRAINT role_permission_fk_role FOREIGN KEY (role_id_) REFERENCES e_platform_role (id_),
    CONSTRAINT role_permission_fk_permission FOREIGN KEY (permission_id_) REFERENCES e_platform_permission (id_)
);

-- ======== ======== ======== ========
-- account resource
-- ======== ======== ======== ========
CREATE TABLE e_platform_account_permission (
    account_id_    BIGINT NOT NULL COMMENT '账号',
    permission_id_ BIGINT NOT NULL COMMENT '权限',
    CONSTRAINT account_permission_pk PRIMARY KEY (account_id_, permission_id_),
    CONSTRAINT account_permission_fk_account FOREIGN KEY (account_id_) REFERENCES e_platform_account (id_),
    CONSTRAINT account_permission_fk_permission FOREIGN KEY (permission_id_) REFERENCES e_platform_permission (id_)
);

-- ======== ======== ======== ========
-- privilege 数据权限
-- ======== ======== ======== ========
CREATE TABLE e_platform_privilege (
    id_         BIGINT AUTO_INCREMENT NOT NULL,
    group_id_   BIGINT                NULL COMMENT '组',
    role_id_    BIGINT                NULL COMMENT '角色',
    entity_     VARCHAR(100)          NULL COMMENT '实体',
    field_      VARCHAR(100)          NULL COMMENT '属性',
    operator_   VARCHAR(100)          NULL COMMENT '关系运算',
    value_      VARCHAR(100)          NULL COMMENT '取值[{uid}|{rid}|{gid}]',
    restricted_ VARCHAR(999)          NULL COMMENT '受限属性',
    deleted_    BOOLEAN DEFAULT FALSE NOT NULL,
    created_    DATETIME              NULL,
    updated_    DATETIME              NULL,
    CONSTRAINT privilege_pk PRIMARY KEY (id_),
    CONSTRAINT privilege_fk_role FOREIGN KEY (role_id_) REFERENCES e_platform_role (id_),
    CONSTRAINT privilege_fk_group FOREIGN KEY (group_id_) REFERENCES e_platform_group (id_)
);

-- ======== ======== ======== ========
-- module
-- ======== ======== ======== ========
CREATE TABLE e_platform_module (
    id_      BIGINT AUTO_INCREMENT NOT NULL,
    name_    VARCHAR(20)           NOT NULL COMMENT '模块名称',
    icon_    VARCHAR(100)          NULL COMMENT '图标',
    ordinal_ INT                   NULL COMMENT '顺序编号',
    deleted_ BOOLEAN DEFAULT FALSE NOT NULL,
    created_ DATETIME              NULL,
    updated_ DATETIME              NULL,
    CONSTRAINT module_pk PRIMARY KEY (id_),
    CONSTRAINT module_uk_name UNIQUE (name_)
);

-- ======== ======== ======== ========
-- function
-- ======== ======== ======== ========
CREATE TABLE e_platform_function (
    id_        BIGINT AUTO_INCREMENT NOT NULL,
    name_      VARCHAR(50)           NOT NULL COMMENT '功能名称',
    code_      VARCHAR(50)           NULL COMMENT '功能编码',
    module_id_ BIGINT                NULL COMMENT '所属模块',
    parent_    BIGINT                NULL COMMENT '父级功能',
    url_       VARCHAR(100)          NOT NULL COMMENT '地址',
    icon_      VARCHAR(20)           NULL COMMENT '图标',
    type_      VARCHAR(10)           NOT NULL COMMENT '类型',
    display_   INT                   NULL COMMENT '显示[1:菜单显示|2:快捷显示|3:菜单和快捷显示]',
    is_lock_   INT                   NULL COMMENT '是否锁定',
    ordinal_   INT                   NULL COMMENT '顺序编号',
    deleted_   BOOLEAN DEFAULT FALSE NOT NULL,
    created_   DATETIME              NULL,
    updated_   DATETIME              NULL,
    CONSTRAINT function_pk PRIMARY KEY (id_),
    CONSTRAINT function_fk_module FOREIGN KEY (module_id_) REFERENCES e_platform_module (id_)
);
-- //CONSTRAINT function_uk_name UNIQUE (name_),

-- ======== ======== ======== ========
-- message 系统消息
-- ======== ======== ======== ========
CREATE TABLE e_platform_message (
    id_      BIGINT AUTO_INCREMENT NOT NULL,
    title_   VARCHAR(100)          NULL COMMENT '标题',
    content_ VARCHAR(999)          NULL COMMENT '内容',
    from_    BIGINT                NOT NULL COMMENT '发件人',
    to_      BIGINT                NOT NULL COMMENT '收件人',
    deleted_ BOOLEAN DEFAULT FALSE NOT NULL,
    created_ DATETIME              NULL,
    updated_ DATETIME              NULL,
    CONSTRAINT message_pk PRIMARY KEY (id_)
);
