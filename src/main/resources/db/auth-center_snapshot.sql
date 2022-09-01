/*
 Navicat Premium Data Transfer

 Source Server         : localroot
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : localhost:3306
 Source Schema         : auth-center

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 27/06/2022 16:58:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for login_log
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log`  (
                              `id` bigint NOT NULL COMMENT '主键id',
                              `login_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名，用于登录和显示',
                              `login_time` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '登陆时间',
                              `login_location` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '登陆地点',
                              `login_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '登陆IP,兼容IPv6, 只存转换后的内容',
                              `login_system` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作系统',
                              `login_browser` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '浏览器',
                              `dt_created` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
                              `dt_modified` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '登录日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of login_log
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
                         `id` bigint NOT NULL COMMENT '主键id',
                         `role_desc` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '权限描述',
                         `dt_created` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
                         `dt_modified` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '访客', '2022-02-22 14:44:49.113812', '2022-02-22 14:44:49.113812');
INSERT INTO `role` VALUES (2, '注册用户', '2022-02-22 14:45:06.142902', '2022-02-22 14:45:06.142902');
INSERT INTO `role` VALUES (300, '店铺持有者', '2022-02-22 14:45:25.653622', '2022-02-22 14:45:25.653622');
INSERT INTO `role` VALUES (301, '店铺管理员', '2022-02-22 14:45:35.095515', '2022-02-22 14:45:35.095515');
INSERT INTO `role` VALUES (302, '店铺服务员', '2022-02-22 14:45:52.214990', '2022-02-22 14:45:52.214990');
INSERT INTO `role` VALUES (901, '超级管理员', '2022-02-22 14:46:09.332808', '2022-02-22 14:46:09.332808');

-- ----------------------------
-- Table structure for role_ref_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_ref_permission`;
CREATE TABLE `role_ref_permission`  (
                                        `id` bigint NOT NULL COMMENT '主键id',
                                        `role_id` bigint NOT NULL COMMENT '角色id',
                                        `permission_id` bigint NOT NULL COMMENT '权限id',
                                        `invalid` tinyint NOT NULL DEFAULT 1 COMMENT '角色权限是否失效,0 已失效, 1 未失效',
                                        `dt_created` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
                                        `dt_modified` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
                                        PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色权限关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_ref_permission
-- ----------------------------
INSERT INTO `role_ref_permission` VALUES (1, 1, 200, 1, '2022-02-22 14:59:10.554488', '2022-02-22 14:59:10.554488');
INSERT INTO `role_ref_permission` VALUES (2, 2, 201, 1, '2022-02-22 14:59:38.139084', '2022-02-22 14:59:38.139084');
INSERT INTO `role_ref_permission` VALUES (3, 300, 201, 1, '2022-02-22 14:59:54.833473', '2022-02-22 14:59:54.833473');
INSERT INTO `role_ref_permission` VALUES (4, 301, 201, 1, '2022-02-22 15:00:01.361267', '2022-02-22 15:00:01.361267');
INSERT INTO `role_ref_permission` VALUES (5, 302, 201, 1, '2022-02-22 15:00:17.192884', '2022-02-22 15:00:17.192884');
INSERT INTO `role_ref_permission` VALUES (6, 901, 201, 1, '2022-02-22 15:00:44.016711', '2022-02-22 15:00:44.016711');
INSERT INTO `role_ref_permission` VALUES (7, 1, 100, 1, '2022-02-22 15:01:54.959843', '2022-02-22 15:01:54.959843');
INSERT INTO `role_ref_permission` VALUES (8, 2, 101, 1, '2022-02-22 15:26:23.013659', '2022-02-22 15:26:23.013659');
INSERT INTO `role_ref_permission` VALUES (9, 300, 801, 1, '2022-02-22 15:31:30.388813', '2022-02-22 15:31:30.388813');
INSERT INTO `role_ref_permission` VALUES (10, 301, 801, 1, '2022-02-22 15:31:39.290674', '2022-02-22 15:31:39.290674');
INSERT INTO `role_ref_permission` VALUES (11, 302, 801, 1, '2022-02-22 15:32:10.393763', '2022-02-22 15:32:10.393763');
INSERT INTO `role_ref_permission` VALUES (12, 300, 820, 1, '2022-02-22 15:32:27.068561', '2022-02-22 15:32:27.068561');
INSERT INTO `role_ref_permission` VALUES (13, 301, 820, 1, '2022-02-22 15:32:37.267413', '2022-02-22 15:32:37.267413');
INSERT INTO `role_ref_permission` VALUES (14, 302, 820, 1, '2022-02-22 15:32:44.908270', '2022-02-22 15:32:44.908270');
INSERT INTO `role_ref_permission` VALUES (15, 300, 802, 1, '2022-02-22 15:33:19.764881', '2022-02-22 15:33:19.764881');
INSERT INTO `role_ref_permission` VALUES (16, 301, 802, 1, '2022-02-22 15:33:25.830094', '2022-02-22 15:33:25.830094');
INSERT INTO `role_ref_permission` VALUES (17, 300, 803, 1, '2022-02-22 15:33:41.696468', '2022-02-22 15:33:41.696468');
INSERT INTO `role_ref_permission` VALUES (18, 301, 803, 1, '2022-02-22 15:33:59.933964', '2022-02-22 15:33:59.933964');
INSERT INTO `role_ref_permission` VALUES (20, 2, 811, 1, '2022-02-22 15:34:31.291543', '2022-02-22 15:34:31.291543');
INSERT INTO `role_ref_permission` VALUES (21, 300, 811, 1, '2022-02-22 15:34:38.640902', '2022-02-22 15:34:38.640902');
INSERT INTO `role_ref_permission` VALUES (22, 301, 811, 1, '2022-02-22 15:34:44.367380', '2022-02-22 15:34:44.367380');
INSERT INTO `role_ref_permission` VALUES (23, 302, 811, 1, '2022-02-22 15:34:51.763895', '2022-02-22 15:34:51.763895');
INSERT INTO `role_ref_permission` VALUES (24, 2, 880, 1, '2022-02-22 15:35:12.405917', '2022-02-22 15:35:12.405917');
INSERT INTO `role_ref_permission` VALUES (25, 300, 821, 1, '2022-02-22 15:35:21.217239', '2022-02-22 15:35:21.217239');
INSERT INTO `role_ref_permission` VALUES (26, 301, 821, 1, '2022-02-22 15:35:30.019292', '2022-02-22 15:35:30.019292');
INSERT INTO `role_ref_permission` VALUES (27, 302, 821, 1, '2022-02-22 15:35:39.697050', '2022-02-22 15:35:39.697050');

-- ----------------------------
-- Table structure for u_permission
-- ----------------------------
DROP TABLE IF EXISTS `u_permission`;
CREATE TABLE `u_permission`  (
                                 `id` bigint NOT NULL COMMENT '主键id',
                                 `desc` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限描述',
                                 `invalid` tinyint NOT NULL DEFAULT 1 COMMENT '权限是否失效,0 已失效, 1 未失效',
                                 `dt_created` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
                                 `dt_modified` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '操作权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of u_permission
-- ----------------------------
INSERT INTO `u_permission` VALUES (100, '进行注册', 1, '2022-02-22 15:01:29.413017', '2022-02-22 15:01:29.413017');
INSERT INTO `u_permission` VALUES (101, '进行注销', 1, '2022-02-22 15:01:45.214641', '2022-02-22 15:01:45.214641');
INSERT INTO `u_permission` VALUES (200, '只能浏览首页', 1, '2022-02-22 14:56:14.746339', '2022-02-22 14:56:14.746339');
INSERT INTO `u_permission` VALUES (201, '浏览全部内容', 1, '2022-02-22 14:57:44.647635', '2022-02-22 14:57:44.647635');
INSERT INTO `u_permission` VALUES (801, '上架商品', 1, '2022-02-22 15:28:11.893947', '2022-02-22 15:28:11.893947');
INSERT INTO `u_permission` VALUES (802, '修改商品属性', 1, '2022-02-22 15:28:33.472266', '2022-02-22 15:28:33.472266');
INSERT INTO `u_permission` VALUES (803, '修改商品状态', 1, '2022-02-22 15:29:02.794455', '2022-02-22 15:29:02.794455');
INSERT INTO `u_permission` VALUES (811, '查看商品列表', 1, '2022-02-22 15:29:23.236370', '2022-02-22 15:29:23.236370');
INSERT INTO `u_permission` VALUES (820, '查看订单列表', 1, '2022-02-22 15:30:15.457182', '2022-02-22 15:30:15.457182');
INSERT INTO `u_permission` VALUES (821, '修改订单状态', 1, '2022-02-22 15:30:35.080470', '2022-02-22 15:30:35.080470');
INSERT INTO `u_permission` VALUES (880, '下单', 1, '2022-02-22 15:29:51.431220', '2022-02-22 15:29:51.431220');
INSERT INTO `u_permission` VALUES (8808, 'user:visit', 1, '2022-05-01 20:48:57.524241', '2022-05-01 20:48:57.524241');
INSERT INTO `u_permission` VALUES (10000, 'manager:visit', 1, '2022-05-01 20:56:15.992502', '2022-05-01 20:56:15.992502');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
                         `id` bigint NOT NULL COMMENT '主键id',
                         `login_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名，用于登录和显示',
                         `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户邮箱',
                         `phone` bigint NULL DEFAULT NULL COMMENT '手机号',
                         `id_card` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证号',
                         `pwd` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '经后端加密后的密码',
                         `invalid` tinyint NOT NULL DEFAULT 1 COMMENT '顾客是否注销,0 已注销, 1 未注销',
                         `login_source` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '浏览器' COMMENT '登陆来源',
                         `dt_created` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
                         `dt_modified` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '顾客账号信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (111111111, 'storeHost', '222@mail.com', 15509981028, '123456788760319283', 'root123456', 1, '浏览器', '2022-02-22 15:07:27.105671', '2022-02-22 15:07:27.105671');
INSERT INTO `user` VALUES (123456789, 'customer01', '123456789@mail.com', 18875647778, '1234567898291', 'customer123456', 1, '浏览器', '2022-02-22 15:37:39.592283', '2022-02-22 15:37:39.592283');
INSERT INTO `user` VALUES (1000000000, 'lucheng', '123456@mail.com', 17705549779, '340421199703130235', 'lc123456', 1, '浏览器', '2022-02-22 14:44:27.133522', '2022-02-22 14:44:27.133522');

-- ----------------------------
-- Table structure for user_ref_role
-- ----------------------------
DROP TABLE IF EXISTS `user_ref_role`;
CREATE TABLE `user_ref_role`  (
                                  `id` bigint NOT NULL COMMENT '主键id',
                                  `user_id` bigint NOT NULL COMMENT '用户id',
                                  `role_id` bigint NOT NULL COMMENT '权限id',
                                  `invalid` tinyint NOT NULL DEFAULT 1 COMMENT '权限是否失效, 0 已失效, 1 未失效',
                                  `expired_data` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '权限到期日期',
                                  `dt_created` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
                                  `dt_modified` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '修改时间',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户权限关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_ref_role
-- ----------------------------
INSERT INTO `user_ref_role` VALUES (1, 1000000000, 1, 1, '2022-02-22 14:46:53.584359', '2022-02-22 14:46:53.584359', '2022-02-22 14:46:53.584359');
INSERT INTO `user_ref_role` VALUES (2, 111111111, 300, 1, '2022-02-22 15:06:35.546122', '2022-02-22 15:06:35.546122', '2022-02-22 15:06:35.546122');
INSERT INTO `user_ref_role` VALUES (3, 111111111, 2, 1, '2022-02-22 15:12:56.864394', '2022-02-22 15:12:56.864394', '2022-02-22 15:12:56.864394');
INSERT INTO `user_ref_role` VALUES (4, 111111111, 301, 1, '2022-02-22 15:13:12.942118', '2022-02-22 15:13:12.942118', '2022-02-22 15:13:12.942118');
INSERT INTO `user_ref_role` VALUES (5, 111111111, 302, 1, '2022-02-22 15:36:25.975102', '2022-02-22 15:36:25.975102', '2022-02-22 15:36:25.975102');
INSERT INTO `user_ref_role` VALUES (6, 123456789, 2, 1, '2022-02-22 15:36:55.352530', '2022-02-22 15:36:55.352530', '2022-02-22 15:36:55.352530');

SET FOREIGN_KEY_CHECKS = 1;
