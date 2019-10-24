create table person(
	id int not null AUTO_INCREMENT,
    first_name varchar(20) not null,
    last_name varchar(20) not null,
    user_name varchar(20) not null,
    password varchar(10) not null,
    email varchar(30) not null,
    dob timestamp default current_timestamp,	
    primary key(id)
);

create table developer(
	developer_key varchar(10) not null,
    person int not null,
    primary key(person),
    CONSTRAINT developer_person_generalization foreign key(person) references person(id)
    on delete cascade
    on update cascade
);

create table user(
    user_agreement BOOLEAN,
    person int not null,
    primary key(person),
    CONSTRAINT user_person_generalization foreign key(person) references person(id)
    on delete cascade
    on update cascade
);

create table website(
	id int not null AUTO_INCREMENT,
    name varchar(20) not null,
    description varchar(255),
    created date default null,
    updated timestamp default current_timestamp on update current_timestamp,
    visits int default '0',
    developer int not null,
    primary key(id),
    CONSTRAINT website_developer_generalization foreign key(developer) references developer(person)
    on delete cascade
    on update cascade
);

create table page(
	id int not null AUTO_INCREMENT,
    title varchar(50) not null,
    description varchar(100),
    created date default null,
    updated timestamp default current_timestamp on update current_timestamp,
    views int default '0',
    website int not null,
    primary key(id),
    CONSTRAINT page_website_id foreign key(website) references website(id)
    on delete cascade
    on update cascade
);

create table widget(
	id int not null AUTO_INCREMENT,
    name varchar(20) not null,
    width int,
    height int,
    css_class varchar(20),
    css_style varchar(20),
    text varchar(100),
    order_widget int not null,
    page int not null,
    heading_size int default '2',
    html_html varchar(255),
    youtube_url varchar(255),
    youtube_shareble BOOLEAN,
    youtube_expandable BOOLEAN,
    image_src varchar(255),
    DTYPE varchar(20) not null,
    primary key(id),
    CONSTRAINT widget_page_generalization foreign key(page) references page(id)
    on delete cascade
    on update cascade    
);

create table address(
	id int not null AUTO_INCREMENT,
    street1 varchar(20) not null,
    street2 varchar(20),
    city varchar(20) not null,
    state varchar(20),
    zip varchar(10) not null,
    primary_address BOOLEAN not null,
	person int not null,
    primary key(id),
    CONSTRAINT address_person_generalization foreign key(person) references person(id)
    on delete cascade
    on update cascade
);

create table phone(
	id int not null AUTO_INCREMENT,
    phone varchar(20) not null,
    primary_phone BOOLEAN not null,
    person int not null,
    primary key(id),
    CONSTRAINT phone_person_generalization foreign key(person) references person(id)
    on delete cascade
    on update cascade
);

create table role(
	name varchar(20) not null default '',
    primary key(name)
);

insert into role(name) values ('owner');
insert into role(name) values ('admin');
insert into role(name) values ('writer');
insert into role(name) values ('editor'); 
insert into role(name) values ('reviewer'); 

create table priviledge(
	name varchar(20) not null default '',
    primary key(name)
);

insert into priviledge(name) values ('create');
insert into priviledge(name) values ('read');
insert into priviledge(name) values ('update');
insert into priviledge(name) values ('delete');

create table page_role(
	id int not null AUTO_INCREMENT,
	role varchar(20) default null,
    foreign key(role) references role(name),
    developer int not null,
    page int not null,
    primary key(id),
    CONSTRAINT page_role_developer_person foreign key(developer) references developer(person), 
    CONSTRAINT page_role_page_id foreign key(page) references page(id)
	on delete cascade
    on update cascade
); 

create table website_role(
	id int not null AUTO_INCREMENT,
	role varchar(20) default null,
    foreign key(role) references role(name),
	developer int not null,
    website int not null,
    primary key(id),
    CONSTRAINT website_role_developer_person foreign key(developer) references developer(person), 
    CONSTRAINT website_role_website_id foreign key(website) references website(id)
	on delete cascade
    on update cascade
); 

create table page_priviledge(
	id int not null AUTO_INCREMENT,
	priviledge varchar(20) default null,
    foreign key(priviledge) references priviledge(name),
    developer int not null,
    page int not null,
    primary key(id),
    CONSTRAINT page_priviledge_developer_person foreign key(developer) references developer(person), 
    CONSTRAINT page_priviledge_page_id foreign key(page) references page(id)
	on delete cascade
    on update cascade
); 

create table website_priviledge(
	id int not null AUTO_INCREMENT,
	priviledge varchar(20) default null,
    foreign key(priviledge) references priviledge(name),
    developer int not null,
    website int not null,
    primary key(id),
    CONSTRAINT website_priviledge_developer_person foreign key(developer) references developer(person), 
    CONSTRAINT website_priviledge_website_id foreign key(website) references website(id)
	on delete cascade
    on update cascade
); 

create table answer(
	id int not null AUTO_INCREMENT,
    text varchar(255),
    postedBy int not null,
    postedOn date,
    correctAnswer boolean,
    upVotes int,
    downVotes int,
    primary key(id),
    CONSTRAINT answer_user_generalization foreign key(postedBy) references user(person),
    CONSTRAINT answer_widget_generalization foreign key(id) references widget(id)
    );
    
create table module(
	name varchar(20) not null,
    primary key(name)
);

insert into module(name) values ('Project1');
insert into module(name) values ('Project2');
insert into module(name) values ('Assignment1');
insert into module(name) values ('Assignment2');
insert into module(name) values ('Quiz1');
insert into module(name) values ('Exam');
insert into module(name) values ('Logistics');

create table question(
	id int not null AUTO_INCREMENT,
    text varchar(250) not null,
    askedBy int not null,
    postedOn date,
    length int,
    views int,
    endorsedByInstructor boolean,
    module varchar(50) not null,
    primary key(id),
    foreign key(module) references module(name),
    CONSTRAINT question_user_generalization foreign key(askedBy) references user(person),
	CONSTRAINT question_widget_generalization foreign key(id) references widget(id)
);

drop table answer;
drop table question;
drop table module;
drop table website_priviledge;
drop table page_priviledge;
drop table website_role;
drop table page_role;
drop table priviledge;
drop table role;
drop table phone;
drop table address;
drop table widget;
drop table page;
drop table website;
drop table user;
drop table developer;
drop table person;



