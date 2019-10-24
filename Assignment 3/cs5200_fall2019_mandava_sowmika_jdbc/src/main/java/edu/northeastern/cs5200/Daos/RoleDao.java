package edu.northeastern.cs5200.Daos;

import edu.northeastern.cs5200.models.Role;

public interface RoleDao {

    void assignWebsiteRole(int developerId, int websiteId, int roleId);
    void assignPageRole(int developerId, int pageId, int roleId);
    int deleteWebsiteRole(int developerId, int websiteId, int roleId);
    int deletePageRole(int developerId, int pageId, int roleId);
}
