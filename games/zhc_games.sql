# ************************************************************
# Sequel Pro SQL dump
# Version 4499
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.6.35)
# Database: zhc_games
# Generation Time: 2017-06-14 06:07:48 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table Account
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Account`;

CREATE TABLE `Account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `currIndex` int(11) DEFAULT NULL,
  `remark` text,
  `userTag` varchar(255) DEFAULT NULL,
  `game_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jyjqjpbk5aqyk5x5o70f8nje5` (`game_id`),
  CONSTRAINT `FK_jyjqjpbk5aqyk5x5o70f8nje5` FOREIGN KEY (`game_id`) REFERENCES `Game` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table AccountDetail
# ------------------------------------------------------------

DROP TABLE IF EXISTS `AccountDetail`;

CREATE TABLE `AccountDetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adindex` int(11) DEFAULT NULL,
  `content` longtext,
  `account_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_8nmp5ufqr3sjnxpvwc1gv3q90` (`account_id`),
  CONSTRAINT `FK_8nmp5ufqr3sjnxpvwc1gv3q90` FOREIGN KEY (`account_id`) REFERENCES `Account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table affix
# ------------------------------------------------------------

DROP TABLE IF EXISTS `affix`;

CREATE TABLE `affix` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `downNum` bigint(20) DEFAULT NULL,
  `extname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `hotNum` bigint(20) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `objectId` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `objectType` int(11) DEFAULT NULL,
  `path` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `size` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `source` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



# Dump of table Game
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Game`;

CREATE TABLE `Game` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `gname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `Game` WRITE;
/*!40000 ALTER TABLE `Game` DISABLE KEYS */;

INSERT INTO `Game` (`id`, `createDate`, `description`, `gname`)
VALUES
	(1,'2017-06-14 14:06:56','功夫熊猫3','功夫熊猫3');

/*!40000 ALTER TABLE `Game` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table MailFailLog
# ------------------------------------------------------------

DROP TABLE IF EXISTS `MailFailLog`;

CREATE TABLE `MailFailLog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` text,
  `exception` text,
  `execDate` datetime DEFAULT NULL,
  `receiverMails` text,
  `sendDate` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table qz_job_detail
# ------------------------------------------------------------

DROP TABLE IF EXISTS `qz_job_detail`;

CREATE TABLE `qz_job_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `className` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `extendf1` bigint(20) DEFAULT NULL,
  `extendf2` bigint(20) DEFAULT NULL,
  `extendf3` int(11) DEFAULT NULL,
  `extendf4` int(11) DEFAULT NULL,
  `extendf5` varchar(255) DEFAULT NULL,
  `extendf6` varchar(255) DEFAULT NULL,
  `extendf7` varchar(255) DEFAULT NULL,
  `extendf8` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parameters` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table qz_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `qz_log`;

CREATE TABLE `qz_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exception` text,
  `execTime` datetime DEFAULT NULL,
  `jobName` varchar(255) DEFAULT NULL,
  `otherRemark` text,
  `result` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table qz_trigger
# ------------------------------------------------------------

DROP TABLE IF EXISTS `qz_trigger`;

CREATE TABLE `qz_trigger` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cronExpression` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `extendf1` bigint(20) DEFAULT NULL,
  `extendf2` bigint(20) DEFAULT NULL,
  `extendf3` int(11) DEFAULT NULL,
  `extendf4` int(11) DEFAULT NULL,
  `extendf5` varchar(255) DEFAULT NULL,
  `extendf6` varchar(255) DEFAULT NULL,
  `extendf7` varchar(255) DEFAULT NULL,
  `extendf8` varchar(255) DEFAULT NULL,
  `extendf9` text,
  `name` varchar(255) DEFAULT NULL,
  `repeatCount` int(11) DEFAULT NULL,
  `repeatInterval` int(11) DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `startDelay` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `qzJobDetail_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_2gypa1lpf5oij9ksti5ggo1s5` (`qzJobDetail_id`),
  CONSTRAINT `FK_2gypa1lpf5oij9ksti5ggo1s5` FOREIGN KEY (`qzJobDetail_id`) REFERENCES `qz_job_detail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table SerialNumber
# ------------------------------------------------------------

DROP TABLE IF EXISTS `SerialNumber`;

CREATE TABLE `SerialNumber` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `beginDate` datetime DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `serialNumber` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `game_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_p9hv3bbfxqarb72xd2sxndyf2` (`game_id`),
  CONSTRAINT `FK_p9hv3bbfxqarb72xd2sxndyf2` FOREIGN KEY (`game_id`) REFERENCES `Game` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sysmenu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sysmenu`;

CREATE TABLE `sysmenu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `alias` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `ename` varchar(255) DEFAULT NULL,
  `extendf1` varchar(255) DEFAULT NULL,
  `extendf2` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `orderBy` bigint(20) DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5p24yuafh3b9skc4qb4bp6ete` (`parentId`),
  CONSTRAINT `FK_5p24yuafh3b9skc4qb4bp6ete` FOREIGN KEY (`parentId`) REFERENCES `sysmenu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sysmenu` WRITE;
/*!40000 ALTER TABLE `sysmenu` DISABLE KEYS */;

