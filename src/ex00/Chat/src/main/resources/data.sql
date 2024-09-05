insert into users (login, password)
values
('user0', '1'),
('user1', '1'),
('user2', '1'),
('user3', '1'),
('user4', '1');

insert into chat (name, owner_id)
values
('Java', 1),
('C', 2),
('C++', 3),
('Python', 4),
('Go', 5);

insert into messages (author_id, chat_id, text, date)
values
(1, 1, 'Привет, Java Man', '2024-07-17'),
(2, 2, 'Привет, C Man', '2024-07-17'),
(3, 3, 'Привет, C++ Man', '2024-07-17'),
(4, 4, 'Привет, Python Man', '2024-07-17'),
(5, 5, 'Привет, Go Man', '2024-07-17');
