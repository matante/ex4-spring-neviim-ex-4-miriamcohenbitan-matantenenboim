-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 15, 2022 at 10:56 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ex4`
--

-- --------------------------------------------------------

--
-- Table structure for table `sweet`
--

CREATE TABLE `sweet` (
  `id` bigint(20) NOT NULL,
  `discount` double NOT NULL,
  `image_link` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `sweet_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sweet`
--

INSERT INTO `sweet` (`id`, `discount`, `image_link`, `price`, `quantity`, `sweet_name`) VALUES
(1, 10, 'bananas.jpg', 34.9, 199, 'Bananas'),
(2, 5, 'bonbon.jpg', 69.9, 2, 'bon bon'),
(3, 0, 'candy-factory.png', 12, 49, 'Candy Factory'),
(4, 0, 'chupa-chups.png', 1, 1000, 'Chupa Chups'),
(5, 0, 'cigars.png', 3.5, 1750, 'Gum Cigars '),
(6, 7.5, 'ferrero.png', 69, 0, 'Ferrero Rocher'),
(7, 8, 'hubba-bubba.png', 7.9, 349, 'Hubba Bubba'),
(8, 30, 'kinder-schoko.png', 24.9, 0, 'Schoko Bons'),
(9, 0, 'kit-kat.jpg', 22.9, 50, 'KitKat Gold'),
(10, 15, 'mms-hubba-bubba.png', 19.9, 8, 'M&M’s'),
(11, 3.5, 'oreo.jpg', 8.9, 199, 'Oreo');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `sweet`
--
ALTER TABLE `sweet`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
