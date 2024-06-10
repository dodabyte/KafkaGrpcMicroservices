create table if not exists orders (id serial primary key,
                                   user_id bigint,
                                   product_name varchar(100),
                                   count int
                                  );