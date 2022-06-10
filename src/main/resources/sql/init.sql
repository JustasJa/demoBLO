
 create table demoblo.posts (
        id bigint not null,
         body TEXT not null,
         creation_date timestamp not null,
         title varchar(255) not null,
         primary key (id)
     );

         create table demoblo.comments (
        id bigint not null,
         body TEXT not null,
         creation_date timestamp not null,
         post_id bigint not null,
         primary key (id)
     );