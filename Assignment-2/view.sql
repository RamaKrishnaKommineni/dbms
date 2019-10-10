#Views

select p.first_name `First Name`,p.last_name `Last Name`,p.user_name `Username`,p.email `Email`,
w.name `Website Name`,w.visits `Website Visits`,w.updated `Last Updated Date`,wr.role `Developer Website Role`,
wp.priviledge `Developer Previlage`,pa.title `Page Title`,pa.views `Page Views`,pa.updated `Page Last Updated`,
pr.role `Developer Page Role`,pp.priviledge `Developer Page Previlage`
from person p join developer d join website w join website_role wr join website_priviledge wp
join page pa join page_role pr join page_priviledge pp
where d.person = p.id and w.id = wr.website and d.person = wr.developer and
d.person = wp.developer and w.id =  wp.website and w.id = pa.website and
pa.id = pr.page and d.person = pr.developer and pa.id = pp.page and d.person = pp.developer