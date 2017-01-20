 
    INSERT INTO ROLE values (1,'ROLE_ADMIN');
    INSERT INTO ROLE values (2,'ROLE_MODERATOR');
    INSERT INTO ROLE values (3,'ROLE_USER');

    alter table BOX 
        drop constraint FK_fr7q00oxl5cq8u94fbt5y2lst;
 
    alter table CONFIGURATION 
        drop constraint FK_mw62k7ffxlbanuhhbjpbwn67j;
 
    alter table FLOW_HISTORIC 
        drop constraint FK_7oc3pks50ewpr54772scr0d71;
 
    alter table FLOW_HISTORIC 
        drop constraint FK_nmfcokhwtt0en0abc45wduuvm;
 
    alter table FLOW_HISTORIC 
        drop constraint FK_cykq1fk7c9bv10stfd3liwhqb;
 
    alter table REVIEW 
        drop constraint FK_6odhi774r46e3qhdd5dn7ak5r;
 
    alter table REVIEW 
        drop constraint FK_gmrqblirgirficengg87oy08h;
 
    alter table SUGGESTED_TRANSLATION 
        drop constraint FK_qdp44wipqpmwwuyiey0yr8ej1;
 
    alter table SUGGESTED_TRANSLATION 
        drop constraint FK_glf6fy0w948k5gyr4sujg5a5p;
 
    alter table USER_LIST 
        drop constraint FK_mmb4ixgmcuumiv0c54ypu0v3r;
 
    alter table USER_LIST 
        drop constraint FK_66vki67mvyal0yqpgmyl1etkh;
 
    alter table USER_LIST_USER_WORD 
        drop constraint FK_aa4octtv73notypd4vara2nqk;
 
    alter table USER_LIST_USER_WORD 
        drop constraint FK_nwpgdvt0yr4sy22jxtce6ujc6;
 
    alter table USER_LIST_WORD 
        drop constraint FK_htt6ern4ysg4q17vgaoi85gcp;
 
    alter table USER_LIST_WORD 
        drop constraint FK_1o143ioxcminqct3isc9bscbv;
 
    alter table USER_ROLE 
        drop constraint FK_sj8ws1egxrycclrhj8ce8rwb0;
 
    alter table USER_ROLE 
        drop constraint FK_26t62t6pyq5asbi1ogc9fyqlc;
 
    alter table USER_WORD 
        drop constraint FK_hw19f6hgeor1ji6ijl9byymtk;
 
    alter table USER_WORD 
        drop constraint FK_dgqtree62be2g6o2gdgdcgv8t;
 
    alter table WORD 
        drop constraint FK_ra01h61wiw94dfj8vs7x72nb1;
 
    drop table if exists BOX cascade;
 
    drop table if exists CONFIGURATION cascade;
 
    drop table if exists FLOW_HISTORIC cascade;
 
    drop table if exists IDIOM cascade;
 
    drop table if exists REVIEW cascade;
 
    drop table if exists ROLE cascade;
 
    drop table if exists SUGGESTED_TRANSLATION cascade;
 
    drop table if exists USERS cascade;
 
    drop table if exists USER_LIST cascade;
 
    drop table if exists USER_LIST_USER_WORD cascade;
 
    drop table if exists USER_LIST_WORD cascade;
 
    drop table if exists USER_ROLE cascade;
 
    drop table if exists USER_WORD cascade;
 
    drop table if exists WORD cascade;
 
    drop sequence SEQ_BOX_CD_BOX;
 
    drop sequence SEQ_CONF_CD_CONF;
 
    drop sequence SEQ_FLHI_CD_FLHI;
 
    drop sequence SEQ_IDIO_CD_IDIOM;
 
    drop sequence SEQ_ROLE_CD_ROLE;
 
    drop sequence SEQ_USER_CD_USER;
 
    drop sequence SEQ_USLI_CD_USER_LIST;
 
    drop sequence SEQ_USWO_CD_USER_WORD;
 
    drop sequence SEQ_WORD_CD_WORD;
 
    create table BOX (
        BOX_CD_BOX int8 not null,
        BOX_DT_DATE_REGISTER timestamp not null,
        BOX_NR_DAYS int4 not null,
        BOX_TX_NAME varchar(255) not null,
        BOX_NR_ORDER int4 not null,
        USER_CD_USER int8 not null,
        primary key (BOX_CD_BOX)
    );
 
    create table CONFIGURATION (
        CONF_CD_CONF int8 not null,
        CONF_NR_BOX int4 not null,
        CONF_DT_DATE_REGISTER timestamp not null,
        CONF_NR_WORD int4 not null,
        USER_CD_USER int8 not null,
        primary key (CONF_CD_CONF)
    );
 
    create table FLOW_HISTORIC (
        FLHI_CD_FLHI int8 not null,
        FLHI_CD_ACTION varchar(255) not null,
        FLHI_DT_DATE_REGISTER timestamp not null,
        BOX_CD_DESTINATION_BOX int8 not null,
        BOX_CD_SOURCE_BOX int8 not null,
        USLI_CD_USER_LIST int8 not null,
        primary key (FLHI_CD_FLHI)
    );
 
    create table IDIOM (
        IDIO_CD_IDIOM int8 not null,
        IDIO_DT_DATE_REGISTER timestamp not null,
        IDIO_TX_NAME varchar(255) not null,
        primary key (IDIO_CD_IDIOM)
    );
 
    create table REVIEW (
        BOX_CD_BOX int8 not null,
        USLI_CD_USER_LIST int8 not null,
        REVI_DT_DATE_REVIEW timestamp not null,
        primary key (BOX_CD_BOX, USLI_CD_USER_LIST)
    );
 
    create table ROLE (
        ROLE_CD_ROLE int8 not null,
        ROLE_TX_NAME varchar(255) not null,
        primary key (ROLE_CD_ROLE)
    );
 
    create table SUGGESTED_TRANSLATION (
        IDIO_CD_IDIOM int8 not null,
        WORD_CD_WORD int8 not null,
        SUTR_DT_DATE_REGISTER timestamp not null,
        SUTR_TX_TRANSLATION varchar(255) not null,
        primary key (IDIO_CD_IDIOM, WORD_CD_WORD)
    );
 
    create table USERS (
        USER_CD_USER int8 not null,
        USER_DT_DATE_REGISTER timestamp not null,
        USER_TX_EMAIL varchar(255) not null,
        USER_TX_NAME varchar(255) not null,
        USER_TX_PASSWORD varchar(255) not null,
        primary key (USER_CD_USER)
    );
 
    create table USER_LIST (
        USLI_CD_USER_LIST int8 not null,
        USWO_DT_DATE_REGISTER timestamp not null,
        USLI_NR_GROUP int4 not null,
        USLI_TX_OBSERVATION varchar(255),
        USER_CD_USER int8 not null,
        primary key (USLI_CD_USER_LIST)
    );
 
    create table USER_LIST_USER_WORD (
        userList_USLI_CD_USER_LIST int8 not null,
        userWords_USWO_CD_USER_WORD int8 not null
    );
 
    create table USER_LIST_WORD (
        userList_USLI_CD_USER_LIST int8 not null,
        words_WORD_CD_WORD int8 not null
    );
 
    create table USER_ROLE (
        USER_CD_USER int8 not null,
        ROLE_CD_ROLE int8 not null
    );
 
    create table USER_WORD (
        USWO_CD_USER_WORD int8 not null,
        USWO_DT_DATE_REGISTER timestamp not null,
        USWO_TX_WORD varchar(255) not null,
        IDIO_CD_IDIOM int8 not null,
        USER_CD_USER int8 not null,
        primary key (USWO_CD_USER_WORD)
    );
 
    create table WORD (
        WORD_CD_WORD int8 not null,
        WORD_DT_DATE_REGISTER timestamp not null,
        WORD_IN_PRIORITY varchar(255) not null,
        WORD_TX_WORD varchar(255) not null,
        IDIO_CD_IDIOM int8 not null,
        primary key (WORD_CD_WORD)
    );
 
    alter table BOX 
        add constraint UK_hy7lm4ap43lusb1dtvvtblt0n  unique (BOX_NR_DAYS);
 
    alter table BOX 
        add constraint UK_mfu2isx37y6wg4jmj09unb5ur  unique (BOX_TX_NAME);
 
    alter table BOX 
        add constraint UK_guyxmfbv1cjmsgtoi9ljpbvpv  unique (BOX_NR_ORDER);
 
    alter table IDIOM 
        add constraint UK_8ydr3aesu4ua78hym2bulyh1  unique (IDIO_TX_NAME);
 
    alter table BOX 
        add constraint FK_fr7q00oxl5cq8u94fbt5y2lst 
        foreign key (USER_CD_USER) 
        references USERS;
 
    alter table CONFIGURATION 
        add constraint FK_mw62k7ffxlbanuhhbjpbwn67j 
        foreign key (USER_CD_USER) 
        references USERS;
 
    alter table FLOW_HISTORIC 
        add constraint FK_7oc3pks50ewpr54772scr0d71 
        foreign key (BOX_CD_DESTINATION_BOX) 
        references BOX;
 
    alter table FLOW_HISTORIC 
        add constraint FK_nmfcokhwtt0en0abc45wduuvm 
        foreign key (BOX_CD_SOURCE_BOX) 
        references BOX;
 
    alter table FLOW_HISTORIC 
        add constraint FK_cykq1fk7c9bv10stfd3liwhqb 
        foreign key (USLI_CD_USER_LIST) 
        references USER_LIST;
 
    alter table REVIEW 
        add constraint FK_6odhi774r46e3qhdd5dn7ak5r 
        foreign key (BOX_CD_BOX) 
        references BOX;
 
    alter table REVIEW 
        add constraint FK_gmrqblirgirficengg87oy08h 
        foreign key (USLI_CD_USER_LIST) 
        references USER_LIST;
 
    alter table SUGGESTED_TRANSLATION 
        add constraint FK_qdp44wipqpmwwuyiey0yr8ej1 
        foreign key (IDIO_CD_IDIOM) 
        references IDIOM;
 
    alter table SUGGESTED_TRANSLATION 
        add constraint FK_glf6fy0w948k5gyr4sujg5a5p 
        foreign key (WORD_CD_WORD) 
        references WORD;
 
    alter table USER_LIST 
        add constraint FK_mmb4ixgmcuumiv0c54ypu0v3r 
        foreign key (USER_CD_USER) 
        references USERS;
 
    alter table USER_LIST 
        add constraint FK_66vki67mvyal0yqpgmyl1etkh 
        foreign key (USLI_CD_USER_LIST) 
        references USER_LIST;
 
    alter table USER_LIST_USER_WORD 
        add constraint FK_aa4octtv73notypd4vara2nqk 
        foreign key (userWords_USWO_CD_USER_WORD) 
        references USER_WORD;
 
    alter table USER_LIST_USER_WORD 
        add constraint FK_nwpgdvt0yr4sy22jxtce6ujc6 
        foreign key (userList_USLI_CD_USER_LIST) 
        references USER_LIST;
 
    alter table USER_LIST_WORD 
        add constraint FK_htt6ern4ysg4q17vgaoi85gcp 
        foreign key (words_WORD_CD_WORD) 
        references WORD;
 
    alter table USER_LIST_WORD 
        add constraint FK_1o143ioxcminqct3isc9bscbv 
        foreign key (userList_USLI_CD_USER_LIST) 
        references USER_LIST;
 
    alter table USER_ROLE 
        add constraint FK_sj8ws1egxrycclrhj8ce8rwb0 
        foreign key (ROLE_CD_ROLE) 
        references ROLE;
 
    alter table USER_ROLE 
        add constraint FK_26t62t6pyq5asbi1ogc9fyqlc 
        foreign key (USER_CD_USER) 
        references USERS;
 
    alter table USER_WORD 
        add constraint FK_hw19f6hgeor1ji6ijl9byymtk 
        foreign key (IDIO_CD_IDIOM) 
        references IDIOM;
 
    alter table USER_WORD 
        add constraint FK_dgqtree62be2g6o2gdgdcgv8t 
        foreign key (USER_CD_USER) 
        references USERS;
 
    alter table WORD 
        add constraint FK_ra01h61wiw94dfj8vs7x72nb1 
        foreign key (IDIO_CD_IDIOM) 
        references IDIOM;
 
    create sequence SEQ_BOX_CD_BOX;
 
    create sequence SEQ_CONF_CD_CONF;
 
    create sequence SEQ_FLHI_CD_FLHI;
 
    create sequence SEQ_IDIO_CD_IDIOM;
 
    create sequence SEQ_ROLE_CD_ROLE;
 
    create sequence SEQ_USER_CD_USER;
 
    create sequence SEQ_USLI_CD_USER_LIST;
 
    create sequence SEQ_USWO_CD_USER_WORD;
 
    create sequence SEQ_WORD_CD_WORD;