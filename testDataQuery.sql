drop table if exists books;
drop table if exists authors;
drop table if exists genres;
drop table if exists publishers;

create table books(
	id serial primary key,
	title varchar(255),
	description varchar(255)
);

create table authors(
	id serial primary key,	
	name varchar(255),
	nationality varchar(255),
	age integer,
	book_id integer references books
);

create table genres(
	id serial primary key,	
	name varchar(255),
	book_id integer references books
);


create table publishers(
	id serial primary key,	
	name varchar(255),
	location varchar(255),
	book_id integer references books
);

INSERT INTO books (title, description) VALUES ('Book One', 'Book One Description');
INSERT INTO books (title, description) VALUES ('Book Two', 'Book Two Description');
INSERT INTO authors (name, nationality, age, book_id) VALUES ('Marc', 'USA', 32, 1);
INSERT INTO genres (name, book_id) VALUES ('Fiction', 1);
INSERT INTO publishers (name, location, book_id) VALUES ('PublisherOne', 'Chicago', 1);
INSERT INTO authors (name, nationality, age, book_id) VALUES ('Suresh', 'USA', 32, 2);

