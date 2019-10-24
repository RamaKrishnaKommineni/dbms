package edu.northeastern.cs5200.Daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.DatabaseConnection;
import edu.northeastern.cs5200.Daos.PageDao;
import edu.northeastern.cs5200.models.Page;

public class PageImpl implements PageDao {

    private DatabaseConnection connection = null;
    private PreparedStatement pStatement = null;
    private static PageImpl instance= null;
    private PageImpl() {}

    public static PageImpl getInstance() {
        if(instance == null)
            instance = new PageImpl();
        return instance;
    }

    String CREATE_PAGE = "INSERT into page (id, website, title, description, created, updated, views) VALUES (?, ?, ?, ?, ?, ?, ?)";
    String FIND_ALL_PAGES = "SELECT * from page";
    String FIND_PAGE_BY_ID = "SELECT * from page where page.id=?";
    String FIND_PAGES_FOR_WEBSITE = "SELECT * from page WHERE page.website=?";
    String UPDATE_PAGE = "UPDATE page SET title = ?, description = ?, created = ?, updated = ?, views = ? WHERE page.id = ?";
    String DELETE_PAGE = "DELETE from page where page.id = ?";

    @Override
    public void createPageForWebsite(int websiteId, Page page) {
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(CREATE_PAGE);
            pStatement.setInt(1, page.getId());
            pStatement.setInt(2, websiteId);
            pStatement.setString(3, page.getTitle());
            pStatement.setString(4, page.getDescription());
            pStatement.setTimestamp(5, page.getCreated());
            pStatement.setTimestamp(6, page.getUpdated());
            pStatement.setInt(7, page.getViews());
            pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Page> findAllPages() {
        Collection<Page> allPages = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement = null;
        Page page = null;
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_PAGES);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int websiteId = resultSet.getInt("website");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                Timestamp created = resultSet.getTimestamp("created");
                Timestamp updated = resultSet.getTimestamp("updated");
                Integer views = resultSet.getInt("views");
                page = new Page(id, title, description, created, updated, views);
                allPages.add(page);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allPages;
    }

    @Override
    public Page findPageById(int pageId) {
        Page page = null;
        ResultSet resultSet = null;
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(FIND_PAGE_BY_ID);
            pStatement.setInt(1, pageId);
            resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                pageId = resultSet.getInt("id");
                int websiteId = resultSet.getInt("website");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                Timestamp created = resultSet.getTimestamp("created");
                Timestamp updated = resultSet.getTimestamp("updated");
                Integer views = resultSet.getInt("views");
                page = new Page(pageId, title, description, created, updated, views);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return page;
    }

    @Override
    public Collection<Page> findPagesForWebsite(int websiteId) {
        Collection<Page> websitePages = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(FIND_PAGES_FOR_WEBSITE);
            pStatement.setInt(1, websiteId);
            resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                Timestamp created = resultSet.getTimestamp("created");
                Timestamp updated = resultSet.getTimestamp("updated");
                Integer views = resultSet.getInt("views");
                Page page = new Page(id, title, description, created, updated, views);
                websitePages.add(page);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return websitePages;
    }

    @Override
    public int updatePage(int pageId, Page page) {
        int status = 0;
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(UPDATE_PAGE);
            pStatement.setString(1, page.getTitle());
            pStatement.setString(2, page.getDescription());
            pStatement.setTimestamp(3, page.getCreated());
            pStatement.setTimestamp(4, page.getUpdated());
            pStatement.setInt(5, page.getViews());
            pStatement.setInt(6, pageId);
            status = pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int deletePage(int pageId) {
        int status = 0;
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(DELETE_PAGE);
            pStatement.setInt(1, pageId);
            status = pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
