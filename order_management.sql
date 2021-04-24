-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Apr 24, 2021 at 05:21 PM
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
-- Database: `order_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
CREATE TABLE IF NOT EXISTS `cart` (
  `cID` int(11) NOT NULL AUTO_INCREMENT,
  `ptname` varchar(50) NOT NULL,
  `unitPrice` double NOT NULL,
  `qty` int(11) NOT NULL,
  PRIMARY KEY (`cID`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`cID`, `ptname`, `unitPrice`, `qty`) VALUES
(1, 'Scanmarker', 12500, 2),
(3, 'Motar', 12000, 3),
(8, 'Flash Drive', 7500, 5),
(7, 'BiKN tracking device', 4000, 2);

-- --------------------------------------------------------

--
-- Table structure for table `orderdetails`
--

DROP TABLE IF EXISTS `orderdetails`;
CREATE TABLE IF NOT EXISTS `orderdetails` (
  `oID` int(11) NOT NULL AUTO_INCREMENT,
  `cID` int(11) NOT NULL,
  `date` varchar(50) NOT NULL,
  `cname` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `total` double NOT NULL,
  PRIMARY KEY (`oID`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orderdetails`
--

INSERT INTO `orderdetails` (`oID`, `cID`, `date`, `cname`, `phone`, `address`, `email`, `total`) VALUES
(4, 1, '2021-01-02', 'Thirushi  Dilmika', '0711442589', '54/17 a,8th lane,Kirillawala', 'thiru99@gmail.com', 25000),
(10, 8, '2021-02-06', 'Ruwan Dias', '0784567547', '45 / 34 ,Malabe', 'ruwan44@gmail.com', 37500),
(9, 7, '2021-04-03', 'Praveen Max', '0784567893', '45 / 34 ,galle', 'pra45gfhg@gmail.com', 8000),
(8, 3, '2021-03-27', 'Dilini Thari', '0789388456', '32/ A ,Jaffna', 'dil99@gmail.com', 36000);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
CREATE TABLE IF NOT EXISTS `payment` (
  `pID` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(50) NOT NULL,
  `lname` varchar(50) NOT NULL,
  `cardNo` varchar(50) NOT NULL,
  `cdate` varchar(50) NOT NULL,
  `ccv` varchar(10) NOT NULL,
  PRIMARY KEY (`pID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`pID`, `fname`, `lname`, `cardNo`, `cdate`, `ccv`) VALUES
(1, 'Thirushi', 'Dilmika', '345 4356 5643 213 ', '2021-04-21', '013'),
(3, 'Hiruni', 'Sakunika', '234 5678 6786 234', '2021-03-18', '234'),
(4, 'Eshan', 'Sandeepa', '234 8765 0758 122 ', '2021-04-24', '087'),
(8, 'Sonu', 'Nigam', '342 4567 4567 128', '2021-04-16', '001'),
(9, 'Charith', 'Dias', '001 0987 7568 342', '2021-04-24', '908');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
