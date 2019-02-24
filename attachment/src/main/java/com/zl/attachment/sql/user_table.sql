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
  `code` varchar(12) DEFAULT '' COMMENT '激活码',
  `status` varchar(20) DEFAULT '' COMMENT '账号状态',
  `activated_time` datetime DEFAULT NULL COMMENT '激活时间',
  `regist_time`  datetime DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `lastpwd_error_time`  datetime DEFAULT NULL COMMENT '最后一次密码输错时间',
  `created_time`  datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time`  datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';



CREATE TABLE ``  snmp4j
