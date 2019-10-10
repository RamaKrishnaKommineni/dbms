#Insert into person
INSERT INTO person (id,user_name,password,first_name,last_name,email) VALUES (12,'alice','alice','Alice','Wonder','alice@wonder.com');
INSERT INTO person (id,user_name,password,first_name,last_name,email) VALUES (23,'bob','bob','bob','Marley','bob@marley.com');
INSERT INTO person (id,user_name,password,first_name,last_name,email) VALUES (34,'charlie','charlie','Charles','Garcia','chuch@garcia.com');
INSERT INTO person (id,user_name,password,first_name,last_name,email) VALUES (45,'dan','dan','Dan','Martin','dan@martin.com');
INSERT INTO person (id,user_name,password,first_name,last_name,email) VALUES (56,'ed','ed','Ed','Karaz','ed@kar.com');

#Insert into developer
INSERT INTO developer (developer_key,person) values ('4321rewq',12);
INSERT INTO developer (developer_key,person) values ('5432trew',23);
INSERT INTO developer (developer_key,person) values ('6543ytre',34);

#Insert into user
INSERT INTO user (person) values(45);
INSERT INTO user (person) values(56);

# insert into website
INSERT INTO website (id,name,description,visits,developer) values(123,'Facebook','an online social media and social networking service',1234234,(select id from person where user_name='alice'));
INSERT INTO website (id,name,description,visits,developer) values(234,'Twitter','an online news and social networking service',4321543,(select id from person where user_name='bob'));
INSERT INTO website (id,name,description,visits,developer) values(345,'Wikipedia','a free online encyclopedia',3456654,(select id from person where user_name='charlie'));
INSERT INTO website (id,name,description,visits,developer) values(456,'CNN','an American basic cable and satellite television news channel',6543345,(select id from person where user_name='alice'));
INSERT INTO website (id,name,description,visits,developer) values(567,'CNET',
'an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics',5433455,(select id from person where user_name='bob'));
INSERT INTO website (id,name,description,visits,developer) values(678,'Gizmodo',
'a design, technology, science and science fiction website that also writes articles on politics',4322345,(select id from person where user_name='charlie'));

#Insert into website_role
#Facebook
INSERT INTO website_role (role,developer,website) values('owner',(select id from person where user_name='alice'),123);
INSERT INTO website_role (role,developer,website) values('editor',(select id from person where user_name='bob'),123);
INSERT INTO website_role (role,developer,website) values('admin',(select id from person where user_name='charlie'),123);

#Twitter
INSERT INTO website_role (role,developer,website) values('owner',(select id from person where user_name='bob'),234);
INSERT INTO website_role (role,developer,website) values('editor',(select id from person where user_name='charlie'),234);
INSERT INTO website_role (role,developer,website) values('admin',(select id from person where user_name='alice'),234);

#Wikipedia
INSERT INTO website_role (role,developer,website) values('owner',(select id from person where user_name='charlie'),345);
INSERT INTO website_role (role,developer,website) values('editor',(select id from person where user_name='alice'),345);
INSERT INTO website_role (role,developer,website) values('admin',(select id from person where user_name='bob'),345);

#CNN
INSERT INTO website_role (role,developer,website) values('owner',(select id from person where user_name='alice'),456);
INSERT INTO website_role (role,developer,website) values('editor',(select id from person where user_name='bob'),456);
INSERT INTO website_role (role,developer,website) values('admin',(select id from person where user_name='charlie'),456);

#CNET
INSERT INTO website_role (role,developer,website) values('owner',(select id from person where user_name='bob'),567);
INSERT INTO website_role (role,developer,website) values('editor',(select id from person where user_name='charlie'),567);
INSERT INTO website_role (role,developer,website) values('admin',(select id from person where user_name='alice'),567);

#Gizmodo
INSERT INTO website_role (role,developer,website) values('owner',(select id from person where user_name='charlie'),678);
INSERT INTO website_role (role,developer,website) values('editor',(select id from person where user_name='alice'),678);
INSERT INTO website_role (role,developer,website) values('admin',(select id from person where user_name='bob'),678);

#Insert into pages
INSERT INTO page(id,title,description,website,views) values(123,'Home','Landing page',(select id from website where name='CNET'),123434);
INSERT INTO page(id,title,description,website,views) values(234,'About','Website description',(select id from website where name='Gizmodo'),234545);
INSERT INTO page(id,title,description,website,views) values(345,'Contact','Addresses, phones, and contact info',(select id from website where name='Wikipedia'),345656);
INSERT INTO page(id,title,description,website,views) values(456,'Preferences','Where users can configure their preferences',(select id from website where name='CNN'),456776);
INSERT INTO page(id,title,description,website,views) values(567,'Profile','Users can configure their personal information',(select id from website where name='CNET'),567878);

