package edu.northeastern.cs5200;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import edu.northeastern.cs5200.Daos.AddressDao;
import edu.northeastern.cs5200.Daos.AddressImpl;
import edu.northeastern.cs5200.Daos.DeveloperDao;
import edu.northeastern.cs5200.Daos.PageDao;
import edu.northeastern.cs5200.Daos.PhoneDao;
import edu.northeastern.cs5200.Daos.RoleDao;
import edu.northeastern.cs5200.Daos.UserDao;
import edu.northeastern.cs5200.Daos.WebsiteDao;
import edu.northeastern.cs5200.Daos.WidgetDao;
import edu.northeastern.cs5200.Daos.DeveloperImpl;
import edu.northeastern.cs5200.Daos.PageImpl;
import edu.northeastern.cs5200.Daos.PhoneImpl;
import edu.northeastern.cs5200.Daos.RoleImpl;
import edu.northeastern.cs5200.Daos.UserImpl;
import edu.northeastern.cs5200.Daos.WebsiteImpl;
import edu.northeastern.cs5200.Daos.WidgetImpl;
import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.HeadingWidget;
import edu.northeastern.cs5200.models.HtmlWidget;
import edu.northeastern.cs5200.models.ImageWidget;
import edu.northeastern.cs5200.models.Page;
import edu.northeastern.cs5200.models.Phone;
import edu.northeastern.cs5200.models.User;
import edu.northeastern.cs5200.models.Website;
import edu.northeastern.cs5200.models.Widget;
import edu.northeastern.cs5200.models.YouTubeWidget;

public class hw_jdbc_mandava_sowmika{
	Connection connection = null;
	ResultSet result = null;
	CallableStatement callableStatement = null;

	public static void main(String[] args){
		hw_jdbc_mandava_sowmika runner = new hw_jdbc_mandava_sowmika();
		runner.insertDevelopers();
		runner.insertUsers();
		runner.insertWebsites();
		runner.insertPages();
		runner.insertRoles();
		runner.updateDeveloper();
		runner.updateWidget();
		runner.updatePage();
		runner.updateRole();
		runner.deleteDeveloper();
		runner.deleteWebsite();
    	runner.deleteWidget();
    	runner.deletePage();
	}

	void insertDevelopers(){
		DeveloperDao developerDao = DeveloperImpl.getInstance();
		Developer alice = new Developer("4321rewq", 12, "Alice", "Wonder", "alice", "alice", "alice@wonder.com", null, null, null);
		Developer bob = new Developer("5432trew", 23, "Bob", "Marley", "bob", "bob", "bob@marley.com", null, null, null);
		Developer charlie = new Developer("6543ytre", 34, "Charles", "Garcia", "charlie", "charlie", "chuch@garcia.com", null, null, null);
		developerDao.createDeveloper(alice);
    	developerDao.createDeveloper(bob);
    	developerDao.createDeveloper(charlie);
	}

	void insertUsers(){
		UserDao userDao = UserImpl.getInstance();
		User dan = new User(45, "Dan", "Martin", "dan", "dan", "dan@martin.com", null, null, null, false);
		User ed = new User(56, "Ed", "Karaz", "ed", "ed", "ed@kar.com", null, null, null, false);
		userDao.createUser(dan);
		userDao.createUser(ed);
	}

