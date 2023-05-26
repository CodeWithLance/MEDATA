-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 26, 2023 at 09:38 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `medata`
--

-- --------------------------------------------------------

--
-- Table structure for table `userinfo`
--

CREATE TABLE `userinfo` (
  `sno` int(11) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `firstName` varchar(50) NOT NULL,
  `middleInitial` varchar(50) NOT NULL,
  `age` int(10) NOT NULL,
  `dateOfBirth` date NOT NULL,
  `address` varchar(100) NOT NULL,
  `contact` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `sex` varchar(50) NOT NULL,
  `civilStatus` varchar(50) NOT NULL,
  `height` int(50) NOT NULL,
  `weight` int(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `confirmPassword` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `userinfo`
--

INSERT INTO `userinfo` (`sno`, `lastName`, `firstName`, `middleInitial`, `age`, `dateOfBirth`, `address`, `contact`, `email`, `sex`, `civilStatus`, `height`, `weight`, `username`, `password`, `confirmPassword`, `role`) VALUES
(1, 'Arcega', 'Lance Angelo', 'P', 22, '2000-09-07', 'Angeles University Foundation, Angeles City, Pampanga, 2009', '09187110987', 'arcega.lanceangelo@auf.edu.ph', 'Male', 'Single', 183, 220, 'admin01', 'meow0907', 'meow0907', 'admin'),
(2, 'Munoz', 'Nathan Sheary', 'G', 20, '2023-05-01', 'Angeles City', '09181234567', 'munoz.nathansheary@auf.edu.ph', 'Male', 'Single', 180, 150, 'doctor01', 'dok123', 'dok123', 'doctor'),
(3, 'Pare', 'Neo Jezer', 'A.', 20, '2023-05-09', 'Angeles City', '09180001234', 'pare.neojezer@auf.edu.ph', 'Male', 'Single', 175, 130, 'patient01', 'pass01', 'pass01', 'patient');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `userinfo`
--
ALTER TABLE `userinfo`
  ADD PRIMARY KEY (`sno`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `contact` (`contact`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `userinfo`
--
ALTER TABLE `userinfo`
  MODIFY `sno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
