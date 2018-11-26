insert into niadmin.account (uuid, username, password) values (uuid(), 'Admin1', '$2a$10$Al3jAZHUr/uEBKER3D0MnO/gjJn3OtfOjWihKjyf8jNIsuPyYchcm');
insert into niadmin.account (uuid, username, password) values (uuid(), 'Admin2', '$2a$10$lL/TPkN701P4KsV3LeBUOO/kquf3O/euqM1.bS6XnF0V.TBadYaVK');
insert into niadmin.account (uuid, username, password) values (uuid(), 'Admin3', '$2a$10$.25Z2HJMNBBeADfKSJHqaOwCDBFNQMRyr9nGL//ZjCtq0AUu0dmNe');
insert into niadmin.account (uuid, username, password) values (uuid(), 'Admin4', '$2a$10$RGKIm3pG/dzL.yqeUy0iauEaQTRmARHMbSq9A8PrrJFK9AMJnug0y');

insert into niadmin.elytronrole (id, uuid, rolename) values (1,  uuid(), 'System');
insert into niadmin.elytronrole (id, uuid, rolename) values (2,  uuid(), 'Poweruser');
insert into niadmin.elytronrole (id, uuid, rolename) values (3,  uuid(), 'Administrator');
insert into niadmin.elytronrole (id, uuid, rolename) values (4,  uuid(), 'User');
insert into niadmin.elytronrole (id, uuid, rolename) values (5,  uuid(), 'Guest');

insert into niadmin.elytronuser (uuid, username, password, comment, defaultLanguage, defaultTheme, elytron_role_id) values (uuid(), 'N-U-T',  'secret01', 'New-User-Template',  'english', 'Default', 3);
insert into niadmin.elytronuser (uuid, username, password, comment, defaultLanguage, defaultTheme, elytron_role_id) values (uuid(), 'Admin1', 'secret01', 'First User', 'german', 'Default', 1);
insert into niadmin.elytronuser (uuid, username, password, comment, defaultLanguage, defaultTheme, elytron_role_id) values (uuid(), 'Admin2', 'secret01', 'Second User', 'english', 'Medjugorje', 3);

insert into niadmin.visor (uuid, listPrio, mdvalue) values (uuid(), 1, 'Prof. Braun');
insert into niadmin.visor (uuid, listPrio, mdvalue) values (uuid(), 2, 'Prof. Neumann');
insert into niadmin.visor (uuid, listPrio, mdvalue) values (uuid(), 3, 'PD Schwenker');