INSERT INTO `sysmenu` (`id`, `alias`, `createDate`, `description`, `ename`, `extendf1`, `extendf2`, `img`, `link`, `name`, `orderBy`, `updateDate`, `parentId`)
VALUES
	(1,'Root',NULL,'Root','Root',NULL,NULL,NULL,NULL,'Root',0,NULL,NULL),
	(7,'首页','2014-09-10 10:33:19',NULL,'首页',NULL,NULL,NULL,'/manage/index.do','首页',0,'2015-11-03 22:09:36',1),
	(36,'系统管理','2014-09-10 10:53:07',NULL,'系统管理',NULL,NULL,NULL,NULL,'系统管理',9,'2015-11-03 22:11:11',1),
	(37,'管理员','2014-09-10 10:53:30',NULL,'管理员',NULL,NULL,NULL,'/sys/listSysUser.do','管理员',0,'2015-11-03 22:11:39',36),
	(38,'系统菜单','2014-09-10 10:53:56',NULL,'系统菜单',NULL,NULL,NULL,'/sys/listMenu.do','系统菜单',1,'2015-11-03 22:11:52',36),
	(41,'管理员角色','2014-09-11 14:59:18',NULL,'管理员角色',NULL,NULL,NULL,'/sys/sysrole.do','管理员角色',2,'2015-11-03 22:12:05',36),
	(43,'权限管理','2014-09-11 15:00:12',NULL,'权限管理',NULL,NULL,NULL,'/sys/syspopedom.do','权限管理',3,'2015-11-03 22:12:13',36),
	(45,'修改密码','2014-10-09 16:31:37',NULL,'修改密码',NULL,NULL,NULL,'/sys/toChangePwd.do','修改密码',4,'2015-11-03 22:12:20',36),
	(77,'定时器任务','2015-03-04 08:29:55',NULL,'定时器任务',NULL,NULL,NULL,'/manage/quartz/toJob.do','定时器任务',5,'2015-11-03 22:12:39',36),
	(78,'定时器执行','2015-03-04 08:31:30',NULL,'定时器执行',NULL,NULL,NULL,'/manage/quartz/toTrigger.do','定时器执行',6,'2015-11-03 22:12:46',36),
	(81,'授权管理','2015-11-03 22:18:40',NULL,'授权管理',NULL,NULL,NULL,NULL,'授权管理',1,'2015-11-03 22:18:40',1),
	(83,'应用管理','2015-11-03 22:19:36',NULL,'应用管理',NULL,NULL,NULL,'/manage/game/gamelist.do','应用管理',1,'2015-11-10 21:08:36',81),
	(84,'账号管理','2015-11-03 22:19:46',NULL,'账号管理',NULL,NULL,NULL,'/manage/game/accountlist.do','账号管理',2,'2015-11-10 21:45:29',81);

/*!40000 ALTER TABLE `sysmenu` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table syspopedom
# ------------------------------------------------------------

DROP TABLE IF EXISTS `syspopedom`;

CREATE TABLE `syspopedom` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sys_code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sysrole
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sysrole`;

CREATE TABLE `sysrole` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `extendf1` varchar(255) DEFAULT NULL,
  `extendf2` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `popedoms` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sysrole` WRITE;
/*!40000 ALTER TABLE `sysrole` DISABLE KEYS */;

