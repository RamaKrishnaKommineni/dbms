package edu.northeastern.cs5200.Daos;

import java.sql.Connection;
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

public class UserImpl implements UserDao{
	private Connection connection = null;
  	private PreparedStatement pStatement = null;
  	private static UserImpl instance= null;
  	private UserImpl(){
  	}

  	public static UserImpl getInstance(){
  		if(instance == null)
  			instance = new UserImpl();
  		return instance;
  	}

  	String createPerson = "insert into person (id, first_name, last_name, user_name, password, email, dob) values (?, ?, ?, ?, ?, ?, ?)";
  	String createUser = "insert into user (user_agreement, person) values (?, ?)";
  	@Override
  	public void createUser(User user){
  		try{
  			connection = DatabaseConnection.getInstance().getConnection();
  			pStatement = connection.prepareStatement(createPerson);
  			pStatement.setInt(1,user.getId());
  			pStatement.setString(2,user.getFirstName());
  			pStatement.setString(3,user.getLastName());
  			pStatement.setString(4,user.getUsername());
  			pStatement.setString(5,user.getPassword());
  			pStatement.setString(6,user.getEmail());
  			pStatement.setDate(7, (Date) user.getDob());
  			PhoneDao phoneDao = PhoneImpl.getInstance();
  			Collection<Phone> phones = user.getPhones();
  			if(phones != null && !phones.isEmpty()){
  				for (Phone phone : phones){
  					phoneDao.createPhone(phone,user.getId());
  				}
  			}
  			pStatement.executeUpdate();
  			pStatement.close();
  			pStatement = connection.prepareStatement(createUser);
  			pStatement.setBoolean(1,user.getUserAgreement());
  			pStatement.setInt(2,user.getId());
  			pStatement.executeUpdate();
  			pStatement.close();
  		} 
  		catch (SQLException e){
  			e.printStackTrace();
  		}
  	}

  	String findAllUsers =  "SELECT * FROM User, person WHERE User.person = person.id";
  	@Override
  	public Collection<User> findAllUsers(){
  		ResultSet resultSet = null;
  		Statement statement = null;
  		Collection<User> users = new ArrayList<>();
  		try{
  			connection = DatabaseConnection.getInstance().getConnection();
  			statement = connection.createStatement();
  			resultSet = statement.executeQuery(findAllUsers);
  			while (resultSet.next()){
  				int id = resultSet.getInt("id");
  				Boolean userAgreement = resultSet.getBoolean("user_agreement");
  				String firstName = resultSet.getString("first_name");
  				String lastName = resultSet.getString("last_name");
  				String username = resultSet.getString("user_name");
  				String email = resultSet.getString("email");
  				String password = resultSet.getString("password");
  				Date dob = resultSet.getDate("dob");
  				User user = new User(id, firstName, lastName, username, email, password, dob, userAgreement);
  				users.add(user);
  			}
  		}
  		catch (SQLException e){
  			e.printStackTrace();
  		}
  		return users;
  	}
  	
  	String findUserById = "select * from User, person where User.person = person.id and person.id=?";
  	@Override
  	public User findUserById(int userId){
  		User user = null;
  		ResultSet resultSet = null;
  		try{
  			connection = DatabaseConnection.getInstance().getConnection();
  			pStatement = connection.prepareStatement(findUserById);
  			pStatement.setInt(1,userId);
  			resultSet = pStatement.executeQuery();
  			user = fetchUser(resultSet);
  		} 
  		catch (SQLException e){
  			e.printStackTrace();
  		}
  		return user;
  	}

  	private User fetchUser(ResultSet resultSet) throws SQLException{
  		User user = null;
  		if(resultSet.next()){
  			int id = resultSet.getInt("id");
  			Boolean userAgreement = resultSet.getBoolean("user_agreement");
  			String firstName = resultSet.getString("first_name");
  			String lastName = resultSet.getString("last_name");
  			String userName = resultSet.getString("user_name");
  			String email = resultSet.getString("email");
  			String pass = resultSet.getString("password");
  			Date dob = resultSet.getDate("dob");
  			user = new User(id, firstName, lastName, userName, email, pass, dob, userAgreement);
  		}
    	return user;
  	}
  	
  	String findUserbyUsername = "select * FROM User, person where User.person = person.id and person.username=?";
  	@Override
  	public User findUserByUsername(String username){
  		ResultSet resultSet = null;
  		User user = null;
  		try{
  			connection = DatabaseConnection.getInstance().getConnection();
  			pStatement = connection.prepareStatement(findUserbyUsername);
  			pStatement.setString(1, username);
  			resultSet = pStatement.executeQuery();
  			user = fetchUser(resultSet);
  		} 
  		catch (SQLException e){
  			e.printStackTrace();
  		}
  		return user;
  	}

  	String findUserByCredentials = "select * from user, person where user.person = person.id and person.username=? and person.password=?";
  	@Override
  	public User findUserByCredentials(String username, String password){
  		ResultSet resultSet = null;
  		User user = null;
    	try{
    		connection = DatabaseConnection.getInstance().getConnection();
    		pStatement = connection.prepareStatement(findUserByCredentials);
    		pStatement.setString(1,username);
    		pStatement.setString(2,password);
    		resultSet = pStatement.executeQuery();
    		user = fetchUser(resultSet);
    	} 
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	return user;
  	}

  	String updateUser = "update user, person set user_agreement = ?, first_name = ?, last_name = ?, user_name = ?, password = ?, email = ?, dob = ? where person.id = user.person and user.person = ?";
  	@Override
  	public int updateUser(int userId, User user){
  		int status = 0;
  		try{
  			this.connection = DatabaseConnection.getInstance().getConnection();
  			pStatement = connection.prepareStatement(updateUser);
  			pStatement.setBoolean(1, user.getUserAgreement());
  			pStatement.setString(2, user.getFirstName());
  			pStatement.setString(3, user.getLastName());
  			pStatement.setString(4, user.getUsername());
  			pStatement.setString(5, user.getPassword());
  			pStatement.setString(6, user.getEmail());
  			pStatement.setDate(7,(Date) user.getDob());
  			pStatement.setInt(8, user.getId());
  			PhoneDao phoneDao = PhoneImpl.getInstance();
  			Collection<Phone> phones = user.getPhones();
  			if(phones!= null && !phones.isEmpty()){
  				for (Phone phone : phones)
  					if(phone.getId() != 0)
  						phoneDao.updatePhone(phone);
  					else
  						phoneDao.createPhone(phone, user.getId());
  			}
  			AddressDao addressDao = AddressImpl.getInstance();
  			Collection<Address> addresses = user.getAddresses();
  			if(!addresses.isEmpty()){
  				for (Address address : addresses)
  					addressDao.updateAddress(address);
  			}
  			status = pStatement.executeUpdate();
  		}
  		catch (SQLException e){
  			e.printStackTrace();
  		}
  		return status;
  	}

	String deleteUser = "delete from person where person.id = ?";
  	@Override
  	public int deleteUser(int userId){
  		int status = 0;
  		try{
  			connection = DatabaseConnection.getInstance().getConnection();
  			pStatement = connection.prepareStatement(deleteUser);
  			pStatement.setInt(1, userId);
  			status = pStatement.executeUpdate();
  		} 
  		catch (SQLException e){
  			e.printStackTrace();
  		}
  		return status;
  	}	
}
