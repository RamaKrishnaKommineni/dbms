package edu.northeastern.cs5200;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

import edu.northeastern.cs5200.DatabaseConnection;
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
import edu.northeastern.cs5200.models.User;
import edu.northeastern.cs5200.models.Website;
import edu.northeastern.cs5200.models.Widget;
import edu.northeastern.cs5200.models.YouTubeWidget;

public class hw_jdbc_mandava_sowmika {
    Connection connection = null;
    ResultSet result = null;
    CallableStatement callableStatement = null;

    public static void main(String[] args) {
        hw_jdbc_mandava_sowmika runner = new hw_jdbc_mandava_sowmika();
        runner.insertDevelopers();
        runner.insertUsers();
        runner.insertWebsites();
        runner.insertPages();
        runner.insertRoles();
    }

    void insertDevelopers() {
        DeveloperDao developerDao = DeveloperImpl.getInstance();

        Developer alice = new Developer("4321rewq", 12, "Alice", "Wonder", "alice", "alice", "alice@wonder.com", null, null, null);
        Developer bob = new Developer("5432trew", 23, "Bob", "Marley", "bob", "bob", "bob@marley.com", null, null, null);
        Developer charlie = new Developer("6543ytre", 34, "Charles", "Garcia", "charlie", "charlie", "chuch@garcia.com", null, null, null);

        developerDao.createDeveloper(alice);
        developerDao.createDeveloper(bob);
        developerDao.createDeveloper(charlie);
    }

    void insertUsers() {
        UserDao userDao = UserImpl.getInstance();
        User dan = new User(45, "Dan", "Martin", "dan", "dan", "dan@martin.com", null, null, null, false);
        User ed = new User(56, "Ed", "Karaz", "ed", "ed", "ed@kar.com", null, null, null, false);

        userDao.createUser(dan);
        userDao.createUser(ed);
    }


