package edu.northeastern.cs5200.Daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import edu.northeastern.cs5200.DatabaseConnection;
import edu.northeastern.cs5200.Daos.AddressDao;
import edu.northeastern.cs5200.models.Address;

public class AddressImpl implements AddressDao{
	private Connection connection= null;
	private PreparedStatement statement= null;
	private static AddressImpl instance= null;
	private AddressImpl(){
	}

	public static AddressImpl getInstance(){
		if(instance == null)
			instance = new AddressImpl();
		return instance;
	}

	String createAddress = "insert into address (street1, street2, city, state, zip, primary, "+" person, id) VALUES (?, ?, ? , ? , ? , ? , ?, ?, ?)";
	@Override
	public void createAddress(Address address, int personId){
		try{
			connection = DatabaseConnection.getInstance().getConnection();
			statement = connection.prepareStatement(createAddress);
			statement.setString(1, address.getStreet1());
			statement.setString(2, address.getStreet2());
			statement.setString(3, address.getCity());
			statement.setString(4, address.getState());
			statement.setString(5,address.getZip());
			statement.setBoolean(6,address.isPrimary());
			statement.setInt(7, personId);
			statement.executeUpdate();
		} 
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	String updateAddress = "update address set street1 = ?, street2 = ?, city = ?, state = ?, "+" zip = ?, primary = ? where person = ?";
	@Override
	public int updateAddress(Address address){
		int status = 0;
		try{
			connection = DatabaseConnection.getInstance().getConnection();
			statement = connection.prepareStatement(updateAddress);
			statement.setString(1, address.getStreet1());
			statement.setString(2, address.getStreet2());
			statement.setString(3, address.getCity());
			statement.setString(4, address.getState());
			statement.setString(5,address.getZip());
			statement.setBoolean(6,address.isPrimary());
			status = statement.executeUpdate();
		} 
		catch (SQLException e){
			e.printStackTrace();
		}
		return status;
	}

	String deleteAddress = "delete from address where person=? and primary_address=?";
	@Override
	public void deleteAddress(int personId, int primaryaddress){
		try{
			connection = DatabaseConnection.getInstance().getConnection();
			statement = connection.prepareStatement(deleteAddress);
	      	statement.setInt(1,personId);
	      	statement.setInt(2,primaryaddress);
	      	statement.executeUpdate();
	    } 
		catch (SQLException e){
			e.printStackTrace();
		}
	}
}
