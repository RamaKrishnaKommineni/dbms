package edu.northeastern.cs5200.Daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.northeastern.cs5200.DatabaseConnection;
import edu.northeastern.cs5200.Daos.RoleDao;

public class RoleImpl implements RoleDao {

    private DatabaseConnection connection = null;
    private PreparedStatement pStatement = null;
    private static RoleImpl instance= null;
    private RoleImpl() {}

    public static RoleImpl getInstance() {
        if(instance == null)
            instance = new RoleImpl();
        return instance;
    }

    String INSERT_WEBSITE_ROLE = "INSERT into website_role (role, website, developer) VALUES (?, ?, ?)";


    @Override
    public void assignWebsiteRole(int developerId, int websiteId, int roleId) {
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement= connection.prepareStatement(INSERT_WEBSITE_ROLE);
            pStatement.setInt(1, roleId);
            pStatement.setInt(2, websiteId);
            pStatement.setInt(3, developerId);
            pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    String INSERT_PAGE_ROLE = "INSERT into page_role (role, developer, page) VALUES (?, ?, ?)";

    @Override
    public void assignPageRole(int developerId, int pageId, int roleId) {
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement= connection.prepareStatement(INSERT_PAGE_ROLE);

            pStatement.setInt(1, roleId);
            pStatement.setInt(2, developerId);
            pStatement.setInt(3, pageId);
            pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    String DELETE_WEBSITE_ROLE = "DELETE from website_role where website_role.role = ? and website_role.developer = ? and website_role.website = ?";

    @Override
    public int deleteWebsiteRole(int developerId, int websiteId, int roleId) {
        int status = 0;
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            String roleName = getRoleById(roleId, connection);
            pStatement= connection.prepareStatement(DELETE_WEBSITE_ROLE);
            pStatement.setString(1, roleName);
            pStatement.setInt(2,  developerId);
            pStatement.setInt(3, websiteId);
            status = pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public String getRoleById(int roleId, java.sql.Connection connection) {
        final String FIND_ROLE_USING_ID = "SELECT name from role where role.id = ?";
        String roleName = null;
        ResultSet resultSet = null;
        try {
            pStatement= connection.prepareStatement(FIND_ROLE_USING_ID);
            pStatement.setInt(1, roleId);
            resultSet = pStatement.executeQuery();
            if (resultSet.next())
                roleName = resultSet.getString("name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleName;
    }

    String DELETE_PAGE_ROLE = "DELETE from page_role where page_role.role = ? and page_role.developer = ? and page_role.page = ?";

    @Override
    public int deletePageRole(int developerId, int pageId, int roleId) {
        int status = 0;
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            String roleName = getRoleById(roleId, connection);
            pStatement= connection.prepareStatement(DELETE_PAGE_ROLE);
            pStatement.setString(1, roleName);
            pStatement.setInt(2,  developerId);
            pStatement.setInt(3, pageId);
            status = pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
