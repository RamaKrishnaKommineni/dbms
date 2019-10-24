package edu.northeastern.cs5200.Daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.northeastern.cs5200.DatabaseConnection;
import edu.northeastern.cs5200.Daos.RoleDao;

public class RoleImpl implements RoleDao{
	private Connection connection = null;
	private PreparedStatement pStatement = null;
	private static RoleImpl instance= null;
	private RoleImpl(){
	}

	public static RoleImpl getInstance(){
		if(instance == null)
			instance = new RoleImpl();
		return instance;
	}

	String insertWebsiteRole = "insert into website_role (role, website, developer) values (?, ?, ?)";
	@Override
	public void assignWebsiteRole(int developerId, int websiteId, String role){
		try{
			connection = DatabaseConnection.getInstance().getConnection();
			pStatement= connection.prepareStatement(insertWebsiteRole);
			pStatement.setString(1,role);
			pStatement.setInt(2,websiteId);
			pStatement.setInt(3,developerId);
			pStatement.executeUpdate();
		} 
		catch (SQLException e){
			e.printStackTrace();
		}
	}

	String insertPageRole = "insert into page_role (role, developer, page) values (?, ?, ?)";
	@Override
	public void assignPageRole(int developerId, int pageId, String role){
		try{
			connection = DatabaseConnection.getInstance().getConnection();
			pStatement= connection.prepareStatement(insertPageRole);
			pStatement.setString(1,role);
			pStatement.setInt(2,developerId);
			pStatement.setInt(3,pageId);
			pStatement.executeUpdate();
		} 
		catch (SQLException e){
			e.printStackTrace();
		}
	}

	String deleteWebsiteRole = "delete from website_role where website_role.role = ? and website_role.developer = ? and website_role.website = ?";
	@Override
	public int deleteWebsiteRole(int developerId, int websiteId, String role){
		int status = 0;
		try{
			connection = DatabaseConnection.getInstance().getConnection();
			pStatement= connection.prepareStatement(deleteWebsiteRole);
			pStatement.setString(1, role);
			pStatement.setInt(2,  developerId);
			pStatement.setInt(3, websiteId);
			status = pStatement.executeUpdate();
		} 
		catch (SQLException e){
			e.printStackTrace();
		}
		return status;
	}
	public String getRoleById(int roleId, java.sql.Connection connection){
		final String findRoleUsingId = "select name from role where role.id = ?";
		String roleName = null;
		ResultSet resultSet = null;
		try{
			pStatement= connection.prepareStatement(findRoleUsingId);
			pStatement.setInt(1, roleId);
			resultSet = pStatement.executeQuery();
			if(resultSet.next())
				roleName = resultSet.getString("name");
		} 
		catch (SQLException e){
			e.printStackTrace();
		}
		return roleName;
	}

	String deletPageRole = "delete from page_role where page_role.developer = ? and page_role.page = ?";
	@Override
	public int deletePageRole(int developerId, int pageId){
		int status = 0;
		try{
			connection = DatabaseConnection.getInstance().getConnection();
			pStatement= connection.prepareStatement(deletPageRole);
			pStatement.setInt(1,developerId);
			pStatement.setInt(2,pageId);
			status = pStatement.executeUpdate();
		} 
		catch (SQLException e){
			e.printStackTrace();
		}
		return status;
	}
}
