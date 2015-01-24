CREATE DATABASE Twittsire;

USE Twittsire;

CREATE table Users(
	idUser INTEGER(10) NOT NULL AUTO_INCREMENT,
	fullName VARCHAR(100) NOT NULL,
	username VARCHAR(25) NOT NULL UNIQUE,
	mail VARCHAR(100) NOT NULL UNIQUE,
	pwd VARCHAR(100) NOT NULL,

	PRIMARY KEY (idUser) 
);

INSERT INTO users(idUser, fullName, username, mail, pwd)
			VALUES(1, "Gastly Haunter Gengar",  "Ghost", "ghg@gmail.com", "pokemons");

SELECT * FROM users WHERE username="Ghost";