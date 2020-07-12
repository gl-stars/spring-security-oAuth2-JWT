
-- ----------------------------
-- table structure for sys_permission 
-- ----------------------------
drop table if exists `sys_permission`;
create table `sys_permission` (
  `id` bigint(20) not null auto_increment comment '权限 id',
  `parent_id` bigint(20) default null comment '父权限 id (0为顶级菜单)',
  `name` varchar(64) not null comment '权限名称',
  `code` varchar(64) default null comment '授权标识符',
  `url` varchar(255) default null comment '授权路径',
  `type` int(2) not null default '1' comment '类型(1菜单，2按钮)',
  `icon` varchar(200) default null comment '图标',
  `remark` varchar(200) default null comment '备注',
  `create_date` timestamp not null default current_timestamp,
  `update_date` timestamp not null default current_timestamp,
  primary key (`id`)
) engine=innodb auto_increment=33 default charset=utf8 comment='权限表';

-- ----------------------------
-- records of sys_permission
-- ----------------------------
insert into `sys_permission` values ('11', '0', '首页', 'sys:index', '/', '1', 'fa fa-dashboard', '', '2023-08-08 11:11:11', '2023-08-09 15:26:28');
insert into `sys_permission` values ('17', '0', '系统管理', 'sys:manage', null, '1', 'fa fa-cogs', null, '2023-08-08 11:11:11', '2023-08-09 15:26:28');
insert into `sys_permission` values ('18', '17', '用户管理', 'sys:user', '/user', '1', 'fa fa-users', null, '2023-08-08 11:11:11', '2023-08-09 15:26:28');
insert into `sys_permission` values ('19', '18', '列表', 'sys:user:list', '', '2', '', '员工列表', '2023-08-08 11:11:11', '2023-08-08 11:11:11');
insert into `sys_permission` values ('20', '18', '新增', 'sys:user:add', '', '2', '', '新增用户', '2023-08-08 11:11:11', '2023-08-09 15:26:28');
insert into `sys_permission` values ('21', '18', '修改', 'sys:user:edit', '', '2', '', '修改用户', '2023-08-08 11:11:11', '2023-08-09 15:26:28');
insert into `sys_permission` values ('22', '18', '删除', 'sys:user:delete', '', '2', '', '删除用户', '2023-08-08 11:11:11', '2023-08-09 15:26:28');
insert into `sys_permission` values ('23', '17', '角色管理', 'sys:role', '/role', '1', 'fa fa-user-secret', null, '2023-08-08 11:11:11', '2023-08-09 15:26:28');
insert into `sys_permission` values ('24', '23', '列表', 'sys:role:list', null, '2', null, '角色列表', '2023-08-08 11:11:11', '2023-08-08 11:11:11');
insert into `sys_permission` values ('25', '23', '新增', 'sys:role:add', '', '2', '', '新增角色', '2023-08-08 11:11:11', '2023-08-09 15:26:28');
insert into `sys_permission` values ('26', '23', '修改', 'sys:role:edit', '', '2', '', '修改角色', '2023-08-08 11:11:11', '2023-08-09 15:26:28');
insert into `sys_permission` values ('27', '23', '删除', 'sys:role:delete', '', '2', '', '删除角色', '2023-08-08 11:11:11', '2023-08-09 15:26:28');
insert into `sys_permission` values ('28', '17', '权限管理', 'sys:permission', '/permission', '1', 'fa fa-cog', null, '2023-08-08 11:11:11', '2023-08-09 15:26:28');
insert into `sys_permission` values ('29', '28', '列表', 'sys:permission:list', null, '2', null, '权限列表', '2023-08-08 11:11:11', '2023-08-08 11:11:11');
insert into `sys_permission` values ('30', '28', '新增', 'sys:permission:add', '', '2', null, '新增权限', '2023-08-08 11:11:11', '2023-08-09 15:26:28');
insert into `sys_permission` values ('31', '28', '修改', 'sys:permission:edit', '', '2', null, '修改权限', '2023-08-08 11:11:11', '2023-08-09 15:26:28');
insert into `sys_permission` values ('32', '28', '删除', 'sys:permission:delete', '', '2', '', '删除权限', '2023-08-08 11:11:11', '2023-08-09 15:26:28');

-- ----------------------------
-- table structure for sys_role
-- ----------------------------
drop table if exists `sys_role`;
create table `sys_role` (
  `id` bigint(20) not null auto_increment comment '角色 id',
  `name` varchar(64) not null comment '角色名称',
  `remark` varchar(200) default null comment '角色说明',
  `create_date` timestamp not null default current_timestamp,
  `update_date` timestamp not null default current_timestamp,
  primary key (`id`)
) engine=innodb auto_increment=12 default charset=utf8 comment='角色表';

-- ----------------------------
-- records of sys_role
-- ----------------------------
insert into `sys_role` values ('9', '超级管理员', '拥有所有的权限', '2023-08-08 11:11:11', '2023-08-08 11:11:11');
insert into `sys_role` values ('10', '普通管理员', '拥有查看权限', '2023-08-08 11:11:11', '2023-08-08 11:11:11');

