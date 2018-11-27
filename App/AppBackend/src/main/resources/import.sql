insert into visor (uuid, listPrio, mdvalue) values (uuid_generate_v4(), 1, 'Prof. Braun');
insert into visor (uuid, listPrio, mdvalue) values (uuid_generate_v4(), 2, 'Prof. Neumann');
insert into visor (uuid, listPrio, mdvalue) values (uuid_generate_v4(), 3, 'PD Schwenker');

insert into elytronrole (id, uuid, rolename) values (1, uuid_generate_v4(), 'System');
insert into elytronrole (id, uuid, rolename) values (2, uuid_generate_v4(), 'Poweruser');
insert into elytronrole (id, uuid, rolename) values (3, uuid_generate_v4(), 'Administrator');
insert into elytronrole (id, uuid, rolename) values (4, uuid_generate_v4(), 'User');
insert into elytronrole (id, uuid, rolename) values (5, uuid_generate_v4(), 'Guest');

insert into elytronuser (uuid, username, comment, defaultLanguage, defaultTheme, elytronrole_id) values (uuid_generate_v4(), 'N-U-T',  'New-User-Template',  'english', 'Default', 3);
insert into elytronuser (uuid, username, comment, defaultLanguage, defaultTheme, elytronrole_id) values (uuid_generate_v4(), 'Admin1', 'First User', 'german', 'Default', 1);
insert into elytronuser (uuid, username, comment, defaultLanguage, defaultTheme, elytronrole_id) values (uuid_generate_v4(), 'Admin2', 'Second User', 'english', 'Medjugorje', 3);
