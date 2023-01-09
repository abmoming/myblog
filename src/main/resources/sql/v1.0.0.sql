DROP TABLE IF EXISTS blog_user;
CREATE TABLE blog_user
(
    id              bigint        NOT NULL COMMENT '主键',
    username        varchar(255)  NOT NULL COMMENT '登录账号',
    password        varchar(255)  NOT NULL COMMENT '登录密码',
    nickname        varchar(30)   NOT NULL DEFAULT '匿名游客' COMMENT '昵称',
    state           int           NOT NULL DEFAULT 0 COMMENT '用户状态;0-离线；1-在线 ；3-已被锁；默认0',
    sex             int           NOT NULL DEFAULT 0 COMMENT '性别;0-未知；1-男性；2-女性；默认0',
    email           varchar(255)  NOT NULL DEFAULT '' COMMENT '邮箱',
    mobile          varchar(11)   NOT NULL DEFAULT '' COMMENT '手机号码',
    source          varchar(64)   NOT NULL DEFAULT '' COMMENT '来源',
    signature       varchar(1024) NOT NULL DEFAULT '' COMMENT '个人简介',
    last_login_time datetime      NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '最后登录时间',
    has_delete      int           NOT NULL DEFAULT 0 COMMENT '是否删除;0-未删除；1-已删除；默认0',
    create_time     datetime      NOT NULL DEFAULT now() COMMENT '创建时间',
    creator         varchar(30)   NOT NULL DEFAULT '' COMMENT '创建人',
    update_time     datetime      NOT NULL DEFAULT now() COMMENT '修改时间',
    updater         varchar(30)   NOT NULL DEFAULT '' COMMENT '修改人',
    PRIMARY KEY (id)
) COMMENT = '用户表';

DROP TABLE IF EXISTS blog_role;
CREATE TABLE blog_role
(
    id          bigint      NOT NULL COMMENT '主键',
    name        varchar(30) NOT NULL DEFAULT '' COMMENT '角色英文名称',
    alias       varchar(30) NOT NULL DEFAULT '' COMMENT '角色中文名称',
    sort        int         NOT NULL DEFAULT 1 COMMENT '排序',
    has_delete  int         NOT NULL DEFAULT 0 COMMENT '是否删除;0-未删除；1-已删除；默认0',
    create_time datetime    NOT NULL DEFAULT now() COMMENT '创建时间',
    creator     varchar(30) NOT NULL DEFAULT '' COMMENT '创建人',
    update_time datetime    NOT NULL DEFAULT now() COMMENT '修改时间',
    updater     varchar(30) NOT NULL DEFAULT '' COMMENT '修改人',
    PRIMARY KEY (id)
) COMMENT = '角色表';

DROP TABLE IF EXISTS blog_user_role;
CREATE TABLE blog_user_role
(
    id      bigint NOT NULL COMMENT '主键',
    user_id bigint NOT NULL COMMENT '用户ID',
    role_id bigint NOT NULL COMMENT '角色ID',
    PRIMARY KEY (id)
) COMMENT = '用户角色关联表';