/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : hhnail

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 24/10/2022 20:56:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for api
-- ----------------------------
DROP TABLE IF EXISTS `api`;
CREATE TABLE `api`  (
  `api_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `c_time` datetime NULL DEFAULT NULL,
  `u_time` datetime NULL DEFAULT NULL,
  `config` json NULL COMMENT '配置信息',
  `is_del` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '是否删除',
  `req_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求方式',
  `timeout` bigint NULL DEFAULT NULL COMMENT '超时时间（单位：毫秒）',
  PRIMARY KEY (`api_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of api
-- ----------------------------
INSERT INTO `api` VALUES (1, '获取用户信息', '2022-01-14 14:10:19', '2022-01-14 14:10:23', NULL, 0, 'GET', 3000);
INSERT INTO `api` VALUES (2, '测试idea数据库插件', NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `api` VALUES (3, NULL, NULL, NULL, NULL, 0, NULL, NULL);

-- ----------------------------
-- Table structure for free_report
-- ----------------------------
DROP TABLE IF EXISTS `free_report`;
CREATE TABLE `free_report`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报表名称',
  `module_id` int NULL DEFAULT NULL COMMENT '所属模块id',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `report_sql` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报表SQL语句',
  `columns_view` json NULL COMMENT '展示字段',
  `columns_query` json NULL COMMENT '查询字段',
  `deleted` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '删除标记（0：未删除；1：已删除）',
  `order_id` int NULL DEFAULT -1 COMMENT '排序号',
  `primary_table` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主表',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of free_report
-- ----------------------------

-- ----------------------------
-- Table structure for header_menu
-- ----------------------------
DROP TABLE IF EXISTS `header_menu`;
CREATE TABLE `header_menu`  (
  `id` int NOT NULL COMMENT '唯一编号',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `order_id` int NULL DEFAULT NULL COMMENT '排序号',
  `deleted` int NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of header_menu
-- ----------------------------
INSERT INTO `header_menu` VALUES (1, '员工桌面', 1, 0);
INSERT INTO `header_menu` VALUES (2, '人力资本', 2, 0);
INSERT INTO `header_menu` VALUES (3, '管理赋能', 3, 0);
INSERT INTO `header_menu` VALUES (4, '领导决策', 4, 0);
INSERT INTO `header_menu` VALUES (5, '配置平台', 5, 0);

-- ----------------------------
-- Table structure for login_log
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `staff_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工号',
  `login_time` datetime NULL DEFAULT NULL COMMENT '登录时间',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '上次登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of login_log
-- ----------------------------

-- ----------------------------
-- Table structure for operation_log
-- ----------------------------
DROP TABLE IF EXISTS `operation_log`;
CREATE TABLE `operation_log`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `operation_route` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作路由',
  `operation_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作名称',
  `operation_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
  `operation_user_id` int NULL DEFAULT NULL COMMENT '操作人工号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of operation_log
-- ----------------------------

-- ----------------------------
-- Table structure for personnel_category
-- ----------------------------
DROP TABLE IF EXISTS `personnel_category`;
CREATE TABLE `personnel_category`  (
  `id` int NOT NULL COMMENT '唯一编码',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `p_id` int NULL DEFAULT NULL COMMENT '父编码',
  `other_language` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '其他语言',
  `english_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of personnel_category
-- ----------------------------
INSERT INTO `personnel_category` VALUES (1, '在职', NULL, '正式员工', 'Permanent employees');
INSERT INTO `personnel_category` VALUES (2, '不在职', NULL, '劳务派遣工', 'Probationary employees');
INSERT INTO `personnel_category` VALUES (10, '试用期', 1, '', NULL);
INSERT INTO `personnel_category` VALUES (11, '正式员工', 1, '', NULL);
INSERT INTO `personnel_category` VALUES (12, '长期病休', 1, '', NULL);
INSERT INTO `personnel_category` VALUES (13, '停薪留职', 1, '', NULL);
INSERT INTO `personnel_category` VALUES (14, '待岗', 1, '', NULL);
INSERT INTO `personnel_category` VALUES (15, '工伤医疗期内', 1, '', NULL);
INSERT INTO `personnel_category` VALUES (16, '其他不在岗', 1, '', NULL);
INSERT INTO `personnel_category` VALUES (17, '劳务派遣工', 1, '', NULL);
INSERT INTO `personnel_category` VALUES (18, '非全日制工', 1, '', NULL);
INSERT INTO `personnel_category` VALUES (19, '实习生', 1, '', NULL);
INSERT INTO `personnel_category` VALUES (20, '外聘专家', 1, '', NULL);
INSERT INTO `personnel_category` VALUES (21, '见习人员', 1, '', NULL);
INSERT INTO `personnel_category` VALUES (23, '其他人员', 1, '', NULL);
INSERT INTO `personnel_category` VALUES (24, '内退', 1, '', NULL);
INSERT INTO `personnel_category` VALUES (25, '退岗安置', 1, '', NULL);
INSERT INTO `personnel_category` VALUES (30, '离职', 2, '', NULL);
INSERT INTO `personnel_category` VALUES (31, '退休', 2, '', NULL);

-- ----------------------------
-- Table structure for process_node
-- ----------------------------
DROP TABLE IF EXISTS `process_node`;
CREATE TABLE `process_node`  (
  `id` int NOT NULL,
  `template_id` int NULL DEFAULT NULL COMMENT '模板ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审批节点名称',
  `approver_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审批人ID',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `rule` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点规则',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of process_node
-- ----------------------------

-- ----------------------------
-- Table structure for process_template
-- ----------------------------
DROP TABLE IF EXISTS `process_template`;
CREATE TABLE `process_template`  (
  `id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板名称',
  `creater_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人ID',
  `creater_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '最后更新时间',
  `deleted` int NULL DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of process_template
-- ----------------------------

-- ----------------------------
-- Table structure for resignation_nature
-- ----------------------------
DROP TABLE IF EXISTS `resignation_nature`;
CREATE TABLE `resignation_nature`  (
  `id` int NOT NULL COMMENT '唯一编码',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `p_id` int NULL DEFAULT NULL COMMENT '父编码',
  `other_language` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '其他语言',
  `english_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文名称',
  `grade` int NULL DEFAULT NULL COMMENT '级数',
  `chinese_traditional` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中文繁体',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resignation_nature
-- ----------------------------

-- ----------------------------
-- Table structure for resignation_type
-- ----------------------------
DROP TABLE IF EXISTS `resignation_type`;
CREATE TABLE `resignation_type`  (
  `id` int NOT NULL COMMENT '唯一编码',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `p_id` int NULL DEFAULT NULL COMMENT '父编码',
  `other_language` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '其他语言',
  `english_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文名称',
  `grade` int NULL DEFAULT NULL COMMENT '级数',
  `chinese_traditional` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中文繁体',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resignation_type
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '唯一编号',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `deleted` int NULL DEFAULT 0,
  `p_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for single_code_recruitment_source
-- ----------------------------
DROP TABLE IF EXISTS `single_code_recruitment_source`;
CREATE TABLE `single_code_recruitment_source`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `deleted` int NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of single_code_recruitment_source
-- ----------------------------
INSERT INTO `single_code_recruitment_source` VALUES (1, 'BOSS直聘', 0);
INSERT INTO `single_code_recruitment_source` VALUES (2, '校园宣讲会', 0);
INSERT INTO `single_code_recruitment_source` VALUES (3, '智联招聘', 0);
INSERT INTO `single_code_recruitment_source` VALUES (4, '51job', 0);
INSERT INTO `single_code_recruitment_source` VALUES (5, '猎头引荐', 0);

-- ----------------------------
-- Table structure for single_code_staff_type
-- ----------------------------
DROP TABLE IF EXISTS `single_code_staff_type`;
CREATE TABLE `single_code_staff_type`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `english_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文名称',
  `deleted` int NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of single_code_staff_type
-- ----------------------------
INSERT INTO `single_code_staff_type` VALUES (1, '正式员工', NULL, 0);
INSERT INTO `single_code_staff_type` VALUES (2, '外包员工', NULL, 0);
INSERT INTO `single_code_staff_type` VALUES (3, '实习生', NULL, 0);
INSERT INTO `single_code_staff_type` VALUES (4, '离职员工', NULL, 0);
INSERT INTO `single_code_staff_type` VALUES (6, '其他', NULL, 0);

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `job_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工号',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `used_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '曾用名',
  `gender` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `entry_time` datetime NULL DEFAULT NULL COMMENT '入职日期',
  `quit_time` datetime NULL DEFAULT NULL COMMENT '离职日期',
  `staff_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工类型',
  `deleted` int NULL DEFAULT NULL COMMENT '删除标记',
  `recruitment_source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '招聘来源',
  `name6` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name7` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES (1, '221587', '张三', '张叁', '男', '2022-10-07', '2021-12-14 21:57:46', '2022-06-09 21:57:54', '1', 0, 'BOSS直聘', NULL, NULL);
INSERT INTO `staff` VALUES (2, '221612', '李四', '李思', '女', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `staff` VALUES (3, '221588', '王五', '赵六', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_column
-- ----------------------------
DROP TABLE IF EXISTS `sys_column`;
CREATE TABLE `sys_column`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中文名称',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字段名称',
  `visible` int NULL DEFAULT 1 COMMENT '系统全局可见标记',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字段数据类型（int、date...）',
  `length` int NULL DEFAULT NULL COMMENT '字段长度',
  `accuracy` int NULL DEFAULT NULL COMMENT '字段精度',
  `nullable` int NULL DEFAULT 1 COMMENT '是否可为空',
  `table_key` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '键类型',
  `default_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '默认值',
  `auto_increment` int NULL DEFAULT 0 COMMENT '是否自增',
  `virtual` int NULL DEFAULT 0 COMMENT '是否虚拟',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `deleted` int NULL DEFAULT 0 COMMENT '删除标记',
  `sys_table_id` int NULL DEFAULT NULL COMMENT '外键_所属表',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 85 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_column
-- ----------------------------
INSERT INTO `sys_column` VALUES (1, NULL, 'name', 1, NULL, NULL, NULL, 1, NULL, NULL, 0, 0, '备注1', 0, NULL);
INSERT INTO `sys_column` VALUES (4, '编号', 'id', 1, NULL, NULL, NULL, 1, NULL, NULL, 0, 0, '备注1', 0, 1);
INSERT INTO `sys_column` VALUES (5, '编号', 'id', 1, 'int', 11, 0, 1, 'PK', NULL, 1, 0, '备注1', 0, 20);
INSERT INTO `sys_column` VALUES (6, '姓名', 'name', 1, 'varchar', 50, NULL, 1, NULL, '张三', 0, 0, '备注1', 0, 20);
INSERT INTO `sys_column` VALUES (7, '父表', 'parent_id', 1, 'int', 11, 0, 1, 'FK', NULL, 0, 0, NULL, 0, 20);
INSERT INTO `sys_column` VALUES (8, '测试', NULL, 1, NULL, NULL, NULL, 1, NULL, NULL, 0, 0, NULL, 0, 20);
INSERT INTO `sys_column` VALUES (9, '而实', NULL, 1, NULL, NULL, NULL, 1, NULL, NULL, 0, 0, NULL, 0, 20);
INSERT INTO `sys_column` VALUES (42, '', 'id', 1, NULL, NULL, NULL, 1, NULL, NULL, 0, 0, NULL, 0, 24);
INSERT INTO `sys_column` VALUES (43, '', 'name', 0, NULL, NULL, NULL, 1, NULL, NULL, 0, 0, NULL, 0, 24);
INSERT INTO `sys_column` VALUES (44, '', 'id', 1, NULL, NULL, NULL, 1, NULL, NULL, 0, 0, NULL, 0, 25);
INSERT INTO `sys_column` VALUES (45, '', 'name', 0, NULL, NULL, NULL, 1, NULL, NULL, 0, 0, NULL, 0, 25);
INSERT INTO `sys_column` VALUES (46, '', 'p_id', 0, NULL, NULL, NULL, 1, NULL, NULL, 0, 0, NULL, 0, 25);
INSERT INTO `sys_column` VALUES (47, '', 'other_language', 0, NULL, NULL, NULL, 1, NULL, NULL, 0, 0, NULL, 0, 25);
INSERT INTO `sys_column` VALUES (48, '', 'english_name', 0, NULL, NULL, NULL, 1, NULL, NULL, 0, 0, NULL, 0, 25);
INSERT INTO `sys_column` VALUES (49, '', 'id', 1, NULL, NULL, NULL, 1, NULL, NULL, 0, 0, NULL, 0, 26);
INSERT INTO `sys_column` VALUES (50, '', 'name', 0, NULL, NULL, NULL, 1, NULL, NULL, 0, 0, NULL, 0, 26);
INSERT INTO `sys_column` VALUES (51, '', 'p_id', 0, NULL, NULL, NULL, 1, NULL, NULL, 0, 0, NULL, 0, 26);
INSERT INTO `sys_column` VALUES (52, '', 'other_language', 0, NULL, NULL, NULL, 1, NULL, NULL, 0, 0, NULL, 0, 26);
INSERT INTO `sys_column` VALUES (53, '', 'english_name', 0, NULL, NULL, NULL, 1, NULL, NULL, 0, 0, NULL, 0, 26);
INSERT INTO `sys_column` VALUES (54, '', 'grade', 0, NULL, NULL, NULL, 1, NULL, NULL, 0, 0, NULL, 0, 26);
INSERT INTO `sys_column` VALUES (55, '', 'chinese_traditional', 0, NULL, NULL, NULL, 1, NULL, NULL, 0, 0, NULL, 0, 26);
INSERT INTO `sys_column` VALUES (56, '', 'id', 1, NULL, NULL, NULL, 1, NULL, NULL, 0, 0, NULL, 0, 27);
INSERT INTO `sys_column` VALUES (57, '', 'name', 0, NULL, NULL, NULL, 1, NULL, NULL, 0, 0, NULL, 0, 27);
INSERT INTO `sys_column` VALUES (58, '', 'p_id', 0, NULL, NULL, NULL, 1, NULL, NULL, 0, 0, NULL, 0, 27);
INSERT INTO `sys_column` VALUES (59, '', 'other_language', 0, NULL, NULL, NULL, 1, NULL, NULL, 0, 0, NULL, 0, 27);
INSERT INTO `sys_column` VALUES (60, '', 'english_name', 0, NULL, NULL, NULL, 1, NULL, NULL, 0, 0, NULL, 0, 27);
INSERT INTO `sys_column` VALUES (61, '', 'grade', 0, NULL, NULL, NULL, 1, NULL, NULL, 0, 0, NULL, 0, 27);
INSERT INTO `sys_column` VALUES (62, '', 'chinese_traditional', 0, NULL, NULL, NULL, 1, NULL, NULL, 0, 0, NULL, 0, 27);
INSERT INTO `sys_column` VALUES (63, '编号', 'id', 0, 'int', 11, NULL, 1, '1', NULL, 1, 0, '人员编号', 0, 6);
INSERT INTO `sys_column` VALUES (83, NULL, 'name6', NULL, 'varchar', 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, 0, 6);
INSERT INTO `sys_column` VALUES (84, NULL, 'name7', NULL, 'varchar', 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, 0, 6);

-- ----------------------------
-- Table structure for sys_table
-- ----------------------------
DROP TABLE IF EXISTS `sys_table`;
CREATE TABLE `sys_table`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表英文名',
  `label` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表中文名',
  `module_id` int NULL DEFAULT NULL COMMENT '所属模块编号',
  `order_id` int NULL DEFAULT NULL COMMENT '排序号',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `deleted` int NULL DEFAULT 0 COMMENT '删除标记',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表类型（单级码表、多级码表）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_table
-- ----------------------------
INSERT INTO `sys_table` VALUES (1, 'role', '角色表', NULL, 2, NULL, 0, NULL);
INSERT INTO `sys_table` VALUES (2, 'sys_column', '系统字段表', NULL, 4, NULL, 0, NULL);
INSERT INTO `sys_table` VALUES (3, 'sys_table', '系统表表', NULL, 4, NULL, 0, NULL);
INSERT INTO `sys_table` VALUES (5, 'tree_node', '系统树结构表', NULL, 4, NULL, 0, NULL);
INSERT INTO `sys_table` VALUES (6, 'staff', '员工表', NULL, 5, NULL, 0, NULL);
INSERT INTO `sys_table` VALUES (19, 'test2123', '测试123', NULL, 4, NULL, 0, NULL);
INSERT INTO `sys_table` VALUES (20, 'test2new2', '测试2', NULL, 1, NULL, 0, NULL);
INSERT INTO `sys_table` VALUES (24, 'personnel_category', '人员类别', 1, 1, NULL, 0, 'SINGLE_CODE');
INSERT INTO `sys_table` VALUES (25, 'personnel_category', '人员类别', 1, 1, NULL, 0, 'MULTILEVEL_CODE');
INSERT INTO `sys_table` VALUES (26, 'resignation_type', '离职类型', 1, 1, NULL, 0, 'SINGLE_CODE');
INSERT INTO `sys_table` VALUES (27, 'resignation_nature', '离职性质', 1, 1, NULL, 0, 'SINGLE_CODE');

-- ----------------------------
-- Table structure for test2123
-- ----------------------------
DROP TABLE IF EXISTS `test2123`;
CREATE TABLE `test2123`  (
  `id` int NOT NULL COMMENT '唯一编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test2123
-- ----------------------------
INSERT INTO `test2123` VALUES (1);
INSERT INTO `test2123` VALUES (2);

-- ----------------------------
-- Table structure for test2new2
-- ----------------------------
DROP TABLE IF EXISTS `test2new2`;
CREATE TABLE `test2new2`  (
  `id` int NOT NULL COMMENT '唯一编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test2new2
-- ----------------------------
INSERT INTO `test2new2` VALUES (1);
INSERT INTO `test2new2` VALUES (3);
INSERT INTO `test2new2` VALUES (4);

-- ----------------------------
-- Table structure for tree_node
-- ----------------------------
DROP TABLE IF EXISTS `tree_node`;
CREATE TABLE `tree_node`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `pid` int NULL DEFAULT NULL COMMENT '父编号',
  `routing_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由地址',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型',
  `level` int NULL DEFAULT NULL COMMENT '所处层级',
  `leafy` int NOT NULL COMMENT '标记：叶子节点',
  `deleted` int NULL DEFAULT 0 COMMENT '标记：逻辑删除',
  `order_id` int NULL DEFAULT 0 COMMENT '排序号',
  `module_id` int NULL DEFAULT NULL COMMENT '所属模块编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 109 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tree_node
-- ----------------------------
INSERT INTO `tree_node` VALUES (1, '用户模块', NULL, '/test', 'DATA', 1, 1, 0, NULL, NULL);
INSERT INTO `tree_node` VALUES (2, '论坛模块', NULL, '/test', 'DATA', 1, 1, 0, NULL, NULL);
INSERT INTO `tree_node` VALUES (3, 'api1', NULL, '/test', 'DATA', 1, 0, 0, NULL, NULL);
INSERT INTO `tree_node` VALUES (4, 'api2', NULL, '/test', 'DATA', 1, 0, 0, NULL, NULL);
INSERT INTO `tree_node` VALUES (5, 'api1.1', 3, '/test', 'DATA', 2, 0, 0, NULL, NULL);
INSERT INTO `tree_node` VALUES (6, 'api1.2', 3, '/test', 'DATA', 2, 1, 0, NULL, NULL);
INSERT INTO `tree_node` VALUES (7, 'api2.1', 4, '/test', 'DATA', 2, 1, 0, NULL, NULL);
INSERT INTO `tree_node` VALUES (8, 'api2.2', 4, '/test', 'DATA', 2, 1, 0, NULL, NULL);
INSERT INTO `tree_node` VALUES (9, 'api1.1.1', 5, '/test', 'DATA', 3, 0, 0, NULL, NULL);
INSERT INTO `tree_node` VALUES (10, 'api1.1.1.1', 9, '/test', 'DATA', 4, 1, 0, NULL, NULL);
INSERT INTO `tree_node` VALUES (11, '员工桌面', NULL, '/staffDesktop', 'HEADER_MENU', 1, 0, 0, 1, NULL);
INSERT INTO `tree_node` VALUES (12, '人力资本', NULL, '/humanResource', 'HEADER_MENU', 1, 0, 0, 3, NULL);
INSERT INTO `tree_node` VALUES (13, '管理赋能', NULL, '/manageEmpower', 'HEADER_MENU', 1, 0, 1, 4, NULL);
INSERT INTO `tree_node` VALUES (14, '领导决策', NULL, '/leaderDecide', 'HEADER_MENU', 1, 0, 1, 5, NULL);
INSERT INTO `tree_node` VALUES (15, '配置平台', NULL, '/configPlatform', 'HEADER_MENU', 1, 0, 0, 7, NULL);
INSERT INTO `tree_node` VALUES (16, '组织', 12, '/humanResource/organization', 'SIDEBAR', 2, 0, 0, NULL, 12);
INSERT INTO `tree_node` VALUES (17, '人员', 12, '/humanResource/staff', 'SIDEBAR', 2, 0, 0, NULL, 12);
INSERT INTO `tree_node` VALUES (18, '流程配置', 15, '/configPlatform/processConfig', 'SIDEBAR', 2, 0, 0, -1, 15);
INSERT INTO `tree_node` VALUES (19, '系统配置', 15, '/configPlatform/test', 'SIDEBAR', 2, 0, 0, NULL, 15);
INSERT INTO `tree_node` VALUES (20, '流程管理', 18, '/configPlatform/processConfig/processManage', 'SIDEBAR', 3, 0, 0, NULL, 15);
INSERT INTO `tree_node` VALUES (22, '报表统计', 18, '/configPlatform/test', 'SIDEBAR', 3, 0, 0, NULL, 15);
INSERT INTO `tree_node` VALUES (23, '流程监控', 20, '/configPlatform/processConfig/processManage/monitor', 'SIDEBAR', 4, 0, 0, NULL, 15);
INSERT INTO `tree_node` VALUES (24, '设置代理', 20, '/configPlatform/processConfig/processManage/setProxy', 'SIDEBAR', 4, 0, 0, NULL, 15);
INSERT INTO `tree_node` VALUES (25, '按实例查询', 22, '/configPlatform/test', 'SIDEBAR', 5, 0, 0, NULL, 15);
INSERT INTO `tree_node` VALUES (26, '按任务查询', 22, '/configPlatform/test', 'SIDEBAR', 5, 0, 0, NULL, 15);
INSERT INTO `tree_node` VALUES (27, '系统工具', 19, '/configPlatform/systemTool', 'SIDEBAR', 3, 0, 0, NULL, 15);
INSERT INTO `tree_node` VALUES (28, '数据重构', 27, '/configPlatform/systemTool/dataRestructure', 'SIDEBAR', 4, 0, 0, NULL, 15);
INSERT INTO `tree_node` VALUES (29, '自由表单', 27, '/configPlatform/systemTool/freeForm', 'SIDEBAR', 4, 0, 0, NULL, 15);
INSERT INTO `tree_node` VALUES (52, '我的申请', 11, '/staffDesktop/myApply', 'SIDEBAR', 2, 1, 1, NULL, 11);
INSERT INTO `tree_node` VALUES (53, '组织关系', 16, '/humanResource/organization/relationship', 'SIDEBAR', 3, 1, 0, NULL, 12);
INSERT INTO `tree_node` VALUES (54, '人力组织', 16, '/humanResource/organization/test2', 'SIDEBAR', 3, 1, 0, NULL, 12);
INSERT INTO `tree_node` VALUES (55, '投管组织', 16, '/humanResource/organization/touguan', 'SIDEBAR', 3, 1, 0, NULL, 12);
INSERT INTO `tree_node` VALUES (56, '业务查询', 16, '/humanResource/organization/test4', 'SIDEBAR', 3, 1, 0, NULL, 12);
INSERT INTO `tree_node` VALUES (57, '历史查询', 16, '/humanResource/organization/test5', 'SIDEBAR', 3, 1, 0, NULL, 12);
INSERT INTO `tree_node` VALUES (58, '多组织管理', 53, '/humanResource/organization/relationship/many', 'SIDEBAR', 4, 1, 0, NULL, 12);
INSERT INTO `tree_node` VALUES (59, '组织管理', 54, '/humanResource/test', 'SIDEBAR', 4, 1, 0, NULL, 12);
INSERT INTO `tree_node` VALUES (60, '投管组织组织管理', 55, '/humanResource/organization/test3/touguan', 'SIDEBAR', 4, 1, 0, NULL, 12);
INSERT INTO `tree_node` VALUES (61, '信息查询', 17, '/humanResource/test', 'SIDEBAR', 3, 1, 0, NULL, 12);
INSERT INTO `tree_node` VALUES (62, '增员业务', 17, '/humanResource/test', 'SIDEBAR', 3, 1, 0, NULL, 12);
INSERT INTO `tree_node` VALUES (63, '新员工入职', 62, '/humanResource/test', 'SIDEBAR', 4, 1, 0, NULL, 12);
INSERT INTO `tree_node` VALUES (64, '再入职', 62, '/humanResource/test', 'SIDEBAR', 4, 1, 0, NULL, 12);
INSERT INTO `tree_node` VALUES (65, '我的信息', 11, '/staffDesktop/myMessage', 'SIDEBAR', 2, 1, 0, NULL, 11);
INSERT INTO `tree_node` VALUES (66, '我的待办', 11, '/staffDesktop/myTodo', 'SIDEBAR', 2, 1, 0, NULL, 11);
INSERT INTO `tree_node` VALUES (67, '数据字典', 19, '/configPlatform/dataDictionary', 'SIDEBAR', 3, 1, 0, NULL, 15);
INSERT INTO `tree_node` VALUES (68, '单级编码', 67, '/configPlatform/dataDictionary/singleCode', 'SIDEBAR', 4, 1, 0, NULL, 15);
INSERT INTO `tree_node` VALUES (69, '多级编码', 67, '/configPlatform/dataDictionary/multilevelCode', 'SIDEBAR', 4, 1, 0, NULL, 15);
INSERT INTO `tree_node` VALUES (70, '模块管理', 27, '/configPlatform/systemTool/moduleMaintenance', 'SIDEBAR', 4, 1, 0, 0, 15);
INSERT INTO `tree_node` VALUES (71, 'test', 11, '/test', 'SIDEBAR', 2, 1, 1, NULL, NULL);
INSERT INTO `tree_node` VALUES (72, '在线学习', NULL, '/onlineLearn', 'HEADER_MENU', 1, 0, 0, 2, 72);
INSERT INTO `tree_node` VALUES (73, '组织', NULL, NULL, 'TABLE_GROUP', 1, 0, 0, 0, 12);
INSERT INTO `tree_node` VALUES (74, '人员', NULL, NULL, 'TABLE_GROUP', 1, 0, 0, 0, 12);
INSERT INTO `tree_node` VALUES (75, '主业务表', 74, NULL, 'TABLE_GROUP', 2, 1, 0, 0, 12);
INSERT INTO `tree_node` VALUES (76, '人员附表', 74, NULL, 'TABLE_GROUP', 2, 1, 0, 0, 12);
INSERT INTO `tree_node` VALUES (77, '主业务表', 73, NULL, 'TABLE_GROUP', 2, 1, 0, 0, 12);
INSERT INTO `tree_node` VALUES (78, '合同', NULL, NULL, 'TABLE_GROUP', 1, 1, 0, 0, NULL);
INSERT INTO `tree_node` VALUES (79, '招聘', NULL, NULL, 'TABLE_GROUP', 1, 1, 0, 0, NULL);
INSERT INTO `tree_node` VALUES (80, '管理员', NULL, NULL, 'ROLE_GROUP', 1, 0, 0, 0, NULL);
INSERT INTO `tree_node` VALUES (81, '运维管理员', 80, NULL, 'ROLE_GROUP', 2, 1, 0, 0, NULL);
INSERT INTO `tree_node` VALUES (82, '超级管理员', 80, NULL, 'ROLE_GROUP', 2, 1, 0, 0, NULL);
INSERT INTO `tree_node` VALUES (83, '系统阶段一', NULL, NULL, 'ROLE_GROUP', 1, 0, 0, 0, NULL);
INSERT INTO `tree_node` VALUES (84, '阿里巴巴', 83, NULL, 'ROLE_GROUP', 2, 1, 0, 0, NULL);
INSERT INTO `tree_node` VALUES (85, '百度', 83, NULL, 'ROLE_GROUP', 2, 1, 0, 0, NULL);
INSERT INTO `tree_node` VALUES (86, '审批', 12, '/humanResource/approve', 'SIDEBAR', 2, 1, 0, NULL, 12);
INSERT INTO `tree_node` VALUES (87, '合同', 12, '/humanResource/contract', 'SIDEBAR', 2, 1, 0, NULL, 12);
INSERT INTO `tree_node` VALUES (88, '招聘', 12, '/humanResource/recruit', 'SIDEBAR', 2, 1, 0, NULL, 12);
INSERT INTO `tree_node` VALUES (89, '人才开发', NULL, '/talentDevelop', 'HEADER_MENU', 1, 1, 0, 6, 89);
INSERT INTO `tree_node` VALUES (91, 'test', 72, '/onlineLearn/test', 'SIDEBAR', 2, 1, 0, NULL, 72);
INSERT INTO `tree_node` VALUES (92, 'test', 89, '/test', 'SIDEBAR', 2, 1, 1, NULL, NULL);
INSERT INTO `tree_node` VALUES (93, 'test', 89, '/test', 'SIDEBAR', 2, 1, 0, NULL, 89);
INSERT INTO `tree_node` VALUES (94, 'test', 89, '/test', 'SIDEBAR', 2, 1, 0, NULL, 89);
INSERT INTO `tree_node` VALUES (95, 'test', 89, '/test', 'SIDEBAR', 2, 1, 0, NULL, 89);
INSERT INTO `tree_node` VALUES (96, 'test2', 72, '/onlineLearn/test2', 'SIDEBAR', 2, 1, 0, NULL, 72);
INSERT INTO `tree_node` VALUES (97, '权限管理', 15, '/configPlatform/permissionManage', 'SIDEBAR', 2, 1, 0, 1, 15);
INSERT INTO `tree_node` VALUES (98, '/test', 15, '/configPlatform/test', 'SIDEBAR', 2, 1, 1, NULL, 15);
INSERT INTO `tree_node` VALUES (99, '角色管理', 97, '/configPlatform/permissionManage/role', 'SIDEBAR', 3, 1, 0, NULL, 15);
INSERT INTO `tree_node` VALUES (100, '权限分配', 97, '/configPlatform/permissionManage/permission', 'SIDEBAR', 3, 1, 0, NULL, 15);
INSERT INTO `tree_node` VALUES (101, '权限审批', 97, '/configPlatform/permissionManage/permissionApprove', 'SIDEBAR', 3, 1, 0, NULL, 15);
INSERT INTO `tree_node` VALUES (102, '权限托管', 97, '/configPlatform/permissionManage/trusteeship', 'SIDEBAR', 3, 1, 0, NULL, 15);
INSERT INTO `tree_node` VALUES (103, '测试', 11, '/staffDesktop/test', 'SIDEBAR', 2, 1, 1, NULL, 11);
INSERT INTO `tree_node` VALUES (104, '测试', 11, '/staffDesktop/测试', 'SIDEBAR', 2, 1, 1, NULL, 11);
INSERT INTO `tree_node` VALUES (105, '表单管理', 15, '/configPlatform/formConfig', 'SIDEBAR', 2, 1, 0, 2, 15);
INSERT INTO `tree_node` VALUES (106, '自由报表', 27, '/configPlatform/systemTool/freeReport', 'SIDEBAR', 4, 1, 0, NULL, 15);
INSERT INTO `tree_node` VALUES (107, '决策平台', NULL, '/policy', 'HEADER_MENU', 1, 0, 0, 8, NULL);
INSERT INTO `tree_node` VALUES (108, '台账报表', 107, '/policy/report', 'SIDEBAR', 2, 1, 0, NULL, 107);

-- ----------------------------
-- Function structure for getNodeByPid
-- ----------------------------
DROP FUNCTION IF EXISTS `getNodeByPid`;
delimiter ;;
CREATE FUNCTION `getNodeByPid`(`id` int)
 RETURNS varchar(1000) CHARSET utf8mb3
BEGIN
    DECLARE childList VARCHAR(1000);      # 返回叶子节点结果集
    DECLARE tempChild VARCHAR(1000);      # 临时存放子节点

    SET childList = '';
    SET tempChild = CAST(id as CHAR); 		# 将int类型转换为String

    WHILE tempChild is not null DO        # 循环，用于查询节点下所有的子节点
        SET childList = CONCAT(childList, ',', tempChild);   # 存入到返回结果中
        SELECT GROUP_CONCAT(id) INTO tempChild FROM tree_node where FIND_IN_SET(pid, tempChild) > 0;   # 查询节点下所有子节点
    END WHILE;
    RETURN SUBSTRING(childList, 2);       # 将返回结果处理，截取掉结果集前面的逗号
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
