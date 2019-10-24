package edu.northeastern.cs5200.Daos;

public interface PriviledgeDao{
	void assignWebsitePrivilege(int developerId, int websiteId, String priviledge);
	void assignPagePriviledge(int developerId, int pageId, String priviledge);
	int deleteWebsitePriviledge(int developerId, int websiteId, String priviledge);
	int deletePagePriviledge(int developerId, int pageId, String priviledge);
}
