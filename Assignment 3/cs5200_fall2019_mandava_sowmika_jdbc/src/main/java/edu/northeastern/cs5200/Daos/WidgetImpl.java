package edu.northeastern.cs5200.Daos;

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

public class WidgetImpl implements WidgetDao {

    private DatabaseConnection connection = null;
    private PreparedStatement pStatement = null;
    private static WidgetImpl instance= null;
    private WidgetImpl() {}

    public static WidgetImpl getInstance() {
        if(instance == null)
            instance = new WidgetImpl();
        return instance;
    }

    String CREATE_HEADING_WIDGET = "INSERT into widget (`id`, `page`, `name`, `width`, `height`, `css_class`, `css_style`, `text`, `order`, `dtype`, `size`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String CREATE_HTML_WIDGET = "INSERT into widget (`id`, `page`, `name`, `width`, `height`, `css_class`, `css_style`, `text`, `order`, `dtype`, html) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String CREATE_IMAGE_WIDGET = "INSERT into widget (id, page, `name`, width, height, css_class, css_style, text, `order`, dtype, src) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String CREATE_YOUTUBE_WIDGET = "INSERT into widget (id, page, `name`, width, height, css_class, css_style, text, `order`, dtype, url, shareble, expandable) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String FIND_ALL_WIDGETS = "SELECT * from widget";
    String FIND_WIDGET_BY_ID = "SELECT * from widget where widget.id=?";
    String FIND_WIDGETS_FOR_PAGE = "SELECT * from widget WHERE widget.page=?";
    String UPDATE_HEADING_WIDGET = "UPDATE widget SET `name` = ?, width = ?, height = ?, css_class = ?, css_style = ?, text = ?, `order` = ?, dtype = ?, `size` = ? WHERE widget.id = ?";
    String UPDATE_HTML_WIDGET = "UPDATE widget SET `name` = ?, width = ?, height = ?, css_class = ?, css_style = ?, text = ?, `order` = ?, dtype = ?, html = ? WHERE widget.id = ?";
    String UPDATE_IMAGE_WIDGET = "UPDATE widget SET `name` = ?, width = ?, height = ?, css_class = ?, css_style = ?, text = ?, `order` = ?, dtype = ?, src = ? WHERE widget.id = ?";
    String UPDATE_YOUTUBE_WIDGET = "UPDATE widget SET `name` = ?, width = ?, height = ?, css_class = ?, css_style = ?, text = ?, `order` = ?, dtype = ?, url = ?, shareble = ?, expandable = ? WHERE widget.id = ?";
    String DELETE_WIDGET = "DELETE from widget where widget.id = ?";

