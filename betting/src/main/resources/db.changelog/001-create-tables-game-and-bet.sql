-- liquibase formatted sql
-- changeset betpawa:liqubase run create table game and bet
create table IF NOT EXISTS game (
    id  bigint primary key auto_increment,
    city varchar(50) not null ,
    date timestamp not null ,
    team_one varchar(50) not null,
    odds_team_one int not null ,
    team_two varchar(50) not null,
    odds_team_two int not null);



create table IF NOT EXISTS bet (
        id  bigint primary key auto_increment,
        game_id bigint not null ,
        user_id  bigint not null,
        bet decimal not null,
        status varchar(50) not null,
        foreign key (game_id)  references game (id)
);

