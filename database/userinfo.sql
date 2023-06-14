-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 14, 2023 at 12:14 PM
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
  `middleName` varchar(50) NOT NULL,
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
  `role` varchar(50) NOT NULL,
  `isActivated` tinyint(1) NOT NULL,
  `doctorID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `userinfo`
--

INSERT INTO `userinfo` (`sno`, `lastName`, `firstName`, `middleName`, `age`, `dateOfBirth`, `address`, `contact`, `email`, `sex`, `civilStatus`, `height`, `weight`, `username`, `password`, `role`, `isActivated`, `doctorID`) VALUES
(0, 'none', 'SuperAdmin', 'none', 0, '2023-05-28', 'Java x MySQL', '+63000-000-0000', 'tuwatech@auf.edu.ph', 'none', 'none', 0, 0, '1', '1', 'admin', 1, 0),
(1, 'Esquivel', 'Rosanna', 'Atienza', 59, '1963-12-20', '', '123-123-1234', 'equivel.rosanna@auf.edu.ph', 'Female', 'Married', 0, 0, 'esquivelr-2062621', '1', 'doctor', 1, 0),
(2, 'Esquivel', 'James', 'Alejandro', 59, '1963-07-29', '', '123-123-1234', 'esquivel.james@auf.edu.ph', 'Male', 'Married', 0, 0, 'esquivelj-2909367', '1', 'doctor', 1, 0),
(3, 'Arcega', 'Lance Angelo', 'Palanca', 22, '2000-09-07', '', '918-123-0001', 'arcega.lanceangelo@auf.edu.ph', 'Male', 'Single', 0, 0, 'arcegala-0793954', 'arcega.medata', 'patient', 0, 2),
(4, 'Pare', 'Neo Jezer', 'Aguilar', 19, '2003-10-13', '', '123-123-0002', 'pare.neojezer@auf.edu.ph', 'Male', 'Single', 0, 0, 'parenj-1300893', 'pare.medata', 'patient', 0, 0),
(5, 'Muñoz', 'Nathan Sheary', 'Garcia', 19, '2003-10-11', '', '123-123-0003', 'munoz.nathansheary@auf.edu.com', 'Male', 'Single', 0, 0, 'muñozns-1134885', 'muñoz.medata', 'patient', 0, 0),
(13, ' Samonte', 'Jeremy Sheary', 'Tate', 21, '2002-06-04', '', '+63 123-123-1234', 'jeremysheary@gmail.com', 'Male', 'Divorced', 0, 0, ' samontejs-0468021', ' samonte.medata', 'patient', 0, 1),
(15, 'Balagtas', 'Jomar Benedict', '', 22, '2000-06-16', '', '+63 987-467-3433', 'balagtas@gmail.com', 'Male', 'Single', 0, 0, 'balagtasjb-1623994', '1', 'patient', 1, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `userinfo`
--
ALTER TABLE `userinfo`
  ADD PRIMARY KEY (`sno`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `userinfo`
--
ALTER TABLE `userinfo`
  MODIFY `sno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
