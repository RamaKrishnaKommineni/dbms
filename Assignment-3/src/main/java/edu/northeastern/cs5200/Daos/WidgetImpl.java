package edu.northeastern.cs5200.Daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.DatabaseConnection;
import edu.northeastern.cs5200.Daos.WidgetDao;
import edu.northeastern.cs5200.models.HeadingWidget;
import edu.northeastern.cs5200.models.HtmlWidget;
import edu.northeastern.cs5200.models.ImageWidget;
import edu.northeastern.cs5200.models.Widget;
import edu.northeastern.cs5200.models.YouTubeWidget;

public class WidgetImpl implements WidgetDao{
	private Connection connection = null;
	private PreparedStatement pStatement = null;
	private static WidgetImpl instance= null;
	private WidgetImpl(){
	}

	public static WidgetImpl getInstance(){
		if(instance == null)
			instance = new WidgetImpl();
		return instance;
	}

	@Override
	public void createWidgetForPage(int pageId, Widget widget){
		try{
			connection = DatabaseConnection.getInstance().getConnection();
			Boolean isHeadingWidget = widget instanceof HeadingWidget;
			Boolean isHtmlWidget = widget instanceof HtmlWidget;
			Boolean isImageWidget = widget instanceof ImageWidget;
			Boolean isYoutubeWidget = widget instanceof YouTubeWidget;
			if(isHeadingWidget){
				createHeadingWidget(pageId, (HeadingWidget) widget);
			}
			if(isHtmlWidget){
				createHtmlWidget(pageId, (HtmlWidget) widget);
			}
			if(isImageWidget){
				createImageWidget(pageId, (ImageWidget) widget);
			}
			if(isYoutubeWidget){
				createYoutubeWidget(pageId, (YouTubeWidget) widget);
			}
		} 
		catch (SQLException e){
			e.printStackTrace();
		}
	}

	String createHeadingWidget = "insert into widget (id, page, name, width, height, css_class, css_style, text, order_widget, DTYPE, heading_size) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	String createHtmlWidget = "insert into widget (id, page, name, width, height, css_class, css_style, text, order_widget, DTYPE, html_html) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	String createImageWidget = "insert into widget (id, page, name, width, height, css_class, css_style, text, order_widget, DTYPE, image_src) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	String createYoutubeWidget = "insert into widget (id, page, name, width, height, css_class, css_style, text, order_widget, DTYPE, youtube_url, youtube_shareble, youtube_expandable) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private void createHeadingWidget(int pageId, HeadingWidget widget) throws SQLException{
		pStatement = connection.prepareStatement(createHeadingWidget);
		HeadingWidget headingWidget = widget;
		pStatement.setInt(1,headingWidget.getId());
		pStatement.setInt(2,pageId);
		pStatement.setString(3,headingWidget.getName());
		pStatement.setInt(4,headingWidget.getWidth());
		pStatement.setInt(5,headingWidget.getHeight());
		pStatement.setString(6,headingWidget.getCssClass());
		pStatement.setString(7,headingWidget.getCssStyle());
		pStatement.setString(8,headingWidget.getText());
		pStatement.setInt(9,headingWidget.getOrder());
		pStatement.setString(10,"heading");
		pStatement.setInt(11,headingWidget.getHeadingSize());
		pStatement.executeUpdate();
	}
	private void createHtmlWidget(int pageId, HtmlWidget widget) throws SQLException{
		pStatement = connection.prepareStatement(createHtmlWidget);
		HtmlWidget htmlWidget = widget;
		pStatement.setInt(1,htmlWidget.getId());
		pStatement.setInt(2,pageId);
		pStatement.setString(3,htmlWidget.getName());
		pStatement.setInt(4,htmlWidget.getWidth());
		pStatement.setInt(5,htmlWidget.getHeight());
    	pStatement.setString(6,htmlWidget.getCssClass());
    	pStatement.setString(7,htmlWidget.getCssStyle());
    	pStatement.setString(8,htmlWidget.getText());
    	pStatement.setInt(9,htmlWidget.getOrder());
    	pStatement.setString(10,"html");
    	pStatement.setString(11,htmlWidget.getHtml());
    	pStatement.executeUpdate();
	}
	private void createImageWidget(int pageId, ImageWidget widget) throws SQLException{
		pStatement = connection.prepareStatement(createImageWidget);
		ImageWidget imageWidget = widget;
		pStatement.setInt(1,imageWidget.getId());
		pStatement.setInt(2,pageId);
		pStatement.setString(3,imageWidget.getName());
    	pStatement.setInt(4,imageWidget.getWidth());
    	pStatement.setInt(5,imageWidget.getHeight());
    	pStatement.setString(6,imageWidget.getCssClass());
    	pStatement.setString(7,imageWidget.getCssStyle());
    	pStatement.setString(8,imageWidget.getText());
    	pStatement.setInt(9,imageWidget.getOrder());
    	pStatement.setString(10,"image");
    	pStatement.setString(11,imageWidget.getSrc());
    	pStatement.executeUpdate();
	}
	private void createYoutubeWidget(int pageId, YouTubeWidget widget) throws SQLException{
		pStatement = connection.prepareStatement(createYoutubeWidget);
		YouTubeWidget youtubeWidget = widget;
		pStatement.setInt(1,youtubeWidget.getId());
		pStatement.setInt(2,pageId);
		pStatement.setString(3,youtubeWidget.getName());
		pStatement.setInt(4,youtubeWidget.getWidth());
		pStatement.setInt(5,youtubeWidget.getHeight());
		pStatement.setString(6,youtubeWidget.getCssClass());
		pStatement.setString(7,youtubeWidget.getCssStyle());
		pStatement.setString(8,youtubeWidget.getText());
		pStatement.setInt(9,youtubeWidget.getOrder());
    	pStatement.setString(10,"youtube");
    	pStatement.setString(11,youtubeWidget.getUrl());
    	pStatement.setBoolean(12,youtubeWidget.isShareble());
    	pStatement.setBoolean(13,youtubeWidget.getExpandable());
    	pStatement.executeUpdate();
	}
	
