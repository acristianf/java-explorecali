-- DROP TABLE tour_rating, tour, tour_package, security_user, security_role, user_role;
CREATE TABLE tour_package
(
    code  char(2) PRIMARY KEY,
    title varchar(50) NOT NULL
);

CREATE TABLE tour
(
    id                bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    title             varchar(100)  NOT NULL,
    description       varchar(2000) NOT NULL,
    blurb             varchar(2000) NOT NULL,
    price             varchar(10)   NOT NULL,
    duration          varchar(32)   NOT NULL,
    difficulty        varchar(16)   NOT NULL,
    region            varchar(20)   NOT NULL,
    bullets           varchar(2000) NOT NULL,
    keywords          varchar(100),
    tour_package_code char(2)       NOT NULL REFERENCES tour_package (code)
);

CREATE TABLE tour_rating
(
    id          bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    tour_id     bigint REFERENCES tour (id),
    customer_id bigint,
    rating      int,
    comment     varchar(100),
    CONSTRAINT unique_tour_customer_id_rating UNIQUE (tour_id, customer_id)
);

CREATE TABLE security_user
(
    id         bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    username   varchar(255) NOT NULL,
    password   varchar(255) NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name  varchar(255) NOT NULL
);

CREATE TABLE security_role
(
    id          bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    role_name   varchar(255) NOT NULL,
    description varchar(255) NOT NULL
);

CREATE TABLE user_role
(
    user_id bigint REFERENCES security_user (id),
    role_id bigint REFERENCES security_role (id),
    PRIMARY KEY (user_id, role_id)
);