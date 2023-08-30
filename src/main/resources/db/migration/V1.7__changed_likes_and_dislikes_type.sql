alter table `post`
    drop column `likes`;

alter table `post`
    add column `likes` int null after `date_posted`;

alter table `post`
    drop column `dislikes`;

alter table `post`
    add column `dislikes` int null after `likes`;
