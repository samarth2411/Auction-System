-- the first script for migration
-- create table Article (
--     id bigint generated by default as identity,
--     content varchar(5000),
--     postedAt timestamp,
--     title varchar(255),
--     primary key (id)
-- );

-- create table Article_authorIds (
--     Article_id bigint not null,
--     authorIds bigint
-- );

create table Bidder(
    id bigint not null primary key,
    userId varchar(200) not null,
    currentBid bigint not null,
    productId bigint not null
);

create table Product(
    id bigint not null primary key,
    name varchar(200) not null,
    description varchar(200) not null,
    winner varchar(200),
    owner varchar(200) not null,
    inAuction boolean not null,
    startTime bigint not null,
    endTime bigint not null,
    price bigint not null,
    isActive boolean not null
);

create table AppUser(
        name varchar(200) not null ,
        email varchar(200) not null primary key ,
        password varchar(200) not null
);
--
CREATE SEQUENCE hibernate_sequence START 1;
    
-- alter table Article_authorIds
-- add constraint FK_f9ivk719aqb0rqd8my08loev7
-- foreign key (Article_id)
-- references Article;

alter table Bidder
add constraint sam1
foreign key (userId)
references AppUser;

alter table Bidder
add constraint sam2
foreign key (productId)
references Product;

alter table Product
add constraint sam3
foreign key (owner)
references AppUser;

alter table Product
add constraint sam4
foreign key (winner)
references AppUser;