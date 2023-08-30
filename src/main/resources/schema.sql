create table users(
    username varchar(128) not null primary key,
    password varchar(512) not null,
    enabled boolean not null
);

create table authorities(
    username varchar(128) not null,
    authority varchar(128) not null
);

create unique index idx_auth_username on authorities (username,authority);

create table public.t_company(
    id bigint not null,
    company_code integer not null ,
    company_name varchar(255) not null ,
    address varchar(255) not null ,
    e_mail varchar(255) not null
);

create table public.t_product(
    id bigint not null,
    product_code integer not null ,
    product_name varchar(255) not null ,
    categories varchar(255) not null ,
    explanation varchar(255) not null ,
    price integer not null ,
    company_id bigint not null
);

create table public.t_customer(
    id bigint not null,
    tc varchar(11) not null ,
    customer_number integer not null,
    first_name varchar(255) not null ,
    last_name varchar(255) not null ,
    gender varchar(128) not null ,
    e_mail varchar(255) not null ,
);


alter table public.t_company add constraint public.constraint_1 primary key(id);
alter table public.t_product add constraint public.constraint_2 primary key(id);
alter table public.t_product add constraint public.constraint_3 foreign key(company_id) references public.t_company(id);
alter table public.t_customer add constraint public.constraint_11 primary key (id);

create sequence public.stajprojesi_sequence start with 100;