    @Override
    public void createWidgetForPage(int pageId, Widget widget) {
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            boolean isHeadingWidget = widget instanceof HeadingWidget;
            boolean isHtmlWidget = widget instanceof HtmlWidget;
            boolean isImageWidget = widget instanceof ImageWidget;
            boolean isYoutubeWidget = widget instanceof YouTubeWidget;

            if (isHeadingWidget) {
                createHeadingWidget(pageId, (HeadingWidget) widget);
            }

            if (isHtmlWidget) {
                createHtmlWidget(pageId, (HtmlWidget) widget);
            }

            if (isImageWidget) {
                createImageWidget(pageId, (ImageWidget) widget);
            }

            if (isYoutubeWidget) {

                createYoutubeWidget(pageId, (YouTubeWidget) widget);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createYoutubeWidget(int pageId, YouTubeWidget widget) throws SQLException, ClassNotFoundException {
        try{
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(CREATE_YOUTUBE_WIDGET);
            YouTubeWidget youtubeWidget = widget;
            pStatement.setInt(1, youtubeWidget.getId());
            pStatement.setInt(2, pageId);
            pStatement.setString(3, youtubeWidget.getName());
            pStatement.setInt(4, youtubeWidget.getWidth());
            pStatement.setInt(5, youtubeWidget.getHeight());
            pStatement.setString(6, youtubeWidget.getCssClass());
            pStatement.setString(7, youtubeWidget.getCssStyle());
            pStatement.setString(8, youtubeWidget.getText());
            pStatement.setInt(9, youtubeWidget.getOrder());
            pStatement.setString(10, "youtube");
            pStatement.setString(11, youtubeWidget.getUrl());
            pStatement.setBoolean(12, youtubeWidget.isShareble());
            pStatement.setBoolean(13, youtubeWidget.getExpandable());
            pStatement.executeUpdate();}
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createImageWidget(int pageId, ImageWidget widget) throws SQLException, ClassNotFoundException {
        java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
        pStatement = connection.prepareStatement(CREATE_IMAGE_WIDGET);
        ImageWidget imageWidget = widget;
        pStatement.setInt(1, imageWidget.getId());
        pStatement.setInt(2, pageId);
        pStatement.setString(3, imageWidget.getName());
        pStatement.setInt(4, imageWidget.getWidth());
        pStatement.setInt(5, imageWidget.getHeight());
        pStatement.setString(6, imageWidget.getCssClass());
        pStatement.setString(7, imageWidget.getCssStyle());
        pStatement.setString(8, imageWidget.getText());
        pStatement.setInt(9, imageWidget.getOrder());
        pStatement.setString(10, "image");
        pStatement.setString(11, imageWidget.getSrc());
        pStatement.executeUpdate();
    }

    private void createHtmlWidget(int pageId, HtmlWidget widget) throws SQLException {
        try{
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(CREATE_HTML_WIDGET);
            HtmlWidget htmlWidget = widget;
            pStatement.setInt(1, htmlWidget.getId());
            pStatement.setInt(2, pageId);
            pStatement.setString(3, htmlWidget.getName());
            pStatement.setInt(4, htmlWidget.getWidth());
            pStatement.setInt(5, htmlWidget.getHeight());
            pStatement.setString(6, htmlWidget.getCssClass());
            pStatement.setString(7, htmlWidget.getCssStyle());
            pStatement.setString(8, htmlWidget.getText());
            pStatement.setInt(9, htmlWidget.getOrder());
            pStatement.setString(10, "html");
            pStatement.setString(11, htmlWidget.getHtml());
            pStatement.executeUpdate();}
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createHeadingWidget(int pageId, HeadingWidget widget) throws SQLException {
        try{
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(CREATE_HEADING_WIDGET);
            HeadingWidget headingWidget = widget;
            pStatement.setInt(1, headingWidget.getId());
            pStatement.setInt(2, pageId);
            pStatement.setString(3, headingWidget.getName());
            pStatement.setInt(4, headingWidget.getWidth());
            pStatement.setInt(5, headingWidget.getHeight());
            pStatement.setString(6, headingWidget.getCssClass());
            pStatement.setString(7, headingWidget.getCssStyle());
            pStatement.setString(8, headingWidget.getText());
            pStatement.setInt(9, headingWidget.getOrder());
            pStatement.setString(10, "heading");
            pStatement.setInt(11, headingWidget.getHeadingSize());
            pStatement.executeUpdate();}
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Widget> findAllWidgets() {
        Collection<Widget> widgets = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet =null;
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_WIDGETS);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int pageId = resultSet.getInt("page");
                String name = resultSet.getString("name");
                int width = resultSet.getInt("width");
                int height = resultSet.getInt("height");
                String cssClass = resultSet.getString("css_class");
                String cssStyle = resultSet.getString("css_style");
                String text = resultSet.getString("text");
                int order = resultSet.getInt("order");
                String dtype = resultSet.getString("DTYPE");

                boolean isHeadingWidget = dtype.equals("heading");
                boolean isHtmlWidget = dtype.equals("html");
                boolean isYoutubeWidget = dtype.equals("youtube");
                boolean isImageWidget = dtype.equals("image");

                if (isHeadingWidget) {
                    int headingSize = resultSet.getInt("size");
                    widgets.add(new HeadingWidget(id, name, width, height, cssClass, cssStyle, order, text,headingSize));
                }

                if (isYoutubeWidget) {
                    String url = resultSet.getString("url");
                    Boolean shareble = resultSet.getBoolean("shareble");
                    Boolean expandable = resultSet.getBoolean("expandable");
                    widgets.add(new YouTubeWidget(id, name, width, height, cssClass, cssStyle, order, text, url, shareble, expandable));
                }

                if (isHtmlWidget) {
                    String html = resultSet.getString("html");
                    widgets.add(new HtmlWidget(id, name, width, height, cssClass, cssStyle, order, text, html));
                }

                if (isImageWidget) {
                    String src = resultSet.getString("src");
                    widgets.add(new ImageWidget(id, name, width, height, cssClass, cssStyle, order, text, src));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return widgets;
    }

    @Override
    public Widget findWidgetById(int widgetId) {
        ResultSet resultSet = null;
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(FIND_WIDGET_BY_ID);
            pStatement.setInt(1, widgetId);
            resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                widgetId = resultSet.getInt("id");
                int pageId = resultSet.getInt("page");
                String name = resultSet.getString("name");
                int width = resultSet.getInt("width");
                int height = resultSet.getInt("height");
                String cssClass = resultSet.getString("css_class");
                String cssStyle = resultSet.getString("css_style");
                String text = resultSet.getString("text");
                int order = resultSet.getInt("order");
                String dtype = resultSet.getString("dtype");

                boolean isHeadingWidget = dtype.equals("heading");
                boolean isHtmlWidget = dtype.equals("html");
                boolean isImageWidget = dtype.equals("image");
                boolean isYoutubeWidget = dtype.equals("youtube");

                if (isHeadingWidget) {
                    int headingSize = resultSet.getInt("size");
                    return new HeadingWidget(widgetId, name, width, height, cssClass, cssStyle, order, text, headingSize);
                }

                if (isHtmlWidget) {
                    String html = resultSet.getString("html");
                    return new HtmlWidget(widgetId, name, width, height, cssClass, cssStyle, order, text, html);
                }

                if (isImageWidget) {
                    String src = resultSet.getString("src");
                    return new ImageWidget(widgetId, name, width, height, cssClass, cssStyle, order, text, src);
                }

                if (isYoutubeWidget) {
                    String url = resultSet.getString("url");
                    Boolean shareble = resultSet.getBoolean("shareble");
                    Boolean expandable = resultSet.getBoolean("expandable");
                    return new YouTubeWidget(widgetId,  name, width, height, cssClass, cssStyle, order, text, url, shareble, expandable);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Widget> findWidgetsForPage(int pageId) {
        Collection<Widget> widgets = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(FIND_WIDGETS_FOR_PAGE);
            pStatement.setInt(1, pageId);
            resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int width = resultSet.getInt("width");
                int height = resultSet.getInt("height");
                String cssClass = resultSet.getString("css_class");
                String cssStyle = resultSet.getString("css_style");
                String text = resultSet.getString("text");
                int order = resultSet.getInt("order");
                String dtype = resultSet.getString("dtype");

                boolean isHeadingWidget = dtype.equals("heading");
                boolean isHtmlWidget = dtype.equals("html");
                boolean isImageWidget = dtype.equals("image");
                boolean isYoutubeWidget = dtype.equals("youtube");

                if (isHeadingWidget) {
                    int headingSize = resultSet.getInt("size");
                    widgets.add(new HeadingWidget(id,name, width, height, cssClass, cssStyle, order, text, headingSize));
                }

                if (isHtmlWidget) {
                    String html = resultSet.getString("html");
                    widgets.add(new HtmlWidget(id, name, width, height, cssClass, cssStyle, order, text, html));
                }

                if (isImageWidget) {
                    String src = resultSet.getString("src");
                    widgets.add(new ImageWidget(id, name, width, height, cssClass, cssStyle, order, text, src));
                }

                if (isYoutubeWidget) {
                    String url = resultSet.getString("url");
                    Boolean shareble = resultSet.getBoolean("shareble");
                    Boolean expandable = resultSet.getBoolean("expandable");
                    widgets.add(new YouTubeWidget(id, name, width, height, cssClass, cssStyle, order, text, url, shareble, expandable));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return widgets;
    }

    @Override
    public int updateWidget(int widgetId, Widget widget) {
        int status = 0;
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();

            boolean isHeadingWidget = widget instanceof HeadingWidget;
            boolean isHtmlWidget = widget instanceof HtmlWidget;
            boolean isImageWidget = widget instanceof ImageWidget;
            boolean isYoutubeWidget = widget instanceof YouTubeWidget;

            if (isHeadingWidget) {
                pStatement = connection.prepareStatement(UPDATE_HEADING_WIDGET);
                HeadingWidget headingWidget = (HeadingWidget) widget;
                pStatement.setString(1, headingWidget.getName());
                pStatement.setInt(2,  headingWidget.getWidth());
                pStatement.setInt(3,  headingWidget.getHeight());
                pStatement.setString(4, headingWidget.getCssClass());
                pStatement.setString(5, headingWidget.getCssStyle());
                pStatement.setString(6, headingWidget.getText());
                pStatement.setInt(7, headingWidget.getOrder());
                pStatement.setString(8,  "heading");
                pStatement.setInt(9, headingWidget.getHeadingSize());
                pStatement.setInt(10, widgetId);
                status = pStatement.executeUpdate();
            }

            if (isHtmlWidget) {
                pStatement = connection.prepareStatement(UPDATE_HTML_WIDGET);
                HtmlWidget htmlWidget = (HtmlWidget) widget;
                pStatement.setString(1, htmlWidget.getName());
                pStatement.setInt(2,  htmlWidget.getWidth());
                pStatement.setInt(3,  htmlWidget.getHeight());
                pStatement.setString(4, htmlWidget.getCssClass());
                pStatement.setString(5, htmlWidget.getCssStyle());
                pStatement.setString(6, htmlWidget.getText());
                pStatement.setInt(7, htmlWidget.getOrder());
                pStatement.setString(8,  "html");
                pStatement.setString(9, htmlWidget.getHtml());
                pStatement.setInt(10, widgetId);
                status = pStatement.executeUpdate();
            }

            if (isImageWidget) {
                pStatement = connection.prepareStatement(UPDATE_IMAGE_WIDGET);
                ImageWidget imageWidget = (ImageWidget) widget;
                pStatement.setString(1, imageWidget.getName());
                pStatement.setInt(2,  imageWidget.getWidth());
                pStatement.setInt(3,  imageWidget.getHeight());
                pStatement.setString(4, imageWidget.getCssClass());
                pStatement.setString(5, imageWidget.getCssStyle());
                pStatement.setString(6, imageWidget.getText());
                pStatement.setInt(7, imageWidget.getOrder());
                pStatement.setString(8,  "image");
                pStatement.setString(9, imageWidget.getSrc());
                pStatement.setInt(10, widgetId);
                status = pStatement.executeUpdate();
            }

            if (isYoutubeWidget) {
                pStatement = connection.prepareStatement(UPDATE_YOUTUBE_WIDGET);
                YouTubeWidget youtubeWidget = (YouTubeWidget) widget;
                pStatement.setString(1, youtubeWidget.getName());
                pStatement.setInt(2,  youtubeWidget.getWidth());
                pStatement.setInt(3,  youtubeWidget.getHeight());
                pStatement.setString(4, youtubeWidget.getCssClass());
                pStatement.setString(5, youtubeWidget.getCssStyle());
                pStatement.setString(6, youtubeWidget.getText());
                pStatement.setInt(7, youtubeWidget.getOrder());
                pStatement.setString(8,  "youtube");
                pStatement.setString(9, youtubeWidget.getUrl());
                pStatement.setBoolean(10, youtubeWidget.isShareble());
                pStatement.setBoolean(11, youtubeWidget.getExpandable());
                pStatement.setInt(12, widgetId);
                status = pStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int deleteWidget(int widgetId) {
        int status = 0;
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(DELETE_WIDGET);
            pStatement.setInt(1, widgetId);
            status = pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
