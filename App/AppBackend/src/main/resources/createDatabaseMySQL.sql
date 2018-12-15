CREATE DATABASE neuro CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
SET NAMES utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'neuro'@'localhost' IDENTIFIED BY '123atgfd';
GRANT ALL ON neuro.* TO 'neuro'@'localhost' IDENTIFIED BY '123atgfd' WITH GRANT OPTION;

ALTER DATABASE neuro CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

ALTER TABLE neuro.Student CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;