drop table if exists device_templates;
create table device_templates(
    device_id int primary key auto_increment,
    name varchar(100) not null,
    days_between_cleanings int,
    days_between_part_exchange int
);
drop table if exists food_templates;
create table food_templates(
    food_id int primary key auto_increment,
    name varchar(100) not null,
    days_stored_room_temperature int,
    days_stored_in_fridge int,
    days_stored_in_freezer int
);
drop table if exists medicine_templates;
create table medicine_templates(
    medicine_id int primary key auto_increment,
    name varchar(100) not null,
    days_stored int
);
drop table if exists subscription_templates;
create table subscription_templates(
    subscription_id int primary key auto_increment,
    name varchar(100) not null,
    days_trial_period int,
    days_between_renewals int,
    max_days_on_hold int
);