INSERT INTO `sysrole` (`id`, `description`, `extendf1`, `extendf2`, `name`, `popedoms`, `type`)
VALUES
	(1,'Super Manager',NULL,NULL,'Super Manager',NULL,1);

/*!40000 ALTER TABLE `sysrole` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sysrole_syspopedom
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sysrole_syspopedom`;

CREATE TABLE `sysrole_syspopedom` (
  `sysrole_id` bigint(20) NOT NULL,
  `syspopedom_id` bigint(20) NOT NULL,
  PRIMARY KEY (`sysrole_id`,`syspopedom_id`),
  KEY `FK_oo7xoflcnvf8skw5omrbql5h9` (`syspopedom_id`),
  CONSTRAINT `FK_53kgl5trsotv45d01ox8o04ru` FOREIGN KEY (`sysrole_id`) REFERENCES `sysrole` (`id`),
  CONSTRAINT `FK_oo7xoflcnvf8skw5omrbql5h9` FOREIGN KEY (`syspopedom_id`) REFERENCES `syspopedom` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sysroletosysmenu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sysroletosysmenu`;

CREATE TABLE `sysroletosysmenu` (
  `extendf1` varchar(255) DEFAULT NULL,
  `sysrole_id` bigint(20) NOT NULL,
  `sysmenu_id` bigint(20) NOT NULL,
  PRIMARY KEY (`sysmenu_id`,`sysrole_id`),
  KEY `FK_m56ufpq4f5xl2jrv8acjjxsk4` (`sysrole_id`),
  CONSTRAINT `FK_bi2ymf7m10j8fe7d1ll4bq47q` FOREIGN KEY (`sysmenu_id`) REFERENCES `sysmenu` (`id`),
  CONSTRAINT `FK_m56ufpq4f5xl2jrv8acjjxsk4` FOREIGN KEY (`sysrole_id`) REFERENCES `sysrole` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sysroletosysmenu` WRITE;
/*!40000 ALTER TABLE `sysroletosysmenu` DISABLE KEYS */;

INSERT INTO `sysroletosysmenu` (`extendf1`, `sysrole_id`, `sysmenu_id`)
VALUES
	(NULL,1,1),
	(NULL,1,7),
	(NULL,1,36),
	(NULL,1,37),
	(NULL,1,38),
	(NULL,1,41),
	(NULL,1,43),
	(NULL,1,45),
	(NULL,1,77),
	(NULL,1,78),
	(NULL,1,81),
	(NULL,1,83),
	(NULL,1,84);

/*!40000 ALTER TABLE `sysroletosysmenu` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sysuser
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sysuser`;

CREATE TABLE `sysuser` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `lastLoginTime` datetime DEFAULT NULL,
  `loginName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `trueName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sysuser` WRITE;
/*!40000 ALTER TABLE `sysuser` DISABLE KEYS */;

INSERT INTO `sysuser` (`id`, `createDate`, `email`, `lastLoginTime`, `loginName`, `password`, `phone`, `status`, `trueName`)
VALUES
	(1,'2014-07-25 16:57:49','','2017-06-14 14:06:16','admin','202CB962AC59075B964B07152D234B70','',0,'超级管理员');

/*!40000 ALTER TABLE `sysuser` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sysusertosysrole
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sysusertosysrole`;

CREATE TABLE `sysusertosysrole` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sysrole_id` bigint(20) DEFAULT NULL,
  `sysuser_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_lmbccldu4qd824ptp58txfr6o` (`sysrole_id`),
  KEY `FK_e5l6iwfa48hhxe0mog0ct1j44` (`sysuser_id`),
  CONSTRAINT `FK_e5l6iwfa48hhxe0mog0ct1j44` FOREIGN KEY (`sysuser_id`) REFERENCES `sysuser` (`id`),
  CONSTRAINT `FK_lmbccldu4qd824ptp58txfr6o` FOREIGN KEY (`sysrole_id`) REFERENCES `sysrole` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sysusertosysrole` WRITE;
/*!40000 ALTER TABLE `sysusertosysrole` DISABLE KEYS */;

INSERT INTO `sysusertosysrole` (`id`, `sysrole_id`, `sysuser_id`)
VALUES
	(3,1,1);

/*!40000 ALTER TABLE `sysusertosysrole` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
