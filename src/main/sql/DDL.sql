
create table roles
(
    id          bigserial
        primary key,
    description varchar(255),
    title       varchar(255) not null
);

create table users
(
    id                    bigint       not null
        primary key,
    created_by            varchar(255) not null,
    created_when          timestamp(6) not null,
    deleted_by            varchar(255),
    deleted_when          timestamp(6),
    is_deleted            boolean,
    updated_by            varchar(255),
    updated_when          timestamp(6),

    change_password_token varchar(255),
    email                 varchar(255) not null,
    first_name            varchar(255) not null,
    last_name             varchar(255) not null,
    login                 varchar(255) not null,
    password              varchar(255) not null,
    role_id               bigint       not null
        constraint fk_users_roles
            references roles
);

create table films
(
    id               bigint       not null
        primary key,
    created_by       varchar(255) not null,
    created_when     timestamp(6) not null,
    deleted_by       varchar(255),
    deleted_when     timestamp(6),
    is_deleted       boolean,
    updated_by       varchar(255),
    updated_when     timestamp(6),
    country       smallint     not null,
    description      varchar(255) not null,
    genre          smallint     not null,
    duration int;
    poster_file_name varchar(255),
    release_year     smallint     not null,
    title            varchar(255) not null,

);

create table film_creators
(
    id           bigint       not null
        primary key,
    created_by   varchar(255) not null,
    created_when timestamp(6) not null,
    deleted_by   varchar(255),
    deleted_when timestamp(6),
    is_deleted   boolean,
    updated_by   varchar(255),
    updated_when timestamp(6),
    full_name    varchar(255) not null
);

create table films_film_creators
(
    film_id         bigint not null
        constraint fk_films_film_creators
            references films,
    film_creator_id bigint not null
        constraint fk_film_creators_films
            references film_creators,
    primary key (film_id, film_creator_id)
);

create table film_sessions
(
    id           bigint           not null
        primary key,
    created_by   varchar(255)     not null,
    created_when timestamp(6)     not null,
    deleted_by   varchar(255),
    deleted_when timestamp(6),
    is_deleted   boolean,
    updated_by   varchar(255),
    updated_when timestamp(6),
    price        double precision not null,
    start_date   date             not null,
    start_time   time             not null,
    film_id      bigint           not null
        constraint fk_filmsessions_films
            references films,
     hall_id      bigint       not null,
        constraint fk_film_session_hall
            foreign key (hall_id) references halls (id)
);

create table seats
(
    id           bigint       not null
        primary key,
    created_by   varchar(255) not null,
    created_when timestamp(6) not null,
    deleted_by   varchar(255),
    deleted_when timestamp(6),
    is_deleted   boolean,
    updated_by   varchar(255),
    updated_when timestamp(6),
    price        double precision default=1,
    place        smallint     not null,
    row          smallint     not null,
    hall_id      bigint       not null,
    constraint fk_seats_hall
        foreign key (hall_id) references halls (id)
);

create table orders
(
    id              bigint           not null
        primary key,
    created_by      varchar(255)     not null,
    created_when    timestamp(6)     not null,
    deleted_by      varchar(255),
    deleted_when    timestamp(6),
    is_deleted      boolean,
    updated_by      varchar(255),
    updated_when    timestamp(6),
    cost            double precision not null,
    purchase        boolean          not null,
    film_session_id bigint           not null
        constraint fk_orders_filmsessions
            references film_sessions,
    user_id         bigint           not null
        constraint fk_orders_users
            references users
);

create table orders_seats
(
    order_id bigint not null
        constraint fk_orders_seats
            references orders,
    seat_id  bigint not null
        constraint fk_seats_orders
            references seats,
    primary key (order_id, seat_id)
);

create table reviews
(
    id           bigint       not null
        primary key,
    created_by   varchar(255) not null,
    created_when timestamp(6) not null,
    deleted_by   varchar(255),
    deleted_when timestamp(6),
    is_deleted   boolean,
    updated_by   varchar(255),
    updated_when timestamp(6),
    content      varchar(255) not null,
    film_id      bigint       not null
        constraint fk_reviews_films
            references films,
    user_id      bigint       not null
        constraint fk_reviews_users
            references users
);

create table countries (
    id           bigint       not null
        primary key,
    created_by   varchar(255) not null,
    created_when timestamp(6) not null,
    deleted_by   varchar(255),
    deleted_when timestamp(6),
    is_deleted   boolean,
    updated_by   varchar(255),
    updated_when timestamp(6),
   country_name    varchar(255) not null
);

create table genres (
    id           bigint       not null
            primary key,
        created_by   varchar(255) not null,
        created_when timestamp(6) not null,
        deleted_by   varchar(255),
        deleted_when timestamp(6),
        is_deleted   boolean,
        updated_by   varchar(255),
        updated_when timestamp(6),
        genre_name    varchar(255) not null
);

create table halls (
 id           bigint       not null
            primary key,
        created_by   varchar(255) not null,
        created_when timestamp(6) not null,
        deleted_by   varchar(255),
        deleted_when timestamp(6),
        is_deleted   boolean,
        updated_by   varchar(255),
        updated_when timestamp(6),
        count_row smallint not null,
        count_place smallint not null,
        hall_type varchar(20) not null
);