#Insert into page_role
#Home
INSERT INTO page_role(role,developer,page) values('editor',(select id from person where user_name='alice'),123);
INSERT INTO page_role(role,developer,page) values('reviewer',(select id from person where user_name='bob'),123);
INSERT INTO page_role(role,developer,page) values('writer',(select id from person where user_name='charlie'),123);

#About
INSERT INTO page_role(role,developer,page) values('editor',(select id from person where user_name='bob'),234);
INSERT INTO page_role(role,developer,page) values('reviewer',(select id from person where user_name='charlie'),234);
INSERT INTO page_role(role,developer,page) values('writer',(select id from person where user_name='alice'),234);

#Contact
INSERT INTO page_role(role,developer,page) values('editor',(select id from person where user_name='charlie'),345);
INSERT INTO page_role(role,developer,page) values('reviewer',(select id from person where user_name='alice'),345);
INSERT INTO page_role(role,developer,page) values('writer',(select id from person where user_name='bob'),345);

#Preferences
INSERT INTO page_role(role,developer,page) values('editor',(select id from person where user_name='alice'),456);
INSERT INTO page_role(role,developer,page) values('reviewer',(select id from person where user_name='bob'),456);
INSERT INTO page_role(role,developer,page) values('writer',(select id from person where user_name='charlie'),456);

#Profile
INSERT INTO page_role(role,developer,page) values('editor',(select id from person where user_name='bob'),567);
INSERT INTO page_role(role,developer,page) values('reviewer',(select id from person where user_name='charlie'),567);
INSERT INTO page_role(role,developer,page) values('writer',(select id from person where user_name='alice'),567);

#Insert into widget
INSERT INTO widget(id,name,DTYPE,text,order_widget,page) values(123,'head123','heading','Welcome',0,(select id from page where title='Home'));
INSERT INTO widget(id,name,DTYPE,text,order_widget,page) values(234,'post234','html','<p>Lorem</p>',0,(select id from page where title='About'));
INSERT INTO widget(id,name,DTYPE,text,order_widget,page) values(345,'head345','heading','Hi',1,(select id from page where title='Contact'));
INSERT INTO widget(id,name,DTYPE,text,order_widget,page) values(456,'intro456','html','<h1>Hi</h1>',2,(select id from page where title='Contact'));
INSERT INTO widget(id,name,DTYPE,order_widget,page,width,height,image_src) 
values(567,'image345','image',3,(select id from page where title='Contact'),50,100,'/img/567.png');
INSERT INTO widget(id,name,DTYPE,order_widget,page,width,height,image_src) 
values(678,'video456','youtube',0,(select id from page where title='Preferences'),400,300,'https://youtu.be/h67VX51QXiQ');

#Insert into address
INSERT INTO address (person,street1,city,zip,primary_address) values((select id from person where user_name='alice'),'123 Adam St.','Alton',02134,true);
INSERT INTO address (person,street1,city,zip,primary_address) values((select id from person where user_name='alice'),'234 Birch St.','Boston',02345,false);
INSERT INTO address (person,street1,city,zip,primary_address) values((select id from person where user_name='bob'),'345 Charles St.','Chelms',03455,true);
INSERT INTO address (person,street1,city,zip,primary_address) values((select id from person where user_name='bob'),'456 Down St.','Dalton',04566,false);
INSERT INTO address (person,street1,city,zip,primary_address) values((select id from person where user_name='bob'),'543 East St.','Everett',01112,false);
INSERT INTO address (person,street1,city,zip,primary_address) values((select id from person where user_name='charlie'),'654 Frank St.','Foulton',04322,true);

#Insert into phone
INSERT INTO phone(phone,primary_phone,person) values('123-234-3456',true,(select id from person where user_name='alice'));
INSERT INTO phone(phone,primary_phone,person) values('234-345-4566',false,(select id from person where user_name='alice'));
INSERT INTO phone(phone,primary_phone,person) values('345-456-5677',true,(select id from person where user_name='bob'));
INSERT INTO phone(phone,primary_phone,person) values('321-432-5435',true,(select id from person where user_name='charlie'));
INSERT INTO phone(phone,primary_phone,person) values('432-432-5433',false,(select id from person where user_name='charlie'));
INSERT INTO phone(phone,primary_phone,person) values('543-543-6544',false,(select id from person where user_name='charlie'));