    void insertWebsites() {
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

    void insertPages() {
        PageDao pageDao = PageImpl.getInstance();

        Page home = new Page(123, "Home", "Landing page", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 123434);
        Page about = new Page(234, "About", "Website description", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 234545);
        Page contact = new Page(345, "Contact", "Addresses, phones and contact info", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 345656);
        Page preferences = new Page(456, "Preferences", "Where users can configure their preferences", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 456776);
        Page profile = new Page(567, "Profile", "Users can configure their personal information", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 567878);

        pageDao.createPageForWebsite(567, home);
        pageDao.createPageForWebsite(678, about);
        pageDao.createPageForWebsite(345, contact);
        pageDao.createPageForWebsite(456, preferences);
        pageDao.createPageForWebsite(567, profile);
    }

    void insertRoles() {
        RoleDao roleDao = RoleImpl.getInstance();

        roleDao.assignWebsiteRole(12, 123, 1);
        roleDao.assignWebsiteRole(23, 123, 4);
        roleDao.assignWebsiteRole(34, 123, 2);
        roleDao.assignWebsiteRole(23, 234, 1);
        roleDao.assignWebsiteRole(34, 234, 4);
        roleDao.assignWebsiteRole(12, 234, 2);
        roleDao.assignWebsiteRole(34, 345, 1);
        roleDao.assignWebsiteRole(12, 345, 4);
        roleDao.assignWebsiteRole(23, 345, 2);
        roleDao.assignWebsiteRole(12, 456, 1);
        roleDao.assignWebsiteRole(23, 456, 4);
        roleDao.assignWebsiteRole(34, 456, 2);
        roleDao.assignWebsiteRole(23, 567, 1);
        roleDao.assignWebsiteRole(34, 567, 4);
        roleDao.assignWebsiteRole(12, 567, 2);
        roleDao.assignWebsiteRole(34, 678, 1);
        roleDao.assignWebsiteRole(12, 678, 4);
        roleDao.assignWebsiteRole(23, 678, 2);

        roleDao.assignPageRole(12, 123, 4);
        roleDao.assignPageRole(23, 123, 5);
        roleDao.assignPageRole(34, 123, 3);
        roleDao.assignPageRole(23, 234, 5);
        roleDao.assignPageRole(34, 234, 3);
        roleDao.assignPageRole(12, 234, 3);
        roleDao.assignPageRole(34, 345, 4);
        roleDao.assignPageRole(12, 345, 5);
        roleDao.assignPageRole(23, 345, 3);
        roleDao.assignPageRole(12, 456, 4);
        roleDao.assignPageRole(23, 456, 5);
        roleDao.assignPageRole(34, 456, 3);
        roleDao.assignPageRole(23, 567, 4);
        roleDao.assignPageRole(34, 567, 5);
        roleDao.assignPageRole(12, 567, 3);
    }
//
//  void insertWidgets(){
//    WidgetDao widgetDao = WidgetDaoImpl.getInstance();
//
//    Widget heading1 = new HeadingWidget(123, "head123", 0, 0, null, null, "Welcome", 0, 0);
//    Widget html1 = new HtmlWidget(234, "post234", 0, 0, null, null, "<p>Lorem</p>", 0, null);
//    Widget heading2 = new HeadingWidget(345, "head345", 0, 0, null, null, "Hi", 1, 6);
//    Widget html2 = new HtmlWidget(456, "intro456", 0, 0, null, null, "<h1>Hi</h1>", 2, null);
//    Widget image = new ImageWidget(567, "image345", 50, 100, null, null, null, 3, "/img/567.png");
//    Widget youtube = new YouTubeWidget(678, "video456", 400, 300, null, null, null, 0, "https://youtu.be/h67VX51QXiQ", false, false);
//
//    widgetDao.createWidgetForPage(123, heading1);
//    widgetDao.createWidgetForPage(234, html1);
//    widgetDao.createWidgetForPage(345, heading2);
//    widgetDao.createWidgetForPage(345, html2);
//    widgetDao.createWidgetForPage(345, image);
//    widgetDao.createWidgetForPage(456, youtube);
//  }
//
//  void updateDeveloper(){
//    PhoneDao phoneDao = PhoneDaoImpl.getInstance();
//    int userId = 34;
//    String phoneNumber = "333-444-5555";
//    if(phoneDao.updatePrimaryPhone(phoneNumber) == 0)
//      phoneDao.createPrimaryPhone(userId, phoneNumber);
//  }
//
//  void updateWidget(){
//    WidgetDao widgetDao = WidgetDao.getInstance();
//    Collection<Widget> widgets = widgetDao.findWidgetsForPage(345);
//    for(Widget widget: widgets){
//      widget.setOrder(((widget.getOrder()+1)%3)+1);
//      widgetDao.updateWidget(widget.getId(), widget);
//    }
//  }
//
//  void updatePage(){
//    PageDao pageDao = PageDao.getInstance();
//    Collection<Page> cnetPages = pageDao.findPagesForWebsite(567);
//    for(Page page: cnetPages){
//      page.setTitle("CNET-" + page.getTitle());
//      pageDao.updatePage(page.getId(), page);
//    }
//  }
//
//  void updateRole(){
//    RoleDao roleDao = RoleDao.getInstance();
//    Collection<Role> charlieRoles = roleDao.getPageRole(123, 34);
//    Collection<Role> bobRoles = roleDao.getPageRole(123, 23);
//    for(Role role: charlieRoles)
//      roleDao.deletePageRole(34, 123, role.getRoleId());
//    for(Role role: bobRoles)
//      roleDao.deletePageRole(23, 123, role.getRoleId());
//    for(Role role: charlieRoles)
//      roleDao.assignPageRole(23, 123, role.getRoleId());
//    for(Role role: bobRoles)
//      roleDao.assignPageRole(34, 123, role.getRoleId());
//  }
//
//  void deleteDeveloper(){
//    AddressDao addressDao = AddressDao.getInstance();
//    addressDao.deletePrimaryAddress(12);
//  }
//
//  void deleteWebsite(){
//    WebsiteDao websiteDao = WebsiteDao.getInstance();
//    websiteDao.deleteWebsite(567);
//  }
//
//  void deleteWidget(){
//    WidgetDao widgetDao = WidgetDao.getInstance();
//    Collection<Widget> widgets = widgetDao.findWidgetsForPage(345);
//    if(! widgets.isEmpty()){
//      Widget maxWidget = Collections.max(widgets,
//              Comparator.comparing(widget -> widget.getOrder()));
//      widgetDao.deleteWidget(maxWidget.getId());
//    }
//  }
//
//  void deletePage(){
//    PageDao pageDao = PageDao.getInstance();
//    Collection<Page> pages = pageDao.findPagesForWebsite(345);
//    if(! pages.isEmpty()){
//      Page lastUpdatePage = Collections.max(pages,
//              Comparator.comparing(page -> page.getUpdated()));
//      pageDao.deletePage(lastUpdatePage.getId());
//    }
//  }
//
//  void getGetUnansweredQuestions(){
//    try{
//      connection = Connection.getConnection();
//      String PROCEDURE = "{call getUnansweredQuestions()}";
//      callableStmt = connection.prepareCall(PROCEDURE);
//      if(callableStmt.execute()){
//        result = callableStmt.getResultSet();
//        while (result.next()){
//          String text = result.getString("text");
//          int answers = result.getInt("answers");
//          System.out.println(text + "\t" + answers);
//        }
//      }
//    } catch (ClassNotFoundException | SQLException e) {
//      e.printStackTrace();
//    }
//  }
//
//  void getEndorsedUserForWeek(Date startDate, Date endDate){
//    try{
//      connection = Connection.getConnection();
//      String PROCEDURE = "{call endorsedUsersForWeek(?, ?)}";
//      callableStmt = connection.prepareCall(PROCEDURE);
//      callableStmt.setDate(1, startDate);
//      callableStmt.setDate(2, endDate);
//      if(callableStmt.execute()){
//        result = callableStmt.getResultSet();
//        while (result.next()){
//          int id = result.getInt("id");
//          String name = result.getString("name");
//          int correctAnswers = result.getInt("correct_answers");
//          System.out.println(id + "\t" + name + "\t" + correctAnswers);
//        }
//      }
//
//    } catch (ClassNotFoundException | SQLException e) {
//      e.printStackTrace();
//    }
//  }
//
//  void implementInserts(){
//    insertDevelopers();
//    insertUsers();
//    insertWebsites();
//    insertPages();
//    insertRoles();
//    insertWidgets();
//  }
//
//  void implementUpdates(){
//    updateDeveloper();
//    updateWidget();
//    updatePage();
//    updateRole();
//  }
//
//  void implementDeletes(){
//    deleteDeveloper();
//    deleteWidget();
//    deletePage();
//    deleteWebsite();
//  }
//
//  void implementStoredProcedures(){
//    java.sql.Date start_date = java.sql.Date.valueOf("2018-10-24");
//    java.sql.Date end_date = java.sql.Date.valueOf("2018-10-31");
//    getGetUnansweredQuestions();
//    getEndorsedUserForWeek(start_date, end_date);
//  }
//
//


}
