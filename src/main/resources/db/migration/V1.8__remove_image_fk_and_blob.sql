alter table `user`
    drop foreign key `fk_user_media`;

alter table `user`
    drop column `image_fk`;

alter table `user`
    add column `image` blob after `email_verified`;