package edu.northeastern.cs5200.Daos;

import java.sql.Connection;
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

public class PageImpl implements PageDao{
	private Connection connection = null;
	private PreparedStatement pStatement = null;
	private static PageImpl instance= null;
	private PageImpl(){
	}

	public static PageImpl getInstance(){
		if(instance == null)
			instance = new PageImpl();
		return instance;
	}

	String createPage = "insert into page (id, website, title, description, created, updated, views) values (?, ?, ?, ?, ?, ?, ?)";
	@Override
	public void createPageForWebsite(int websiteId, Page page) {
		try {
			this.connection = DatabaseConnection.getInstance().getConnection();
			pStatement = connection.prepareStatement(createPage);
			pStatement.setInt(1, page.getId());
			pStatement.setInt(2, websiteId);
			pStatement.setString(3, page.getTitle());
			pStatement.setString(4, page.getDescription());
			pStatement.setTimestamp(5, page.getCreated());
			pStatement.setTimestamp(6, page.getUpdated());
			pStatement.setInt(7, page.getViews());
			pStatement.executeUpdate();
		} 
		catch (SQLException e){
			e.printStackTrace();
		}
	}

	String findAllPages = "select * from page";
	@Override
	public Collection<Page> findAllPages(){
		Collection<Page> allPages = new ArrayList<>();
		ResultSet resultSet = null;
		Statement statement = null;
		Page page = null;
		try{
			this.connection = DatabaseConnection.getInstance().getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(findAllPages);
			while (resultSet.next()){
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
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return allPages;
	}

	String findPageById = "select * from page where page.id=?";
	@Override
	public Page findPageById(int pageId){
		Page page = null;
		ResultSet resultSet = null;
		try{
				this.connection = DatabaseConnection.getInstance().getConnection();
				pStatement = connection.prepareStatement(findPageById);
				pStatement.setInt(1, pageId);
				resultSet = pStatement.executeQuery();
				if (resultSet.next()){
					pageId = resultSet.getInt("id");
					int websiteId = resultSet.getInt("website");
					String title = resultSet.getString("title");
					String description = resultSet.getString("description");
					Timestamp created = resultSet.getTimestamp("created");
					Timestamp updated = resultSet.getTimestamp("updated");
					Integer views = resultSet.getInt("views");
					page = new Page(pageId, title, description, created, updated, views);
				}
		} 
		catch (SQLException e){
			e.printStackTrace();
		}
		return page;
	}

	String findPagesForWebsite = "select * from page where page.website=?";
	@Override
	public Collection<Page> findPagesForWebsite(int websiteId){
		Collection<Page> websitePages = new ArrayList<>();
		ResultSet resultSet = null;
		try{
			this.connection = DatabaseConnection.getInstance().getConnection();
			pStatement = connection.prepareStatement(findPagesForWebsite);
			pStatement.setInt(1, websiteId);
			resultSet = pStatement.executeQuery();
			while (resultSet.next()){
				int id = resultSet.getInt("id");
				String title = resultSet.getString("title");
				String description = resultSet.getString("description");
				Timestamp created = resultSet.getTimestamp("created");
				Timestamp updated = resultSet.getTimestamp("updated");
				Integer views = resultSet.getInt("views");
				Page page = new Page(id, title, description, created, updated, views);
				websitePages.add(page);
			}
		} 
		catch (SQLException e){
			e.printStackTrace();
		}
		return websitePages;
	}

	String updatePage = "update page SET title = ?, description = ?, created = ?, updated = ?, views = ? where page.id = ?";
	@Override
	public int updatePage(int pageId, Page page){
		int status = 0;
		try{
			this.connection = DatabaseConnection.getInstance().getConnection();
			pStatement = connection.prepareStatement(updatePage);
			pStatement.setString(1, page.getTitle());
			pStatement.setString(2, page.getDescription());
			pStatement.setTimestamp(3, page.getCreated());
			pStatement.setTimestamp(4, page.getUpdated());
			pStatement.setInt(5, page.getViews());
			pStatement.setInt(6, pageId);
			status = pStatement.executeUpdate();
		} 
		catch (SQLException e){
			e.printStackTrace();
		}
		return status;
	}

	String deletePage = "delete from page where page.id = ?";
	@Override
	public int deletePage(int pageId){
		int status = 0;
		try{
			this.connection = DatabaseConnection.getInstance().getConnection();
			pStatement = connection.prepareStatement(deletePage);
			pStatement.setInt(1, pageId);
			status = pStatement.executeUpdate();
		} 
		catch (SQLException e){
			e.printStackTrace();
		}
		return status;
	}
}
