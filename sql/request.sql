CREATE database gongdae;
use gongdae;

CREATE table request(
    id int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    title VARCHAR(255) NOT NULL,
    place VARCHAR(255) NOT NULL,
    vr_image_url MEDIUMTEXT NOT NULL
);

create table request_sub(
    id int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    request_id int(11) NOT NULL,
    description MEDIUMTEXT NOT NULL,
    sub_img_url MEDIUMTEXT NOT NULL,
    FOREIGN KEY (request_id) REFERENCES request(id) on update cascade
);