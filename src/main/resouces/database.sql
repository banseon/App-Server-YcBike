-- CREATE DATABASE
CREATE DATABASE `DB_YCBIKE`;
-- CREATE TABLE
CREATE TABLE `ycbike_user_login` (
  `uuid` varchar(20) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(16) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `flag` int(1) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
