package edu.northeastern.cs5200.Daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.DatabaseConnection;
import edu.northeastern.cs5200.Daos.WebsiteDao;
import edu.northeastern.cs5200.models.Website;

public class WebsiteImpl implements WebsiteDao {

    private DatabaseConnection connection = null;
    private PreparedStatement pStatement = null;
    private static WebsiteImpl instance= null;
    private WebsiteImpl() {}

    public static WebsiteImpl getInstance() {
        if(instance == null)
            instance = new WebsiteImpl();
        return instance;
    }

    String CREATE_WEBSITE = "INSERT into website (id, name, description, created, updated, visits) VALUES (?, ?, ?, ?, ?, ?)";
    String FIND_ALL_WEBSITES = "SELECT * from website";
    String FIND_WEBSITES_FOR_DEVELOPER = "SELECT website.* from person inner join developer on developer.person_id = person.id inner join website_role on website_role.developer_id = developer.id inner join website on website.id = website_role.website_id WHERE developer.id=?";
    String FIND_WEBSITE_BY_ID = "SELECT * from website where website.id=?";
    String UPDATE_WEBSITE = "UPDATE website SET name = ?, description = ?, created = ?, updated = ?, visits = ? WHERE website.id = ?";
    String DELETE_WEBSITE = "DELETE from website where website.id = ?";

    @Override
    public void createWebsiteForDeveloper(int developerId, Website website) {
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(CREATE_WEBSITE);
            pStatement.setInt(1, website.getId());
            pStatement.setString(2, website.getName());
            pStatement.setString(3, website.getDescription());
            pStatement.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
            pStatement.setTimestamp(5, new java.sql.Timestamp(new java.util.Date().getTime()));
            pStatement.setInt(6, website.getVisits());
            pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Website> findAllWebsites() {
        ResultSet resultSet = null;
        Statement statement = null;
        Collection<Website> websites = new ArrayList<>();
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_WEBSITES);
            websites = getAllWebsites(resultSet, websites);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return websites;
    }

    private Collection<Website> getAllWebsites(ResultSet resultSet, Collection<Website> websites) throws SQLException {
        Website website;
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            Timestamp created = resultSet.getTimestamp("created");
            Timestamp updated = resultSet.getTimestamp("updated");
            int visits = resultSet.getInt("visits");
            website = new Website(id, description, name, created, updated, visits);
            websites.add(website);
        }
        return websites;
    }

    @Override
    public Collection<Website> findWebsitesForDeveloper(int developerId) {
        ResultSet resultSet = null;
        Collection<Website> websites = new ArrayList<>();
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(FIND_WEBSITES_FOR_DEVELOPER);
            pStatement.setInt(1, developerId);
            resultSet = pStatement.executeQuery();
            websites = getAllWebsites(resultSet, websites);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return websites;
    }

    @Override
    public Website findWebsiteById(int websiteId) {
        Website website = null;
        ResultSet resultSet = null;
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(FIND_WEBSITE_BY_ID);
            pStatement.setInt(1, websiteId);
            resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                websiteId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Timestamp created = resultSet.getTimestamp("created");
                Timestamp updated = resultSet.getTimestamp("updated");
                int visits = resultSet.getInt("visits");
                website = new Website(websiteId, description, name, created, updated, visits);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return website;
    }

    @Override
    public int updateWebsite(int websiteId, Website website) {
        int status = 0;
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(UPDATE_WEBSITE);
            pStatement.setString(1, website.getName());
            pStatement.setString(2, website.getDescription());
            pStatement.setTimestamp(3, website.getCreated());
            pStatement.setTimestamp(4, website.getUpdated());
            pStatement.setInt(5, website.getVisits());
            pStatement.setInt(6,  websiteId);
            status = pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int deleteWebsite(int websiteId) {
        int status = 0;
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(DELETE_WEBSITE);
            pStatement.setInt(1, websiteId);
            status = pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;

    }
}
