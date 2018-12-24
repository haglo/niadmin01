insert into neuro.visor (uuidEntry, listPrio, entityValue) values (uuid(), 1, 'Prof. Braun');
insert into neuro.visor (uuidEntry, listPrio, entityValue) values (uuid(), 2, 'Prof. Neumann');
insert into neuro.visor (uuidEntry, listPrio, entityValue) values (uuid(), 3, 'PD Schwenker');

insert into neuro.room (uuidEntry, listPrio, entityValue) values (uuid(), 1, 'Robi-Lab');
insert into neuro.room (uuidEntry, listPrio, entityValue) values (uuid(), 2, 'Studentenraum 425');

insert into neuro.sgigroup (uuidEntry, listPrio, entityValue) values (uuid(), 1, 'ni-wir');
insert into neuro.sgigroup (uuidEntry, listPrio, entityValue) values (uuid(), 2, 'ni-vision');
insert into neuro.sgigroup (uuidEntry, listPrio, entityValue) values (uuid(), 3, 'ni-ext');
insert into neuro.sgigroup (uuidEntry, listPrio, entityValue) values (uuid(), 4, 'ni-stud');

insert into neuro.elytronrole (id, uuidEntry, rolename) values (1, uuid(), 'System');
insert into neuro.elytronrole (id, uuidEntry, rolename) values (2, uuid(), 'Poweruser');
insert into neuro.elytronrole (id, uuidEntry, rolename) values (3, uuid(), 'Administrator');
insert into neuro.elytronrole (id, uuidEntry, rolename) values (4, uuid(), 'User');
insert into neuro.elytronrole (id, uuidEntry, rolename) values (5, uuid(), 'Guest');

insert into neuro.elytronuser (uuidEntry, username, comment, defaultLanguage, defaultTheme, elytronrole_id) values (uuid(), 'N-U-T',  'New-User-Template',  'english', 'Default', 3);
insert into neuro.elytronuser (uuidEntry, username, comment, defaultLanguage, defaultTheme, elytronrole_id) values (uuid(), 'Admin1', 'First User', 'german', 'Default', 1);
insert into neuro.elytronuser (uuidEntry, username, comment, defaultLanguage, defaultTheme, elytronrole_id) values (uuid(), 'Admin2', 'Second User', 'english', 'Medjugorje', 3);

insert into neuro.dbaccount (uuidEntry, username, password) values (uuid(), 'Admin1', '$2a$10$Al3jAZHUr/uEBKER3D0MnO/gjJn3OtfOjWihKjyf8jNIsuPyYchcm');
insert into neuro.dbaccount (uuidEntry, username, password) values (uuid(), 'Admin2', '$2a$10$lL/TPkN701P4KsV3LeBUOO/kquf3O/euqM1.bS6XnF0V.TBadYaVK');
insert into neuro.dbaccount (uuidEntry, username, password) values (uuid(), 'Admin3', '$2a$10$.25Z2HJMNBBeADfKSJHqaOwCDBFNQMRyr9nGL//ZjCtq0AUu0dmNe');
insert into neuro.dbaccount (uuidEntry, username, password) values (uuid(), 'Admin4', '$2a$10$RGKIm3pG/dzL.yqeUy0iauEaQTRmARHMbSq9A8PrrJFK9AMJnug0y');

insert into neuro.student (uuidEntry, firstname, lastname, sgigroup_id, visor_id) values (uuid(), 'Adrian', 'Steinert', 4, 2);
insert into neuro.student (uuidEntry, firstname, lastname, sgigroup_id, visor_id) values (uuid(), 'Natalie', 'Schromm', 4, 2);
insert into neuro.student (uuidEntry, firstname, lastname, sgigroup_id, visor_id) values (uuid(), 'Johannes', 'Birk', 4, 2);

insert into neuro.desk (uuidEntry, deskNumber, room_id) values (uuid(), 'Tisch 1a',  1);
insert into neuro.desk (uuidEntry, deskNumber, room_id) values (uuid(), 'Tisch 1b',  1);

insert into neuro.person (uuidEntry, firstname, lastname,elytronuser_id) values (uuid(), 'Johann Georg', 'Glöckler', 4);
