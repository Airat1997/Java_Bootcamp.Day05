insert into users (id, login, password)
values
(0, 'user0', '1'),
(1, 'user1', '1'),
(2, 'user2', '1'),
(3, 'user3', '1'),
(4, 'user4', '1');

insert into chat (id, name, owner_id)
values
(0, 'Java', 0),
(1, 'C', 1),
(2, 'C++', 2),
(3, 'Python', 3),
(4, 'Go', 4);

insert into messages (id, author_id, chat_id, text, date)
values
(0, 0, 0, 'Привет, Java Man', '2024-07-17'),
(1, 1, 1, 'Привет, C Man', '2024-07-17'),
(2, 2, 2, 'Привет, C++ Man', '2024-07-17'),
(3, 3, 3, 'Привет, Python Man', '2024-07-17'),
(4, 4, 4, 'Привет, Go Man', '2024-07-17');
