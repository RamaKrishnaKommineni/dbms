package edu.northeastern.cs5200.models;

public class HtmlWidget extends Widget{
	private String html;
	private Widget widget;

	public HtmlWidget(Integer id, String name, Integer width, Integer height, String cssClass, String cssStyle, Integer order, String text, String html){
		super(id, name, width, height, cssClass, cssStyle, order, text);
		this.html = html;
	}

	public String getHtml(){
		return html;
	}

	public void setHtml(String html){
		this.html = html;
	}

	public Widget getWidget(){
		return widget;
	}

	public void setWidget(Widget widget){
		this.widget = widget;
	}
}
