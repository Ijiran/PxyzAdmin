/*
Navicat MySQL Data Transfer

Source Database       : pxyzadmin

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2020-06-11 22:01:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `SYS_MENU`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_MENU`;
CREATE TABLE `SYS_MENU` (
  `id` varchar(32) NOT NULL,
  `title` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `href` varchar(200) DEFAULT NULL COMMENT '链接',
  `target` varchar(100) DEFAULT NULL COMMENT '打开方式',
  `sort` int(10) DEFAULT NULL COMMENT '排序',
  `status` varchar(1) DEFAULT NULL COMMENT '状态',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `pid` varchar(32) DEFAULT NULL COMMENT '父id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `mark` varchar(1) DEFAULT NULL COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of SYS_MENU
-- ----------------------------
INSERT INTO `SYS_MENU` VALUES ('0', 'PXYZ ADMIN', null, null, null, '1', '1', null, '-1', null, '2020-06-07 09:43:47', '1');
INSERT INTO `SYS_MENU` VALUES ('1000', '文档效验', 'fa fa-check-circle', '1111', '_self', '1', '0', '2342', '0', null, '2020-06-08 22:49:13', '1');
INSERT INTO `SYS_MENU` VALUES ('2000', '文档合并', 'fa fa-code-fork', '11', '_self', '2', '0', '', '0', null, '2020-06-08 21:39:05', '1');
INSERT INTO `SYS_MENU` VALUES ('2020060700065300000', '模板管理', 'fa fa-excel', '1', '_self', '13', '0', '1544', '1000', '2020-06-07 00:06:50', '2020-06-08 22:49:12', '1');
INSERT INTO `SYS_MENU` VALUES ('3000', '文档编辑', 'fa fa-edit', null, '_self', '3', '0', null, '0', null, '2020-06-08 21:39:05', '1');
INSERT INTO `SYS_MENU` VALUES ('4000', '系统管理', 'fa fa-inbox', null, '_self', '4', '1', null, '0', null, '2020-06-07 09:43:54', '1');
INSERT INTO `SYS_MENU` VALUES ('400001', '用户管理', 'fa fa-users', '/pxyzadmin/user/index', '_self', '1', '1', null, '4000', null, '2020-06-07 09:43:54', '1');
INSERT INTO `SYS_MENU` VALUES ('400002', '角色管理', 'fa fa-user-secret', '/pxyzadmin/role/index', '_self', '2', '1', '', '4000', null, '2020-06-07 09:53:17', '1');
INSERT INTO `SYS_MENU` VALUES ('400003', '菜单管理', 'fa fa-tachometer', '/pxyzadmin/menu/index', '_self', '3', '1', null, '4000', null, '2020-06-07 09:43:55', '1');

-- ----------------------------
-- Table structure for `SYS_ROLE`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_ROLE`;
CREATE TABLE `SYS_ROLE` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `role_name` varchar(100) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `mark` varchar(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of SYS_ROLE
-- ----------------------------
INSERT INTO `SYS_ROLE` VALUES ('2020060716140600000', '用户角色1', '业务菜单权限', '1', '2020-06-07 16:14:04', '2020-06-08 22:55:04');

-- ----------------------------
-- Table structure for `SYS_ROLE_MENU`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_ROLE_MENU`;
CREATE TABLE `SYS_ROLE_MENU` (
  `role_id` varchar(32) DEFAULT NULL,
  `menu_id` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of SYS_ROLE_MENU
-- ----------------------------
INSERT INTO `SYS_ROLE_MENU` VALUES ('2020060716140600000', '0');
INSERT INTO `SYS_ROLE_MENU` VALUES ('2020060716140600000', '2000');
INSERT INTO `SYS_ROLE_MENU` VALUES ('2020060716140600000', '4000');
INSERT INTO `SYS_ROLE_MENU` VALUES ('2020060716140600000', '400001');
INSERT INTO `SYS_ROLE_MENU` VALUES ('2020060716140600000', '400002');
INSERT INTO `SYS_ROLE_MENU` VALUES ('2020060716140600000', '400003');

-- ----------------------------
-- Table structure for `SYS_ROLE_USER`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_ROLE_USER`;
CREATE TABLE `SYS_ROLE_USER` (
  `role_id` varchar(32) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of SYS_ROLE_USER
-- ----------------------------
INSERT INTO `SYS_ROLE_USER` VALUES ('2020060716140600000', '2020060714160700000');
INSERT INTO `SYS_ROLE_USER` VALUES ('2020060716140600000', '2020060714205300000');

-- ----------------------------
-- Table structure for `SYS_USER`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_USER`;
CREATE TABLE `SYS_USER` (
  `id` varchar(32) NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `mark` varchar(1) DEFAULT NULL COMMENT '删除标志',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of SYS_USER
-- ----------------------------
INSERT INTO `SYS_USER` VALUES ('2020060714160700000', 'admin', '7C4A8D09CA3762AF61E59520943DC26494F8941B', '213', '989', '1', '2020-06-07 14:16:06', '2020-06-07 14:44:02');
INSERT INTO `SYS_USER` VALUES ('2020060714205300000', 'zhangsan', '7C4A8D09CA3762AF61E59520943DC26494F8941B', '33444', '123123', '1', '2020-06-07 14:20:51', '2020-06-07 14:49:34');
