-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 18, 2022 at 07:37 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `penjualan_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `kode` varchar(12) NOT NULL,
  `nama` varchar(40) NOT NULL,
  `satuan` enum('Kilo','Liter','Box') NOT NULL,
  `jumlah` int(11) NOT NULL,
  `harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`kode`, `nama`, `satuan`, `jumlah`, `harga`) VALUES
('KA01', 'Beras', 'Kilo', 2, 15000),
('KA02', 'Gula', 'Kilo', 1, 10000),
('KA03', 'Garam', 'Kilo', 5, 5000),
('KA04', 'Minyak', 'Liter', 5, 12000),
('KA05', 'Kopi', 'Kilo', 1, 20000),
('KA06', 'Jagung', 'Kilo', 1, 40000),
('KA07', 'Teh', 'Kilo', 1, 5),
('KA08', 'Gula merah', 'Kilo', 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `kode` varchar(20) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `alamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`kode`, `nama`, `email`, `alamat`) VALUES
('PA01', 'Fatkhur', 'Fatkhur@gmail.com', 'Pekalongan'),
('PA02', 'Rozin', 'Rozin@gmail.om', 'Peklaongan g');

-- --------------------------------------------------------

--
-- Table structure for table `penjualan`
--

CREATE TABLE `penjualan` (
  `kode_penjualan` varchar(20) NOT NULL,
  `tanggal_penjualan` datetime NOT NULL,
  `total_penjualan` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `penjualan`
--

INSERT INTO `penjualan` (`kode_penjualan`, `tanggal_penjualan`, `total_penjualan`) VALUES
('T001', '2022-01-01 09:07:54', 90000),
('T002', '2022-01-03 09:07:54', 15000);

-- --------------------------------------------------------

--
-- Table structure for table `penjualan_detail`
--

CREATE TABLE `penjualan_detail` (
  `kode_detail` int(11) NOT NULL,
  `kode_barang` varchar(12) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `harga` double NOT NULL,
  `subtotal` double NOT NULL,
  `kode_penjualan` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `penjualan_detail`
--

INSERT INTO `penjualan_detail` (`kode_detail`, `kode_barang`, `jumlah`, `harga`, `subtotal`, `kode_penjualan`) VALUES
(1, 'KA01', 2, 15000, 30000, 'T001'),
(2, 'KA04', 5, 12000, 60000, 'T001'),
(3, 'KA03', 3, 5000, 15000, 'T002');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `hak_akses` enum('kasir','pemilik') NOT NULL,
  `nama_lengkap` varchar(50) NOT NULL,
  `alamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `hak_akses`, `nama_lengkap`, `alamat`) VALUES
(1, 'kasir', '8cfab3d2724448440ea03b9cfa0194cb962a7723', 'kasir', 'Fatkhur', 'Doro, Pekalongan'),
(2, 'pemilik', '6245ec2883185a10f6a50b92dcd4db6aba0c1c1d', 'pemilik', 'Rozin', 'Doro, Pekalongan'),
(18, 'admin', 'F865B53623B121FD34EE5426C792E5C33AF8C227', 'pemilik', 'asda', 'asdasd');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`kode`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`kode`);

--
-- Indexes for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`kode_penjualan`);

--
-- Indexes for table `penjualan_detail`
--
ALTER TABLE `penjualan_detail`
  ADD PRIMARY KEY (`kode_detail`),
  ADD KEY `kode_barang` (`kode_barang`),
  ADD KEY `kode_penjualan` (`kode_penjualan`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `penjualan_detail`
--
ALTER TABLE `penjualan_detail`
  MODIFY `kode_detail` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `penjualan_detail`
--
ALTER TABLE `penjualan_detail`
  ADD CONSTRAINT `penjualan_detail_ibfk_1` FOREIGN KEY (`kode_barang`) REFERENCES `barang` (`kode`),
  ADD CONSTRAINT `penjualan_detail_ibfk_2` FOREIGN KEY (`kode_penjualan`) REFERENCES `penjualan` (`kode_penjualan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
