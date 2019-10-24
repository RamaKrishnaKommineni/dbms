package edu.northeastern.cs5200.Daos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.DatabaseConnection;
import edu.northeastern.cs5200.Daos.AddressDao;
import edu.northeastern.cs5200.Daos.PhoneDao;
import edu.northeastern.cs5200.Daos.UserDao;
import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.Phone;
import edu.northeastern.cs5200.models.User;

public class UserImpl implements UserDao {

    private DatabaseConnection connection = null;
    private PreparedStatement pStatement = null;
    private static UserImpl instance= null;
    private UserImpl() {}

    public static UserImpl getInstance() {
        if(instance == null)
            instance = new UserImpl();
        return instance;
    }

    String CREATE_PERSON = "INSERT INTO person (id, first_name, last_name, user_name, password, email, dob) VALUES (?, ?, ?, ?, ?, ?, ?)";
    String CREATE_USER = "INSERT INTO user (user_agreement, person) VALUES (?, ?)";
    String FIND_ALL_USERS =  "SELECT * FROM User, person WHERE User.person = person.id";
    String FIND_USER_BY_ID = "SELECT * FROM User, person WHERE User.person = person.id AND person.id=?";
    String FIND_USER_BY_USERNAME = "SELECT * FROM User, person WHERE User.person = person.id AND person.username=?";
    String FIND_USER_BY_CREDENTIALS = "SELECT * from user, person where user.person = person.id and person.username=? and person.password=?";
    String UPDATE_USER = "UPDATE user, person SET user_agreement = ?, first_name = ?, last_name = ?, username = ?, password = ?, email = ?, dob = ? WHERE person.id = user.person and user.person = ?";
    String DELETE_USER = "DELETE from person where person.id = ?";


    @Override
    public void createUser(User user) {
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(CREATE_PERSON);
            pStatement.setInt(1, user.getId());
            pStatement.setString(2, user.getFirstName());
            pStatement.setString(3,user.getLastName());
            pStatement.setString(4, user.getUsername());
            pStatement.setString(5, user.getPassword());
            pStatement.setString(6, user.getEmail());
            pStatement.setDate(7, (Date) user.getDob());

            PhoneDao phoneDao = PhoneImpl.getInstance();
            Collection<Phone> phones = user.getPhone();

            if(phones != null && !phones.isEmpty()){
                for (Phone phone : phones) {
                    phoneDao.createPhone(phone,user.getId());
                }
            }
            pStatement.executeUpdate();
            pStatement.close();

            pStatement = connection.prepareStatement(CREATE_USER);
            pStatement.setBoolean(1, user.getUserAgreement());
            pStatement.setInt(2, user.getId());
            pStatement.executeUpdate();
            pStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<User> findAllUsers() {
        ResultSet resultSet = null;
        Statement statement = null;
        Collection<User> users = new ArrayList<>();
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_USERS);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Boolean userAgreement = resultSet.getBoolean("user_agreement");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                Date dob = resultSet.getDate("dob");
                User user = new User(id, firstName, lastName, username, email, password,
                        dob, userAgreement);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findUserById(int userId) {
        User user = null;
        ResultSet resultSet = null;
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(FIND_USER_BY_ID);
            pStatement.setInt(1, userId);
            resultSet = pStatement.executeQuery();
            user = fetchUser(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private User fetchUser(ResultSet resultSet)  throws SQLException{
        User user =  null;
        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            Boolean userAgreement = resultSet.getBoolean("user_agreement");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String userName = resultSet.getString("username");
            String email = resultSet.getString("email");
            String pass = resultSet.getString("password");
            Date dob = resultSet.getDate("dob");
            user = new User(id, firstName, lastName, userName, email, pass, dob, userAgreement);
        }
        return user;
    }

    @Override
    public User findUserByUsername(String username) {
        ResultSet resultSet = null;
        User user = null;
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(FIND_USER_BY_USERNAME);
            pStatement.setString(1, username);
            resultSet = pStatement.executeQuery();
            user = fetchUser(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findUserByCredentials(String username, String password) {
        ResultSet resultSet = null;
        User user = null;
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(FIND_USER_BY_CREDENTIALS);
            pStatement.setString(1, username);
            pStatement.setString(2, password);
            resultSet = pStatement.executeQuery();
            user = fetchUser(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int updateUser(int userId, User user) {
        int status = 0;
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(UPDATE_USER);
            pStatement.setBoolean(1, user.getUserAgreement());
            pStatement.setString(2, user.getFirstName());
            pStatement.setString(3, user.getLastName());
            pStatement.setString(4, user.getUsername());
            pStatement.setString(5, user.getPassword());
            pStatement.setString(6, user.getEmail());
            pStatement.setDate(7,(Date) user.getDob());
            pStatement.setInt(8, user.getId());

            PhoneDao phoneDao = PhoneImpl.getInstance();
            Collection<Phone> phones = user.getPhone();
            if(phones != null && ! phones.isEmpty()){
                for (Phone phone : phones)
                    if(phone.getId() != 0)
                        phoneDao.updatePhone(phone);
                    else
                        phoneDao.createPhone(phone, user.getId());
            }

            AddressDao addressDao = AddressImpl.getInstance();
            Collection<Address> addresses = user.getAddress();
            if(! addresses.isEmpty()){
                for (Address address : addresses)
                    addressDao.updateAddress(address);
            }
            status = pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int deleteUser(int userId) {
        int status = 0;
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(DELETE_USER);
            pStatement.setInt(1, userId);
            status = pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
