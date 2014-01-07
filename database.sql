-- MySQL dump 10.13  Distrib 5.6.12, for Win32 (x86)
--
-- Host: localhost    Database: easyFuse
-- ------------------------------------------------------
-- Server version	5.6.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_sys_org`
--

DROP TABLE IF EXISTS `t_sys_org`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_org` (
  `c_id` varchar(32) NOT NULL,
  `c_name` varchar(45) DEFAULT NULL,
  `c_code` varchar(45) DEFAULT NULL,
  `c_parentid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_org`
--

LOCK TABLES `t_sys_org` WRITE;
/*!40000 ALTER TABLE `t_sys_org` DISABLE KEYS */;
INSERT INTO `t_sys_org` VALUES ('402880ef436ca08701436cbb26480004','和平','10001','0'),('402880ef436ca08701436cbbaa8c0005','沈河','100002','0');
/*!40000 ALTER TABLE `t_sys_org` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_user`
--

DROP TABLE IF EXISTS `t_sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_user` (
  `C_ID` varchar(32) NOT NULL,
  `C_USERNAME` varchar(45) DEFAULT NULL,
  `C_PASSWORD` varchar(45) DEFAULT NULL,
  `C_EMAIL` varchar(45) DEFAULT NULL,
  `C_PHONENUMBER` varchar(45) DEFAULT NULL,
  `C_ACCOUNT_ENABLED` int(11) DEFAULT NULL,
  `C_ACCOUNT_EXPIRED` int(11) DEFAULT NULL,
  `C_ACCOUNT_LOCKED` int(11) DEFAULT NULL,
  `C_CREDENTIALS_EXPIRED` int(11) DEFAULT NULL,
  PRIMARY KEY (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_user`
--

LOCK TABLES `t_sys_user` WRITE;
/*!40000 ALTER TABLE `t_sys_user` DISABLE KEYS */;
INSERT INTO `t_sys_user` VALUES ('402880ef436ca08701436cb87baa0002','admin','admin','admin@163.com','15678945210',1,0,0,0),('402880ef436ca08701436cba36890003','卡耐基','admin','knj@163.com','13712345874',1,0,0,0);
/*!40000 ALTER TABLE `t_sys_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-01-07 20:45:32
