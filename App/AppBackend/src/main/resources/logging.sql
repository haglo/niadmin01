CREATE TABLE pilgerdb.log_table(
	id integer NOT NULL PRIMARY key auto_increment,
	timestamp VARCHAR(255),
	log_level VARCHAR(255),
	class VARCHAR(255),
	message VARCHAR(3000)
);
