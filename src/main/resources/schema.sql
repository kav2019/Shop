-- drop table ingredient;

-- create table Ingredient(
--     id varchar(100) unique ,
--     name varchar(100) ,
--     type varchar(100)
-- );

-- create table Burger(
--     id int PRIMARY KEY generated by default as identity,
--     name varchar(100) ,
--     createdAt timestamp
-- );

-- create table Burger_Ingredient(
--     burger_id int references burger(id),
--     ingredient_id varchar(100) references ingredient(id)
-- );

-- create table Order_Burger(
--     id int PRIMARY KEY generated by default as identity,
--     deliveryName varchar(50) not null,
--     deliveryStreet varchar(50) not null,
--     deliveryCity varchar(50) not null,
--     deliveryState varchar(50) not null,
--     deliveryZip varchar(10) not null,
--     ccNumber varchar(16) not null,
--     ccExpiration varchar(5) not null,
--     ccCVV varchar(3) not null,
--     placedAt timestamp not null
-- );

-- create table Burger_Order_Burger(
--     burger_id int references burger(id),
--     order_burger_id int references order_burger(id)
-- );

