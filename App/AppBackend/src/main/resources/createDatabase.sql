CREATE DATABASE niadmin CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'neurouser'@'localhost' IDENTIFIED BY '123atgfd';
GRANT ALL ON pilgerdb.* TO 'neurouser'@'localhost' IDENTIFIED BY '123atgfd' WITH GRANT OPTION;