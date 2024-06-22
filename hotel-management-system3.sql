-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 21, 2024 at 11:59 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel-management-system3`
--

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `employee_id` bigint(20) NOT NULL,
  `end_date` date DEFAULT NULL,
  `role` enum('HOUSEKEEPER','MANAGER','RECEPTIONIST') DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `status` enum('ACTIVE','TERMINATED') DEFAULT NULL,
  `managed_by_admin_id` bigint(20) NOT NULL,
  `hotel_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`employee_id`, `end_date`, `role`, `salary`, `start_date`, `status`, `managed_by_admin_id`, `hotel_id`) VALUES
(1, NULL, 'HOUSEKEEPER', 500, '2023-01-01', 'ACTIVE', 1, 1),
(2, NULL, 'HOUSEKEEPER', 500, '2023-01-01', 'ACTIVE', 1, 1),
(3, NULL, 'MANAGER', 500, '2023-01-01', 'ACTIVE', 1, 1),
(4, NULL, 'MANAGER', 500, '2023-01-01', 'ACTIVE', 1, 1),
(5, NULL, 'MANAGER', 500, '2023-01-01', 'ACTIVE', 1, 2),
(6, NULL, 'MANAGER', 500, '2023-01-01', 'ACTIVE', 1, 2),
(7, NULL, 'RECEPTIONIST', 500, '2023-01-01', 'ACTIVE', 1, 2),
(8, '2024-06-22', 'HOUSEKEEPER', 500, '2024-06-22', 'TERMINATED', 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `hotels`
--

CREATE TABLE `hotels` (
  `hotel_id` bigint(20) NOT NULL,
  `contact_number` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `rating` decimal(38,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hotels`
--

INSERT INTO `hotels` (`hotel_id`, `contact_number`, `description`, `email`, `location`, `name`, `rating`) VALUES
(1, '0599', 'hotel1', 'hotel1@gmail.com', 'h1', 'hotel1', 5.00),
(2, '0566', 'hotel2', 'hotel2@gmail.com', 'h2', 'hotel2', 5.00),
(3, '0577', 'hotel3', 'hotel3@gmail.com', 'h3', 'hotel3', 5.00);

-- --------------------------------------------------------

--
-- Table structure for table `housekeeping_schedules`
--

CREATE TABLE `housekeeping_schedules` (
  `schedule_id` bigint(20) NOT NULL,
  `date` date DEFAULT NULL,
  `status` enum('COMPLETED','PENDING') DEFAULT NULL,
  `employee_id` bigint(20) NOT NULL,
  `room_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `housekeeping_schedules`
--

INSERT INTO `housekeeping_schedules` (`schedule_id`, `date`, `status`, `employee_id`, `room_id`) VALUES
(1, '2024-06-22', 'PENDING', 4, 5),
(2, '2024-06-22', 'PENDING', 5, 6),
(3, '2024-06-22', 'COMPLETED', 5, 3);

-- --------------------------------------------------------

--
-- Table structure for table `invoices`
--

CREATE TABLE `invoices` (
  `invoice_id` bigint(20) NOT NULL,
  `amount` decimal(38,2) DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `invoice_date` date DEFAULT NULL,
  `status` enum('PAID','UNPAID') DEFAULT NULL,
  `reservation_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `invoices`
--

INSERT INTO `invoices` (`invoice_id`, `amount`, `due_date`, `invoice_date`, `status`, `reservation_id`) VALUES
(1, 100.00, NULL, '2024-06-22', 'UNPAID', 1),
(2, 100.00, '2024-06-22', '2024-06-22', 'PAID', 2);

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE `reservations` (
  `reservation_id` bigint(20) NOT NULL,
  `booking_date` date DEFAULT NULL,
  `check_in_date` date DEFAULT NULL,
  `check_out_date` date DEFAULT NULL,
  `status` enum('CANCELLED','CONFIRMED') DEFAULT NULL,
  `total_price` decimal(38,2) DEFAULT NULL,
  `room_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`reservation_id`, `booking_date`, `check_in_date`, `check_out_date`, `status`, `total_price`, `room_id`, `user_id`) VALUES
(1, '2024-06-22', NULL, NULL, 'CONFIRMED', 50.00, 1, 2),
(2, '2024-06-22', NULL, NULL, 'CONFIRMED', 50.00, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

CREATE TABLE `rooms` (
  `room_id` bigint(20) NOT NULL,
  `capacity` int(11) DEFAULT NULL,
  `details` varchar(255) DEFAULT NULL,
  `facilities` varchar(255) DEFAULT NULL,
  `features` varchar(255) DEFAULT NULL,
  `last_maintenance_date` date DEFAULT NULL,
  `price` double DEFAULT NULL,
  `size` double DEFAULT NULL,
  `status` enum('AVAILABLE','BOOKED','MAINTENANCE') DEFAULT NULL,
  `hotel_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`room_id`, `capacity`, `details`, `facilities`, `features`, `last_maintenance_date`, `price`, `size`, `status`, `hotel_id`) VALUES
(1, 5, 'room1', 'room1', 'room1', NULL, 500, 30, 'BOOKED', 1),
(2, 5, 'room2', 'room2', 'room2', NULL, 500, 30, 'BOOKED', 1),
(3, 5, 'room3', 'room3', 'room3', NULL, 500, 30, 'AVAILABLE', 1),
(4, 5, 'room4', 'room4', 'room4', NULL, 500, 30, 'AVAILABLE', 2),
(5, 5, 'room5', 'room5', 'room5', NULL, 500, 30, 'AVAILABLE', 2),
(6, 5, 'room6', 'room6', 'room6', NULL, 500, 30, 'AVAILABLE', 2);

-- --------------------------------------------------------

--
-- Table structure for table `token`
--

CREATE TABLE `token` (
  `id` bigint(20) NOT NULL,
  `expired` bit(1) NOT NULL,
  `revoked` bit(1) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `token_type` enum('BEARER') DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `token`
--

INSERT INTO `token` (`id`, `expired`, `revoked`, `token`, `token_type`, `user_id`) VALUES
(1, b'1', b'1', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5YXphbkBnbWFpbC5jb20iLCJpYXQiOjE3MTkwMDM5NDgsImV4cCI6MTcxOTA5MDM0OH0.jJCEGs8k7m7S5ip93PMBQsjL9gZsu_uMfOFFyRfuEgU', 'BEARER', 1),
(2, b'1', b'1', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5YXphbkBnbWFpbC5jb20iLCJpYXQiOjE3MTkwMDM5ODEsImV4cCI6MTcxOTA5MDM4MX0.rhN8odscB1OJA6pRE_6_om3FpMyvu-QB1udTPoSXf7k', 'BEARER', 1),
(52, b'1', b'1', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5YXphbkBnbWFpbC5jb20iLCJpYXQiOjE3MTkwMDQ3MjAsImV4cCI6MTcxOTA5MTEyMH0.D30zZD2QhMdFuAYvp7lSXaUi53Fpc6FQ1W3h02HV9oA', 'BEARER', 1),
(102, b'1', b'1', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5YXphbkBnbWFpbC5jb20iLCJpYXQiOjE3MTkwMDQ4MjksImV4cCI6MTcxOTA5MTIyOX0.sK3uLOTW6GCtqL0_O76auYf7xZmHdSwnsUqb9l_XK50', 'BEARER', 1),
(152, b'1', b'1', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5YXphbkBnbWFpbC5jb20iLCJpYXQiOjE3MTkwMDUwNTAsImV4cCI6MTcxOTA5MTQ1MH0.2Mb4L-qQUD1DNSTYDsDgdoCfSfsRCzDjEJwxjO_RkSI', 'BEARER', 1),
(153, b'0', b'0', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhaGFtZCIsImlhdCI6MTcxOTAwNjE2OSwiZXhwIjoxNzE5MDkyNTY5fQ.Om-8tbyPmscFK6-VCXfZR7Zi-vjZFe5U3Bv5tj63SYc', 'BEARER', 2),
(154, b'0', b'0', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5YXphbkBnbWFpbC5jb20iLCJpYXQiOjE3MTkwMDY0MzYsImV4cCI6MTcxOTA5MjgzNn0.hM1q1bYhrSGaWh60dP3-nN_7NjcboTmySItu247qQjM', 'BEARER', 1);

-- --------------------------------------------------------

--
-- Table structure for table `token_seq`
--

CREATE TABLE `token_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `token_seq`
--

INSERT INTO `token_seq` (`next_val`) VALUES
(251);

-- --------------------------------------------------------

--
-- Table structure for table `_user`
--

CREATE TABLE `_user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','CUSTOMER') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `_user`
--

INSERT INTO `_user` (`id`, `email`, `firstname`, `lastname`, `password`, `role`) VALUES
(1, 'yazan@gmail.com', 'yazan', 'zidan', '$2a$10$WtluEYXeVtiynEIVy65FGuj4IsZr8.6VGO4t/pWZ2qVauZCWoGz8G', 'ADMIN'),
(2, 'ahamd', 'ahamd', 'osmam', '$2a$10$4Lmj.ccGaOemmnrJ.cebre3EleuIsEljWbUibRe8j1IP4348rxI2G', 'CUSTOMER');

-- --------------------------------------------------------

--
-- Table structure for table `_user_seq`
--

CREATE TABLE `_user_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `_user_seq`
--

INSERT INTO `_user_seq` (`next_val`) VALUES
(101);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`employee_id`),
  ADD KEY `FKtlrav5xw3qcevfbpq03r2jyxk` (`managed_by_admin_id`),
  ADD KEY `FKl6mewl85djruoxecubfwwn07u` (`hotel_id`);

--
-- Indexes for table `hotels`
--
ALTER TABLE `hotels`
  ADD PRIMARY KEY (`hotel_id`),
  ADD UNIQUE KEY `UKhya09rs530tjy5pmh5ij1yqtf` (`email`);

--
-- Indexes for table `housekeeping_schedules`
--
ALTER TABLE `housekeeping_schedules`
  ADD PRIMARY KEY (`schedule_id`),
  ADD KEY `FK18ql5aqsmiuvecfdr9dx8ux53` (`employee_id`),
  ADD KEY `FKl0mn6l7cwf37wx1r23ni8462a` (`room_id`);

--
-- Indexes for table `invoices`
--
ALTER TABLE `invoices`
  ADD PRIMARY KEY (`invoice_id`),
  ADD KEY `FKkga8j6hh54xf5hl3qiovlsa4l` (`reservation_id`);

--
-- Indexes for table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`reservation_id`),
  ADD KEY `FKljt6q1tp205b0h26eiegc5mx6` (`room_id`),
  ADD KEY `FKco5q2n6des5w1yufto66l1whe` (`user_id`);

--
-- Indexes for table `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`room_id`),
  ADD KEY `FKp5lufxy0ghq53ugm93hdc941k` (`hotel_id`);

--
-- Indexes for table `token`
--
ALTER TABLE `token`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKpddrhgwxnms2aceeku9s2ewy5` (`token`),
  ADD KEY `FKiblu4cjwvyntq3ugo31klp1c6` (`user_id`);

--
-- Indexes for table `_user`
--
ALTER TABLE `_user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `employee_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `hotels`
--
ALTER TABLE `hotels`
  MODIFY `hotel_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `housekeeping_schedules`
--
ALTER TABLE `housekeeping_schedules`
  MODIFY `schedule_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `invoices`
--
ALTER TABLE `invoices`
  MODIFY `invoice_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `reservations`
--
ALTER TABLE `reservations`
  MODIFY `reservation_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `rooms`
--
ALTER TABLE `rooms`
  MODIFY `room_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `employees`
--
ALTER TABLE `employees`
  ADD CONSTRAINT `FKl6mewl85djruoxecubfwwn07u` FOREIGN KEY (`hotel_id`) REFERENCES `hotels` (`hotel_id`),
  ADD CONSTRAINT `FKtlrav5xw3qcevfbpq03r2jyxk` FOREIGN KEY (`managed_by_admin_id`) REFERENCES `_user` (`id`);

--
-- Constraints for table `housekeeping_schedules`
--
ALTER TABLE `housekeeping_schedules`
  ADD CONSTRAINT `FK18ql5aqsmiuvecfdr9dx8ux53` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`),
  ADD CONSTRAINT `FKl0mn6l7cwf37wx1r23ni8462a` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_id`);

--
-- Constraints for table `invoices`
--
ALTER TABLE `invoices`
  ADD CONSTRAINT `FKkga8j6hh54xf5hl3qiovlsa4l` FOREIGN KEY (`reservation_id`) REFERENCES `reservations` (`reservation_id`);

--
-- Constraints for table `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `FKco5q2n6des5w1yufto66l1whe` FOREIGN KEY (`user_id`) REFERENCES `_user` (`id`),
  ADD CONSTRAINT `FKljt6q1tp205b0h26eiegc5mx6` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_id`);

--
-- Constraints for table `rooms`
--
ALTER TABLE `rooms`
  ADD CONSTRAINT `FKp5lufxy0ghq53ugm93hdc941k` FOREIGN KEY (`hotel_id`) REFERENCES `hotels` (`hotel_id`);

--
-- Constraints for table `token`
--
ALTER TABLE `token`
  ADD CONSTRAINT `FKiblu4cjwvyntq3ugo31klp1c6` FOREIGN KEY (`user_id`) REFERENCES `_user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
