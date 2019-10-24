package edu.northeastern.cs5200.Daos;

import java.util.Collection;

import edu.northeastern.cs5200.models.Widget;

public interface WidgetDao{
	void createWidgetForPage(int pageId, Widget widget);
	Collection<Widget> findAllWidgets();
	Widget findWidgetById(int widgetId);
	Collection<Widget> findWidgetsForPage(int pageId);
	int updateWidget(int widgetId, Widget widget);
	int deleteWidget(int widgetId);
}

