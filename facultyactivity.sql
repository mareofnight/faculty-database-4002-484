-- MySQL dump 10.13  Distrib 5.1.59, for Win32 (ia32)
--
-- Host: localhost    Database: facultyactivity
-- ------------------------------------------------------
-- Server version	5.1.59-community

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


create database facultyactivity;
use facultyactivity;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `courses` (
  `UserId` int(11) NOT NULL,
  `CourseId` int(11) NOT NULL,
  `Year` varchar(45) DEFAULT NULL,
  `CourseNumber` varchar(45) DEFAULT NULL,
  `CourseName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`UserId`,`CourseId`),
  KEY `fk_c_u` (`UserId`),
  CONSTRAINT `fk_c_u` FOREIGN KEY (`UserId`) REFERENCES `users` (`UserId`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO 'courses' (userID, courseID) VALUES (1, 1);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grants`
--

DROP TABLE IF EXISTS `grants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grants` (
  `UserId` int(11) NOT NULL,
  `GrantId` int(11) NOT NULL,
  `Year` varchar(45) DEFAULT NULL,
  `Citation` varchar(145) DEFAULT NULL,
  `Tease` text,
  `Amount` varchar(45) DEFAULT NULL,
  `Status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`UserId`,`GrantId`),
  KEY `fk_g_u` (`UserId`),
  CONSTRAINT `fk_g_u` FOREIGN KEY (`UserId`) REFERENCES `users` (`UserId`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grants`
--

LOCK TABLES `grants` WRITE;
/*!40000 ALTER TABLE `grants` DISABLE KEYS */;
INSERT INTO `grants` VALUES (1,1,'11-12','CER: Encouraging STEM Studies with Informatics” to National Science Foundation, PI-Steve Zilora, Co-PI- Robert Parody','Show kids how to use computing tools to track their favorite bands, movies, etc.','$500k','Submitted'),(1,2,'10-11','Sub-award to RGH R01 project” from NIH, PI-Steve Zilora','Work with RGH to address ear infections in kids.','$28k','Awarded');
/*!40000 ALTER TABLE `grants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kudos`
--

DROP TABLE IF EXISTS `kudos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kudos` (
  `UserId` int(11) NOT NULL,
  `KudoId` int(11) NOT NULL,
  `Year` varchar(45) DEFAULT NULL,
  `Kudo` text,
  PRIMARY KEY (`UserId`,`KudoId`),
  KEY `fk_k_u` (`UserId`),
  CONSTRAINT `fk_k_u` FOREIGN KEY (`UserId`) REFERENCES `users` (`UserId`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kudos`
--

LOCK TABLES `kudos` WRITE;
/*!40000 ALTER TABLE `kudos` DISABLE KEYS */;
INSERT INTO `kudos` VALUES (1,1,'11-12','Ran bootcamp for project management'),(1,2,'11-12','wrote several new courses for semesters');
/*!40000 ALTER TABLE `kudos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pubs`
--

DROP TABLE IF EXISTS `pubs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pubs` (
  `UserId` int(11) NOT NULL,
  `PubId` int(11) NOT NULL,
  `Year` varchar(45) DEFAULT NULL,
  `Citation` varchar(145) DEFAULT NULL,
  `Tease` text,
  `Status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`UserId`,`PubId`),
  KEY `fk_p_u` (`UserId`),
  CONSTRAINT `fk_p_u` FOREIGN KEY (`UserId`) REFERENCES `users` (`UserId`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pubs`
--

LOCK TABLES `pubs` WRITE;
/*!40000 ALTER TABLE `pubs` DISABLE KEYS */;
INSERT INTO `pubs` VALUES (1,1,'11-12','Informatics Minor for Non-Computer Students”, ACM SIGITE’11, West Point, NY, 10/2011','Bring digital literacy to social science students.','Published'),(1,2,'11-12','\"STEM Collaboration Cubed”, ASEE/IEEE Frontiers in Education Conference 2011, Rapid City, South Dakota','Teach informatics to HS kids.','Published');
/*!40000 ALTER TABLE `pubs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `service` (
  `UserId` int(11) NOT NULL,
  `ServiceId` int(11) NOT NULL,
  `Year` varchar(45) DEFAULT NULL,
  `Description` varchar(45) DEFAULT NULL,
  `Role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`UserId`,`ServiceId`),
  KEY `fk_s_u` (`UserId`),
  CONSTRAINT `fk_s_u` FOREIGN KEY (`UserId`) REFERENCES `users` (`UserId`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,1,'11-12','SIGCSE 2012 Conference','Reviewer'),(1,2,'11-12','2012 ASEE Annual Conference','Reviewer'),(1,3,'11-12','Institute Eisenhart Committee ','Chair');
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `UserId` int(11) NOT NULL,
  `FName` varchar(45) DEFAULT NULL,
  `LName` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Pswd` varchar(45) DEFAULT NULL,
  `Role` int(11) DEFAULT NULL,
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Steve','Zilora','sjz@it.rit.edu','5f47859188a602594556580532e814a3',1),(2,'Dan','Bogaard','dsb@it.rit.edu','f4f6172eb26581952a70d7199bfd2ddb',3),(3,'Karen','Griffith','kdgvks@rit.edu','084387d79f1cae0cecd9a8eaccbd23b3',2);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-10-02 11:17:28
