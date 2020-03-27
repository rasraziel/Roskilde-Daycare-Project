CREATE DATABASE  IF NOT EXISTS `daycare` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `daycare`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: daycare
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `children`
--

DROP TABLE IF EXISTS `children`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `children` (
  `children_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `date_of_birth` date NOT NULL,
  `gender` varchar(1) NOT NULL,
  `phone_number` varchar(8) DEFAULT 'null',
  PRIMARY KEY (`children_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `children`
--

LOCK TABLES `children` WRITE;
/*!40000 ALTER TABLE `children` DISABLE KEYS */;
INSERT INTO `children` VALUES (1,'Axel','King','2005-01-02','M','null'),
						(3,'Lana','Cooper','2007-05-24','F','null'),
						(4,'Arnold','Cooper','2004-05-20','M','65281907'),
                        (5,'Simon','Williams','2005-02-12','M','null'),
                        (6,'Philippa','Black','2006-05-15','F','null'),
                        (7,'Anastasia','Curry','2006-05-20','F','null'),
                        (8,'Nicolas','Green','2005-07-20','M','null');
/*!40000 ALTER TABLE `children` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `children_schedule`
--

DROP TABLE IF EXISTS `children_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `children_schedule` (
  `children_program_id` int NOT NULL AUTO_INCREMENT,
  `children_id` int NOT NULL,
  `schedule_day_id` int NOT NULL,
  PRIMARY KEY (`children_program_id`),
  KEY `fk_children_program_children_idx` (`children_id`),
  KEY `fk_children_program_schedule_day_idx` (`schedule_day_id`),
  CONSTRAINT `fk_children_program_children` FOREIGN KEY (`children_id`) REFERENCES `children` (`children_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_children_program_schedule_day` FOREIGN KEY (`schedule_day_id`) REFERENCES `schedule_day` (`schedule_day_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `children_schedule`
--

LOCK TABLES `children_schedule` WRITE;
/*!40000 ALTER TABLE `children_schedule` DISABLE KEYS */;
INSERT INTO `children_schedule` VALUES (1,1,1),(2,3,2),(3,4,2),(4,5,5),(5,6,5),(6,7,5),(7,1,6),(9,3,13),(10,4,13),(11,5,15),(12,6,16),(13,7,17),(14,8,18),(15,5,18),(16,6,19),(17,7,19),(18,8,20),(19,1,20);
/*!40000 ALTER TABLE `children_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `cpr` varchar(10) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `salary` double NOT NULL,
  `phone_number` varchar(8) NOT NULL,
  `gender` varchar(1) NOT NULL,
  `address` varchar(100) NOT NULL,
  PRIMARY KEY (`cpr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES ('0504856532','Albert','Johnston',18000,'65214508','M','Riverside Drive 45'),
						('0804928461','Peter','Price',18000,'81250817','M','Mulberry Avenue 12'),
						('1208925412','Sophia','Hall',15000,'87542103','F','Carolina Avenue 16'),
						('1506808425','Jason','Hamilton',30000,'28451525','M','Rosewood Court 29'),
						('2408908425','Sydney','Price',18000,'91280745','F','Mulberry Avenue 12'),
						('2501958745','Marco','James',15000,'84512305','M','Jewell Road 14');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parent_has_children`
--

DROP TABLE IF EXISTS `parent_has_children`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parent_has_children` (
  `parent_has_children_id` int NOT NULL AUTO_INCREMENT,
  `parent_id` int NOT NULL,
  `children_id` int NOT NULL,
  PRIMARY KEY (`parent_has_children_id`),
  KEY `fk_parent_has_children_children_idx` (`children_id`),
  KEY `fk_parent_has_children_parents_idx` (`parent_id`),
  CONSTRAINT `fk_parent_has_children_children` FOREIGN KEY (`children_id`) REFERENCES `children` (`children_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_parent_has_children_parents` FOREIGN KEY (`parent_id`) REFERENCES `parents` (`parent_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parent_has_children`
--

LOCK TABLES `parent_has_children` WRITE;
/*!40000 ALTER TABLE `parent_has_children` DISABLE KEYS */;
INSERT INTO `parent_has_children` VALUES (1,1,1),(2,2,3),(3,2,4),(4,3,5),(5,4,6),(6,5,7),(7,6,8);
/*!40000 ALTER TABLE `parent_has_children` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parents`
--

DROP TABLE IF EXISTS `parents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parents` (
  `parent_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `number_of_children` tinyint NOT NULL,
  `date_of_birth` date NOT NULL,
  `gender` varchar(1) NOT NULL,
  `email_address` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phone_number` varchar(8) NOT NULL,
  `paid` varchar(6) NOT NULL,
  PRIMARY KEY (`parent_id`),
  UNIQUE KEY `email_address_UNIQUE` (`email_address`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parents`
--

LOCK TABLES `parents` WRITE;
/*!40000 ALTER TABLE `parents` DISABLE KEYS */;
INSERT INTO `parents` VALUES (1,'Stephen','King',1,'1975-04-02','M','stephenK@email.com','FirstStreet Avenue 3','15243687','true'),
					(2,'Alice','Cooper',2,'1980-06-06','F','aliceC@gmail.com','FirstStreet Avenue 15','12345678','true'),
					(3,'Vanessa','Williams',1,'1980-07-07','F','vanessaW@gmail.com','Cloverfield Lane 15','15456585','true'),
					(4,'Jack','Black',1,'1982-09-09','M','jackB@gmail.com','Cloverfield Lane 13','84650827','false'),
					(5,'Arthur','Curry',1,'1980-04-04','M','arthurC@email.com','Cloverfield Lane 18','91284608','true'),
					(6,'Mike','Green',1,'1981-02-25','M','mikeG@email.com','Cloverfield Lane 10','97581528','true');
/*!40000 ALTER TABLE `parents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule_day`
--

DROP TABLE IF EXISTS `schedule_day`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule_day` (
  `schedule_day_id` int NOT NULL AUTO_INCREMENT,
  `week_number` int NOT NULL,
  `day` varchar(5) NOT NULL,
  `employees_cpr` varchar(10) NOT NULL,
  PRIMARY KEY (`schedule_day_id`),
  KEY `fk_schedule_day_employees_idx` (`employees_cpr`),
  CONSTRAINT `fk_schedule_day_employees` FOREIGN KEY (`employees_cpr`) REFERENCES `employees` (`cpr`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule_day`
--

LOCK TABLES `schedule_day` WRITE;
/*!40000 ALTER TABLE `schedule_day` DISABLE KEYS */;
INSERT INTO `schedule_day` VALUES (1,1,'TUE','0504856532'),
						(2,1,'WED','0804928461'), 
						(3,1,'THU','1208925412'), 
						(4,1,'FRI','1506808425'),
						(5,1,'FRI','2408908425'),
						(6,2,'MON','2501958745'),
						(13,2,'MON','0504856532'),
						(15,2,'MON','0804928461'),
						(16,2,'TUE','1208925412'),
						(17,2,'WED','1506808425'),
						(18,2,'THU','2408908425'),
						(19,2,'FRI','2501958745'),
						(20,2,'FRI','1506808425');
/*!40000 ALTER TABLE `schedule_day` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'daycare'
--

--
-- Dumping routines for database 'daycare'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-25 18:07:17
