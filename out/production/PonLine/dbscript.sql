create table category (
  id serial not null primary key,
  c  text
);
create table tag (
  id serial not null primary key,
  t  text
);
create table users (
  id          serial             not null primary key,
  admin       boolean            not null,
  name        text               not null,
  login       varchar(30) unique not null,
  describtion text,
  passw       text               not null
);
create table topic (
  id   serial not null primary key,
  name text,
  link text
);
create table news (
  id        serial      not null primary key,
  author_id integer references users (id),
  topic_id  integer references topic (id),
  header    text        not null,
  text      text        not null,
  accepted  boolean     not null,
  date      varchar(30) not null
);
create table category_to_news (
  id          serial                           not null primary key,
  category_id integer references category (id) not null,
  news_id     integer references news (id)     not null
);
create table tags_to_news (
  id      serial                       not null primary key,
  tag_id  integer references tag (id)  not null,
  news_id integer references news (id) not null
);
create table file (
  id      serial not null primary key,
  news_id integer references news (id)
);
create table comment (
  id        serial                        not null primary key,
  topic_id  integer references topic (id) not null,
  sender_id integer references users (id) not null,
  date      text                          not null,
  text      text
);
create table theme (
  id         serial not null primary key,
  topic_id   integer references topic (id),
  creator_id integer references users (id),
  name       text   not null,
  date       text
);