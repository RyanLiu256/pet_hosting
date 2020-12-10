/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : pethosting

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2020-06-18 19:22:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_breeds
-- ----------------------------
DROP TABLE IF EXISTS `tb_breeds`;
CREATE TABLE `tb_breeds` (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `species` varchar(20) DEFAULT NULL,
  `breedPrice` decimal(10,2) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bid`),
  UNIQUE KEY `species` (`species`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_breeds
-- ----------------------------
INSERT INTO `tb_breeds` VALUES ('1', '中华田园犬', '13.00', 'zhonghua.png');
INSERT INTO `tb_breeds` VALUES ('2', '萨摩', '18.00', 'samo.png');
INSERT INTO `tb_breeds` VALUES ('3', '阿富汗猎犬', '25.00', 'afuhan.png');
INSERT INTO `tb_breeds` VALUES ('4', '边境牧羊犬', '34.00', 'bianjing.png');
INSERT INTO `tb_breeds` VALUES ('5', '柴犬', '22.00', 'chaiquan.png');
INSERT INTO `tb_breeds` VALUES ('6', '哈士奇', '18.00', 'hashiqi.png');
INSERT INTO `tb_breeds` VALUES ('7', '金毛', '25.00', 'jinmao.png');
INSERT INTO `tb_breeds` VALUES ('8', '波斯猫', '21.00', 'bosimao.png');
INSERT INTO `tb_breeds` VALUES ('9', '美国短毛猫', '31.00', 'meiguoduanmao.png');
INSERT INTO `tb_breeds` VALUES ('30', '阿拉斯加', '13.00', 'alasijia.png');
INSERT INTO `tb_breeds` VALUES ('38', '贵宾犬', '22.00', 'guibinquan.png');
INSERT INTO `tb_breeds` VALUES ('39', '泰迪', '20.00', 'taidi.png');
INSERT INTO `tb_breeds` VALUES ('40', '吉娃娃', '13.00', 'jiwawa.png');
INSERT INTO `tb_breeds` VALUES ('41', '博美犬', '12.00', 'bomei.png');

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `day` datetime DEFAULT CURRENT_TIMESTAMP,
  `comment` varchar(255) DEFAULT NULL,
  `reply` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_comment
-- ----------------------------
INSERT INTO `tb_comment` VALUES ('1', '2020-06-05 16:48:30', '很好很好', null);
INSERT INTO `tb_comment` VALUES ('2', '2020-06-05 16:48:30', '很好很好', null);
INSERT INTO `tb_comment` VALUES ('3', '2020-06-05 16:48:30', 'heiheihei', null);
INSERT INTO `tb_comment` VALUES ('4', '2020-06-05 16:48:30', 'heiheihei', null);
INSERT INTO `tb_comment` VALUES ('5', '2020-06-05 16:48:30', 'heiheihei', null);
INSERT INTO `tb_comment` VALUES ('6', '2020-06-05 16:48:30', '感觉害行吧，可以接受', '谢谢');
INSERT INTO `tb_comment` VALUES ('7', '2020-06-05 16:48:30', '非常好的服务', '谢谢支持');
INSERT INTO `tb_comment` VALUES ('8', '2020-06-05 16:48:30', '服务很好', '谢谢您的支持');
INSERT INTO `tb_comment` VALUES ('9', '2020-06-05 16:48:30', '服务不错，下次还来', null);
INSERT INTO `tb_comment` VALUES ('10', '2020-06-05 16:48:30', '服务态度很好，赞一个', null);
INSERT INTO `tb_comment` VALUES ('11', '2020-06-05 16:48:30', '服务很到位', '感谢支持');
INSERT INTO `tb_comment` VALUES ('12', '2020-06-05 16:48:30', '服务不错，下次还来', null);
INSERT INTO `tb_comment` VALUES ('13', '2020-06-05 16:48:30', '托管负责人服务很周到', null);
INSERT INTO `tb_comment` VALUES ('14', '2020-06-05 18:15:34', '一般般吧', null);
INSERT INTO `tb_comment` VALUES ('15', '2020-06-06 05:25:09', '不错不错，服务很好。', '您的肯定是我们的动力！');

-- ----------------------------
-- Table structure for tb_deposit
-- ----------------------------
DROP TABLE IF EXISTS `tb_deposit`;
CREATE TABLE `tb_deposit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `username` varchar(20) NOT NULL,
  `petname` varchar(20) NOT NULL,
  `age` decimal(10,2) DEFAULT NULL,
  `weight` decimal(10,2) DEFAULT NULL,
  `startTime` date NOT NULL,
  `endTime` date NOT NULL,
  `day_count` int(11) NOT NULL,
  `total_price` decimal(10,2) NOT NULL,
  `species_id` int(11) NOT NULL,
  `food_id` int(11) NOT NULL,
  `keeper_id` int(11) NOT NULL,
  `state` int(11) DEFAULT '4',
  `ispay` int(11) DEFAULT '2',
  `isprove` int(11) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `comment_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tb_deposit_ibfk_3` (`keeper_id`),
  KEY `tb_deposit_food` (`food_id`),
  KEY `tb_deposit_ibfk_1` (`species_id`),
  KEY `userid` (`userid`),
  KEY `username` (`username`),
  CONSTRAINT `tb_deposit_food` FOREIGN KEY (`food_id`) REFERENCES `tb_petfood` (`pid`) ON UPDATE CASCADE,
  CONSTRAINT `tb_deposit_ibfk_1` FOREIGN KEY (`species_id`) REFERENCES `tb_breeds` (`bid`) ON UPDATE CASCADE,
  CONSTRAINT `tb_deposit_ibfk_3` FOREIGN KEY (`keeper_id`) REFERENCES `tb_keeper` (`kid`) ON UPDATE CASCADE,
  CONSTRAINT `tb_deposit_ibfk_4` FOREIGN KEY (`userid`) REFERENCES `tb_user` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `tb_deposit_ibfk_5` FOREIGN KEY (`username`) REFERENCES `tb_user` (`username`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_deposit
-- ----------------------------
INSERT INTO `tb_deposit` VALUES ('28', '29', '张三', '旺财', '1.00', '2.00', '2020-05-01', '2020-05-07', '6', '570.00', '7', '3', '1', '3', '1', '1', null, '7');
INSERT INTO `tb_deposit` VALUES ('29', '29', '张三', 'vv', '1.00', '1.00', '2020-05-01', '2020-05-02', '1', '57.00', '1', '1', '1', '3', '1', '1', '太胖', null);
INSERT INTO `tb_deposit` VALUES ('31', '29', '张三', 'meimei', '1.00', '1.00', '2020-05-01', '2020-05-09', '8', '216.00', '2', '2', '1', '3', '1', '1', null, '8');
INSERT INTO `tb_deposit` VALUES ('32', '29', '张三', '小四', '1.00', '1.00', '2020-05-08', '2020-05-09', '1', '28.00', '1', '3', '1', '3', '1', '1', null, '6');
INSERT INTO `tb_deposit` VALUES ('39', '29', '张三', 'a', '1.00', '1.00', '2020-01-01', '2020-01-03', '2', '142.00', '2', '1', '1', '3', '1', '0', null, null);
INSERT INTO `tb_deposit` VALUES ('40', '29', '张三', 'b', '1.00', '2.00', '2020-02-01', '2020-02-06', '5', '350.00', '3', '2', '1', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('41', '29', '张三', 'c', '2.00', '3.00', '2020-03-01', '2020-03-05', '4', '404.00', '4', '8', '2', '3', '1', '1', null, '13');
INSERT INTO `tb_deposit` VALUES ('42', '29', '张三', 'd', '2.00', '2.00', '2020-06-01', '2020-06-05', '4', '260.00', '6', '6', '2', '1', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('43', '29', '张三', 'e', '2.00', '4.00', '2020-07-02', '2020-07-05', '3', '183.00', '30', '14', '1', '1', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('44', '29', '张三', 'f', '2.00', '2.00', '2020-08-01', '2020-08-05', '4', '292.00', '2', '3', '1', '1', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('45', '29', '张三', 'g', '2.00', '3.00', '2020-09-01', '2020-09-03', '2', '204.00', '4', '4', '1', '1', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('46', '29', '张三', 'h', '2.00', '1.00', '2020-10-01', '2020-10-04', '3', '141.00', '2', '2', '1', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('48', '29', '张三', 'j', '2.00', '2.00', '2020-12-02', '2020-12-03', '1', '95.00', '3', '3', '1', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('49', '29', '张三', '旺财', '2.00', '3.00', '2020-01-01', '2020-01-04', '3', '192.00', '2', '1', '4', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('50', '40', '李四', 'tom', '2.00', '2.00', '2020-01-01', '2020-01-03', '2', '128.00', '2', '3', '3', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('51', '40', '李四', 'lulu', '2.00', '1.00', '2020-01-01', '2020-01-09', '8', '424.00', '5', '5', '5', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('52', '40', '李四', 'momo', '2.00', '2.00', '2020-02-01', '2020-02-05', '4', '272.00', '8', '8', '1', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('53', '23', '赵六', 'nini', '2.00', '3.00', '2020-02-01', '2020-02-05', '4', '300.00', '39', '2', '2', '3', '1', '1', null, '10');
INSERT INTO `tb_deposit` VALUES ('54', '23', '赵六', 'bibi', '2.00', '2.00', '2020-03-01', '2020-03-05', '4', '192.00', '40', '7', '2', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('55', '28', '王五', 'qiqi', '2.00', '1.00', '2020-03-06', '2020-03-11', '5', '355.00', '3', '5', '2', '3', '1', '1', null, '12');
INSERT INTO `tb_deposit` VALUES ('56', '28', '王五', 'vivi', '2.00', '2.00', '2020-03-07', '2020-03-11', '4', '228.00', '1', '4', '5', '3', '1', '1', null, '11');
INSERT INTO `tb_deposit` VALUES ('57', '29', '张三', 'qwe', '2.00', '3.00', '2019-12-01', '2019-12-03', '2', '118.00', '1', '1', '1', '3', '1', '1', null, '14');
INSERT INTO `tb_deposit` VALUES ('59', '40', '李四', 'nn', '2.00', '4.00', '2019-01-01', '2019-01-03', '2', '146.00', '2', '3', '2', '3', '1', '1', null, '9');
INSERT INTO `tb_deposit` VALUES ('60', '40', '李四', 'qq', '3.00', '2.00', '2019-01-04', '2019-01-08', '4', '300.00', '5', '3', '1', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('61', '40', '李四', 'ee', '3.00', '1.00', '2019-01-03', '2019-01-05', '2', '82.00', '6', '4', '4', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('62', '40', '李四', 'a', '3.00', '2.00', '2019-02-01', '2019-02-06', '5', '260.00', '7', '8', '5', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('63', '40', '李四', 'b', '3.00', '1.00', '2019-02-08', '2019-02-10', '2', '98.00', '8', '10', '5', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('64', '40', '李四', 'xx', '3.00', '3.00', '2019-02-07', '2019-02-10', '3', '150.00', '38', '11', '3', '3', '1', '1', '不行', null);
INSERT INTO `tb_deposit` VALUES ('65', '40', '李四', 'z', '3.00', '1.00', '2019-03-01', '2019-03-03', '2', '146.00', '39', '16', '4', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('66', '40', '李四', 'aaa', '3.00', '2.00', '2019-03-02', '2019-03-03', '1', '76.00', '9', '3', '4', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('69', '40', '李四', 'mm', '3.00', '1.00', '2019-05-03', '2019-05-08', '5', '410.00', '3', '6', '1', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('70', '40', '李四', 'dd', '3.00', '2.00', '2019-05-03', '2019-05-08', '5', '385.00', '5', '16', '2', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('71', '23', '赵六', 'ff', '3.00', '3.00', '2019-06-01', '2019-06-02', '1', '76.00', '5', '2', '3', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('72', '23', '赵六', 'gg', '3.00', '1.00', '2019-06-07', '2019-06-08', '1', '65.00', '1', '2', '1', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('73', '23', '赵六', 'hh', '3.00', '2.00', '2019-07-02', '2019-07-05', '3', '186.00', '6', '4', '4', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('74', '23', '赵六', 'jj', '3.00', '1.00', '2019-07-04', '2019-07-06', '2', '144.00', '7', '3', '1', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('75', '23', '赵六', 'kk', '3.00', '1.00', '2019-07-05', '2019-07-10', '5', '380.00', '38', '16', '5', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('76', '23', '赵六', 'll', '3.00', '2.00', '2019-08-07', '2019-08-10', '3', '240.00', '9', '4', '2', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('77', '23', '赵六', 'pp', '3.00', '3.00', '2019-08-03', '2019-08-07', '4', '248.00', '39', '11', '5', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('78', '30', 'lisa', 'sasa', '3.00', '1.00', '2019-09-06', '2019-09-07', '1', '64.00', '1', '1', '1', '3', '1', '1', null, '15');
INSERT INTO `tb_deposit` VALUES ('79', '30', 'lisa', 'lili', '3.00', '2.00', '2019-09-03', '2019-09-05', '2', '156.00', '3', '3', '2', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('80', '30', 'lisa', 'momo', '3.00', '3.00', '2019-10-03', '2019-10-04', '1', '55.00', '5', '5', '3', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('81', '30', 'lisa', 'nini', '3.00', '2.00', '2019-10-05', '2019-10-08', '3', '159.00', '8', '6', '1', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('82', '30', 'lisa', 'kiki', '2.00', '1.00', '2019-11-01', '2019-11-03', '2', '82.00', '30', '4', '3', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('83', '30', 'lisa', 'aiai', '2.00', '1.00', '2019-11-02', '2019-11-03', '1', '52.00', '39', '5', '2', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('84', '30', 'lisa', 'qiqi', '2.00', '1.00', '2019-12-06', '2019-12-08', '2', '84.00', '6', '2', '1', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('85', '30', 'lisa', 'yiyi', '1.00', '1.00', '2019-12-13', '2019-12-15', '2', '118.00', '7', '7', '4', '3', '1', '0', '无免疫证明', null);
INSERT INTO `tb_deposit` VALUES ('86', '29', '张三', '小四', '1.00', '1.00', '2020-05-22', '2020-05-23', '1', '73.00', '5', '4', '2', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('88', '29', '张三', 'xiaoni', '1.00', '1.00', '2020-05-23', '2020-05-24', '1', '74.00', '7', '4', '1', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('89', '29', '张三', '丁丁', '1.50', '3.00', '2020-05-30', '2020-05-31', '1', '73.00', '2', '7', '3', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('90', '29', '张三', 'asdf', '2.50', '3.00', '2020-05-23', '2020-05-24', '1', '53.00', '1', '4', '2', '4', '2', '0', null, null);
INSERT INTO `tb_deposit` VALUES ('91', '28', '王五', 'lili', '1.00', '2.00', '2020-04-03', '2020-04-07', '4', '228.00', '2', '11', '2', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('92', '28', '王五', 'nunu', '2.00', '3.00', '2020-04-03', '2020-04-05', '2', '132.00', '5', '5', '5', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('93', '28', '王五', 'ruirui', '2.50', '3.20', '2020-04-08', '2020-04-10', '2', '106.00', '7', '4', '4', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('94', '31', 'lucy', 'cici', '2.30', '1.30', '2019-04-04', '2019-04-07', '3', '150.00', '9', '10', '2', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('95', '31', 'lucy', '小白', '2.00', '3.00', '2019-04-13', '2019-04-16', '3', '225.00', '8', '5', '2', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('96', '31', 'lucy', '大白', '3.00', '4.00', '2019-04-20', '2019-04-23', '3', '174.00', '6', '2', '3', '3', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('97', '28', '王五', '妮妮', '3.00', '5.00', '2020-11-06', '2020-11-08', '2', '130.00', '8', '6', '3', '4', '2', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('98', '28', '王五', '胖胖', '3.00', '5.00', '2020-11-06', '2020-11-08', '2', '146.00', '5', '10', '3', '1', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('99', '29', '张三', 'sss', '2.30', '2.30', '2020-05-24', '2020-05-24', '1', '68.00', '3', '3', '1', '4', '2', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('100', '29', '张三', 'aaaa', '2.00', '3.00', '2021-01-01', '2021-01-03', '2', '174.00', '4', '3', '3', '1', '1', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('101', '31', 'lucy', '小胖', '2.20', '2.30', '2020-05-27', '2020-05-29', '2', '118.00', '6', '4', '2', '4', '2', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('102', '29', '张三', 'aaa', '2.00', '2.00', '2020-05-28', '2020-05-31', '3', '234.00', '4', '3', '3', '4', '2', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('103', '29', '张三', 'aqwew', '3.00', '2.00', '2020-05-28', '2020-05-30', '2', '156.00', '3', '3', '3', '4', '2', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('104', '46', '张三', '奈奈', '1.20', '2.00', '2020-05-28', '2020-05-30', '2', '132.00', '3', '2', '2', '4', '2', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('105', '46', '张三', 'rr', '2.00', '3.00', '2020-05-28', '2020-05-30', '2', '74.00', '2', '4', '2', '4', '2', '0', null, null);
INSERT INTO `tb_deposit` VALUES ('106', '31', 'lucy', 'cycy', '2.10', '2.30', '2020-05-29', '2020-05-31', '2', '142.00', '5', '4', '4', '1', '1', '0', 'ninini', null);
INSERT INTO `tb_deposit` VALUES ('108', '30', 'lisa', '瘦瘦', '2.00', '4.00', '2020-06-05', '2020-06-07', '2', '114.00', '40', '5', '4', '1', '2', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('109', '30', 'lisa', '胖胖', '3.00', '30.00', '2020-06-06', '2020-06-07', '1', '64.00', '6', '6', '5', '2', '2', '1', '太胖了', null);
INSERT INTO `tb_deposit` VALUES ('110', '30', 'lisa', '大大', '3.00', '4.00', '2020-06-07', '2020-06-09', '2', '114.00', '1', '1', '1', '4', '2', '1', null, null);
INSERT INTO `tb_deposit` VALUES ('112', '29', '张三', '小胖', '3.00', '3.00', '2020-06-06', '2020-06-06', '1', '53.00', '5', '4', '2', '4', '2', '1', null, null);

-- ----------------------------
-- Table structure for tb_deposit_service
-- ----------------------------
DROP TABLE IF EXISTS `tb_deposit_service`;
CREATE TABLE `tb_deposit_service` (
  `deposit_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  PRIMARY KEY (`deposit_id`,`service_id`),
  KEY `tb_deposit_service_ibfk_2` (`service_id`),
  CONSTRAINT `tb_deposit_service_ibfk_1` FOREIGN KEY (`deposit_id`) REFERENCES `tb_deposit` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_deposit_service_ibfk_2` FOREIGN KEY (`service_id`) REFERENCES `tb_service` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_deposit_service
-- ----------------------------
INSERT INTO `tb_deposit_service` VALUES ('39', '4');
INSERT INTO `tb_deposit_service` VALUES ('41', '4');
INSERT INTO `tb_deposit_service` VALUES ('43', '4');
INSERT INTO `tb_deposit_service` VALUES ('45', '4');
INSERT INTO `tb_deposit_service` VALUES ('46', '4');
INSERT INTO `tb_deposit_service` VALUES ('48', '4');
INSERT INTO `tb_deposit_service` VALUES ('49', '4');
INSERT INTO `tb_deposit_service` VALUES ('51', '4');
INSERT INTO `tb_deposit_service` VALUES ('55', '4');
INSERT INTO `tb_deposit_service` VALUES ('59', '4');
INSERT INTO `tb_deposit_service` VALUES ('63', '4');
INSERT INTO `tb_deposit_service` VALUES ('65', '4');
INSERT INTO `tb_deposit_service` VALUES ('66', '4');
INSERT INTO `tb_deposit_service` VALUES ('69', '4');
INSERT INTO `tb_deposit_service` VALUES ('71', '4');
INSERT INTO `tb_deposit_service` VALUES ('75', '4');
INSERT INTO `tb_deposit_service` VALUES ('77', '4');
INSERT INTO `tb_deposit_service` VALUES ('78', '4');
INSERT INTO `tb_deposit_service` VALUES ('81', '4');
INSERT INTO `tb_deposit_service` VALUES ('88', '4');
INSERT INTO `tb_deposit_service` VALUES ('89', '4');
INSERT INTO `tb_deposit_service` VALUES ('93', '4');
INSERT INTO `tb_deposit_service` VALUES ('96', '4');
INSERT INTO `tb_deposit_service` VALUES ('106', '4');
INSERT INTO `tb_deposit_service` VALUES ('40', '8');
INSERT INTO `tb_deposit_service` VALUES ('50', '8');
INSERT INTO `tb_deposit_service` VALUES ('53', '8');
INSERT INTO `tb_deposit_service` VALUES ('56', '8');
INSERT INTO `tb_deposit_service` VALUES ('60', '8');
INSERT INTO `tb_deposit_service` VALUES ('64', '8');
INSERT INTO `tb_deposit_service` VALUES ('65', '8');
INSERT INTO `tb_deposit_service` VALUES ('70', '8');
INSERT INTO `tb_deposit_service` VALUES ('72', '8');
INSERT INTO `tb_deposit_service` VALUES ('73', '8');
INSERT INTO `tb_deposit_service` VALUES ('76', '8');
INSERT INTO `tb_deposit_service` VALUES ('79', '8');
INSERT INTO `tb_deposit_service` VALUES ('83', '8');
INSERT INTO `tb_deposit_service` VALUES ('85', '8');
INSERT INTO `tb_deposit_service` VALUES ('86', '8');
INSERT INTO `tb_deposit_service` VALUES ('88', '8');
INSERT INTO `tb_deposit_service` VALUES ('90', '8');
INSERT INTO `tb_deposit_service` VALUES ('91', '8');
INSERT INTO `tb_deposit_service` VALUES ('95', '8');
INSERT INTO `tb_deposit_service` VALUES ('97', '8');
INSERT INTO `tb_deposit_service` VALUES ('98', '8');
INSERT INTO `tb_deposit_service` VALUES ('100', '8');
INSERT INTO `tb_deposit_service` VALUES ('103', '8');
INSERT INTO `tb_deposit_service` VALUES ('104', '8');
INSERT INTO `tb_deposit_service` VALUES ('106', '8');
INSERT INTO `tb_deposit_service` VALUES ('29', '9');
INSERT INTO `tb_deposit_service` VALUES ('39', '9');
INSERT INTO `tb_deposit_service` VALUES ('54', '9');
INSERT INTO `tb_deposit_service` VALUES ('60', '9');
INSERT INTO `tb_deposit_service` VALUES ('70', '9');
INSERT INTO `tb_deposit_service` VALUES ('72', '9');
INSERT INTO `tb_deposit_service` VALUES ('74', '9');
INSERT INTO `tb_deposit_service` VALUES ('75', '9');
INSERT INTO `tb_deposit_service` VALUES ('79', '9');
INSERT INTO `tb_deposit_service` VALUES ('80', '9');
INSERT INTO `tb_deposit_service` VALUES ('86', '9');
INSERT INTO `tb_deposit_service` VALUES ('89', '9');
INSERT INTO `tb_deposit_service` VALUES ('92', '9');
INSERT INTO `tb_deposit_service` VALUES ('95', '9');
INSERT INTO `tb_deposit_service` VALUES ('98', '9');
INSERT INTO `tb_deposit_service` VALUES ('99', '9');
INSERT INTO `tb_deposit_service` VALUES ('100', '9');
INSERT INTO `tb_deposit_service` VALUES ('101', '9');
INSERT INTO `tb_deposit_service` VALUES ('102', '9');
INSERT INTO `tb_deposit_service` VALUES ('103', '9');
INSERT INTO `tb_deposit_service` VALUES ('108', '9');
INSERT INTO `tb_deposit_service` VALUES ('109', '9');
INSERT INTO `tb_deposit_service` VALUES ('110', '9');
INSERT INTO `tb_deposit_service` VALUES ('29', '10');
INSERT INTO `tb_deposit_service` VALUES ('90', '10');
INSERT INTO `tb_deposit_service` VALUES ('91', '10');
INSERT INTO `tb_deposit_service` VALUES ('92', '10');
INSERT INTO `tb_deposit_service` VALUES ('94', '10');
INSERT INTO `tb_deposit_service` VALUES ('96', '10');
INSERT INTO `tb_deposit_service` VALUES ('97', '10');
INSERT INTO `tb_deposit_service` VALUES ('99', '10');
INSERT INTO `tb_deposit_service` VALUES ('101', '10');
INSERT INTO `tb_deposit_service` VALUES ('104', '10');
INSERT INTO `tb_deposit_service` VALUES ('105', '10');
INSERT INTO `tb_deposit_service` VALUES ('108', '10');
INSERT INTO `tb_deposit_service` VALUES ('110', '10');
INSERT INTO `tb_deposit_service` VALUES ('112', '10');
INSERT INTO `tb_deposit_service` VALUES ('102', '14');
INSERT INTO `tb_deposit_service` VALUES ('109', '14');
INSERT INTO `tb_deposit_service` VALUES ('112', '14');

-- ----------------------------
-- Table structure for tb_keeper
-- ----------------------------
DROP TABLE IF EXISTS `tb_keeper`;
CREATE TABLE `tb_keeper` (
  `kid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `sex` tinyint(4) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `description` varchar(255) DEFAULT '暂无',
  `picture` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`kid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_keeper
-- ----------------------------
INSERT INTO `tb_keeper` VALUES ('1', '何小石', '1', '18593273424', '资深宠物饲养员', 'k10.png');
INSERT INTO `tb_keeper` VALUES ('2', '黄大明', '1', '17482956271', '暂无', 'k2.png');
INSERT INTO `tb_keeper` VALUES ('3', '刘欣欣', '0', '18748242231', '暂无', 'k6.png');
INSERT INTO `tb_keeper` VALUES ('4', '蒋小欣', '0', '18593271111', '暂无', 'k7.png');
INSERT INTO `tb_keeper` VALUES ('5', '李晓明', '1', '18593272222', '暂无', 'k5.png');
INSERT INTO `tb_keeper` VALUES ('9', '张小红', '0', '18593272321', '', 'k8.png');
INSERT INTO `tb_keeper` VALUES ('14', '杨菲菲', '1', '18593271232', '', 'k9.png');

-- ----------------------------
-- Table structure for tb_petfood
-- ----------------------------
DROP TABLE IF EXISTS `tb_petfood`;
CREATE TABLE `tb_petfood` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `brand` varchar(20) DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`pid`),
  UNIQUE KEY `brand` (`brand`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_petfood
-- ----------------------------
INSERT INTO `tb_petfood` VALUES ('1', '冠能', '11.00');
INSERT INTO `tb_petfood` VALUES ('2', '好主人', '9.00');
INSERT INTO `tb_petfood` VALUES ('3', '伯纳天纯', '10.00');
INSERT INTO `tb_petfood` VALUES ('4', '皇家', '8.00');
INSERT INTO `tb_petfood` VALUES ('5', '麦富迪', '11.00');
INSERT INTO `tb_petfood` VALUES ('6', '比瑞吉', '12.00');
INSERT INTO `tb_petfood` VALUES ('7', '海洋之星', '13.00');
INSERT INTO `tb_petfood` VALUES ('8', '耐威克', '7.00');
INSERT INTO `tb_petfood` VALUES ('10', '宝路', '8.00');
INSERT INTO `tb_petfood` VALUES ('11', '疯狂的小狗', '7.00');
INSERT INTO `tb_petfood` VALUES ('13', '渴望', '7.00');
INSERT INTO `tb_petfood` VALUES ('14', '爱肯拿', '13.00');
INSERT INTO `tb_petfood` VALUES ('16', '力狼', '12.00');

-- ----------------------------
-- Table structure for tb_service
-- ----------------------------
DROP TABLE IF EXISTS `tb_service`;
CREATE TABLE `tb_service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `service_name` varchar(20) DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `service_name` (`service_name`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_service
-- ----------------------------
INSERT INTO `tb_service` VALUES ('4', '玩耍', '20.00', '每天玩耍30分钟...');
INSERT INTO `tb_service` VALUES ('8', '按摩', '21.00', '每天按摩30分钟');
INSERT INTO `tb_service` VALUES ('9', '除虫', '22.00', '每天按时为宠物除虫');
INSERT INTO `tb_service` VALUES ('10', '洗澡', '11.00', '全方位无死角清洗宠物');
INSERT INTO `tb_service` VALUES ('14', '遛玩', '12.00', '暂无');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) DEFAULT NULL,
  `account` varchar(10) NOT NULL,
  `password` varchar(60) NOT NULL,
  `gender` int(11) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `address` varchar(50) NOT NULL,
  `email` varchar(20) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`),
  KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', '管理员', 'admin', '$2a$10$2CSZr.I1xgoEFXuP6cZdJuWr7ZZK/GcmUvhdoGdcAbCxPxC7jq7Xu', '1', '18593271111', '广西桂林七星区金鸡路', 'lkp0210@163.com', '1');
INSERT INTO `tb_user` VALUES ('23', '赵六', 'zhaoliu', '$2a$10$tZ01t.Mjk/ygnJcwVYNMkeM57N63mbmqykqZEdp5nAnMcX2UzfKU.', '1', '18593271111', '广西桂林七星区', 'lkp0210@163.com', '0');
INSERT INTO `tb_user` VALUES ('28', '王五', 'wangwu', '$2a$10$lZBCKFsLlKl.9bMBfQt/Gu5T8/5ZoArya3jKicADIgSeJdhNuHPUm', '1', '18593274444', '广西桂林七星区', 'wangwu@163.com', '0');
INSERT INTO `tb_user` VALUES ('29', '张三', 'zhangsan', '$2a$10$HpLFRqukrPmtj5Ep2xQLfOSXM9OXByh9XzWefDwvtV3SokAdwqyzq', '1', '18593273424', '广西桂林临桂区', 'lkp0210@163.com', '0');
INSERT INTO `tb_user` VALUES ('30', 'lisa', 'lisa', '$2a$10$jEot2pn4zDzkAZ43tILr4.EJmmPt8z1d0rF4S/pjfTJqQki6QOWom', '0', '18593273424', '广西桂林七星区', 'lisa@163.com', '0');
INSERT INTO `tb_user` VALUES ('31', 'lucy', 'lucy', '$2a$10$GIt/Olq9.IvuExfROmDCGOrXAPFQsBfHVMUWolgOxkSgeeO62sxUS', '0', '18593270000', '广西桂林雁山区', 'lucy@163.com', '0');
INSERT INTO `tb_user` VALUES ('40', '李四', 'lisi', '$2a$10$pY0xYOInuCs/OeBB26ilIOSDt.JsO3ewoT4J4vkOt.3qqaC3uswVu', '1', '18593273424', '广西桂林象山', 'lkp@163.comm', '0');
INSERT INTO `tb_user` VALUES ('44', '曾六', 'zengdada', '$2a$10$jwbD9IPLUUL8YJ7eO8PpielTjm1xtrTWtjvX7OvAe48bvUZiKC0S.', '1', '18593271234', '广西桂林灵川', 'zeng@163.com', '0');
INSERT INTO `tb_user` VALUES ('46', '张三', 'zhangsan1', '$2a$10$HpLFRqukrPmtj5Ep2xQLfOSXM9OXByh9XzWefDwvtV3SokAdwqyzq', '1', '18593273424', '广东东莞', 'zhangsan1@163.com', '0');
