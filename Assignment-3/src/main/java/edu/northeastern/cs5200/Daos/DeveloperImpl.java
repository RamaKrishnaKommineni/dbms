package edu.northeastern.cs5200.Daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.DatabaseConnection;
import edu.northeastern.cs5200.Daos.AddressDao;
import edu.northeastern.cs5200.Daos.DeveloperDao;
import edu.northeastern.cs5200.Daos.PhoneDao;
import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.Phone;

public class DeveloperImpl implements DeveloperDao{
	private Connection connection = null;
	private PreparedStatement pStatement = null;
	private static DeveloperImpl instance= null;
	private DeveloperImpl(){
	}

	public static DeveloperImpl getInstance(){
		if(instance == null)
			instance = new DeveloperImpl();
		return instance;
	}

	String createPerson = "insert into person (id, first_name, last_name, user_name, password, email, dob) values (?, ?, ?, ?, ?, ?, ?)";
	String createDeveloper = "insert into developer (developer_key, person) values (?, ?)";
	@Override
	public void createDeveloper(Developer developer){
		try{
			connection = DatabaseConnection.getInstance().getConnection();
			pStatement = connection.prepareStatement(createPerson);
			pStatement.setInt(1, developer.getId());
			pStatement.setString(2, developer.getFirstName());
			pStatement.setString(3,developer.getLastName());
			pStatement.setString(4, developer.getUsername());
			pStatement.setString(5, developer.getPassword());
			pStatement.setString(6, developer.getEmail());
			pStatement.setDate(7, (Date) developer.getDob());
			PhoneDao phoneDao = PhoneImpl.getInstance();
			Collection<Phone> phones = developer.getPhones();
			if(phones != null && !phones.isEmpty()){
				for (Phone phone : phones){
					phoneDao.createPhone(phone,developer.getId());
				}
			}
			pStatement.executeUpdate();
			pStatement.close();
			pStatement = connection.prepareStatement(createDeveloper);
			pStatement.setInt(2, developer.getId());
			pStatement.setString(1, developer.getDeveloperKey());
			pStatement.executeUpdate();
			pStatement.close();
		} 
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	String findAllDevelopers =  "select * from developer, Person where Developer.person = Person.id";
	@Override
	public Collection<Developer> findAllDevelopers(){
		ResultSet resultSet = null;
		Statement statement = null;
		Collection<Developer> developers = new ArrayList<>();
		try{
			connection = DatabaseConnection.getInstance().getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(findAllDevelopers);
			while (resultSet.next()){
				int id = resultSet.getInt("id");
				String developerKey = resultSet.getString("developer_key");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String username = resultSet.getString("user_name");
				String email = resultSet.getString("email");
				String password = resultSet.getString("password");
				Date dob = resultSet.getDate("dob");
				Developer developer = new Developer(developerKey, id, firstName, lastName, username, email, password, dob);
				developers.add(developer);
			}
		} 
		catch (SQLException e){
			e.printStackTrace();
		}
		return developers;
	}

	String findDeveloperByIdea = "select * from developer, Person where Developer.person = Person.id and Person.id=?";		
	@Override
	public Developer findDeveloperById(int developerId){
		Developer developer = null;
		ResultSet resultSet = null;
		try{
			connection = DatabaseConnection.getInstance().getConnection();
			pStatement = connection.prepareStatement(findDeveloperByIdea);
			pStatement.setInt(1, developerId);
			resultSet = pStatement.executeQuery();
			developer = fetchDeveloper(resultSet);
		} 
		catch (SQLException e){
			e.printStackTrace();
		}
		return developer;
	}

	String findDeveloperByUsername = "select * from developer, Person where Developer.person = Person.id AND Person.username=?";
	@Override
	public Developer findDeveloperByUsername(String username){
		ResultSet resultSet = null;
		Developer developer = null;
		try{
			connection = DatabaseConnection.getInstance().getConnection();
			pStatement = connection.prepareStatement(findDeveloperByUsername);
			pStatement.setString(1, username);
			resultSet = pStatement.executeQuery();
			developer = fetchDeveloper(resultSet);
		} 
		catch (SQLException e){
			e.printStackTrace();
		}
		return developer;
	}
	
	String findDeveloperByCredentials = "select * from developer, person where developer.person = person.id and person.username=? and person.password=?";
	@Override
	public Developer findDeveloperByCredentials(String username, String password){
		ResultSet resultSet = null;
		Developer developer = null;
		try{
			connection = DatabaseConnection.getInstance().getConnection();
			pStatement = connection.prepareStatement(findDeveloperByCredentials);
			pStatement.setString(1, username);
			pStatement.setString(2, password);
			resultSet = pStatement.executeQuery();
			developer = fetchDeveloper(resultSet);
		} 
		catch (SQLException e){
			e.printStackTrace();
		}
		return developer;
	}
	private Developer fetchDeveloper(ResultSet resultSet) throws SQLException{
		Developer developer =  null;
		if (resultSet.next()){
			int id = resultSet.getInt("id");
			String developerKey = resultSet.getString("developer_key");
			String firstName = resultSet.getString("first_name");
			String lastName = resultSet.getString("last_name");
			String userName = resultSet.getString("user_name");
			String email = resultSet.getString("email");
			String pass = resultSet.getString("password");
			Date dob = resultSet.getDate("dob");
			developer = new Developer(developerKey, id, firstName, lastName, userName, email, pass, dob);
		}
		return developer;
	}

	String updateDeveloper = "update developer, person set developer_key = ?, first_name = ?, last_name = ?, user_name = ?, password = ?, email = ?, dob = ? where person.id = developer.person and developer.person = ?";
	@Override
	public int updateDeveloper(int developerId, Developer developer){
		int status = 0;
		try{
			this.connection = DatabaseConnection.getInstance().getConnection();
			pStatement = connection.prepareStatement(updateDeveloper);
			pStatement.setString(1, developer.getDeveloperKey());
			pStatement.setString(2, developer.getFirstName());
			pStatement.setString(3, developer.getLastName());
			pStatement.setString(4, developer.getUsername());
			pStatement.setString(5, developer.getPassword());
			pStatement.setString(6, developer.getEmail());
			pStatement.setDate(7,(Date) developer.getDob());
			pStatement.setInt(8, developer.getId());
			PhoneDao phoneDao = PhoneImpl.getInstance();
			Collection<Phone> phones = developer.getPhones();
			if(phones != null && ! phones.isEmpty()){
				for (Phone phone : phones)
					if(phone.getId()!= 0)
						phoneDao.updatePhone(phone);
					else
						phoneDao.createPhone(phone, developer.getId());
			}
			AddressDao addressDao = AddressImpl.getInstance();
			Collection<Address> addresses = developer.getAddresses();
			if(! addresses.isEmpty()){
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

	String deleteDeveloper = "delete from person where person.id = ?";
	@Override
	public int deleteDeveloper(int developerId){
		int status = 0;
		try{
			connection = DatabaseConnection.getInstance().getConnection();
			pStatement = connection.prepareStatement(deleteDeveloper);
			pStatement.setInt(1, developerId);
			status = pStatement.executeUpdate();
		} 
		catch (SQLException e){
			e.printStackTrace();
		}
		return status;
	}
}
