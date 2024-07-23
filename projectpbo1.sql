-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 24 Bulan Mei 2024 pada 03.52
-- Versi server: 10.4.24-MariaDB
-- Versi PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projectpbo1`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `admin`
--

CREATE TABLE `admin` (
  `id_admin` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `PASSWORD` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `admin`
--

INSERT INTO `admin` (`id_admin`, `username`, `PASSWORD`) VALUES
(1, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Struktur dari tabel `baptis`
--

CREATE TABLE `baptis` (
  `id_baptis` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `nama_calon` varchar(255) NOT NULL,
  `jenis_kelamin` varchar(20) NOT NULL,
  `tanggal_lahir` varchar(100) NOT NULL,
  `nama_ayah` varchar(255) NOT NULL,
  `nama_ibu` varchar(255) NOT NULL,
  `gereja` varchar(255) NOT NULL,
  `status` varchar(50) NOT NULL DEFAULT 'Belum Dikonfirmasi'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `baptis`
--

INSERT INTO `baptis` (`id_baptis`, `user_id`, `nama_calon`, `jenis_kelamin`, `tanggal_lahir`, `nama_ayah`, `nama_ibu`, `gereja`, `status`) VALUES
(1, 1, 'ada', 'Laki Laki', 'porseaaa', 'sdasada', 'sadsa', 'GEREJA C', 'Ditolak'),
(2, 1, 'sada', 'Laki Laki', 'sadasada', 'sada', 'sada', 'HKBP PALMARUM TARUTUNG', 'Ditolak'),
(3, 1, 'asda', 'Perempuan', 'dsaa', 'sada', 'sda', 'HKBP PALMARUM TARUTUNG', 'Sudah Dikonfirmasi'),
(4, 1, 'sadaduatolu', 'Laki Laki', 'dsaad', 'sada', 'sadsa', 'HKBP PALMARUM TARUTUNG', 'Sudah Dikonfirmasi'),
(5, 1, 'asdada', 'Laki Laki', 'sada', 'sadsa', 'sadsa', 'HKBP PALMARUM TARUTUNG', 'Ditolak'),
(6, 1, 'asdaaa', 'Laki Laki', 'asda', 'sada', 'sada', 'HKBP PALMARUM TARUTUNG', 'Belum Dikonfirmasi'),
(8, 2, 'jeremy', 'Laki Laki', 'sada', 'asda', 'asdsa', 'HKBP PALMARUM TARUTUNG', 'Ditolak'),
(10, 1, 'oke gas', 'Laki Laki', '10-20-2024', 'yon', 'yan', 'HKBP PALMARUM TARUTUNG', 'Sudah Dikonfirmasi'),
(11, 1, 'adsada12313', 'Laki Laki', 'asdada', 'sada', 'dsdsdsds', 'HKBP PALMARUM TARUTUNG', 'Belum Dikonfirmasi'),
(12, 1, 'okegas', 'Laki Laki', 'gasgas', 'assaga', 'sada', 'GEREJA B', 'Belum Dikonfirmasi'),
(13, 1, 'renren', 'Laki Laki', '20 april 2024', 'oekoek', 'soakdao', 'GEREJA B', 'Belum Dikonfirmasi'),
(14, 1, 'dsa', 'Laki Laki', 'sdada', 'sadada', 'sada', 'HKBP PALMARUM TARUTUNG', 'Belum Dikonfirmasi'),
(15, 1, 'asdsa', 'Laki Laki', 'dsaasd', 'sada', 'sada', 'HKBP PALMARUM TARUTUNG', 'Belum Dikonfirmasi'),
(16, 1, 'sadasadada', 'Perempuan', 'sdasa', 'sada', 'sada', 'GEREJA B', 'Belum Dikonfirmasi'),
(17, 1, 'asdadadadsa', 'Laki Laki', '12313', 'okegas', '131', 'HKBP PALMARUM TARUTUNG', 'Belum Dikonfirmasi'),
(18, 1, '2131', 'Perempuan', 'sada', 'sada', 'sada', 'HKBP PALMARUM TARUTUNG', 'Belum Dikonfirmasi'),
(19, 1, 'marihot tambunan', 'Laki Laki', '20 februari 2004', 'rohit', 'rahat', 'HKBP PALMARUM TARUTUNG', 'Belum Dikonfirmasi');

-- --------------------------------------------------------

--
-- Struktur dari tabel `naiksidi`
--

CREATE TABLE `naiksidi` (
  `id_naiksidi` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `nama_calon` varchar(255) NOT NULL,
  `jenis_kelamin` varchar(20) NOT NULL,
  `tanggal_lahir` varchar(100) NOT NULL,
  `nama_ayah` varchar(255) NOT NULL,
  `nama_ibu` varchar(255) NOT NULL,
  `gereja` varchar(255) NOT NULL,
  `STATUS` varchar(50) NOT NULL DEFAULT 'Belum Dikonfirmasi'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `naiksidi`
--

INSERT INTO `naiksidi` (`id_naiksidi`, `user_id`, `nama_calon`, `jenis_kelamin`, `tanggal_lahir`, `nama_ayah`, `nama_ibu`, `gereja`, `STATUS`) VALUES
(1, 2, 'Johannes', 'Perempuan', '27-06-2006', 'payung', 'piyungaaa', 'HKBP PALMARUM TARUTUNG', 'Ditolak'),
(2, 1, 'Sion', 'Laki Laki', 'sadsada', 'sadsad', 'sasadsaa', 'HKBP PALMARUM TARUTUNG', 'Sudah Dikonfirmasi'),
(3, 2, 'sadasdsa', 'Laki Laki', 'sadsad', 'sadsa', 'sadas', 'HKBP PALMARUM TARUTUNG', 'Belum Dikonfirmasi'),
(4, 1, 'okeok', 'Perempuan', 'sad', 'asada', 'asdsa', 'HKBP PALMARUM TARUTUNG', 'Sudah Dikonfirmasi'),
(5, 1, 'sdaa', 'Perempuan', 'sada', 'sad', 'asadsa', 'HKBP PALMARUM TARUTUNG', 'Belum Dikonfirmasi'),
(6, 1, 'sada', 'Laki Laki', 'sada', 'sada', 'sadadsa', 'GEREJA B', 'Belum Dikonfirmasi'),
(7, 1, 'oke', 'Laki Laki', '1231', '515151', 'sda', 'GEREJA B', 'Belum Dikonfirmasi');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pernikahan`
--

