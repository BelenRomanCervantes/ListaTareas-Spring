INSERT INTO user (name,lastname,birthdate,email,username,password) VALUES ('luis','marquez','1992-07-13','lmarquez@correo.com','lmarquez','$2a$10$FMTwIvapmAkIxsvRiPFSa.alSH8JZP..Sga761gP.RO8FIqMiYC.m');
INSERT INTO user (name,lastname,birthdate,email,username,password) VALUES ('maria','ramirez','1995-03-27','mar1995@correo.com','mary95','$2a$10$6ZBuk/IwvjFQKpJ5Uo8Fr.6Bc.oO72dXR4yZcdTVirqLfbGc5e5xa');

INSERT INTO task (description,dateline,tag,status,user_id) VALUES ('lavar la ropa','2024-04-06','hogar','completada',1);
INSERT INTO task (description,dateline,tag,status,user_id) VALUES ('lavar los trastes','2024-04-07','hogar','completada',2);
INSERT INTO task (description,dateline,tag,status,user_id) VALUES ('terminar examen tecnico de Concredito','2024-04-09','laboral','completada',1);
INSERT INTO task (description,dateline,tag,status,user_id) VALUES ('investigar sobre el web scraping','2024-04-08','estudios','pendiente',1);