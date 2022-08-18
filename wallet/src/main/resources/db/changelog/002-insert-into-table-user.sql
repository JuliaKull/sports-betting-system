-- liquibase formatted sql
-- changeset betpawa:liqubase run insert into table user

INSERT INTO user (email, password, balance)
VALUES ("user1@gmail.com", "112233", 100),
 ("user2@gmail.com", "112233", 200),
 ("user3@gmail.com", "112233", 300);
