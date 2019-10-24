package edu.northeastern.cs5200.Daos;

import java.util.Collection;

import edu.northeastern.cs5200.models.User;

public interface UserDao {
    void createUser(User user);
    Collection<User> findAllUsers();
    User findUserById(int userId);
    User findUserByUsername(String username);
    User findUserByCredentials(String username, String password);
    int updateUser(int userId, User user);
    int deleteUser(int userId);
}
