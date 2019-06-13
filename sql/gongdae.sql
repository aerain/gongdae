CREATE database gongdae;
use gongdae;

CREATE table member(
    id int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    email MEDIUMTEXT NOT NULL,
    password MEDIUMTEXT NOT NULL,
    name VARCHAR(20) NOT NULL
);

create table company(
    id int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    email MEDIUMTEXT NOT NULL,
    password MEDIUMTEXT NOT NULL,
    company_name varchar(255) NOT NULL,
    description MEDIUMTEXT NOT NULL
);
CREATE table request(
    id int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    user_id int(11) NOT NULL,
    title VARCHAR(255) NOT NULL,
    place VARCHAR(255) NOT NULL,
    vr_image_url MEDIUMTEXT NOT NULL,
    sold TINYINT NOT NULL DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES user(id) on update cascade
);

create table request_sub(
    id int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    request_id int(11) NOT NULL,
    description MEDIUMTEXT NOT NULL,
#     sub_img_url MEDIUMTEXT NOT NULL,
    FOREIGN KEY (request_id) REFERENCES request(id) on update cascade
);

create table reverse_auction(
    id int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    company_id int(11) NOT NULL,
    request_id int(11) NOT NULL,
    FOREIGN KEY (request_id) REFERENCES request(id) on update cascade,
    FOREIGN KEY (company_id) references company(id) on update cascade
);

create table estimate_list(
    id int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    reverse_auction_id int(11) NOT NULL,
    name varchar(255) NOT NULL,
    price int(11) NOT NULL,
    redirect_uri MEDIUMTEXT,
    FOREIGN KEY (reverse_auction_id) references reverse_auction(id) on update cascade
);

create table review(
    id int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    company_id int(11) NOT NULL,
    user_id int(11) NOT NULL,
    description MEDIUMTEXT NOT NULL,
    score int(1) NOT NULL,
    FOREIGN KEY (company_id) references company(id) on update cascade,
    FOREIGN KEY (user_id) references user(id) on update cascade
);