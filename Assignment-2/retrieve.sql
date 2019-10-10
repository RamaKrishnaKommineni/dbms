#1a
select person.first_name,person.last_name from person
inner join developer
on developer.person=person.id;

#1b
select person.first_name,person.last_name from person
inner join developer
on developer.person=person.id
where developer.person='34';
 
#1c
select person.first_name,person.last_name from person 
where id in(select d.person 
from developer d join website_role wr 
on d.person=wr.developer
where wr.website=(select id from website where name='twitter') and wr.role!='owner');

#1d
select person.first_name,person.last_name from person 
where id in(select d.person 
from developer d join page_role pr join website w
on d.person=w.developer
where pr.role='reviewer' and w.visits<300000);

#1e
select person.first_name,person.last_name from person 
where id in(select d.person 
from developer d join page_role pr join website w join page 
on d.person=pr.developer
where pr.role='writer' and w.name='CNET' and page.title='Home');

#2a
select website.name,min(website.visits) from website;

#2b
select website.name from website
where website.id='678';

#2c
select website from page p 
join page_role pr 
on p.id=pr.page 
where p.id=(select page from widget where DTYPE='youtube') and 
pr.developer=(select id from person where user_name='bob') and pr.role='reviewer';

#2d
select website.name from website
join website_role 
on website.id=website_role.website
where website_role.role='owner' and website.developer=(select id from person where user_name='alice');

#2e
select website.name from website
join website_role
on website.id=website_role.website
where website_role.role='admin' and website.developer=(select id from person where user_name='alice') and website.visits>6000000;

#3a
select page.title,min(page.views) from page;

#3b
select page.title from page
where page.id='234';

#3c
select page.title from page 
join page_role 
on page.id=page_role.page
where page_role.role='editor' and page_role.developer=(select id from person where user_name='alice');

#3d
select sum(page.views) from page
where page.website=(select id from website where name='CNET');

#3e
select avg(page.views) from page
where page.website=(select id from website where name='Wikipedia');

#4a
select widget.name from widget where page=(select page.id from page 
where page.website=(select id from website where name='CNET') and page.title='Home');

#4b
select widget.name from widget
join website
where website.name='CNN' and widget.DTYPE='youtube';

#4c 
select widget.name from widget
join page_role
where widget.DTYPE='image' and page_role.role='reviewer' 
and page_role.developer=(select id from person where user_name='alice');

#4d
select count(*) from widget 
where page=(select id from page where website=(select id from website where name='Wikipedia'));

#5a
select website.name from website 
where id in(select website from website_priviledge 
where developer=(select id from person where user_name='bob') and priviledge='delete');

#5b
select title from page 
where id in(select page from page_priviledge 
where developer=(select id from person where user_name='charlie') and priviledge='create');