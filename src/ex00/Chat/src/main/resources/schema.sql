create table users (
  id serial primary key,
  login varchar(255) unique,
  password varchar(255)
);

create table chat (
  id serial primary key,
  name VARCHAR(255),
  owner_id int,
  foreign key(owner_id) references users(id)
);

create table messages (
  id serial primary key,
  author_id int,
  chat_id int,
  text VARCHAR(255),
  date DATE,
  foreign key(author_id) references users(id),
foreign key(chat_id) references chat(id));