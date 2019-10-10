#1
delete from address 
where person='12' and primary_address=1;

#2
delete from widget 
where page=(select id from page where title='contact') order by widget.order_widget desc limit 1;

#3
delete from page
where website=(select id from website where name='Wikipedia') order by page.updated desc limit 1;

#4
delete from website
where name='CNET';
