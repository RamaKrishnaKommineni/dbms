#1
update phone 
set phone='333-444-5555'
where person=(select id from person where user_name='charlie') and 
primary_phone=1;

#2
update widget 
set order_widget =3
where page=(select id from page where title='Contact');

#3
update page
set title=concat('CNET-',title)
where website=(select id from website where name='CNET');

#4
update page_role
set developer=(select id from person where user_name='bob') 
where page=(select id from page where title='CNET-Home') and 
developer=(select id from person where user_name='charlie');