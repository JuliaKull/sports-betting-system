-- liquibase formatted sql
-- changeset betpawa:liqubase run create table user operation
create table IF NOT EXISTS user (
                        id  bigint primary key auto_increment,
                         email varchar(50) unique not null ,
                         password varchar(10) not null,
                        balance decimal,
                        check ( balance>=0 )
            );

create table IF NOT EXISTS operation(
                          id bigint primary key auto_increment,
                          user_id bigint not null,
                          operation varchar(20) not null,
                          amount decimal not null,
                          reference varchar(100),
                          foreign key (user_id)  references user (id) on DELETE cascade);







