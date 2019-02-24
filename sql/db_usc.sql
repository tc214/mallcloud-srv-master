/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : db_usc

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2019-02-24 22:31:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_action
-- ----------------------------
DROP TABLE IF EXISTS `t_action`;
CREATE TABLE `t_action` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `parent_action_id` int(12) DEFAULT NULL,
  `action` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `action_name` varchar(32) NOT NULL,
  `action_desc` varchar(64) DEFAULT NULL,
  `action_type` int(4) NOT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_action
-- ----------------------------
INSERT INTO `t_action` VALUES ('1', '0', 'add user', '增加用户', '增加用户', '1', '2018-10-30 05:42:21', '2018-10-30 05:42:21');
INSERT INTO `t_action` VALUES ('2', '0', 'lookuser', '查看用户', '查看用户', '1', '2018-10-30 05:53:39', '2018-10-30 05:53:39');
INSERT INTO `t_action` VALUES ('3', '0', 'deluser', '删除用户', '删除用户', '1', '2018-10-30 05:53:56', '2018-10-30 05:53:56');
INSERT INTO `t_action` VALUES ('4', '0', 'changeuser', '修改用户', '修改用户', '1', '2018-10-30 05:54:09', '2018-10-30 05:54:09');
INSERT INTO `t_action` VALUES ('6', '0', 'mac_num_one', '目标机数量', '拥有的目标机数量', '2', '2018-10-31 00:54:09', '2018-10-31 00:54:09');

