create table if not exists users (id serial primary key,
                                  first_name varchar(100),
                                  second_name varchar(100),
                                  patronymic varchar(100),
                                  email varchar
                                  );