package edu.northeastern.cs5200.models;

public class HeadingWidget extends Widget{
	private Integer headingSize;
	private Widget widget;

	public HeadingWidget(Integer id, String name, Integer width, Integer height, String cssClass, String cssStyle, Integer order, String text, Integer headingSize){
		super(id, name, width, height, cssClass, cssStyle, order, text);
		this.headingSize = headingSize;
	}

	public Integer getHeadingSize(){
		return headingSize;
	}

	public void setHeadingSize(Integer headingSize){
		this.headingSize = headingSize;
	}

	public Widget getWidget(){
		return widget;
	}

	public void setWidget(Widget widget){
		this.widget = widget;
	}
}
