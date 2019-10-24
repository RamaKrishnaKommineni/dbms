package edu.northeastern.cs5200.Daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import edu.northeastern.cs5200.DatabaseConnection;
import edu.northeastern.cs5200.Daos.AddressDao;
import edu.northeastern.cs5200.Daos.DeveloperDao;
import edu.northeastern.cs5200.Daos.PhoneDao;
import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.Phone;

public class DeveloperImpl implements DeveloperDao {

    private DatabaseConnection connection = null;
    private PreparedStatement pStatement = null;
    private static DeveloperImpl instance= null;
    private DeveloperImpl() {}

    public static DeveloperImpl getInstance() {
        if(instance == null)
            instance = new DeveloperImpl();
        return instance;
    }

    String CREATE_PERSON = "INSERT into person (id, first_name, last_name, user_name, password, email, dob) VALUES (?, ?, ?, ?, ?, ?, ?)";
    String CREATE_DEVELOPER = "INSERT INTO developer (developer_key, person) VALUES (?, ?)";
    String FIND_ALL_DEVELOPERS =  "SELECT * FROM developer, person WHERE Developer.person = person.id";
    String FIND_DEVELOPER_BY_ID = "SELECT * FROM developer, person WHERE Developer.person = person.id AND person.id=?";
    String FIND_DEVELOPER_BY_USERNAME = "SELECT * FROM developer, person WHERE Developer.person = person.id AND person.user_name=?";
    String FIND_DEVELOPER_BY_CREDENTIALS = "SELECT * from developer, person where developer.person = person.id and person.user_name=? and person.password=?";
    String UPDATE_DEVELOPER = "UPDATE developer, person SET developer_key = ?, first_name = ?, last_name = ?, user_name = ?, password = ?, email = ?, dob = ? WHERE person.id = developer.person and developer.person = ?";
    String DELETE_DEVELOPER = "DELETE from person where person.id = ?";

    @Override
    public void createDeveloper(Developer developer) {
        try {
        	
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            System.out.println(connection);
            pStatement = connection.prepareStatement(CREATE_PERSON);
            pStatement.setInt(1, developer.getId());
            pStatement.setString(2, developer.getFirstName());
            pStatement.setString(3,developer.getLastName());
            pStatement.setString(4, developer.getUsername());
            pStatement.setString(5, developer.getPassword());
            pStatement.setString(6, developer.getEmail());
            pStatement.setDate(7,(Date) developer.getDob());

            PhoneDao phoneDao = PhoneImpl.getInstance();
            Collection<Phone> phones = developer.getPhone();

            if(phones != null && !phones.isEmpty()){
                for (Phone phone : phones) {
                    phoneDao.createPhone(phone,developer.getId());
                }
            }
            pStatement.executeUpdate();
            pStatement.close();

            pStatement = connection.prepareStatement(CREATE_DEVELOPER);
            pStatement.setInt(2, developer.getId());
            pStatement.setString(1, developer.getDeveloperkey());
            pStatement.executeUpdate();
            pStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Developer> findAllDevelopers() {
        ResultSet resultSet = null;
        Statement statement = null;
        Collection<Developer> developers = new ArrayList<>();
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_DEVELOPERS);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String developerKey = resultSet.getString("developer_key");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                Date dob = resultSet.getDate("dob");
                Developer developer = new Developer(developerKey, id, firstName, lastName, username, email, password,
                        dob);
                developers.add(developer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developers;
    }

    @Override
    public Developer findDeveloperById(int developerId) {
        Developer developer = null;
        ResultSet resultSet = null;
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(FIND_DEVELOPER_BY_ID);
            pStatement.setInt(1, developerId);
            resultSet = pStatement.executeQuery();
            developer = fetchDeveloper(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developer;
    }

    @Override
    public Developer findDeveloperByUsername(String username) {
        ResultSet resultSet = null;
        Developer developer = null;
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(FIND_DEVELOPER_BY_USERNAME);
            pStatement.setString(1, username);
            resultSet = pStatement.executeQuery();
            developer = fetchDeveloper(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developer;
    }

    @Override
    public Developer findDeveloperByCredentials(String username, String password) {
        ResultSet resultSet = null;
        Developer developer = null;
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(FIND_DEVELOPER_BY_CREDENTIALS);
            pStatement.setString(1, username);
            pStatement.setString(2, password);
            resultSet = pStatement.executeQuery();
            developer = fetchDeveloper(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developer;
    }

    private Developer fetchDeveloper(ResultSet resultSet) throws SQLException {
        Developer developer =  null;
        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String developerKey = resultSet.getString("developer_key");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String userName = resultSet.getString("username");
            String email = resultSet.getString("email");
            String pass = resultSet.getString("password");
            Date dob = resultSet.getDate("dob");
            developer = new Developer(developerKey, id, firstName, lastName, userName, email, pass, dob);
        }
        return developer;
    }

    @Override
    public int updateDeveloper(int developerId, Developer developer) {
        int status = 0;
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(UPDATE_DEVELOPER);
            pStatement.setString(1, developer.getDeveloperkey());
            pStatement.setString(2, developer.getFirstName());
            pStatement.setString(3, developer.getLastName());
            pStatement.setString(4, developer.getUsername());
            pStatement.setString(5, developer.getPassword());
            pStatement.setString(6, developer.getEmail());
            pStatement.setDate(7,(Date) developer.getDob());
            pStatement.setInt(8, developer.getId());

            PhoneDao phoneDao = PhoneImpl.getInstance();
            Collection<Phone> phones = developer.getPhone();
            if(phones != null && ! phones.isEmpty()){
                for (Phone phone : phones)
                    if(phone.getId() != 0)
                        phoneDao.updatePhone(phone);
                    else
                        phoneDao.createPhone(phone, developer.getId());
            }

            AddressDao addressDao = AddressImpl.getInstance();
            Collection<Address> addresses = developer.getAddress();
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
    public int deleteDeveloper(int developerId) {
        int status = 0;
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            pStatement = connection.prepareStatement(DELETE_DEVELOPER);
            pStatement.setInt(1, developerId);
            status = pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
