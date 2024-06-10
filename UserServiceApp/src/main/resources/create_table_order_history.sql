create table if not exists order_history(id serial primary key,
                                       user_id bigint not null,
                                       order_id bigint not null,
                                       foreign key (user_id) references users (id)
                                      );