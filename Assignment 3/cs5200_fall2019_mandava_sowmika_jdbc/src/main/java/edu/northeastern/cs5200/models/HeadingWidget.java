package edu.northeastern.cs5200.models;

public class HeadingWidget extends Widget{

    private Integer heading_size;
    private Widget widget;

    public HeadingWidget(Integer id, String name, Integer width, Integer height, String cssClass,
                         String cssStyle, Integer order, String text, Integer heading_size) {
        super(id, name, width, height, cssClass, cssStyle, order, text);
        this.heading_size = heading_size;
    }

    public Integer getHeadingSize() {
        return heading_size;
    }

    public void setHeadingSize(Integer heading_size) {
        this.heading_size = heading_size;
    }

    public Widget getWidget() {
        return widget;
    }

    public void setWidget(Widget widget) {
        this.widget = widget;
    }
}

