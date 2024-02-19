CREATE DATABASE  IF NOT EXISTS `user`;
USE `user`;

DROP TABLE IF EXISTS `user_login`;
DROP TABLE IF EXISTS `book_list`;
DROP TABLE IF EXISTS `cart_list`;

CREATE TABLE `user_login` (
	`FirstName` varchar(45) NOT NULL ,
	`LastName` varchar(45) NOT NULL,
    `Email` varchar(45) NOT null,
	`UserName` varchar(45) NOT null,
	`Pass` varchar(45) NOT NULL
);

CREATE TABLE `book_list` (
	`No` varchar(15) NOT NULL,
	`BookName` varchar(45) NOT NULL ,
	`Author` varchar(45) NOT NULL,
	`Price` int NOT NULL
);

CREATE TABLE `cart_list` (
	`No` int auto_increment,
	`bookNo` varchar(15) NOT NULL,
	`bookName` varchar(45) ,
	`Quantity` varchar(45) ,
	`Price` varchar(45) ,
    primary key(NO)
); 

insert into user.book_list(No,BookName,Author,Price)
values('B01','Spy x Family','Tatsuya Endo','85');
insert into user.book_list(No,BookName,Author,Price)
values('B02','Jujutsu','Gege Akutami','72');
insert into user.book_list(No,BookName,Author,Price)
values('B03','KAIJYU','Matsumoto, Naoya','85');
insert into user.book_list(No,BookName,Author,Price)
values('B04','Chainsaw Man','Fujimoto, Tatsuki','313');
insert into user.book_list(No,BookName,Author,Price)
values('B05','Star Wars','Scott','501');
insert into user.book_list(No,BookName,Author,Price)
values('B06','Who Is Maud Dixon','Andrews, Alexandra','877');
insert into user.book_list(No,BookName,Author,Price)
values('B07','The Great Mental Models','Parrish, Shane','598');
insert into user.book_list(No,BookName,Author,Price)
values('B08','MD Notebook Journal','ドット方眼','394');
insert into user.book_list(No,BookName,Author,Price)
values('B09','HOMO DEUS','HARARI, YUVAL NOAH','371');
insert into user.book_list(No,BookName,Author,Price)
values('B10','HOW TO WALK','HANH & DEANTONIS','296');
insert into user.book_list(No,BookName,Author,Price)
values('B11','LOVE HYPOTHESIS','HAZELWOOD, ALI','340');
insert into user.book_list(No,BookName,Author,Price)
values('B03','ME BEFORE YOU','MOYES, JOJO','260');
insert into user.book_list(No,BookName,Author,Price)
values('B12','ROYAL SUCCESSION','DRUON, MAURICE','395');
insert into user.book_list(No,BookName,Author,Price)
values('B13','LLEWELLYNS CLASSIC TAROT','LLEWELLYN','998');
insert into user.book_list(No,BookName,Author,Price)
values('B14','IT ENDS WITH US','HOOVER, COLLEEN','332');

