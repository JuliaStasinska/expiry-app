drop table if exists devices;
create table devices(
    my_device_id int primary key auto_increment,
    description varchar(100) not null,
    buying_date date,
    time_to_change_part date,
    time_to_clean date,
    cleaned bit,
    part_changed bit,
    device_id int,
    foreign key (device_id) references device_templates (device_id)
);
drop table if exists food;
create table food(
    my_food_id int primary key auto_increment,
    description varchar(100) not null,
    expiry_date date,
    use_by date,
    storage_status varchar(100),
    usage_status varchar(100),
    food_id int,
    foreign key (food_id) references food_templates (food_id)
);
drop table if exists medicines;
create table medicines(
    my_medicine_id int primary key auto_increment,
    description varchar(100) not null,
    expiry_date date,
    use_before date,
    opened bit,
    status varchar(100),
    medicine_id int,
    foreign key (medicine_id) references medicine_templates (medicine_id)
);
drop table if exists subscriptions;
create table subscriptions(
    my_subscription_id int primary key auto_increment,
    description varchar(100) not null,
    renews_on date,
    status varchar(100),
    subscription_id int,
    foreign key (subscription_id) references subscription_templates (subscription_id)
);

