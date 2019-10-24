package edu.northeastern.cs5200.models;

public class YouTubeWidget extends Widget{

    private String url;
    private Boolean shareble;
    private Boolean expandable;
    private Widget widget;


    public YouTubeWidget(Integer id, String name, Integer width, Integer height, String cssClass,
                         String cssStyle, Integer order, String text, String url, Boolean shareble,
                         Boolean expandable) {
        super(id, name, width, height, cssClass, cssStyle, order, text);
        this.url = url;
        this.shareble = shareble;
        this.expandable = expandable;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getShareble() {
        return shareble;
    }

    public void setShareble(Boolean shareble) {
        this.shareble = shareble;
    }

    public Boolean getExpandable() {
        return expandable;
    }

    public void setExpandable(Boolean expandable) {
        this.expandable = expandable;
    }

    public Widget getWidget() {
        return widget;
    }

    public void setWidget(Widget widget) {
        this.widget = widget;
    }

    public boolean isShareble() {
        return shareble;
    }
}
