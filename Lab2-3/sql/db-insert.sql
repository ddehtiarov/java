CONNECT 'jdbc:derby://localhost:1527/db/rzgi;create=true;user=root;password=root';

INSERT INTO Role VALUES(0, 'admin');
INSERT INTO Role VALUES(1, 'user');

INSERT INTO Client VALUES(DEFAULT, 'admin', '21232F297A57A5A743894A0E4A801FC3', 'admin@gmail.com', 'admin', 'admin', 'admin', 0);
INSERT INTO Client VALUES(DEFAULT, 'client', '21232F297A57A5A743894A0E4A801FC3', 'client@gmail.com', 'client', 'client', 'client', 1);