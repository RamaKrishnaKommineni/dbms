package edu.northeastern.cs5200.Daos;

import edu.northeastern.cs5200.models.Role;

public interface RoleDao{
	void assignWebsiteRole(int developerId, int websiteId, String role);
	void assignPageRole(int developerId, int pageId, String role);
	int deleteWebsiteRole(int developerId, int websiteId, String role);
	int deletePageRole(int developerId, int pageId); 
}
