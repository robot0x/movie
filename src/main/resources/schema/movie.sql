

SET FOREIGN_KEY_CHECKS=0;

CREATE database movie;

use movie;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) NOT NULL,
  `userName` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `isAdmin` tinyint(1) DEFAULT 0,
  `enable` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

