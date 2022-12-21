create table `email_verification_request`
(
    `id`                 int primary key auto_increment,
    `email`              varchar(255) not null,
    `token`              varchar(255) not null,
    `is_verified`        tinyint      not null default 0,
    `valid_until`        timestamp    not null,

    -- audit
    `created_date`       timestamp   default current_timestamp(),
    `last_modified_by`   varchar(32) default 'system',
    `last_modified_date` timestamp   default current_timestamp(),
    `record_status`      int         default 1
);

create unique index `email_verification_request_email_uindex` on `email_verification_request` (`email`);

alter table user
    add column `email_verified` tinyint not null default 0 after `display_name`;