package edu.northeastern.cs5200.Daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.northeastern.cs5200.DatabaseConnection;
import edu.northeastern.cs5200.Daos.PriviledgeDao;

public class PriviledgeImpl implements PriviledgeDao {

    private static PriviledgeImpl instance = null;
    String INSERT_WEBSITE_PRIVILEDGE = "INSERT into website_priviledge (priviledge, website, developer) VALUES (?, ?, ?)";
    String INSERT_PAGE_PRIVILEDGE = "INSERT into page_priviledge (priviledge, page, developer) VALUES (?, ?, ?)";
    String DELETE_WEBSITE_PRIVILEDGE = "DELETE from website_priviledge where website_priviledge.priviledge = ? and website_priviledge.developer = ? and website_priviledge.website = ?";
    String DELETE_PAGE_PRIVILEDGE = "DELETE from page_priviledge where page_priviledge.priviledge = ? and page_priviledge.developer = ? and page_priviledge.page = ?";
    private DatabaseConnection connection = null;
    private PreparedStatement pStatement = null;
    private PriviledgeImpl() {
    }

    public static PriviledgeImpl getInstance() {
        if (instance == null) instance = new PriviledgeImpl();
        return instance;
    }

    @Override
    public void assignWebsitePrivilege(int developerId, int websiteId, String priviledge) {
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(INSERT_WEBSITE_PRIVILEDGE);
            pStatement.setString(1, priviledge);
            pStatement.setInt(2, websiteId);
            pStatement.setInt(3, developerId);
            pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void assignPagePriviledge(int developerId, int pageId, String priviledge) {
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(INSERT_PAGE_PRIVILEDGE);
            pStatement.setString(1, priviledge);
            pStatement.setInt(2, pageId);
            pStatement.setInt(3, developerId);
            pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int deleteWebsitePriviledge(int developerId, int websiteId, String priviledge) {
        int status = 0;
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(DELETE_WEBSITE_PRIVILEDGE);
            pStatement.setString(1, priviledge);
            pStatement.setInt(2, developerId);
            pStatement.setInt(3, websiteId);
            status = pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }


    @Override
    public int deletePagePriviledge(int developerId, int pageId, String priviledge) {
        int status = 0;
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(DELETE_PAGE_PRIVILEDGE);
            pStatement.setString(1, priviledge);
            pStatement.setInt(2, developerId);
            pStatement.setInt(3, pageId);
            status = pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
