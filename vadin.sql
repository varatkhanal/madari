-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 08, 2021 at 07:38 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `vadin`
--

-- --------------------------------------------------------

--
-- Table structure for table `addresses`
--

CREATE TABLE `addresses` (
  `id` bigint(20) NOT NULL,
  `city` varchar(15) NOT NULL,
  `street_name` varchar(100) NOT NULL,
  `uid` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `addresses`
--

INSERT INTO `addresses` (`id`, `city`, `street_name`, `uid`) VALUES
(2, 'ktm', 'ghattekulo', 1),
(4, 'ktm', 'ghattekulo', 3),
(6, 'ktm', 'ghattekulo', 5),
(8, 'ktm', 'ghattekulo', 7),
(10, 'ktm', 'ghattekulo', 9),
(12, 'btl', 'yogikuti', 11),
(14, 'btl', 'yogikuti', 13),
(16, 'btl', 'bhalwari', 15),
(18, 'btl', 'bhalwari', 17),
(20, 'btl', 'jyotinagar', 19),
(22, 'tilottama', 'yogikuti', 21),
(24, 'tilottama', 'jyotinagar', 23),
(26, 'ktm', 'Ghattekulo', 25),
(28, 'ktm', 'Ghattekulo', 27),
(30, 'ktm', 'Ghattekulo', 29),
(32, 'ktm', 'Ghattekulo', 31);

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(33);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `description`, `name`) VALUES
(1, 'ROLE_USER', 'READ_DISCUSSION_THREADS'),
(2, 'READ_DISCUSSION_THREADS', 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(120) NOT NULL,
  `encrypted_password` varchar(255) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `address_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `encrypted_password`, `first_name`, `last_name`, `user_id`, `user_name`, `address_id`) VALUES
(1, 'varat.khanal468@gmail.com', 'bharat@468', 'bharat', 'khanal', '6rm9wggEHFYM1bcJ4hZfKx8y2fyypt', 'bharat_khana', 2),
(3, 'itsmk.khanal468@gmail.com', 'mohan@468', 'mohan', 'khanal', 'IHAWZNwi8ew4QQvZdBj01OJWebJAZB', 'mohan_khana', 4),
(5, 'bijay.khanal468@gmail.com', 'bijay@468', 'bijay', 'khanal', 'oaaYZ8Ky6XlrFab2NpPPpT3QecHT3n', 'bijay_khana', 6),
(7, 'shova.khanal468@gmail.com', 'shova@468', 'shova', 'khanal', '9HNAmmTDQXOXQmgqnPbOnmYcRttB3N', 'shova_khana', 8),
(9, 'abinit.khanal468@gmail.com', 'abinit@468', 'abinit', 'khanal', 'DhmQBtUfksH0JGPhpa6zdP8DMMlTJS', 'abinit_khana', 10),
(11, 'ritisna.khanal468@gmail.com', 'ritisna@468', 'Ritisna', 'khanal', 'mCdSzFxdEyM9YVKxR8MG1PFHUAMDnc', 'ritishna_khana', 12),
(13, 'risit.khanal468@gmail.com', 'risit@468', 'Risit', 'khanal', 'oZZiUd92slFbBJh8QF3OdgtMDcYOHi', 'risit_khana', 14),
(15, 'ashrina.khanal468@gmail.com', 'risit@468', 'ashrina', 'pangeni', 'wAduWl9tzpBKvxfONdRbfqUgLeBNY4', 'ashrina_pangeni', 16),
(17, 'alu.khanal468@gmail.com', 'alu@468', 'alu', 'pangeni', 'DnCDnkgeYzfrjU9KJDOVglGDJXqMOj', 'alu_pangeni', 18),
(19, 'shiva.khanal468@gmail.com', 'shiva@468', 'shiva', 'khanal', 'Y1TTnJvEIfvOY1ox4eXQbDjhVch8OL', 'shiva_khanal', 20),
(21, 'ravi.khanal468@gmail.com', 'ravi@468', 'ravi', 'khanal', 'ckfb78i5JSB69CX2S3n9aVgdrEBxXI', 'ravi_khanal', NULL),
(23, 'anita.khanal468@gmail.com', 'anita@468', 'anita', 'khanal', 'dSkcBMSm5wFeg2Poncx8WqquyiD1Q7', 'anita_khanal', NULL),
(25, 'shelly.neupane468@gmail.com', 'shelly@468', 'Shelly', 'Neupane', 'wWvgMz7nAtCOejkObgu00zNqf7Brsl', 'shelly_neupane', NULL),
(27, 'abc.neupane468@gmail.com', 'abc@468', 'abc', 'Neupane', 'cKP0rKbf5tjoHP4J8kmbJakIk7YXcS', 'abc_neupane', NULL),
(29, 'xyz.neupane468@gmail.com', 'xyz@468', 'xyz', 'Neupane', '2XOQWZP5akB2oFoVSWNC9x73jU90d6', 'xyz_neupane', NULL),
(31, 'mno.neupane468@gmail.com', 'mno@468', 'mno', 'Neupane', 'aG4vC5IVNgRpocYryzE7UKlcRynVjX', 'mno_neupane', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(1, 2),
(3, 2),
(5, 2),
(7, 2),
(9, 2),
(11, 2),
(13, 2),
(15, 2),
(17, 2),
(19, 2),
(21, 2),
(23, 2),
(25, 2),
(27, 2),
(29, 2),
(31, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `addresses`
--
ALTER TABLE `addresses`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKrxfi01hjih09n0sh8p7p9x1no` (`uid`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6efs5vmce86ymf5q7lmvn2uuf` (`user_id`),
  ADD KEY `FKe8vydtk7hf0y16bfm558sywbb` (`address_id`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD KEY `FKrhfovtciq1l558cw6udg0h0d3` (`role_id`),
  ADD KEY `FKhfh9dx7w3ubf1co1vdev94g3f` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `addresses`
--
ALTER TABLE `addresses`
  ADD CONSTRAINT `FKrxfi01hjih09n0sh8p7p9x1no` FOREIGN KEY (`uid`) REFERENCES `users` (`id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FKe8vydtk7hf0y16bfm558sywbb` FOREIGN KEY (`address_id`) REFERENCES `addresses` (`id`);

--
-- Constraints for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKrhfovtciq1l558cw6udg0h0d3` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