CREATE TABLE `pernikahan` (
  `id_pernikahan` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `nama_Calonsuami` varchar(255) NOT NULL,
  `nama_Calonistri` varchar(255) NOT NULL,
  `tanggal_pernikahan` varchar(50) NOT NULL,
  `tempat_pernikahan` varchar(255) NOT NULL,
  `STATUS` varchar(50) NOT NULL DEFAULT 'Belum Dikonfirmasi'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `pernikahan`
--

INSERT INTO `pernikahan` (`id_pernikahan`, `user_id`, `nama_Calonsuami`, `nama_Calonistri`, `tanggal_pernikahan`, `tempat_pernikahan`, `STATUS`) VALUES
(2, 2, 'Hole', 'sadadadada', 'sadasd', 'HKBP PALMARUM TARUTUNG', 'Sudah Dikonfirmasi'),
(3, 1, 'sadas', 'asda', 'asdsa', 'HKBP PALMARUM TARUTUNG', 'Ditolak'),
(4, 2, 'dsadas', 'sada', 'sada', 'GEREJA B', 'Belum Dikonfirmasi'),
(5, 2, 'sadsa', 'soleha', 'porsea', 'GEREJA B', 'Belum Dikonfirmasi'),
(6, 2, 'sion', 'deby', 'sadadas', 'HKBP PALMARUM TARUTUNG', 'Sudah Dikonfirmasi'),
(7, 2, 'sada', 'sadasa', 'sadsa', 'HKBP PALMARUM TARUTUNG', 'Belum Dikonfirmasi'),
(8, 3, 'samuel aritonang', 'rivka', 'Sabtu 20-04-2030', 'HKBP PALMARUM TARUTUNG', 'Sudah Dikonfirmasi');

-- --------------------------------------------------------

--
-- Struktur dari tabel `sejarahgereja`
--

CREATE TABLE `sejarahgereja` (
  `id` int(11) NOT NULL,
  `deskripsi` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `sejarahgereja`
--

INSERT INTO `sejarahgereja` (`id`, `deskripsi`) VALUES
(1, 'Gereja ini didirikan pada tahun 1850 oleh Pendeta Johannes Wibowo. Awalnya, gereja ini hanya terdiri dari beberapa keluarga yang berkumpul di rumah-rumah warga. Seiring dengan pertumbuhan jemaat, pada tahun 1870 dibangunlah gedung gereja pertama.\nPada tahun 1920, gedung gereja lama ditingkatkan dan diperluas \nuntuk mengakomodasi jumlah jemaat yang semakin besar. Beberapa renovasi besar juga dilakukan pada tahun 1950 dan 1980 untuk menyesuaikan dengan perkembangan zaman.),\nGereja ini memiliki tradisi yang kuat dalam pelayanan sosial.\n Sejak awal berdirinya, gereja ini aktif terlibat dalam kegiatan-kegiatan amal dan pemberdayaan masyarakat sekitar.\n Banyak program bantuan dan pembinaan yang telah dijalankan oleh gereja ini.),\nDi bawah kepemimpinan Pendeta Suharto, gereja ini mengalami masa pertumbuhan yang pesat pada dekade 1990-an. \nJumlah jemaat meningkat drastis dan kegiatan-kegiatan gereja semakin beragam. Pada tahun 2000, \ngereja ini pun mendirikan sekolah Minggu dan lembaga pendidikan Kristen.),\nSaat ini, Gereja ini terus berupaya menjadi berkat bagi masyarakat. Selain kegiatan ibadah rutin, \ngereja ini juga aktifff terlibat dalam berbagai program pemberdayaan, advokasi, dan kepedulian sosial. \nGereja ini menjadi salah satu pusat pertumbuhan iman dan pelayanan Kristen di daerah ini.'),
(3, ''),
(5, 'asdadada baadvmkalvmlamlkmalkvva');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id_user`, `username`, `PASSWORD`, `email`) VALUES
(1, 'sada', 'sada', 'sada@gmail.com'),
(2, 'sada123', 'sada123', 'sdaa@gmail.com'),
(3, 'pange123', 'pange123', 'pange@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indeks untuk tabel `baptis`
--
ALTER TABLE `baptis`
  ADD PRIMARY KEY (`id_baptis`),
  ADD KEY `user_id` (`user_id`);

--
-- Indeks untuk tabel `naiksidi`
--
ALTER TABLE `naiksidi`
  ADD PRIMARY KEY (`id_naiksidi`),
  ADD KEY `user_id` (`user_id`);

--
-- Indeks untuk tabel `pernikahan`
--
ALTER TABLE `pernikahan`
  ADD PRIMARY KEY (`id_pernikahan`),
  ADD KEY `user_id` (`user_id`);

--
-- Indeks untuk tabel `sejarahgereja`
--
ALTER TABLE `sejarahgereja`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `admin`
--
ALTER TABLE `admin`
  MODIFY `id_admin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `baptis`
--
ALTER TABLE `baptis`
  MODIFY `id_baptis` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT untuk tabel `naiksidi`
--
ALTER TABLE `naiksidi`
  MODIFY `id_naiksidi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT untuk tabel `pernikahan`
--
ALTER TABLE `pernikahan`
  MODIFY `id_pernikahan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT untuk tabel `sejarahgereja`
--
ALTER TABLE `sejarahgereja`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `baptis`
--
ALTER TABLE `baptis`
  ADD CONSTRAINT `baptis_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id_user`);

--
-- Ketidakleluasaan untuk tabel `naiksidi`
--
ALTER TABLE `naiksidi`
  ADD CONSTRAINT `naiksidi_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id_user`);

--
-- Ketidakleluasaan untuk tabel `pernikahan`
--
ALTER TABLE `pernikahan`
  ADD CONSTRAINT `pernikahan_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
