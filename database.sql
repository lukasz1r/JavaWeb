-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Maj 29, 2023 at 10:39 AM
-- Wersja serwera: 10.4.28-MariaDB
-- Wersja PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `database`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `notes`
--

CREATE TABLE `notes` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `note` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `notes`
--

INSERT INTO `notes` (`id`, `user_id`, `title`, `note`, `date`) VALUES
(1, 3, 'E-commerce', 'E-commerce to dziedzina handlu, która zyskuje na popularności wraz z rozwojem technologii internetowych. Oznacza to, że zakupy są dokonywane online, a produkty są dostarczane bezpośrednio do klienta. W e-commerce ważne są między innymi strony internetowe ', '2023-05-29'),
(2, 3, 'Zdrowie psychiczne', 'Zdrowie psychiczne jest niezwykle ważne dla naszego ogólnego samopoczucia i dobrego funkcjonowania w życiu. Istnieją różne sposoby dbania o zdrowie psychiczne, takie jak medytacja, regularna aktywność fizyczna, rozmowy z bliskimi i specjalistami oraz zdro', '2023-05-28'),
(3, 1, 'Zrównoważony rozwój', 'Zrównoważony rozwój to koncepcja, która zakłada zaspokajanie potrzeb współczesnego społeczeństwa, nie szkodząc przy tym środowisku naturalnemu i zapewniając równowagę między rozwojem a ochroną zasobów naturalnych. Aby zrealizować zrównoważony rozwój, nale', '2023-05-27');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `role`) VALUES
(1, 'adam', 'admin1', 'ADMIN'),
(2, 'lukasz', 'admin2', 'ADMIN'),
(3, 'dawid', 'admin3', 'ADMIN'),
(4, 'zuza', 'admin4', 'ADMIN'),
(5, 'tomasz', 'kuczynski', 'USER'),
(6, 'jola', 'jolajna', 'USER');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `notes`
--
ALTER TABLE `notes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `notes`
--
ALTER TABLE `notes`
  ADD CONSTRAINT `FKnw6x7gk699fs3ngfo1j7gbfon` FOREIGN KEY (`id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