	void insertWebsites(){
		WebsiteDao websiteDao = WebsiteImpl.getInstance();
		Website facebook = new Website(123, "Facebook", "an online social media and social networking service", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 1234234);
		Website twitter = new Website(234, "Twitter", "an online news and social networking service", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 4321543);
		Website wikipedia = new Website(345, "Wikipedia", "a free online encyclopedia", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 3456654);
    	Website cnn = new Website(456, "CNN", "an American basic cable and satellite television news channel", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 6543345);
    	Website cnet = new Website(567, "CNET", "an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 5433455);
    	Website gizmodo = new Website(678, "Gizmodo", "a design, technology, science and science fiction website that also writes articles on politics", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 4322345);
    	websiteDao.createWebsiteForDeveloper(34, cnet);
    	websiteDao.createWebsiteForDeveloper(23, cnn);
    	websiteDao.createWebsiteForDeveloper(12, facebook);
    	websiteDao.createWebsiteForDeveloper(34, gizmodo);
    	websiteDao.createWebsiteForDeveloper(12, twitter);
    	websiteDao.createWebsiteForDeveloper(23, wikipedia);
	}

	void insertPages(){
		PageDao pageDao = PageImpl.getInstance();
		Page home = new Page(123, "Home", "Landing page", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 123434);
		Page about = new Page(234, "About", "Website description", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 234545);
		Page contact = new Page(345, "Contact", "Addresses, phones and contact info", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 345656);
		Page preferences = new Page(456, "Preferences", "Where users can configure their preferences", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 456776);
		Page profile = new Page(567, "Profile", "Users can configure their personal information", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 567878);
		pageDao.createPageForWebsite(567,home);
		pageDao.createPageForWebsite(678,about);
		pageDao.createPageForWebsite(345,contact);
		pageDao.createPageForWebsite(456,preferences);
		pageDao.createPageForWebsite(567,profile);
	}

	void insertRoles(){
		RoleDao roleDao = RoleImpl.getInstance();
		roleDao.assignWebsiteRole(12, 123, "owner");
		roleDao.assignWebsiteRole(23, 123, "editor");
		roleDao.assignWebsiteRole(34, 123, "admin");
		roleDao.assignWebsiteRole(23, 234, "owner");
		roleDao.assignWebsiteRole(34, 234, "editor");
		roleDao.assignWebsiteRole(12, 234, "admin");
		roleDao.assignWebsiteRole(34, 345, "owner");
		roleDao.assignWebsiteRole(12, 345, "editor");
		roleDao.assignWebsiteRole(23, 345, "admin");
		roleDao.assignWebsiteRole(12, 456, "owner");
		roleDao.assignWebsiteRole(23, 456, "editor");
		roleDao.assignWebsiteRole(34, 456, "admin");
		roleDao.assignWebsiteRole(23, 567, "owner");
		roleDao.assignWebsiteRole(34, 567, "editor");
		roleDao.assignWebsiteRole(12, 567, "admin");
		roleDao.assignWebsiteRole(34, 678, "owner");
		roleDao.assignWebsiteRole(12, 678, "editor");
		roleDao.assignWebsiteRole(23, 678, "admin");
		roleDao.assignPageRole(12, 123, "editor"); 
		roleDao.assignPageRole(23, 123, "reviewer");
		roleDao.assignPageRole(34, 123, "writer"); 
		roleDao.assignPageRole(23, 234, "reviewer");
		roleDao.assignPageRole(34, 234, "writer"); 
		roleDao.assignPageRole(12, 234, "writer");
		roleDao.assignPageRole(34, 345, "editor"); 
		roleDao.assignPageRole(12, 345, "reviewer");
		roleDao.assignPageRole(23, 345, "writer"); 
		roleDao.assignPageRole(12, 456, "editor");
		roleDao.assignPageRole(23, 456, "reviewer"); 
		roleDao.assignPageRole(34, 456, "writer");
		roleDao.assignPageRole(23, 567, "editor"); 
		roleDao.assignPageRole(34, 567, "reviewer");
		roleDao.assignPageRole(12, 567, "writer");	 
	}

	void insertWidgets(){
		WidgetDao widgetDao = WidgetImpl.getInstance();
		Widget heading1 = new HeadingWidget(123,"head123",null,null,null,null,0,"Welcome", null);
		Widget html1 = new HtmlWidget(234, "post234",null,null,null,null,0,"<p>Lorem</p>",null);
		Widget heading2 = new HeadingWidget(345,"head345",null,null,null,null,1,"Hi",null);
		Widget html2 = new HtmlWidget(456,"intro456",null,null,null,null,2,"<h1>Hi</h1>",null);
		Widget image = new ImageWidget(567,"image345",50,100,null,null,3,null,"/img/567.png");
		Widget youtube = new YouTubeWidget(678,"video456",400,300, null, null,0,null,"https://youtu.be/h67VX51QXiQ",false,false);
		widgetDao.createWidgetForPage(123,heading1);
		widgetDao.createWidgetForPage(234,html1);
		widgetDao.createWidgetForPage(345,heading2);
		widgetDao.createWidgetForPage(345,html2);
		widgetDao.createWidgetForPage(345,image);
		widgetDao.createWidgetForPage(456,youtube);
	}

	void updateDeveloper(){
		PhoneDao phoneDao = PhoneImpl.getInstance();
		int userId = 34;
		Phone phoneNumber = new Phone(1,"333-444-5555", false);
		if(phoneDao.updatePhone(phoneNumber) == 0)
			phoneDao.createPhone(phoneNumber,userId);
	}

	void updateWidget(){
		WidgetDao widgetDao = WidgetImpl.getInstance();
		Collection<Widget> widgets = widgetDao.findWidgetsForPage(345);
		for(Widget widget: widgets){
			widget.setOrder(((widget.getOrder()+1)%3)+1);
			widgetDao.updateWidget(widget.getId(), widget);
		}
	}

	void updatePage(){
		PageDao pageDao = PageImpl.getInstance();
		Collection<Page> cnetPages = pageDao.findPagesForWebsite(567);
		for(Page page: cnetPages){
			page.setTitle("CNET-" + page.getTitle());
			pageDao.updatePage(page.getId(), page);
		}
	}

	void updateRole(){
		RoleDao roleDao = RoleImpl.getInstance();
		roleDao.deletePageRole(34, 123);
		roleDao.deletePageRole(23, 123);
		roleDao.assignPageRole(34,123, "reviewer");
		roleDao.assignPageRole(23,123,"writer");
	}

	void deleteDeveloper(){
		AddressDao addressDao = AddressImpl.getInstance();
		addressDao.deleteAddress(12, 1);
	}

	void deleteWebsite(){
		WebsiteDao websiteDao = WebsiteImpl.getInstance();
		websiteDao.deleteWebsite(567);
	}

	void deleteWidget(){
		WidgetDao widgetDao = WidgetImpl.getInstance();
		Collection<Widget> widgets = widgetDao.findWidgetsForPage(345);
		if(! widgets.isEmpty()){
			Widget maxWidget = Collections.max(widgets,
			Comparator.comparing(widget -> widget.getOrder()));
			widgetDao.deleteWidget(maxWidget.getId());
		}
	}

	void deletePage(){
		PageDao pageDao = PageImpl.getInstance();
		Collection<Page> pages = pageDao.findPagesForWebsite(345);
		if(! pages.isEmpty()){
			Page lastUpdatePage = Collections.max(pages,
            Comparator.comparing(page -> page.getUpdated()));
			pageDao.deletePage(lastUpdatePage.getId());
		}
	}
}

