insert into visor (uuid, listPrio, entityValue) values (uuid_generate_v4(), 1, 'Prof. Braun');
insert into visor (uuid, listPrio, entityValue) values (uuid_generate_v4(), 2, 'Prof. Neumann');
insert into visor (uuid, listPrio, entityValue) values (uuid_generate_v4(), 3, 'PD Schwenker');

insert into sgigroup (uuid, listPrio, entityValue) values (uuid_generate_v4(), 1, 'ni-wir');
insert into sgigroup (uuid, listPrio, entityValue) values (uuid_generate_v4(), 2, 'ni-vision');
insert into sgigroup (uuid, listPrio, entityValue) values (uuid_generate_v4(), 3, 'ni-extern');
insert into sgigroup (uuid, listPrio, entityValue) values (uuid_generate_v4(), 4, 'ni-student');

insert into elytronrole (id, uuid, rolename) values (1, uuid_generate_v4(), 'System');
insert into elytronrole (id, uuid, rolename) values (2, uuid_generate_v4(), 'Poweruser');
insert into elytronrole (id, uuid, rolename) values (3, uuid_generate_v4(), 'Administrator');
insert into elytronrole (id, uuid, rolename) values (4, uuid_generate_v4(), 'User');
insert into elytronrole (id, uuid, rolename) values (5, uuid_generate_v4(), 'Guest');

insert into elytronuser (uuid, username, comment, defaultLanguage, defaultTheme, elytronrole_id) values (uuid_generate_v4(), 'N-U-T',  'New-User-Template',  'english', 'Default', 3);
insert into elytronuser (uuid, username, comment, defaultLanguage, defaultTheme, elytronrole_id) values (uuid_generate_v4(), 'Admin1', 'First User', 'german', 'Default', 1);
insert into elytronuser (uuid, username, comment, defaultLanguage, defaultTheme, elytronrole_id) values (uuid_generate_v4(), 'Admin2', 'Second User', 'english', 'Medjugorje', 3);

insert into dbaccount (uuid, username, password) values (uuid_generate_v4(), 'Admin1', '$2a$10$Al3jAZHUr/uEBKER3D0MnO/gjJn3OtfOjWihKjyf8jNIsuPyYchcm');
insert into dbaccount (uuid, username, password) values (uuid_generate_v4(), 'Admin2', '$2a$10$lL/TPkN701P4KsV3LeBUOO/kquf3O/euqM1.bS6XnF0V.TBadYaVK');
insert into dbaccount (uuid, username, password) values (uuid_generate_v4(), 'Admin3', '$2a$10$.25Z2HJMNBBeADfKSJHqaOwCDBFNQMRyr9nGL//ZjCtq0AUu0dmNe');
insert into dbaccount (uuid, username, password) values (uuid_generate_v4(), 'Admin4', '$2a$10$RGKIm3pG/dzL.yqeUy0iauEaQTRmARHMbSq9A8PrrJFK9AMJnug0y');

insert into student (uuid, firstname, lastname, sgigroup_id, visor_id) values (uuid_generate_v4(), 'Adrian', 'Steinert', 4, 2);
insert into student (uuid, firstname, lastname, sgigroup_id, visor_id) values (uuid_generate_v4(), 'Natalie', 'Schromm', 4, 2);
insert into student (uuid, firstname, lastname, sgigroup_id, visor_id) values (uuid_generate_v4(), 'Johannes', 'Birk', 4, 2);
