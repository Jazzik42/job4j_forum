insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$6C3pxE7lqIhwpXTOuV8fp.4bWOYr2yUXVACztF/RhdTT8QVh.5bxe',
        (select id from authorities where authority = 'ROLE_ADMIN'));