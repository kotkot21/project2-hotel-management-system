-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 20, 2024 at 11:54 PM
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
(2, NULL, 'HOUSEKEEPER', 500, '2024-01-01', 'ACTIVE', 1, 1),
(3, NULL, 'MANAGER', 500, '2023-01-01', 'ACTIVE', 1, 1);

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
(1, '1234567890', 'A great place to stay.', 'hotel@example.com', 'City Center', 'Hotel 1', 5.00);

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
(1, '2024-01-01', 'COMPLETED', 1, 1),
(2, '2023-01-01', 'COMPLETED', 1, 1),
(3, '2023-01-01', 'PENDING', 2, 1);

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
(1, 50.00, '2023-02-01', '2023-01-01', 'PAID', 1);

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE `reservations` (
  `reservation_id` bigint(20) NOT NULL,
  `booking_date` date DEFAULT NULL,
  `check_in_date` date DEFAULT NULL,
  `check_out_date` date DEFAULT NULL,
  `status` enum('CANCELLED','CHECKED_IN','CHECKED_OUT','CONFIRMED') DEFAULT NULL,
  `total_price` decimal(38,2) DEFAULT NULL,
  `room_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`reservation_id`, `booking_date`, `check_in_date`, `check_out_date`, `status`, `total_price`, `room_id`, `user_id`) VALUES
(1, '2023-01-01', '2023-01-01', NULL, 'CONFIRMED', 300.00, 1, 2),
(2, '2023-01-01', '2023-01-01', NULL, 'CONFIRMED', 300.00, 2, 2);

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
(1, 5, 'room1', 'goof', 'god', '2023-06-01', 50, 5, 'AVAILABLE', 1),
(2, 5, 'room2', 'good2', 'good2', '2023-07-01', 50, 5, 'AVAILABLE', 1),
(3, 2, 'room3', 'good3', 'good3', '2023-07-01', 100, 20, 'MAINTENANCE', 1);

-- --------------------------------------------------------

--
-- Table structure for table `token`
--

CREATE TABLE `token` (
  `id` int(11) NOT NULL,
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
(202, b'1', b'1', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5YXphbkBnbWFpbC5jb20iLCJpYXQiOjE3MTg5MTM5MDksImV4cCI6MTcxOTAwMDMwOX0.7kzt9aZGoV-noXJrbxU51IZuXUqT-BXVwVHk8pqbicQ', 'BEARER', 1),
(252, b'1', b'1', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5YXphbkBnbWFpbC5jb20iLCJpYXQiOjE3MTg5MTQxMTYsImV4cCI6MTcxOTAwMDUxNn0.2ie54MU0Aa4OpQ0pO60Xm4f4efEB1heUA2tmCdl00jk', 'BEARER', 1),
(302, b'1', b'1', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5YXphbkBnbWFpbC5jb20iLCJpYXQiOjE3MTg5MTQzOTQsImV4cCI6MTcxOTAwMDc5NH0.GRyfVP-GPmX1vk2B7bN8ucOfYRqbtIZjUIrW7_VF6oE', 'BEARER', 1),
(352, b'1', b'1', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhaG1hZEBnbWFpbC5jb20iLCJpYXQiOjE3MTg5MTQ3NzEsImV4cCI6MTcxOTAwMTE3MX0.aHCLdELeBP6QXAVi2ozpK2mmzQkGK62gOhltTy9k4Ns', 'BEARER', 2),
(402, b'1', b'1', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhaG1hZEBnbWFpbC5jb20iLCJpYXQiOjE3MTg5MTQ5OTksImV4cCI6MTcxOTAwMTM5OX0.iFlueonJ6AggRshpl0KgYoYahFt-KE0TGnxP9W_SGl8', 'BEARER', 2),
(452, b'1', b'1', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ6aWRhbkBnbWFpbC5jb20iLCJpYXQiOjE3MTg5MTUyOTAsImV4cCI6MTcxOTAwMTY5MH0.aAQ-ifGsiZQYTlAJv8xidZ2VAfpG1O5xSZ93SDjywGA', 'BEARER', 1),
(502, b'0', b'0', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJvc2FtYUBnbWFpbC5jb20iLCJpYXQiOjE3MTg5MTg1NjgsImV4cCI6MTcxOTAwNDk2OH0.Fv_gUNrESzPHlpPfUJ2H1RufedvLc3I1oKvj4xUMV_M', 'BEARER', 2),
(503, b'1', b'1', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ6aWRhbkBnbWFpbC5jb20iLCJpYXQiOjE3MTg5MTkxNjIsImV4cCI6MTcxOTAwNTU2Mn0.8jFI_UuYJgWD1Mc2n63oJCbTQON49-m12QtlpoyFhZY', 'BEARER', 1),
(504, b'0', b'0', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ6aWRhbkBnbWFpbC5jb20iLCJpYXQiOjE3MTg5MTkzNTQsImV4cCI6MTcxOTAwNTc1NH0.Gn4iG0-eA9cw44mWdGsLhwrgNVeOeUcB-vHJhjdvjCI', 'BEARER', 1);

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
(601);

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
(1, 'zidan@gmail.com', 'zidan', 'yazan', '$2a$10$imPqxG4ThHkulOGgDJTuGOrGegsJNIDNkfxS.kuv2ftr1M6//bj3e', 'ADMIN'),
(2, 'osama@gmail.com', 'osama', 'ahamd', '$2a$10$f.gs0kanOzbzOmocU6eFdezZwmHrPIZ1UWGDEYzM0jcZdJnDHzoNq', 'CUSTOMER');

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
  MODIFY `employee_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `hotels`
--
ALTER TABLE `hotels`
  MODIFY `hotel_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `housekeeping_schedules`
--
ALTER TABLE `housekeeping_schedules`
  MODIFY `schedule_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `invoices`
--
ALTER TABLE `invoices`
  MODIFY `invoice_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `reservations`
--
ALTER TABLE `reservations`
  MODIFY `reservation_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `rooms`
--
ALTER TABLE `rooms`
  MODIFY `room_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

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
