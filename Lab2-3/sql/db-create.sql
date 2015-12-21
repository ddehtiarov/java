CONNECT 'jdbc:derby://localhost:1527/db/bookingDB;create=true;user=root;password=root';

drop table Booking;
drop table Seat;
drop table Trip;
drop table Voyage;
drop table Station_route;
drop table Route;
drop table Benefit;
drop table Train;
drop table Station;
drop table Client;
drop table Role;

CREATE TABLE Role(
	id BIGINT NOT NULL PRIMARY KEY,
	name VARCHAR(15) NOT NULL UNIQUE
);

CREATE TABLE Client(
	id BIGINT NOT NULL generated always AS identity PRIMARY KEY,
	login VARCHAR(25) NOT NULL UNIQUE,
	password VARCHAR(32) NOT NULL,
	email VARCHAR(35) NOT NULL,
	firstname VARCHAR(25) NOT NULL,
	surname VARCHAR(25) NOT NULL,
	secondname VARCHAR(25) NOT NULL,
	role_id BIGINT NOT NULL REFERENCES Role(id) 
		ON DELETE CASCADE 
		ON UPDATE RESTRICT
);

DISCONNECT;