	String findAllWidgets = "select * from widget";
	@Override
	public Collection<Widget> findAllWidgets(){
		Collection<Widget> widgets = new ArrayList<>();
		Statement statement = null;
		ResultSet resultSet =null;
		try{
			connection = DatabaseConnection.getInstance().getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(findAllWidgets);
			while (resultSet.next()){
				int id = resultSet.getInt("id");
				int pageId = resultSet.getInt("page");
				String name = resultSet.getString("name");
				int width = resultSet.getInt("width");
				int height = resultSet.getInt("height");
				String cssClass = resultSet.getString("css_class");
				String cssStyle = resultSet.getString("css_style");
				String text = resultSet.getString("text");
        		int order = resultSet.getInt("order");
        		String DTYPE = resultSet.getString("dtype");
        		Boolean isHeadingWidget = DTYPE.equals("heading");
        		Boolean isHtmlWidget = DTYPE.equals("html");
        		Boolean isYoutubeWidget = DTYPE.equals("youtube");
        		Boolean isImageWidget = DTYPE.equals("image");
        		if(isHeadingWidget){
        			int headingSize = resultSet.getInt("size");
        			widgets.add(new HeadingWidget(id, name, width, height, cssClass, cssStyle, order, text,headingSize));
        		}	
        		if(isYoutubeWidget){
        			String url = resultSet.getString("url");
        			Boolean shareble = resultSet.getBoolean("shareble");
        			Boolean expandable = resultSet.getBoolean("expandable");
        			widgets.add(new YouTubeWidget(id, name, width, height, cssClass, cssStyle, order, text, url, shareble, expandable));
        		}
        		if(isHtmlWidget){
        			String html = resultSet.getString("html");
        			widgets.add(new HtmlWidget(id, name, width, height, cssClass, cssStyle, order, text, html));
        		}
        		if(isImageWidget){
        			String src = resultSet.getString("src");
        			widgets.add(new ImageWidget(id, name, width, height, cssClass, cssStyle, order, text, src));
        		}
			}
		} 
		catch (SQLException e){
			e.printStackTrace();
		}
		return widgets;
	}

