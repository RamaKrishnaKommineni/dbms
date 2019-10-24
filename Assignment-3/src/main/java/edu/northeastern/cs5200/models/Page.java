package edu.northeastern.cs5200.models;

import java.sql.Timestamp;
import java.util.Collection;

public class Page {
	private int id;
	private String title;
	private String description;
	private Timestamp created;
	private Timestamp updated;
	private Integer views;
	private Collection<Widget> widgets;
	private Website website;

	public Page(int id, String title, String description, Timestamp created, Timestamp updated, Integer views){
		this.id = id;
		this.title = title;
		this.description = description;
		this.created = created;
		this.updated = updated;
		this.views = views;
	}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public Timestamp getCreated(){
		return created;
	}

	public void setCreated(Timestamp created){
		this.created = created;
	}

	public Timestamp getUpdated(){
		return updated;
	}

	public void setUpdated(Timestamp updated){
		this.updated = updated;
	}

	public Integer getViews(){
		return views;
	}

	public void setViews(Integer views){
		this.views = views;
	}

	public Collection<Widget> getWidgets(){
		return widgets;
	}

	public void setWidgets(Collection<Widget> widgets){
		this.widgets = widgets;
	}

	public Website getWebsite(){
		return website;
	}

	public void setWebsite(Website website){
		this.website = website;
	}
}
