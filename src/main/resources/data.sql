INSERT INTO roles (role_id,name) VALUES (1,'ROLE_USER');
INSERT INTO roles (role_id,name) VALUES (2,'ROLE_ADMIN');
INSERT INTO users (user_id, email, enabled, fullname, password, username) VALUES (1, 'xyz@gmail.com', 1, 'XYZ', 'password', 'user');
INSERT INTO users_roles (user_id, role_id) VALUES (1,1);
INSERT INTO films (id, category, title, year) VALUES (1, "COMEDY", "MADAGASKAR", 2005);
INSERT INTO films (id, category, title, year) VALUES (2, "DRAMA", "THE NOTEBOOK", 2004);
INSERT INTO films (id, category, title, year) VALUES (3, "ACTION", "AQUAMAN", 2018);
INSERT INTO films (id, category, title, year) VALUES (4, "COMEDY", "SHREK", 2001);
INSERT INTO films (id, category, title, year) VALUES (5, "FANTASY", "HARRY POTTER AND THE PHILOSOPHER'S STONE", 2001);
