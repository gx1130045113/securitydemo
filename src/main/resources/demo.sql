/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 19/11/2020 16:55:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_pid` int(11) DEFAULT NULL,
  `menu_pids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `is_leaf` tinyint(4) DEFAULT NULL COMMENT '0不是叶子结点  1是叶子结点',
  `menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `menu_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `icon_color` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `sort` tinyint(4) DEFAULT NULL,
  `level` tinyint(4) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '0启用 1禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '0', 0, '系统管理', '/index', NULL, NULL, 1, 1, 0);
INSERT INTO `sys_menu` VALUES (2, 1, '1', 1, '用户管理', '/sysuser', NULL, NULL, 1, 2, 0);
INSERT INTO `sys_menu` VALUES (3, 1, '1', 1, '日志管理', '/syslog', NULL, NULL, 2, 2, 0);
INSERT INTO `sys_menu` VALUES (4, 1, '1', 1, '业务一', '/biz1', NULL, NULL, 3, 2, 0);
INSERT INTO `sys_menu` VALUES (5, 1, '1', 1, '业务二', '/biz2', NULL, NULL, 4, 2, 0);

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `org_pid` int(11) DEFAULT NULL COMMENT '上级组织编码',
  `org_pids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '所有的父节点id',
  `is_leaf` tinyint(4) DEFAULT NULL COMMENT '0:不是叶子结点  1：是叶子结点',
  `org_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `sort` int(255) DEFAULT NULL,
  `level` int(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '0:启用 1禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES (1, 0, '0', 0, '总部', NULL, NULL, NULL, 1, 1, 0);
INSERT INTO `sys_org` VALUES (2, 1, '1', 0, '研发部', NULL, NULL, NULL, 1, 2, 0);
INSERT INTO `sys_org` VALUES (3, 2, '1,2', 1, '研发一部', NULL, NULL, NULL, 1, 3, 0);
INSERT INTO `sys_org` VALUES (4, 2, '1,2', 1, '研发二部', NULL, NULL, NULL, 2, 3, 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `role_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `role_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `sort` int(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '0启用 1禁用',
  `create_time` datetime(0) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', '管理员', 'admin', 1, 0, '2020-11-18 17:52:48');
INSERT INTO `sys_role` VALUES (2, '普通用户', '普通用户', 'common', 2, 0, '2020-11-18 17:53:13');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1, 2);
INSERT INTO `sys_role_menu` VALUES (2, 1, 3);
INSERT INTO `sys_role_menu` VALUES (3, 2, 4);
INSERT INTO `sys_role_menu` VALUES (4, 2, 5);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `org_id` int(11) DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `enabled` tinyint(11) DEFAULT NULL COMMENT '0无效 1有效',
  `phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'yanfa1', '$2a$10$BDPAZM84je3zcivsgCw./.TX7lsg./Ahk3XuujSvO7Xr9w7M82X1G', 3, '2020-11-18 17:29:21', 1, NULL, NULL);
INSERT INTO `sys_user` VALUES (2, 'admin', '$2a$10$BDPAZM84je3zcivsgCw./.TX7lsg./Ahk3XuujSvO7Xr9w7M82X1G', 1, '2020-11-18 17:55:34', 1, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 2);
INSERT INTO `sys_user_role` VALUES (2, 2, 1);

SET FOREIGN_KEY_CHECKS = 1;
