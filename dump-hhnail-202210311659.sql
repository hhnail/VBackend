-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: hhnail
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `api`
--

DROP TABLE IF EXISTS `api`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `api` (
  `api_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `c_time` datetime DEFAULT NULL,
  `u_time` datetime DEFAULT NULL,
  `config` json DEFAULT NULL COMMENT '配置信息',
  `is_del` int(1) unsigned zerofill DEFAULT '0' COMMENT '是否删除',
  `req_type` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '请求方式',
  `timeout` bigint DEFAULT NULL COMMENT '超时时间（单位：毫秒）',
  PRIMARY KEY (`api_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `api`
--

LOCK TABLES `api` WRITE;
/*!40000 ALTER TABLE `api` DISABLE KEYS */;
INSERT INTO `api` VALUES (1,'获取用户信息','2022-01-14 14:10:19','2022-01-14 14:10:23',NULL,0,'GET',3000),(2,'测试idea数据库插件',NULL,NULL,NULL,0,NULL,NULL),(3,NULL,NULL,NULL,NULL,0,NULL,NULL);
/*!40000 ALTER TABLE `api` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `free_report`
--

DROP TABLE IF EXISTS `free_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `free_report` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '报表名称',
  `module_id` int DEFAULT NULL COMMENT '所属模块id',
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '描述',
  `report_sql` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '报表SQL语句',
  `columns_view` json DEFAULT NULL COMMENT '展示字段',
  `columns_query` json DEFAULT NULL COMMENT '查询字段',
  `deleted` int(1) unsigned zerofill DEFAULT '0' COMMENT '删除标记（0：未删除；1：已删除）',
  `order_id` int DEFAULT '-1' COMMENT '排序号',
  `primary_table` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '主表',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `free_report`
--

LOCK TABLES `free_report` WRITE;
/*!40000 ALTER TABLE `free_report` DISABLE KEYS */;
INSERT INTO `free_report` VALUES (9,'系统树结构查询',NULL,'查询系统中存在的各种树结构信息','select id,name,routing_address,type,LEVEL,leafy from tree_node where 1=1','[{\"key\": \"v_id\", \"title\": \"编码\", \"dataIndex\": \"v_id\", \"columnCode\": \"id\"}, {\"key\": \"v_name\", \"title\": \"姓名\", \"dataIndex\": \"v_name\", \"columnCode\": \"name\"}, {\"key\": \"v_routing_address\", \"title\": \"路由地址\", \"dataIndex\": \"v_routing_address\", \"columnCode\": \"routing_address\"}, {\"key\": \"v_leafy\", \"title\": \"是否为叶子节点\", \"dataIndex\": \"v_leafy\", \"columnCode\": \"leafy\"}]',NULL,0,-1,'tree_node');
/*!40000 ALTER TABLE `free_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `header_menu`
--

DROP TABLE IF EXISTS `header_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `header_menu` (
  `id` int NOT NULL COMMENT '唯一编号',
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '菜单名称',
  `order_id` int DEFAULT NULL COMMENT '排序号',
  `deleted` int DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `header_menu`
--

LOCK TABLES `header_menu` WRITE;
/*!40000 ALTER TABLE `header_menu` DISABLE KEYS */;
INSERT INTO `header_menu` VALUES (1,'员工桌面',1,0),(2,'人力资本',2,0),(3,'管理赋能',3,0),(4,'领导决策',4,0),(5,'配置平台',5,0);
/*!40000 ALTER TABLE `header_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_log`
--

DROP TABLE IF EXISTS `login_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `staff_id` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '工号',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_log`
--

LOCK TABLES `login_log` WRITE;
/*!40000 ALTER TABLE `login_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `login_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operation_log`
--

DROP TABLE IF EXISTS `operation_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operation_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `operation_route` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '操作路由',
  `operation_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '操作名称',
  `operation_time` datetime DEFAULT NULL COMMENT '操作时间',
  `operation_user_id` int DEFAULT NULL COMMENT '操作人工号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operation_log`
--

LOCK TABLES `operation_log` WRITE;
/*!40000 ALTER TABLE `operation_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `operation_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personnel_category`
--

DROP TABLE IF EXISTS `personnel_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personnel_category` (
  `id` int NOT NULL COMMENT '唯一编码',
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '名称',
  `p_id` int DEFAULT NULL COMMENT '父编码',
  `other_language` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '其他语言',
  `english_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '英文名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personnel_category`
--

LOCK TABLES `personnel_category` WRITE;
/*!40000 ALTER TABLE `personnel_category` DISABLE KEYS */;
INSERT INTO `personnel_category` VALUES (1,'在职',NULL,'正式员工','Permanent employees'),(2,'不在职',NULL,'劳务派遣工','Probationary employees'),(10,'试用期',1,'',NULL),(11,'正式员工',1,'',NULL),(12,'长期病休',1,'',NULL),(13,'停薪留职',1,'',NULL),(14,'待岗',1,'',NULL),(15,'工伤医疗期内',1,'',NULL),(16,'其他不在岗',1,'',NULL),(17,'劳务派遣工',1,'',NULL),(18,'非全日制工',1,'',NULL),(19,'实习生',1,'',NULL),(20,'外聘专家',1,'',NULL),(21,'见习人员',1,'',NULL),(23,'其他人员',1,'',NULL),(24,'内退',1,'',NULL),(25,'退岗安置',1,'',NULL),(30,'离职',2,'',NULL),(31,'退休',2,'',NULL);
/*!40000 ALTER TABLE `personnel_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `process_node`
--

DROP TABLE IF EXISTS `process_node`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `process_node` (
  `id` int NOT NULL,
  `template_id` int DEFAULT NULL COMMENT '模板ID',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '审批节点名称',
  `approver_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '审批人ID',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `rule` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '节点规则',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `process_node`
--

LOCK TABLES `process_node` WRITE;
/*!40000 ALTER TABLE `process_node` DISABLE KEYS */;
/*!40000 ALTER TABLE `process_node` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `process_template`
--

DROP TABLE IF EXISTS `process_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `process_template` (
  `id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '模板名称',
  `creater_id` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '创建人ID',
  `creater_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `deleted` int DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `process_template`
--

LOCK TABLES `process_template` WRITE;
/*!40000 ALTER TABLE `process_template` DISABLE KEYS */;
/*!40000 ALTER TABLE `process_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resignation_nature`
--

DROP TABLE IF EXISTS `resignation_nature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resignation_nature` (
  `id` int NOT NULL COMMENT '唯一编码',
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '名称',
  `p_id` int DEFAULT NULL COMMENT '父编码',
  `other_language` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '其他语言',
  `english_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '英文名称',
  `grade` int DEFAULT NULL COMMENT '级数',
  `chinese_traditional` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '中文繁体',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resignation_nature`
--

LOCK TABLES `resignation_nature` WRITE;
/*!40000 ALTER TABLE `resignation_nature` DISABLE KEYS */;
/*!40000 ALTER TABLE `resignation_nature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resignation_type`
--

DROP TABLE IF EXISTS `resignation_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resignation_type` (
  `id` int NOT NULL COMMENT '唯一编码',
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '名称',
  `p_id` int DEFAULT NULL COMMENT '父编码',
  `other_language` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '其他语言',
  `english_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '英文名称',
  `grade` int DEFAULT NULL COMMENT '级数',
  `chinese_traditional` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '中文繁体',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resignation_type`
--

LOCK TABLES `resignation_type` WRITE;
/*!40000 ALTER TABLE `resignation_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `resignation_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '唯一编号',
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '角色名称',
  `deleted` int DEFAULT '0',
  `p_id` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `single_code_recruitment_source`
--

DROP TABLE IF EXISTS `single_code_recruitment_source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `single_code_recruitment_source` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `deleted` int DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `single_code_recruitment_source`
--

LOCK TABLES `single_code_recruitment_source` WRITE;
/*!40000 ALTER TABLE `single_code_recruitment_source` DISABLE KEYS */;
INSERT INTO `single_code_recruitment_source` VALUES (1,'BOSS直聘',0),(2,'校园宣讲会',0),(3,'智联招聘',0),(4,'51job',0),(5,'猎头引荐',0);
/*!40000 ALTER TABLE `single_code_recruitment_source` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `single_code_staff_type`
--

DROP TABLE IF EXISTS `single_code_staff_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `single_code_staff_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '名称',
  `english_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '英文名称',
  `deleted` int DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `single_code_staff_type`
--

LOCK TABLES `single_code_staff_type` WRITE;
/*!40000 ALTER TABLE `single_code_staff_type` DISABLE KEYS */;
INSERT INTO `single_code_staff_type` VALUES (1,'正式员工',NULL,0),(2,'外包员工',NULL,0),(3,'实习生',NULL,0),(4,'离职员工',NULL,0),(6,'其他',NULL,0);
/*!40000 ALTER TABLE `single_code_staff_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `id` int NOT NULL AUTO_INCREMENT,
  `job_no` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '工号',
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '姓名',
  `used_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '曾用名',
  `gender` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '性别',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `entry_time` datetime DEFAULT NULL COMMENT '入职日期',
  `quit_time` datetime DEFAULT NULL COMMENT '离职日期',
  `staff_type` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '员工类型',
  `deleted` int DEFAULT NULL COMMENT '删除标记',
  `recruitment_source` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '招聘来源',
  `name6` varchar(3) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `name7` varchar(3) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'221587','张三','张叁','男','2022-10-07','2021-12-14 21:57:46','2022-06-09 21:57:54','1',0,'BOSS直聘',NULL,NULL),(2,'221612','李四','李思','女',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,'221588','王五','赵六',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_column`
--

DROP TABLE IF EXISTS `sys_column`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_column` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `label` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '中文名称',
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '字段名称',
  `visible` int DEFAULT '1' COMMENT '系统全局可见标记',
  `type` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '字段数据类型（int、date...）',
  `length` int DEFAULT NULL COMMENT '字段长度',
  `accuracy` int DEFAULT NULL COMMENT '字段精度',
  `nullable` int DEFAULT '1' COMMENT '是否可为空',
  `table_key` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '键类型',
  `default_value` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '默认值',
  `auto_increment` int DEFAULT '0' COMMENT '是否自增',
  `virtual` int DEFAULT '0' COMMENT '是否虚拟',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '备注',
  `deleted` int DEFAULT '0' COMMENT '删除标记',
  `sys_table_id` int DEFAULT NULL COMMENT '外键_所属表',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_column`
--

LOCK TABLES `sys_column` WRITE;
/*!40000 ALTER TABLE `sys_column` DISABLE KEYS */;
INSERT INTO `sys_column` VALUES (1,NULL,'name',1,NULL,NULL,NULL,1,NULL,NULL,0,0,'备注1',0,NULL),(4,'编号','id',1,NULL,NULL,NULL,1,NULL,NULL,0,0,'备注1',0,1),(5,'编号','id',1,'int',11,0,1,'PK',NULL,1,0,'备注1',0,20),(6,'姓名','name',1,'varchar',50,NULL,1,NULL,'张三',0,0,'备注1',0,20),(7,'父表','parent_id',1,'int',11,0,1,'FK',NULL,0,0,NULL,0,20),(8,'测试',NULL,1,NULL,NULL,NULL,1,NULL,NULL,0,0,NULL,0,20),(9,'而实',NULL,1,NULL,NULL,NULL,1,NULL,NULL,0,0,NULL,0,20),(42,'','id',1,NULL,NULL,NULL,1,NULL,NULL,0,0,NULL,0,24),(43,'','name',0,NULL,NULL,NULL,1,NULL,NULL,0,0,NULL,0,24),(44,'','id',1,NULL,NULL,NULL,1,NULL,NULL,0,0,NULL,0,25),(45,'','name',0,NULL,NULL,NULL,1,NULL,NULL,0,0,NULL,0,25),(46,'','p_id',0,NULL,NULL,NULL,1,NULL,NULL,0,0,NULL,0,25),(47,'','other_language',0,NULL,NULL,NULL,1,NULL,NULL,0,0,NULL,0,25),(48,'','english_name',0,NULL,NULL,NULL,1,NULL,NULL,0,0,NULL,0,25),(49,'','id',1,NULL,NULL,NULL,1,NULL,NULL,0,0,NULL,0,26),(50,'','name',0,NULL,NULL,NULL,1,NULL,NULL,0,0,NULL,0,26),(51,'','p_id',0,NULL,NULL,NULL,1,NULL,NULL,0,0,NULL,0,26),(52,'','other_language',0,NULL,NULL,NULL,1,NULL,NULL,0,0,NULL,0,26),(53,'','english_name',0,NULL,NULL,NULL,1,NULL,NULL,0,0,NULL,0,26),(54,'','grade',0,NULL,NULL,NULL,1,NULL,NULL,0,0,NULL,0,26),(55,'','chinese_traditional',0,NULL,NULL,NULL,1,NULL,NULL,0,0,NULL,0,26),(56,'','id',1,NULL,NULL,NULL,1,NULL,NULL,0,0,NULL,0,27),(57,'','name',0,NULL,NULL,NULL,1,NULL,NULL,0,0,NULL,0,27),(58,'','p_id',0,NULL,NULL,NULL,1,NULL,NULL,0,0,NULL,0,27),(59,'','other_language',0,NULL,NULL,NULL,1,NULL,NULL,0,0,NULL,0,27),(60,'','english_name',0,NULL,NULL,NULL,1,NULL,NULL,0,0,NULL,0,27),(61,'','grade',0,NULL,NULL,NULL,1,NULL,NULL,0,0,NULL,0,27),(62,'','chinese_traditional',0,NULL,NULL,NULL,1,NULL,NULL,0,0,NULL,0,27),(63,'编号','id',0,'int',11,NULL,1,'1',NULL,1,0,'人员编号',0,6),(83,NULL,'name6',NULL,'varchar',3,NULL,1,NULL,NULL,NULL,NULL,NULL,0,6),(84,NULL,'name7',NULL,'varchar',3,NULL,1,NULL,NULL,NULL,NULL,NULL,0,6);
/*!40000 ALTER TABLE `sys_column` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_table`
--

DROP TABLE IF EXISTS `sys_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_table` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '表英文名',
  `label` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '表中文名',
  `module_id` int DEFAULT NULL COMMENT '所属模块编号',
  `order_id` int DEFAULT NULL COMMENT '排序号',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '描述',
  `deleted` int DEFAULT '0' COMMENT '删除标记',
  `type` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '表类型（单级码表、多级码表）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_table`
--

LOCK TABLES `sys_table` WRITE;
/*!40000 ALTER TABLE `sys_table` DISABLE KEYS */;
INSERT INTO `sys_table` VALUES (1,'role','角色表',NULL,2,NULL,0,NULL),(2,'sys_column','系统字段表',NULL,4,NULL,0,NULL),(3,'sys_table','系统表表',NULL,4,NULL,0,NULL),(5,'tree_node','系统树结构表',NULL,4,NULL,0,NULL),(6,'staff','员工表',NULL,5,NULL,0,NULL),(19,'test2123','测试123',NULL,4,NULL,0,NULL),(20,'test2new2','测试2',NULL,1,NULL,0,NULL),(24,'personnel_category','人员类别',1,1,NULL,0,'SINGLE_CODE'),(25,'personnel_category','人员类别',1,1,NULL,0,'MULTILEVEL_CODE'),(26,'resignation_type','离职类型',1,1,NULL,0,'SINGLE_CODE'),(27,'resignation_nature','离职性质',1,1,NULL,0,'SINGLE_CODE');
/*!40000 ALTER TABLE `sys_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test2123`
--

DROP TABLE IF EXISTS `test2123`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test2123` (
  `id` int NOT NULL COMMENT '唯一编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test2123`
--

LOCK TABLES `test2123` WRITE;
/*!40000 ALTER TABLE `test2123` DISABLE KEYS */;
INSERT INTO `test2123` VALUES (1),(2);
/*!40000 ALTER TABLE `test2123` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test2new2`
--

DROP TABLE IF EXISTS `test2new2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test2new2` (
  `id` int NOT NULL COMMENT '唯一编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test2new2`
--

LOCK TABLES `test2new2` WRITE;
/*!40000 ALTER TABLE `test2new2` DISABLE KEYS */;
INSERT INTO `test2new2` VALUES (1),(3),(4);
/*!40000 ALTER TABLE `test2new2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tree_node`
--

DROP TABLE IF EXISTS `tree_node`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tree_node` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '名称',
  `pid` int DEFAULT NULL COMMENT '父编号',
  `routing_address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '路由地址',
  `type` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '类型',
  `level` int DEFAULT NULL COMMENT '所处层级',
  `leafy` int NOT NULL COMMENT '标记：叶子节点',
  `deleted` int DEFAULT '0' COMMENT '标记：逻辑删除',
  `order_id` int DEFAULT '0' COMMENT '排序号',
  `module_id` int DEFAULT NULL COMMENT '所属模块编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tree_node`
--

LOCK TABLES `tree_node` WRITE;
/*!40000 ALTER TABLE `tree_node` DISABLE KEYS */;
INSERT INTO `tree_node` VALUES (1,'用户模块',NULL,'/test','DATA',1,1,0,NULL,NULL),(2,'论坛模块',NULL,'/test','DATA',1,1,0,NULL,NULL),(3,'api1',NULL,'/test','DATA',1,0,0,NULL,NULL),(4,'api2',NULL,'/test','DATA',1,0,0,NULL,NULL),(5,'api1.1',3,'/test','DATA',2,0,0,NULL,NULL),(6,'api1.2',3,'/test','DATA',2,1,0,NULL,NULL),(7,'api2.1',4,'/test','DATA',2,1,0,NULL,NULL),(8,'api2.2',4,'/test','DATA',2,1,0,NULL,NULL),(9,'api1.1.1',5,'/test','DATA',3,0,0,NULL,NULL),(10,'api1.1.1.1',9,'/test','DATA',4,1,0,NULL,NULL),(11,'员工桌面',NULL,'/staffDesktop','HEADER_MENU',1,0,0,1,NULL),(12,'人力资本',NULL,'/humanResource','HEADER_MENU',1,0,0,3,NULL),(13,'管理赋能',NULL,'/manageEmpower','HEADER_MENU',1,0,1,4,NULL),(14,'领导决策',NULL,'/leaderDecide','HEADER_MENU',1,0,1,5,NULL),(15,'配置平台',NULL,'/configPlatform','HEADER_MENU',1,0,0,7,NULL),(16,'组织',12,'/humanResource/organization','SIDEBAR',2,0,0,NULL,12),(17,'人员',12,'/humanResource/staff','SIDEBAR',2,0,0,NULL,12),(18,'流程配置',15,'/configPlatform/processConfig','SIDEBAR',2,0,0,-1,15),(19,'系统配置',15,'/configPlatform/test','SIDEBAR',2,0,0,NULL,15),(20,'流程管理',18,'/configPlatform/processConfig/processManage','SIDEBAR',3,0,0,NULL,15),(22,'报表统计',18,'/configPlatform/test','SIDEBAR',3,0,0,NULL,15),(23,'流程监控',20,'/configPlatform/processConfig/processManage/monitor','SIDEBAR',4,0,0,NULL,15),(24,'设置代理',20,'/configPlatform/processConfig/processManage/setProxy','SIDEBAR',4,0,0,NULL,15),(25,'按实例查询',22,'/configPlatform/test','SIDEBAR',5,0,0,NULL,15),(26,'按任务查询',22,'/configPlatform/test','SIDEBAR',5,0,0,NULL,15),(27,'系统工具',19,'/configPlatform/systemTool','SIDEBAR',3,0,0,NULL,15),(28,'数据重构',27,'/configPlatform/systemTool/dataRestructure','SIDEBAR',4,0,0,NULL,15),(29,'自由表单',27,'/configPlatform/systemTool/freeForm','SIDEBAR',4,0,0,NULL,15),(52,'我的申请',11,'/staffDesktop/myApply','SIDEBAR',2,1,1,NULL,11),(53,'组织关系',16,'/humanResource/organization/relationship','SIDEBAR',3,1,1,NULL,12),(54,'人力组织',16,'/humanResource/organization/test2','SIDEBAR',3,1,1,NULL,12),(55,'投管组织',16,'/humanResource/organization/touguan','SIDEBAR',3,1,0,1,12),(56,'业务查询',16,'/humanResource/organization/test4','SIDEBAR',3,1,0,NULL,12),(57,'历史查询',16,'/humanResource/organization/test5','SIDEBAR',3,1,0,NULL,12),(58,'多组织管理',53,'/humanResource/organization/relationship/many','SIDEBAR',4,1,0,NULL,12),(59,'组织管理',54,'/humanResource/organization','SIDEBAR',4,1,1,NULL,12),(60,'投管组织组织管理',55,'/humanResource/organization/test3/touguan','SIDEBAR',4,1,0,NULL,12),(61,'信息查询',17,'/humanResource/test','SIDEBAR',3,1,0,NULL,12),(62,'增员业务',17,'/humanResource/test','SIDEBAR',3,1,0,NULL,12),(63,'新员工入职',62,'/humanResource/test','SIDEBAR',4,1,0,NULL,12),(64,'再入职',62,'/humanResource/test','SIDEBAR',4,1,0,NULL,12),(65,'我的信息',11,'/staffDesktop/myMessage','SIDEBAR',2,1,0,NULL,11),(66,'我的待办',11,'/staffDesktop/myTodo','SIDEBAR',2,1,0,NULL,11),(67,'数据字典',19,'/configPlatform/dataDictionary','SIDEBAR',3,1,0,NULL,15),(68,'单级编码',67,'/configPlatform/dataDictionary/singleCode','SIDEBAR',4,1,0,NULL,15),(69,'多级编码',67,'/configPlatform/dataDictionary/multilevelCode','SIDEBAR',4,1,0,NULL,15),(70,'模块管理',27,'/configPlatform/systemTool/moduleMaintenance','SIDEBAR',4,1,0,0,15),(71,'test',11,'/test','SIDEBAR',2,1,1,NULL,NULL),(72,'在线学习',NULL,'/onlineLearn','HEADER_MENU',1,0,0,2,72),(73,'组织',NULL,NULL,'TABLE_GROUP',1,0,0,0,12),(74,'人员',NULL,NULL,'TABLE_GROUP',1,0,0,0,12),(75,'主业务表',74,NULL,'TABLE_GROUP',2,1,0,0,12),(76,'人员附表',74,NULL,'TABLE_GROUP',2,1,0,0,12),(77,'主业务表',73,NULL,'TABLE_GROUP',2,1,0,0,12),(78,'合同',NULL,NULL,'TABLE_GROUP',1,1,0,0,NULL),(79,'招聘',NULL,NULL,'TABLE_GROUP',1,1,0,0,NULL),(80,'管理员',NULL,NULL,'ROLE_GROUP',1,0,0,0,NULL),(81,'运维管理员',80,NULL,'ROLE_GROUP',2,1,0,0,NULL),(82,'超级管理员',80,NULL,'ROLE_GROUP',2,1,0,0,NULL),(83,'系统阶段一',NULL,NULL,'ROLE_GROUP',1,0,0,0,NULL),(84,'阿里巴巴',83,NULL,'ROLE_GROUP',2,1,0,0,NULL),(85,'百度',83,NULL,'ROLE_GROUP',2,1,0,0,NULL),(86,'审批',12,'/humanResource/approve','SIDEBAR',2,1,0,NULL,12),(87,'合同',12,'/humanResource/contract','SIDEBAR',2,1,0,NULL,12),(88,'招聘',12,'/humanResource/recruit','SIDEBAR',2,1,0,NULL,12),(89,'人才开发',NULL,'/talentDevelop','HEADER_MENU',1,1,0,6,89),(91,'test',72,'/onlineLearn/test','SIDEBAR',2,1,0,NULL,72),(92,'test',89,'/test','SIDEBAR',2,1,1,NULL,NULL),(93,'test',89,'/test','SIDEBAR',2,1,0,NULL,89),(94,'test',89,'/test','SIDEBAR',2,1,0,NULL,89),(95,'test',89,'/test','SIDEBAR',2,1,0,NULL,89),(96,'test2',72,'/onlineLearn/test2','SIDEBAR',2,1,0,NULL,72),(97,'权限管理',15,'/configPlatform/permissionManage','SIDEBAR',2,1,0,1,15),(98,'/test',15,'/configPlatform/test','SIDEBAR',2,1,1,NULL,15),(99,'角色管理',97,'/configPlatform/permissionManage/role','SIDEBAR',3,1,0,NULL,15),(100,'权限分配',97,'/configPlatform/permissionManage/permission','SIDEBAR',3,1,0,NULL,15),(101,'权限审批',97,'/configPlatform/permissionManage/permissionApprove','SIDEBAR',3,1,0,NULL,15),(102,'权限托管',97,'/configPlatform/permissionManage/trusteeship','SIDEBAR',3,1,0,NULL,15),(103,'测试',11,'/staffDesktop/test','SIDEBAR',2,1,1,NULL,11),(104,'测试',11,'/staffDesktop/测试','SIDEBAR',2,1,1,NULL,11),(105,'表单管理',15,'/configPlatform/formConfig','SIDEBAR',2,1,0,2,15),(106,'自由报表',27,'/configPlatform/systemTool/freeReport','SIDEBAR',4,1,0,NULL,15),(107,'决策平台',NULL,'/policy','HEADER_MENU',1,0,0,8,NULL),(108,'台账报表',107,'/policy/report','SIDEBAR',2,1,0,NULL,107),(109,'组织管理',16,'/humanResource/organization/manage','SIDEBAR',3,1,0,2,12);
/*!40000 ALTER TABLE `tree_node` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'hhnail'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-31 16:59:41
