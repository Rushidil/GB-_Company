-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Apr 25, 2021 at 09:45 AM
-- Server version: 5.7.31
-- PHP Version: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projectdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
CREATE TABLE IF NOT EXISTS `projects` (
  `projectID` int(11) NOT NULL AUTO_INCREMENT,
  `projectCode` varchar(11) NOT NULL,
  `projectName` varchar(50) NOT NULL,
  `researcherID` varchar(50) NOT NULL,
  `projectDesc` varchar(50) NOT NULL,
  `categoryID` varchar(20) NOT NULL,
  `projectRemarks` varchar(20) NOT NULL,
  PRIMARY KEY (`projectID`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `projects`
--

INSERT INTO `projects` (`projectID`, `projectCode`, `projectName`, `researcherID`, `projectDesc`, `categoryID`, `projectRemarks`) VALUES
(2, 'P040', 'Phone Setup', 'R009', 'Want empharsize', 'C04', '6 times'),
(4, 'P101', 'Sequel Extract', 'R004', 'help for change', 'C12', '4 times');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