	String findWidgetById = "select * from widget where widget.id=?";
	@Override
	public Widget findWidgetById(int widgetId){
		ResultSet resultSet = null;
		try{
			connection = DatabaseConnection.getInstance().getConnection();
			pStatement = connection.prepareStatement(findWidgetById);
			pStatement.setInt(1,widgetId);
			resultSet = pStatement.executeQuery();
			if(resultSet.next()){
				widgetId = resultSet.getInt("id");
				int pageId = resultSet.getInt("page");
				String name = resultSet.getString("name");
				int width = resultSet.getInt("width");
				int height = resultSet.getInt("height");
				String cssClass = resultSet.getString("css_class");
				String cssStyle = resultSet.getString("css_style");
				String text = resultSet.getString("text");
				int order = resultSet.getInt("order");
				String DTYPE = resultSet.getString("dtype");
				Boolean isHeadingWidget = DTYPE.equals("heading");
				Boolean isHtmlWidget = DTYPE.equals("html");
				Boolean isImageWidget = DTYPE.equals("image");
				Boolean isYoutubeWidget = DTYPE.equals("youtube");
				if(isHeadingWidget){
					int headingSize = resultSet.getInt("size");
					return new HeadingWidget(widgetId, name, width, height, cssClass, cssStyle, order, text, headingSize);
				}
				if(isHtmlWidget){
					String html = resultSet.getString("html");
					return new HtmlWidget(widgetId, name, width, height, cssClass, cssStyle, order, text, html);
				}
				if(isImageWidget){
					String src = resultSet.getString("src");
					return new ImageWidget(widgetId, name, width, height, cssClass, cssStyle, order, text, src);
				}
				if(isYoutubeWidget){
					String url = resultSet.getString("url");
					Boolean shareble = resultSet.getBoolean("shareble");
					Boolean expandable = resultSet.getBoolean("expandable");
					return new YouTubeWidget(widgetId,  name, width, height, cssClass, cssStyle, order, text, url, shareble, expandable);
				}
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	String findWidgetsForPage = "select * from widget where widget.page=?";
	@Override
	public Collection<Widget> findWidgetsForPage(int pageId){
		Collection<Widget> widgets = new ArrayList<>();
		ResultSet resultSet = null;
		try{
			connection = DatabaseConnection.getInstance().getConnection();
			pStatement = connection.prepareStatement(findWidgetsForPage);
			pStatement.setInt(1,pageId);
			resultSet = pStatement.executeQuery();
			while(resultSet.next()){
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int width = resultSet.getInt("width");
				int height = resultSet.getInt("height");
				String cssClass = resultSet.getString("css_class");
				String cssStyle = resultSet.getString("css_style");
				String text = resultSet.getString("text");
				int order = resultSet.getInt("order");
				String DTYPE= resultSet.getString("dtype");
				Boolean isHeadingWidget = DTYPE.equals("heading");
				Boolean isHtmlWidget = DTYPE.equals("html");
				Boolean isImageWidget = DTYPE.equals("image");
				Boolean isYoutubeWidget = DTYPE.equals("youtube");
				if(isHeadingWidget){
					int headingSize = resultSet.getInt("size");
					widgets.add(new HeadingWidget(id,name, width, height, cssClass, cssStyle, order, text, headingSize));
				}
				if(isHtmlWidget){
					String html = resultSet.getString("html");
					widgets.add(new HtmlWidget(id, name, width, height, cssClass, cssStyle, order, text, html));
				}
				if(isImageWidget){
					String src = resultSet.getString("src");
					widgets.add(new ImageWidget(id, name, width, height, cssClass, cssStyle, order, text, src));
				}
				if(isYoutubeWidget){
					String url = resultSet.getString("url");
					Boolean shareble = resultSet.getBoolean("shareble");
					Boolean expandable = resultSet.getBoolean("expandable");
					widgets.add(new YouTubeWidget(id, name, width, height, cssClass, cssStyle, order, text, url, shareble, expandable));
				}
			}
		} 
		catch(SQLException e){
			e.printStackTrace();
		}
		return widgets;
	}
	
	String updateHeadingWidget = "update widget set name = ?, width = ?, height = ?, css_class = ?, css_style = ?, text = ?, order_widget = ?, DTYPE = ?, heading_size = ? where widget.id = ?";
	String updateHtmlWidget = "update widget set name = ?, width = ?, height = ?, css_class = ?, css_style = ?, text = ?, order_widget = ?, DTYPE = ?, html_html = ? where widget.id = ?";
	String updateImageWidget = "update widget set name = ?, width = ?, height = ?, css_class = ?, css_style = ?, text = ?, order_widget = ?, DTYPE = ?, image_src = ? where widget.id = ?";
	String updateYoutubeWidget = "update widget set name = ?, width = ?, height = ?, css_class = ?, css_style = ?, text = ?, order_widget = ?, DTYPE = ?, youtube_url = ?, youtube_shareble = ?, youtube_expandable = ? where widget.id = ?";
	@Override
	public int updateWidget(int widgetId, Widget widget){
		int status = 0;
		try{
			this.connection = DatabaseConnection.getInstance().getConnection();
			Boolean isHeadingWidget = widget instanceof HeadingWidget;
			Boolean isHtmlWidget = widget instanceof HtmlWidget;
			Boolean isImageWidget = widget instanceof ImageWidget;
			Boolean isYoutubeWidget = widget instanceof YouTubeWidget;
			if(isHeadingWidget){
				pStatement = connection.prepareStatement(updateHeadingWidget);
				HeadingWidget headingWidget = (HeadingWidget) widget;
				pStatement.setString(1,headingWidget.getName());
				pStatement.setInt(2,headingWidget.getWidth());
				pStatement.setInt(3,headingWidget.getHeight());
				pStatement.setString(4,headingWidget.getCssClass());
				pStatement.setString(5,headingWidget.getCssStyle());
				pStatement.setString(6,headingWidget.getText());
				pStatement.setInt(7,headingWidget.getOrder());
				pStatement.setString(8,"heading");
				pStatement.setInt(9,headingWidget.getHeadingSize());
				pStatement.setInt(10,widgetId);
				status = pStatement.executeUpdate();
			}	
			if(isHtmlWidget){
				pStatement = connection.prepareStatement(updateHtmlWidget);
				HtmlWidget htmlWidget = (HtmlWidget) widget;
				pStatement.setString(1,htmlWidget.getName());
				pStatement.setInt(2,htmlWidget.getWidth());
				pStatement.setInt(3,htmlWidget.getHeight());
				pStatement.setString(4,htmlWidget.getCssClass());
				pStatement.setString(5,htmlWidget.getCssStyle());
				pStatement.setString(6,htmlWidget.getText());
				pStatement.setInt(7,htmlWidget.getOrder());
				pStatement.setString(8,"html");
				pStatement.setString(9,htmlWidget.getHtml());
				pStatement.setInt(10,widgetId);
				status = pStatement.executeUpdate();
			}
			if(isImageWidget){
				pStatement = connection.prepareStatement(updateImageWidget);
				ImageWidget imageWidget = (ImageWidget) widget;
				pStatement.setString(1,imageWidget.getName());
				pStatement.setInt(2,imageWidget.getWidth());
				pStatement.setInt(3,imageWidget.getHeight());
				pStatement.setString(4,imageWidget.getCssClass());
				pStatement.setString(5,imageWidget.getCssStyle());
				pStatement.setString(6,imageWidget.getText());
				pStatement.setInt(7,imageWidget.getOrder());
				pStatement.setString(8,"image");
				pStatement.setString(9,imageWidget.getSrc());
				pStatement.setInt(10,widgetId);
				status = pStatement.executeUpdate();
			}
			if(isYoutubeWidget){
				pStatement = connection.prepareStatement(updateYoutubeWidget);
				YouTubeWidget youtubeWidget = (YouTubeWidget) widget;
				pStatement.setString(1,youtubeWidget.getName());
				pStatement.setInt(2,youtubeWidget.getWidth());
				pStatement.setInt(3,youtubeWidget.getHeight());
				pStatement.setString(4,youtubeWidget.getCssClass());
				pStatement.setString(5,youtubeWidget.getCssStyle());
				pStatement.setString(6,youtubeWidget.getText());
				pStatement.setInt(7,youtubeWidget.getOrder());
				pStatement.setString(8,"youtube");
				pStatement.setString(9,youtubeWidget.getUrl());
				pStatement.setBoolean(10,youtubeWidget.isShareble());
				pStatement.setBoolean(11,youtubeWidget.getExpandable());
				pStatement.setInt(12,widgetId);
				status = pStatement.executeUpdate();
			}
		} 
		catch(SQLException e){
			e.printStackTrace();
		}
		return status;
	}

	String deleteWidget = "delete from widget where widget.id = ?";
	@Override
	public int deleteWidget(int widgetId){
		int status = 0;
		try{
			connection = DatabaseConnection.getInstance().getConnection();
			pStatement = connection.prepareStatement(deleteWidget);
			pStatement.setInt(1,widgetId);
			status = pStatement.executeUpdate();
		} 
		catch(SQLException e){
			e.printStackTrace();
		}
		return status;
	}
}
