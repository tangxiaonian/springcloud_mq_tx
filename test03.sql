/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50724
Source Host           : 127.0.0.1:3306
Source Database       : test03

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-12-29 19:56:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(50) DEFAULT NULL COMMENT '订单ID',
  `stock` int(11) DEFAULT NULL COMMENT '库存余额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stock
-- ----------------------------
INSERT INTO `stock` VALUES ('18', 'e9722fc3353e4d82a46200c1045b7801', '100');
