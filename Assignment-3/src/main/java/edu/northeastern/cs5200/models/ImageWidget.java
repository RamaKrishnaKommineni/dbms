package edu.northeastern.cs5200.models;

public class ImageWidget extends Widget{
	private String src;
	private Widget widget;

	public ImageWidget(Integer id, String name, Integer width, Integer height, String cssClass, String cssStyle, Integer order, String text, String src){
		super(id, name, width, height, cssClass, cssStyle, order, text);
		this.src = src;
	}

	public String getSrc(){
		return src;
	}

	public void setSrc(String src){
		this.src = src;
	}

	public Widget getWidget(){
		return widget;
	}

	public void setWidget(Widget widget){
		this.widget = widget;
	}
}