-- ----------------------------
-- table structure for sys_role_permission
-- ----------------------------
drop table if exists `sys_role_permission`;
create table `sys_role_permission` (
  `id` bigint(20) not null auto_increment comment '主键 id',
  `role_id` bigint(20) not null comment '角色 id',
  `permission_id` bigint(20) not null comment '权限 id',
  primary key (`id`)
) engine=innodb auto_increment=26 default charset=utf8 comment='角色权限表';

-- ----------------------------
-- records of sys_role_permission
-- ----------------------------
insert into `sys_role_permission` values ('1', '9', '11');
insert into `sys_role_permission` values ('2', '9', '17');
insert into `sys_role_permission` values ('3', '9', '18');
insert into `sys_role_permission` values ('4', '9', '19');
insert into `sys_role_permission` values ('5', '9', '20');
insert into `sys_role_permission` values ('6', '9', '21');
insert into `sys_role_permission` values ('7', '9', '22');
insert into `sys_role_permission` values ('8', '9', '23');
insert into `sys_role_permission` values ('9', '9', '24');
insert into `sys_role_permission` values ('10', '9', '25');
insert into `sys_role_permission` values ('11', '9', '26');
insert into `sys_role_permission` values ('12', '9', '27');
insert into `sys_role_permission` values ('13', '9', '28');
insert into `sys_role_permission` values ('14', '9', '29');
insert into `sys_role_permission` values ('15', '9', '30');
insert into `sys_role_permission` values ('16', '9', '31');
insert into `sys_role_permission` values ('17', '9', '32');
insert into `sys_role_permission` values ('18', '10', '11');
insert into `sys_role_permission` values ('19', '10', '17');
insert into `sys_role_permission` values ('20', '10', '18');
insert into `sys_role_permission` values ('21', '10', '19');
insert into `sys_role_permission` values ('22', '10', '23');
insert into `sys_role_permission` values ('23', '10', '24');
insert into `sys_role_permission` values ('24', '10', '28');
insert into `sys_role_permission` values ('25', '10', '29');

-- ----------------------------
-- table structure for sys_user
-- ----------------------------
drop table if exists `sys_user`;
create table `sys_user` (
  `id` bigint(20) not null auto_increment comment '用户 id',
  `username` varchar(50) not null comment '用户名',
  `password` varchar(64) not null comment '密码，加密存储, admin/1234',
  `is_account_non_expired` int(2) default '1' comment '帐户是否过期(1 未过期，0已过期)',
  `is_account_non_locked` int(2) default '1' comment '帐户是否被锁定(1 未过期，0已过期)',
  `is_credentials_non_expired` int(2) default '1' comment '密码是否过期(1 未过期，0已过期)',
  `is_enabled` int(2) default '1' comment '帐户是否可用(1 可用，0 删除用户)',
  `nick_name` varchar(64) default null comment '昵称',
  `mobile` varchar(20) default null comment '注册手机号',
  `email` varchar(50) default null comment '注册邮箱',
  `create_date` timestamp not null default current_timestamp,
  `update_date` timestamp not null default current_timestamp,
  primary key (`id`),
  unique key `username` (`username`) using btree,
  unique key `mobile` (`mobile`) using btree,
  unique key `email` (`email`) using btree
) engine=innodb auto_increment=11 default charset=utf8 comment='用户表';

-- ----------------------------
-- records of sys_user
-- ----------------------------
insert into `sys_user` values ('9', 'admin', '$2a$10$rdkpvvafv8kqwvkjzwlrv.i.q.wz1w1pz0sfshn/55jnezfqv/ecm', '1', '1', '1', '1', '梦学谷', '16888888888', 'mengxuegu888@163.com', '2023-08-08 11:11:11', '2019-12-16 10:25:53');
insert into `sys_user` values ('10', 'test', '$2a$10$rdkpvvafv8kqwvkjzwlrv.i.q.wz1w1pz0sfshn/55jnezfqv/ecm', '1', '1', '1', '1', '测试', '16888886666', 'test11@qq.com', '2023-08-08 11:11:11', '2023-08-08 11:11:11');

-- ----------------------------
-- table structure for sys_user_role
-- ----------------------------
drop table if exists `sys_user_role`;
create table `sys_user_role` (
  `id` bigint(20) not null auto_increment comment '主键 id',
  `user_id` bigint(20) not null comment '用户 id',
  `role_id` bigint(20) not null comment '角色 id',
  primary key (`id`)
) engine=innodb auto_increment=3 default charset=utf8 comment='用户角色表';

-- ----------------------------
-- records of sys_user_role
-- ----------------------------
insert into `sys_user_role` values ('1', '9', '9');
insert into `sys_user_role` values ('2', '10', '10');

-- 获取id=9的用户权限信息
select
	distinct p.id,	p.parent_id, p.name, p.code, p.url, p.type,
	p.icon, p.remark, p.create_date, p.update_date
from
  sys_user as u
  left join sys_user_role as ur
	on u.id = ur.user_id
  left join sys_role as r
	on r.id = ur.role_id
  left join sys_role_permission as rp
	on r.id = rp.role_id
  left join sys_permission as p
	on p.id = rp.permission_id
where u.id = 9
order by p.id
