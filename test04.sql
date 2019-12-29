/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50724
Source Host           : 127.0.0.1:3306
Source Database       : test04

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-12-29 19:56:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '订单名称',
  `order_createtime` datetime DEFAULT NULL COMMENT '下单时间',
  `order_state` int(11) DEFAULT NULL COMMENT '订单状态 0 已经未支付 1已经支付 2已退单',
  `order_money` double(10,0) DEFAULT NULL COMMENT '订单价格',
  `order_id` varchar(50) DEFAULT NULL COMMENT '订单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('38', '蚂蚁课堂', '2019-12-29 14:07:35', '1', '10', 'e9722fc3353e4d82a46200c1045b7801');
INSERT INTO `order` VALUES ('39', '蚂蚁课堂', '2019-12-29 14:07:35', '1', '10', 'e9722fc3353e4d82a46200c1045b7801');