-- ----------------------------
-- Table structure for t_autotest_config
-- ----------------------------
DROP TABLE IF EXISTS `t_autotest_config`;
CREATE TABLE `t_autotest_config` (
  `switch` varchar(6) NOT NULL,
  `running_mode` varchar(10) DEFAULT NULL,
  `running_app` varchar(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `interval_time` varchar(255) DEFAULT NULL,
  `random_time` varchar(255) DEFAULT NULL,
  `solid_time` varchar(255) DEFAULT NULL,
  `stop_condition` varchar(255) DEFAULT NULL,
  `oper_num` int(11) DEFAULT NULL,
  `running_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_autotest_config
-- ----------------------------

-- ----------------------------
-- Table structure for t_company
-- ----------------------------
DROP TABLE IF EXISTS `t_company`;
CREATE TABLE `t_company` (
  `id` bigint(20) NOT NULL,
  `group_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `group_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `liscense_num` varchar(40) NOT NULL,
  `group_type` varchar(1) NOT NULL,
  `group_tele` varchar(50) NOT NULL,
  `fax` varchar(50) NOT NULL,
  `group_email` varchar(20) NOT NULL,
  `group_address` varchar(30) NOT NULL,
  `prj_name` varchar(40) NOT NULL,
  `zip_code` varchar(6) NOT NULL,
  `prj_contract` varchar(50) NOT NULL,
  `prj_start_date` datetime NOT NULL,
  `prj_end_date` datetime NOT NULL,
  `status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_company
-- ----------------------------
INSERT INTO `t_company` VALUES ('247411056656261120', '1zhiling133873211245', 'zhiling', '123456', '1', '133873211245', '0731-12345678', '2472662162@qq.com', '湖南省长沙市', 'dev-cloud', '417000', 'zl-0149001', '2018-10-08 00:00:00', '2020-10-30 00:00:00', 'DISABLE', '2018-10-10 06:44:18', '2018-10-10 06:44:18');
INSERT INTO `t_company` VALUES ('247545623635599360', '1zhiling1133873211245', 'zhiling1', '123456', '1', '133873211245', '0731-12345678', '2472662162@qq.com', '湖南省长沙市', 'dev-cloud', '417000', 'zl-0149001', '2018-10-08 00:00:00', '2020-10-30 00:00:00', 'DISABLE', '2018-10-10 15:39:01', '2018-10-10 15:39:01');
INSERT INTO `t_company` VALUES ('247547366347939840', '1zhiling12133873211245', 'zhiling12', '123456', '1', '133873211245', '0731-12345678', '2472662162@qq.com', '湖南省长沙市', 'dev-cloud', '417000', 'zl-0149001', '2018-10-08 00:00:00', '2020-10-30 00:00:00', 'DISABLE', '2018-10-10 15:45:57', '2018-10-10 15:45:57');
INSERT INTO `t_company` VALUES ('247717562924699648', '1zl10731456789', 'zl1', '21212', '1', '0731456789', 'app', '2472662162@qq.com', '湖南省', 'zl-app', '417000', '1239', '2018-10-08 00:00:00', '2018-10-08 00:00:00', 'DISABLE', '2018-10-11 03:02:15', '2018-10-11 03:02:15');
INSERT INTO `t_company` VALUES ('253117902574653440', '1zhiling12133873211245', 'zhiling12', '123456', '1', '133873211245', '0731-12345678', '2472662162@qq.com', '湖南省长沙市', 'dev-cloud', '417000', 'zl-0149001', '2018-10-08 00:00:00', '2020-10-30 00:00:00', 'DISABLE', '2018-10-26 00:41:16', '2018-10-26 00:41:16');
INSERT INTO `t_company` VALUES ('253125291763892224', '1zhiling12133873211245', 'zhiling12', '123456', '1', '133873211245', '0731-12345678', '2472662162@qq.com', '湖南省长沙市', 'dev-cloud', '417000', 'zl-0149001', '2018-10-08 00:00:00', '2020-10-30 00:00:00', 'DISABLE', '2018-10-26 01:10:38', '2018-10-26 01:10:38');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `role_code` varchar(32) NOT NULL,
  `role_name` varchar(32) NOT NULL,
  `role_desc` varchar(64) NOT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'r1234', '管理员', '管理员', '2018-10-30 00:48:35', '2018-10-30 00:48:35');
INSERT INTO `t_role` VALUES ('2', 'r12345', '超级管理员', '超级管理员', '2018-10-30 00:49:28', '2018-10-30 00:49:28');
INSERT INTO `t_role` VALUES ('3', 'user', '普通用户', '普通用户', '2018-10-30 00:49:56', '2018-10-30 00:49:56');
INSERT INTO `t_role` VALUES ('4', 'senior user', '高级用户', '高级用户', '2018-10-30 00:52:19', '2018-10-30 00:52:19');
INSERT INTO `t_role` VALUES ('5', 'opsuser', '运维用户', '运维用户', '2018-10-30 00:52:50', '2018-10-30 01:09:00');

-- ----------------------------
-- Table structure for t_role_action
-- ----------------------------
DROP TABLE IF EXISTS `t_role_action`;
CREATE TABLE `t_role_action` (
  `role_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `action` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_action
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL COMMENT '用户ID',
  `login_name` varchar(32) NOT NULL COMMENT '用户名',
  `login_pwd` varchar(64) NOT NULL COMMENT '密码',
  `user_name` varchar(32) NOT NULL COMMENT '姓名',
  `email` varchar(32) NOT NULL COMMENT '邮箱',
  `error_times` smallint(6) DEFAULT '0' COMMENT '输错次数',
  `permissions` varchar(50) DEFAULT '' COMMENT '权限',
  `mobile_no` varchar(64) NOT NULL COMMENT '手机',
  `id_card` varchar(64) NOT NULL COMMENT '身份证',
  `group_type` varchar(1) NOT NULL COMMENT '组类型',
  `group_tele` varchar(30) NOT NULL COMMENT '组电话',
  `group_code` varchar(20) NOT NULL COMMENT '组编号',
  `group_name` varchar(50) NOT NULL COMMENT '组名',
  `active_code` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '激活码',
  `status` varchar(20) DEFAULT '' COMMENT '账号状态',
  `activated_time` datetime DEFAULT NULL COMMENT '激活时间',
  `regist_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `lastpwd_error_time` datetime DEFAULT NULL COMMENT '最后一次密码输错时间',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('250618264818896896', 'tancan', '$2a$10$zKDEDj2O7lOmrRIPLQP14urSKaPac0uGUqBsgPdMAU9LtLjZygoW2', 'tancan', '2472662162@qq.com', '0', '', '133873211245', '43250112355775', '0', '0738-4120890', '100201', 'zl', '3d38716627414ae596d3', 'ENABLE', null, '2018-10-19 03:08:36', null, '2018-10-19 03:08:36', '2018-10-19 03:08:36');
INSERT INTO `t_user` VALUES ('250618401158942720', 'test', '$2a$10$r8K31EPZF4ibuM/9Y00q/uGx.IxFqeDjmfGLYWgW3WNqNVgBCTL3m', 'tancan', '24726621621@qq.com', '0', '', '133873211245', '43250112355775', '0', '0738-4120890', '100201', 'zl', '2a37f9147ea54beb9f6d', 'ENABLE', null, '2018-10-19 03:09:09', null, '2018-10-19 03:09:09', '2018-10-19 03:09:09');
INSERT INTO `t_user` VALUES ('253117807141654528', 'test3', '$2a$10$kUVxW42G2ZMMyfeo1Zu9NuCu7dYqwlVYtlvF/5QSyT7ZIa69DIiti', 'tancan', '24726621621@qq.com', '0', '', '133873211245', '43250112355775', '0', '0738-4120890', '100201', 'zl', 'a9c0f36c7fb743aaa0d7', 'ENABLE', null, '2018-10-26 00:40:53', null, '2018-10-26 00:40:53', '2018-10-26 00:40:53');
INSERT INTO `t_user` VALUES ('253125280116310016', 'admin', '$2a$10$GVhAK3zx/T6n89OXODya2O0llMCfaec/68q9A/QJjlQ/VuxHBFcty', 'tancan', '24726621621@qq.com', '0', '', '133873211245', '43250112355775', '0', '0738-4120890', '100201', 'zl', '718fb47c16b848929d2a', 'ENABLE', null, '2018-10-26 01:10:35', null, '2018-10-26 01:10:35', '2018-10-26 01:10:35');
INSERT INTO `t_user` VALUES ('254934374770884608', 'bcng', '$2a$10$ii3TTIvpmG/3c.auKIouk.jI4RXOyPysVtil4awr38JnGFgnR.zca', 'bcng', 'bcng@zx.com', '0', '', '133873211245', '43250112355775', '0', '0738-4120890', '100201', 'zl', '1c6cbe6789144e8d8240', 'ENABLE', null, '2018-10-31 00:59:17', null, '2018-10-31 00:59:17', '2018-10-31 00:59:17');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_code` varchar(32) NOT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`,`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('123', 'user', '2018-10-30 07:53:26', '2018-10-30 07:53:26');
INSERT INTO `t_user_role` VALUES ('250618401158942720', 'user', '2018-10-30 07:55:20', '2018-10-30 07:55:20');
INSERT INTO `t_user_role` VALUES ('253125280116310016', 'admin', '2018-10-30 07:55:33', '2018-10-30 07:55:33');
INSERT INTO `t_user_role` VALUES ('253125280116310016', 'superadmin', '2018-10-30 08:20:24', '2018-10-30 08:20:24');
INSERT INTO `t_user_role` VALUES ('253125280116310016', 'user', '2018-10-30 08:19:21', '2018-10-30 08:19:21');
