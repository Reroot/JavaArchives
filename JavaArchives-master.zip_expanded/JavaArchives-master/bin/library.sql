-- Adminer 4.7.5 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP TABLE IF EXISTS `tbl_author`;
CREATE TABLE `tbl_author` (
  `authorId` int(11) NOT NULL AUTO_INCREMENT,
  `authorName` varchar(45) NOT NULL,
  PRIMARY KEY (`authorId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `tbl_author` (`authorId`, `authorName`) VALUES
(1,	'Bord Filpo'),
(2,	'Finn Kinnane'),
(3,	'Darb Sweetlove');

DROP TABLE IF EXISTS `tbl_book`;
CREATE TABLE `tbl_book` (
  `bookId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `pubId` int(11) DEFAULT NULL,
  PRIMARY KEY (`bookId`),
  KEY `fk_publisher` (`pubId`),
  CONSTRAINT `fk_publisher` FOREIGN KEY (`pubId`) REFERENCES `tbl_publisher` (`publisherId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `tbl_book` (`bookId`, `title`, `pubId`) VALUES
(1,	'Second Jungle Book: Mowgli & Baloo, The',	2),
(2,	'Camille Claudel 1915',	2),
(3,	'Drowning Pool, The',	2),
(4,	'Swimfan',	1),
(5,	'Dark Star',	1);

DROP TABLE IF EXISTS `tbl_book_authors`;
CREATE TABLE `tbl_book_authors` (
  `bookId` int(11) NOT NULL,
  `authorId` int(11) NOT NULL,
  PRIMARY KEY (`bookId`,`authorId`),
  KEY `fk_tbl_book_authors_tbl_author1_idx` (`authorId`),
  CONSTRAINT `fk_tbl_book_authors_tbl_author1` FOREIGN KEY (`authorId`) REFERENCES `tbl_author` (`authorId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_tbl_book_authors_tbl_book1` FOREIGN KEY (`bookId`) REFERENCES `tbl_book` (`bookId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `tbl_book_authors` (`bookId`, `authorId`) VALUES
(1,	2),
(2,	1),
(3,	1),
(3,	2),
(5,	1);

DROP TABLE IF EXISTS `tbl_book_copies`;
CREATE TABLE `tbl_book_copies` (
  `bookId` int(11) NOT NULL,
  `branchId` int(11) NOT NULL,
  `noOfCopies` int(11) DEFAULT NULL,
  PRIMARY KEY (`bookId`,`branchId`),
  KEY `fk_bc_book` (`bookId`),
  KEY `fk_bc_branch` (`branchId`),
  CONSTRAINT `fk_bc_book` FOREIGN KEY (`bookId`) REFERENCES `tbl_book` (`bookId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_bc_branch` FOREIGN KEY (`branchId`) REFERENCES `tbl_library_branch` (`branchId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `tbl_book_copies` (`bookId`, `branchId`, `noOfCopies`) VALUES
(1,	1,	7),
(2,	2,	2),
(2,	3,	27),
(3,	1,	30),
(3,	2,	12),
(3,	3,	30),
(4,	3,	28),
(5,	1,	20),
(5,	2,	18),
(5,	3,	30);

DROP TABLE IF EXISTS `tbl_book_genres`;
CREATE TABLE `tbl_book_genres` (
  `genre_id` int(11) NOT NULL,
  `bookId` int(11) NOT NULL,
  PRIMARY KEY (`genre_id`,`bookId`),
  KEY `fk_tbl_book_genres_tbl_book1_idx` (`bookId`),
  CONSTRAINT `fk_tbl_book_genres_tbl_book1` FOREIGN KEY (`bookId`) REFERENCES `tbl_book` (`bookId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_tbl_book_genres_tbl_genre1` FOREIGN KEY (`genre_id`) REFERENCES `tbl_genre` (`genre_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `tbl_book_genres` (`genre_id`, `bookId`) VALUES
(1,	2),
(1,	4),
(3,	2),
(4,	1),
(4,	2);

DROP TABLE IF EXISTS `tbl_book_loans`;
CREATE TABLE `tbl_book_loans` (
  `bookId` int(11) NOT NULL,
  `branchId` int(11) NOT NULL,
  `cardNo` int(11) NOT NULL,
  `dateOut` datetime DEFAULT NULL,
  `dueDate` datetime DEFAULT NULL,
  `dateIn` datetime DEFAULT NULL,
  PRIMARY KEY (`bookId`,`branchId`,`cardNo`),
  KEY `fk_bl_book` (`bookId`),
  KEY `fk_bl_branch` (`branchId`),
  KEY `fk_bl_borrower` (`cardNo`),
  CONSTRAINT `fk_bl_book` FOREIGN KEY (`bookId`) REFERENCES `tbl_book` (`bookId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_bl_borrower` FOREIGN KEY (`cardNo`) REFERENCES `tbl_borrower` (`cardNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_bl_branch` FOREIGN KEY (`branchId`) REFERENCES `tbl_library_branch` (`branchId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `tbl_book_loans` (`bookId`, `branchId`, `cardNo`, `dateOut`, `dueDate`, `dateIn`) VALUES
(1,	1,	1,	'0000-00-00 00:00:00',	'0000-00-00 00:00:00',	'0000-00-00 00:00:00'),
(1,	1,	3,	'0000-00-00 00:00:00',	'0000-00-00 00:00:00',	'0000-00-00 00:00:00'),
(1,	3,	3,	'0000-00-00 00:00:00',	'0000-00-00 00:00:00',	'0000-00-00 00:00:00'),
(2,	1,	1,	'0000-00-00 00:00:00',	'0000-00-00 00:00:00',	'0000-00-00 00:00:00'),
(2,	1,	4,	'0000-00-00 00:00:00',	'0000-00-00 00:00:00',	'0000-00-00 00:00:00'),
(2,	2,	3,	'0000-00-00 00:00:00',	'0000-00-00 00:00:00',	'0000-00-00 00:00:00'),
(3,	1,	1,	'0000-00-00 00:00:00',	'0000-00-00 00:00:00',	'0000-00-00 00:00:00'),
(3,	2,	2,	'0000-00-00 00:00:00',	'0000-00-00 00:00:00',	'0000-00-00 00:00:00'),
(3,	3,	2,	'0000-00-00 00:00:00',	'0000-00-00 00:00:00',	'0000-00-00 00:00:00'),
(3,	3,	3,	'0000-00-00 00:00:00',	'0000-00-00 00:00:00',	'0000-00-00 00:00:00'),
(4,	1,	2,	'0000-00-00 00:00:00',	'0000-00-00 00:00:00',	'0000-00-00 00:00:00'),
(4,	2,	4,	'0000-00-00 00:00:00',	'0000-00-00 00:00:00',	'0000-00-00 00:00:00'),
(5,	2,	4,	'0000-00-00 00:00:00',	'0000-00-00 00:00:00',	'0000-00-00 00:00:00'),
(5,	3,	1,	'0000-00-00 00:00:00',	'0000-00-00 00:00:00',	'0000-00-00 00:00:00'),
(5,	3,	3,	'0000-00-00 00:00:00',	'0000-00-00 00:00:00',	'0000-00-00 00:00:00');

DROP TABLE IF EXISTS `tbl_borrower`;
CREATE TABLE `tbl_borrower` (
  `cardNo` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cardNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `tbl_borrower` (`cardNo`, `name`, `address`, `phone`) VALUES
(1,	'Oralla Reinert',	'2881 Magdeline Point',	'172-529-4475'),
(2,	'Leighton Phippen',	'15507 Morning Way',	'560-886-9482'),
(3,	'Quinta Dunguy',	'9 Delladonna Plaza',	'916-678-3929'),
(4,	'Ashla Nutting',	'85958 Eastwood Trail',	'584-941-6789');

DROP TABLE IF EXISTS `tbl_genre`;
CREATE TABLE `tbl_genre` (
  `genre_id` int(11) NOT NULL AUTO_INCREMENT,
  `genre_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`genre_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `tbl_genre` (`genre_id`, `genre_name`) VALUES
(1,	'Comedy|Crime|Thriller'),
(2,	'Crime|Drama|Horror|Musical'),
(3,	'Drama'),
(4,	'Crime|Mystery|Thriller');

DROP TABLE IF EXISTS `tbl_library_branch`;
CREATE TABLE `tbl_library_branch` (
  `branchId` int(11) NOT NULL AUTO_INCREMENT,
  `branchName` varchar(45) DEFAULT NULL,
  `branchAddress` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`branchId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `tbl_library_branch` (`branchId`, `branchName`, `branchAddress`) VALUES
(1,	'Domeniga',	'4 Chive Avenue'),
(2,	'Sayer',	'99327 Banding Way'),
(3,	'Mareah',	'47 Darwin Place');

DROP TABLE IF EXISTS `tbl_publisher`;
CREATE TABLE `tbl_publisher` (
  `publisherId` int(11) NOT NULL AUTO_INCREMENT,
  `publisherName` varchar(45) NOT NULL,
  `publisherAddress` varchar(45) DEFAULT NULL,
  `publisherPhone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`publisherId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `tbl_publisher` (`publisherId`, `publisherName`, `publisherAddress`, `publisherPhone`) VALUES
(1,	'Ronnica Clayworth',	'97 Pankratz Point',	'148-650-2942'),
(2,	'Freida Dumingos',	'468 Di Loreto Center',	'370-269-1503');

-- 2019-12-18 23:19:59
