create table articles(
    id bigserial not null constraint articles_pkey primary key,
    article_id varchar(250) not null constraint articles_id_key unique,
    title varchar(250),
    description text,
    thumbnail varchar(250),
    author varchar(50),
    published_date date,
    status bool,
    category_id smallint
                     constraint articles_category_id_fkey
                     references categories
                     on update cascade
);
alter table articles
    owner to postgres;

create table categories(
    id smallserial not null constraint categories_pkey primary key,
    name varchar(150) not null,
    status boolean default true
);
alter table categories owner to postgres;
create table users(
    id bigserial not null constraint users_pkey primary key unique,
    user_id varchar(50) unique,
    first_name varchar(15),
    last_name varchar(15),
    email varchar(25),
    password varchar(50),
    status boolean default true
);
alter table users owner to postgres;