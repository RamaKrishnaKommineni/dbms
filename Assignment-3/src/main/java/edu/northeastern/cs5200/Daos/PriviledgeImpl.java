package edu.northeastern.cs5200.Daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.northeastern.cs5200.DatabaseConnection;
import edu.northeastern.cs5200.Daos.PriviledgeDao;

public class PriviledgeImpl implements PriviledgeDao{
	private static PriviledgeImpl instance = null;
	private Connection connection = null;
	private PreparedStatement pStatement = null;
	private PriviledgeImpl(){
	}

	public static PriviledgeImpl getInstance(){
		if (instance == null) instance = new PriviledgeImpl();
		return instance;
	}

	String insertWebsitePriviledge = "insert into website_priviledge (priviledge, website, developer) values (?, ?, ?)";
	@Override
	public void assignWebsitePrivilege(int developerId, int websiteId, String priviledge){
		try{
			connection = DatabaseConnection.getInstance().getConnection();
			pStatement = connection.prepareStatement(insertWebsitePriviledge);
			pStatement.setString(1, priviledge);
			pStatement.setInt(2, websiteId);
			pStatement.setInt(3, developerId);
			pStatement.executeUpdate();
		} 
		catch (SQLException e){
			e.printStackTrace();
		}
	}

	String insertPagePriviledge = "insert into page_priviledge (priviledge, page, developer) values (?, ?, ?)";
	@Override
	public void assignPagePriviledge(int developerId, int pageId, String priviledge){
		try{
			connection = DatabaseConnection.getInstance().getConnection();
			pStatement = connection.prepareStatement(insertPagePriviledge);
			pStatement.setString(1, priviledge);
			pStatement.setInt(2, pageId);
			pStatement.setInt(3, developerId);
			pStatement.executeUpdate();
		} 
		catch (SQLException e){
			e.printStackTrace();
		}
	 }

	String deleteWebsitePriviledge = "delete from website_priviledge where website_priviledge.priviledge = ? and website_priviledge.developer = ? and website_priviledge.website = ?";
	@Override
	public int deleteWebsitePriviledge(int developerId, int websiteId, String priviledge){
		int status = 0;
		try{
			this.connection = DatabaseConnection.getInstance().getConnection();
			pStatement = connection.prepareStatement(deleteWebsitePriviledge);
			pStatement.setString(1, priviledge);
			pStatement.setInt(2, developerId);
			pStatement.setInt(3, websiteId);
			status = pStatement.executeUpdate();
		} 
		catch (SQLException e){
			e.printStackTrace();
		}
		return status;
	}

	String deletePageByPriviledge = "delete from page_priviledge where page_priviledge.priviledge = ? and page_priviledge.developer = ? and page_priviledge.page = ?";
	@Override
	public int deletePagePriviledge(int developerId, int pageId, String priviledge){
		int status = 0;
		try{
			this.connection = DatabaseConnection.getInstance().getConnection();
			pStatement = connection.prepareStatement(deletePageByPriviledge);
			pStatement.setString(1, priviledge);
			pStatement.setInt(2, developerId);
			pStatement.setInt(3, pageId);
			status = pStatement.executeUpdate();
		} 
		catch (SQLException e){
			e.printStackTrace();
		}
		return status;
	}
}
