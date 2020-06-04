INSERT INTO roles (role_id,name) VALUES (1,'ROLE_USER');
INSERT INTO roles (role_id,name) VALUES (2,'ROLE_ADMIN');
INSERT INTO users (user_id, email, fullname, password, username) VALUES (1, 'xyz@gmail.com', 'XYZ', 'password', 'user');
INSERT INTO users_roles (user_id, role_id) VALUES (1,1);
