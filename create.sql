create sequence hibernate_sequence start with 1 increment by 1;

    create table seminar (
       id_seminar bigint not null,
        date timestamp not null,
        description varchar(255) not null,
        location varchar(255) not null,
        title varchar(255) not null,
        author_id_user bigint not null,
        team_id_team bigint not null,
        seminars_id_team bigint,
        primary key (id_seminar)
    );

    create table seminar_optional_content_links (
       seminar_id_seminar bigint not null,
        optional_content_links varchar(255)
    );

    create table team (
       id_team bigint not null,
        name varchar(255) not null,
        primary key (id_team)
    );

    create table user (
       id_user bigint not null,
        first_name varchar(255) not null,
        mail varchar(255) not null,
        name varchar(255) not null,
        password varchar(255) not null,
        team_id_team bigint,
        members_id_team bigint,
        primary key (id_user)
    );

    create table user_roles (
       user_id_user bigint not null,
        roles varchar(255)
    );

    alter table user 
       add constraint UK_6sou31qus5dnws6dwfu61e71v unique (mail);

    alter table seminar 
       add constraint FK5cisgesppshstbigcyivk9iso 
       foreign key (author_id_user) 
       references user;

    alter table seminar 
       add constraint FKl9i3tl44m84m7tgo6lc3ypetw 
       foreign key (team_id_team) 
       references team;

    alter table seminar 
       add constraint FKo1glvwoewmemrc4bu2y6fg8r1 
       foreign key (seminars_id_team) 
       references team;

    alter table seminar_optional_content_links 
       add constraint FKcqbcxagoteixne8e8am3lfc3p 
       foreign key (seminar_id_seminar) 
       references seminar;

    alter table user 
       add constraint FK4uw64gxvk2cjxvkqygvdgn0sn 
       foreign key (team_id_team) 
       references team;

    alter table user 
       add constraint FKesdnyjp7d4hwd72f7t2lmu9r1 
       foreign key (members_id_team) 
       references team;

    alter table user_roles 
       add constraint FKcwoy89k2wleeoyebc7rfxqu08 
       foreign key (user_id_user) 
       references user;
create sequence hibernate_sequence start with 1 increment by 1;

    create table seminar (
       id_seminar bigint not null,
        date timestamp not null,
        description varchar(255) not null,
        location varchar(255) not null,
        title varchar(255) not null,
        author_id_user bigint not null,
        team_id_team bigint not null,
        seminars_id_team bigint,
        primary key (id_seminar)
    );

    create table seminar_optional_content_links (
       seminar_id_seminar bigint not null,
        optional_content_links varchar(255)
    );

    create table team (
       id_team bigint not null,
        name varchar(255) not null,
        primary key (id_team)
    );

    create table user (
       id_user bigint not null,
        first_name varchar(255) not null,
        mail varchar(255) not null,
        name varchar(255) not null,
        password varchar(255) not null,
        team_id_team bigint,
        members_id_team bigint,
        primary key (id_user)
    );

    create table user_roles (
       user_id_user bigint not null,
        roles varchar(255)
    );

    alter table user 
       add constraint UK_6sou31qus5dnws6dwfu61e71v unique (mail);

    alter table seminar 
       add constraint FK5cisgesppshstbigcyivk9iso 
       foreign key (author_id_user) 
       references user;

    alter table seminar 
       add constraint FKl9i3tl44m84m7tgo6lc3ypetw 
       foreign key (team_id_team) 
       references team;

    alter table seminar 
       add constraint FKo1glvwoewmemrc4bu2y6fg8r1 
       foreign key (seminars_id_team) 
       references team;

    alter table seminar_optional_content_links 
       add constraint FKcqbcxagoteixne8e8am3lfc3p 
       foreign key (seminar_id_seminar) 
       references seminar;

    alter table user 
       add constraint FK4uw64gxvk2cjxvkqygvdgn0sn 
       foreign key (team_id_team) 
       references team;

    alter table user 
       add constraint FKesdnyjp7d4hwd72f7t2lmu9r1 
       foreign key (members_id_team) 
       references team;

    alter table user_roles 
       add constraint FKcwoy89k2wleeoyebc7rfxqu08 
       foreign key (user_id_user) 
       references user;
create sequence hibernate_sequence start with 1 increment by 1;

    create table seminar (
       id_seminar bigint not null,
        date timestamp,
        description varchar(255),
        location varchar(255),
        title varchar(255),
        author_id_user bigint,
        team_id_team bigint,
        seminars_id_team bigint,
        primary key (id_seminar)
    );

    create table seminar_optional_content_links (
       seminar_id_seminar bigint not null,
        optional_content_links varchar(255)
    );

    create table team (
       id_team bigint not null,
        name varchar(255) not null,
        primary key (id_team)
    );

    create table user (
       id_user bigint not null,
        first_name varchar(255) not null,
        mail varchar(255) not null,
        name varchar(255) not null,
        password varchar(255) not null,
        team_id_team bigint,
        members_id_team bigint,
        primary key (id_user)
    );

    create table user_roles (
       user_id_user bigint not null,
        roles varchar(255)
    );

    alter table user 
       add constraint UK_6sou31qus5dnws6dwfu61e71v unique (mail);

    alter table seminar 
       add constraint FK5cisgesppshstbigcyivk9iso 
       foreign key (author_id_user) 
       references user;

    alter table seminar 
       add constraint FKl9i3tl44m84m7tgo6lc3ypetw 
       foreign key (team_id_team) 
       references team;

    alter table seminar 
       add constraint FKo1glvwoewmemrc4bu2y6fg8r1 
       foreign key (seminars_id_team) 
       references team;

    alter table seminar_optional_content_links 
       add constraint FKcqbcxagoteixne8e8am3lfc3p 
       foreign key (seminar_id_seminar) 
       references seminar;

    alter table user 
       add constraint FK4uw64gxvk2cjxvkqygvdgn0sn 
       foreign key (team_id_team) 
       references team;

    alter table user 
       add constraint FKesdnyjp7d4hwd72f7t2lmu9r1 
       foreign key (members_id_team) 
       references team;

    alter table user_roles 
       add constraint FKcwoy89k2wleeoyebc7rfxqu08 
       foreign key (user_id_user) 
       references user;
