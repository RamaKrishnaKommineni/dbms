	delimiter $$
	create trigger website_priviledge_create
	after insert on website_role
	for each row
	begin
		case
			when new.role='owner' then
				insert into website_priviledge(priviledge,developer,website) values('create', new.developer, new.website);
				insert into website_priviledge(priviledge,developer,website) values('read', new.developer, new.website);
				insert into website_priviledge(priviledge,developer,website) values('update', new.developer, new.website);
				insert into website_priviledge(priviledge,developer,website) values('delete', new.developer, new.website);
			when new.role='admin' then
				insert into website_priviledge(priviledge,developer,website) values('create', new.developer, new.website);
				insert into website_priviledge(priviledge,developer,website) values('read', new.developer, new.website);
				insert into website_priviledge(priviledge,developer,website) values('update', new.developer, new.website);
				insert into website_priviledge(priviledge,developer,website) values('delete', new.developer, new.website);
			when new.role='writer' then
				insert into website_priviledge(priviledge,developer,website) values('create', new.developer , new.website);
				insert into website_priviledge(priviledge,developer,website) values('read', new.developer , new.website);
				insert into website_priviledge(priviledge,developer,website) values('update', new.developer , new.website);
			when new.role='editor' then
				insert into website_priviledge(priviledge,developer,website) values('read', new.developer , new.website);
				insert into website_priviledge(priviledge,developer,website) values('update', new.developer , new.website);
			when new.role='read' then
				insert into website_priviledge(priviledge,developer,website) values('read', new.developer , new.website);
		ELSE BEGIN END;
        END CASE;
	END$$
	DELIMITER ;

delimiter $$
create trigger website_priviledge_update
after update on website_role
for each row
begin
	case
    when new.role='owner' then
			delete from website_priviledge where developer=new.developer and website=new.website;
			insert into website_priviledge(priviledge,developer,website) values('create', new.developer , new.website);
			insert into website_priviledge(priviledge,developer,website) values('read', new.developer , new.website);
			insert into website_priviledge(priviledge,developer,website) values('update', new.developer , new.website);
			insert into website_priviledge(priviledge,developer,website) values('delete', new.developer , new.website);
	when new.role='admin' then
			delete from website_priviledge where developer=new.developer and website=new.website;
			insert into website_priviledge(priviledge,developer,website) values('create', new.developer , new.website);
			insert into website_priviledge(priviledge,developer,website) values('read', new.developer , new.website);
			insert into website_priviledge(priviledge,developer,website) values('update', new.developer , new.website);
			insert into website_priviledge(priviledge,developer,website) values('delete', new.developer , new.website);
	when new.role='writer' then
			delete  from website_priviledge where developer=new.developer and website=new.website;
			insert into website_priviledge values('create', new.developer , new.website);
            insert into website_priviledge values('read', new.developer , new.website);
            insert into website_priviledge values('update', new.developer , new.website);
	when new.role='editor' then
			delete from website_priviledge where developer=new.developer and website=new.website;
			insert into website_priviledge(priviledge,developer,website) values('read', new.developer , new.website);
            insert into website_priviledge(priviledge,developer,website) values('update', new.developer , new.website);
	when new.role='read' then
			delete from website_priviledge where developer=new.developer and website=new.website;
			insert into website_priviledge(priviledge,developer,website) values('read', new.developer , new.website);
    ELSE BEGIN END;
    END CASE;
END$$
DELIMITER ;

delimiter $$
create trigger website_priviledge_delete
before delete on website_role
for each row
begin
	delete from website_priviledge where developer=old.developer and website=old.website; 
end$$
DELIMITER ;


	delimiter $$
	create trigger page_priviledge_create
	after insert on page_role
	for each row
	begin
		case
			when new.role='owner' then
				insert into page_priviledge(priviledge,developer,page) values('create', new.developer , new.page);
				insert into page_priviledge(priviledge,developer,page) values('read', new.developer , new.page);
				insert into page_priviledge(priviledge,developer,page) values('update', new.developer , new.page);
				insert into page_priviledge(priviledge,developer,page) values('delete', new.developer , new.page);
			when new.role='admin' then
				insert into page_priviledge(priviledge,developer,page) values('create', new.developer , new.page);
				insert into page_priviledge(priviledge,developer,page) values('read', new.developer , new.page);
				insert into page_priviledge(priviledge,developer,page) values('update', new.developer , new.page);
				insert into page_priviledge(priviledge,developer,page) values('delete', new.developer , new.page);
			when new.role='writer' then
				insert into page_priviledge(priviledge,developer,page) values('create', new.developer , new.page);
				insert into page_priviledge(priviledge,developer,page) values('read', new.developer , new.page);
				insert into page_priviledge(priviledge,developer,page) values('update', new.developer , new.page);
			when new.role='editor' then
				insert into page_priviledge(priviledge,developer,page) values('read', new.developer , new.page);
				insert into page_priviledge(priviledge,developer,page) values('update', new.developer , new.page);
			when new.role='read' then
				insert into page_priviledge(priviledge,developer,page) values('read', new.developer , new.page);
		ELSE BEGIN END;
        END CASE;
	END$$
	DELIMITER ;

delimiter $$
create trigger page_priviledge_update
after update on page_role
for each row
begin
	case
    when new.role='owner' then
			delete from page_priviledge where developer=new.developer and page=new.page;
			insert into page_priviledge(priviledge,developer,page) values('create', new.developer , new.page);
			insert into page_priviledge(priviledge,developer,page) values('read', new.developer , new.page);
			insert into page_priviledge(priviledge,developer,page) values('update', new.developer , new.page);
			insert into page_priviledge(priviledge,developer,page) values('delete', new.developer , new.page);
	when new.role='admin' then
			delete  from page_priviledge where developer=new.developer and page=new.page;
			insert into page_priviledge(priviledge,developer,page) values('create', new.developer , new.page);
			insert into page_priviledge(priviledge,developer,page) values('read', new.developer , new.page);
			insert into page_priviledge(priviledge,developer,page) values('update', new.developer , new.page);
			insert into page_priviledge(priviledge,developer,page) values('delete', new.developer , new.page);
	when new.role='writer' then
			delete from page_priviledge where developer=new.developer and page=new.page;
			insert into page_priviledge(priviledge,developer,page) values('create', new.developer , new.page);
            insert into page_priviledge(priviledge,developer,page) values('read', new.developer , new.page);
            insert into page_priviledge(priviledge,developer,page) values('update', new.developer , new.page);
	when new.role='editor' then
			delete from page_priviledge where developer=new.developer and page=new.page;
			insert into page_priviledge(priviledge,developer,page) values('read', new.developer , new.page);
            insert into page_priviledge(priviledge,developer,page) values('update', new.developer , new.page);
	when new.role='read' then
			delete from page_priviledge where developer=new.developer and page=new.page;
			insert into page_priviledge(priviledge,developer,page) values('read', new.developer , new.page);
    ELSE BEGIN END;
    END CASE;
END$$
DELIMITER ;

delimiter $$
create trigger page_priviledge_delete
before delete on page_role
for each row
begin
	delete from page_priviledge where developer=old.developer and page=old.page; 
end$$
DELIMITER ;

drop trigger website_priviledge_create;
drop trigger website_priviledge_update;
drop trigger website_priviledge_delete;
drop trigger page_priviledge_create;
drop trigger page_priviledge_update;
drop trigger page_priviledge_delete;
