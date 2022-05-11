/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost:3306
 Source Schema         : beiwanglu

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 13/04/2022 12:23:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for beiwanglu
-- ----------------------------
DROP TABLE IF EXISTS `beiwanglu`;
CREATE TABLE `beiwanglu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `dynamic_type_id` bigint(20) NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `read_count` bigint(20) NULL DEFAULT 0,
  `url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `create_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12618 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of beiwanglu
-- ----------------------------
INSERT INTO `beiwanglu` VALUES (12610, 111, '备忘录标题1', 18, '备忘录详情', 9, '<img  src=<img  src=http://dongxiaoyan.oss-cn-beijing.aliyuncs.com/2021/3/4/806a708e-fee5-4edb-9510-15b725c8ec5b.jpg>>', '2022-04-13 11:54:55');
INSERT INTO `beiwanglu` VALUES (12616, 809012, '备忘录标题2', 2, '备忘录详情', 9, 'http://dongxiaoyan.oss-cn-beijing.aliyuncs.com/2022/3/29/20220329154448.jpeg', '2022-04-13 11:54:55');
INSERT INTO `beiwanglu` VALUES (12617, 809012, '备忘录标题3', 2, '备忘录详情', 39, '<img  src=http://dongxiaoyan.oss-cn-beijing.aliyuncs.com/2022/3/26/20220326225328.jpeg>', '2022-04-13 11:54:55');

-- ----------------------------
-- Table structure for tmh_file
-- ----------------------------
DROP TABLE IF EXISTS `tmh_file`;
CREATE TABLE `tmh_file`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `suffix` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `size` int(20) NULL DEFAULT NULL,
  `user_id` bigint(40) NULL DEFAULT NULL,
  `url` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `old_name` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3940 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tmh_file
-- ----------------------------
INSERT INTO `tmh_file` VALUES (3938, 'jpeg', 'png', NULL, 1, 'http://dongxiaoyan.oss-cn-beijing.aliyuncs.com/2022/3/30/20220330154423.jpeg', NULL, '1', '2022-03-30 15:44:24');
INSERT INTO `tmh_file` VALUES (3939, 'jpeg', 'png', NULL, 1, 'http://dongxiaoyan.oss-cn-beijing.aliyuncs.com/2022/3/30/20220330154435.jpeg', NULL, '1', '2022-03-30 15:44:35');

-- ----------------------------
-- Table structure for tmh_user
-- ----------------------------
DROP TABLE IF EXISTS `tmh_user`;
CREATE TABLE `tmh_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `user_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `head_img_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0',
  `work` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_deleted` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '是否删除',
  `nick_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1834 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tmh_user
-- ----------------------------
INSERT INTO `tmh_user` VALUES (1136, 111, '系统管理员', 'admin', 'http://dongxiaoyan.oss-cn-beijing.aliyuncs.com/2019/12/14/20191214114124.jpeg', 'admin', '咸阳市', NULL, '4', '网络营销', '0', NULL, '2022-04-13 11:54:51');
INSERT INTO `tmh_user` VALUES (1830, 386696, '88888888888', '88888888888', 'http://dongxiaoyan.oss-cn-beijing.aliyuncs.com/2020/3/17/d66350e8-75fa-4b3e-8058-fe375ecdce49.jpg', '88888888888', NULL, NULL, '0', NULL, '1', NULL, '2022-04-13 11:54:51');
INSERT INTO `tmh_user` VALUES (1831, 341239, '77777777777', '77777777777', 'http://dongxiaoyan.oss-cn-beijing.aliyuncs.com/2020/3/17/d66350e8-75fa-4b3e-8058-fe375ecdce49.jpg', '77777777777', NULL, NULL, '0', NULL, '1', NULL, '2022-04-13 11:54:51');
INSERT INTO `tmh_user` VALUES (1832, 162758, '77777777777', '77777777777', 'http://dongxiaoyan.oss-cn-beijing.aliyuncs.com/2020/3/17/d66350e8-75fa-4b3e-8058-fe375ecdce49.jpg', '77777777777', NULL, NULL, '0', NULL, '0', NULL, '2022-04-13 11:54:51');
INSERT INTO `tmh_user` VALUES (1833, 184278, '99999999999', '99999999999', 'http://dongxiaoyan.oss-cn-beijing.aliyuncs.com/2020/3/17/d66350e8-75fa-4b3e-8058-fe375ecdce49.jpg', '99999999999', '天津市浑南县城', NULL, '0', '天津学院', '0', NULL, '2022-04-13 11:54:51');

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (12, '备忘录类型一', 'http://dongxiaoyan.oss-cn-beijing.aliyuncs.com/2021/3/3/2baaa271-183e-455f-a9ec-e2fb8af5e814.jpg');
INSERT INTO `type` VALUES (18, '备忘录类型二', 'http://dongxiaoyan.oss-cn-beijing.aliyuncs.com/2022/3/10/f42f59d8-ce65-48f4-92bf-13a4a630e035.png');
INSERT INTO `type` VALUES (19, '备忘录类型三', 'http://dongxiaoyan.oss-cn-beijing.aliyuncs.com/2022/3/10/d60c84ce-df62-4e3e-a66d-6acdef07cbb8.jpg');
INSERT INTO `type` VALUES (22, '备忘录类型四', 'http://dongxiaoyan.oss-cn-beijing.aliyuncs.com/2022/3/10/b41244e4-2256-477b-b72b-b459d7f54dd4.jpg');

-- ----------------------------
-- Procedure structure for per2
-- ----------------------------
DROP PROCEDURE IF EXISTS `per2`;
delimiter ;;
CREATE DEFINER=``@`` PROCEDURE `per2`()
begin 
declare num int; 
set num=1; 
while num < 1000 do 
insert into per2(name) values(concat("fan", num)); 
set num=num+1;
end while;
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for proc_person_findById
-- ----------------------------
DROP PROCEDURE IF EXISTS `proc_person_findById`;
delimiter ;;
CREATE DEFINER=``@`` PROCEDURE `proc_person_findById`(
    in n int
)
BEGIN
     SELECT * FROM tmh_file where id=n;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for proc_person_getCount
-- ----------------------------
DROP PROCEDURE IF EXISTS `proc_person_getCount`;
delimiter ;;
CREATE DEFINER=``@`` PROCEDURE `proc_person_getCount`(
    out n int(11)
)
BEGIN 
     SELECT COUNT(*) INTO n FROM tmh_file ;
END
;;
delimiter ;

-- ----------------------------
-- Event structure for e_test_insert
-- ----------------------------
DROP EVENT IF EXISTS `e_test_insert`;
delimiter ;;
CREATE DEFINER = ``@`` EVENT `e_test_insert`
ON SCHEDULE
EVERY '1' SECOND STARTS '2016-12-14 17:40:18'
ON COMPLETION PRESERVE
DO INSERT INTO `tmh`.`aaa` (`timeline`) VALUES ('2016-12-14 17:40:52')
;
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
