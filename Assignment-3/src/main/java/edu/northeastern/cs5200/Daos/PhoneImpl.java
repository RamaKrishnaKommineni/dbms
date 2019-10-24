package edu.northeastern.cs5200.Daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.northeastern.cs5200.DatabaseConnection;
import edu.northeastern.cs5200.Daos.PhoneDao;
import edu.northeastern.cs5200.models.Phone;

public class PhoneImpl implements PhoneDao{
	private Connection connection= null;
	private PreparedStatement statement= null;
	private static PhoneImpl instance= null;
	private PhoneImpl(){
	}

	public static PhoneImpl getInstance(){
		if(instance == null)
			instance = new PhoneImpl();
		return instance;
	}

	String createPhone = "insert into phone (phone, primary_phone, person) values (?, ?, ?)";
	@Override
	public void createPhone(Phone phone, int personId){
		try{
			this.connection = DatabaseConnection.getInstance().getConnection();
			this.statement = connection.prepareStatement(createPhone);
			this.statement.setString(1, phone.getPhone());
			this.statement.setBoolean(2, phone.isPrimary());
			this.statement.setInt(3, personId);
			this.statement.executeUpdate();
		} 
		catch (SQLException e){
			e.printStackTrace();
		}
	}

	String updatePhone = "update phone set phone = ?, primary_phone = ? where phone.person = ?";
	@Override
	public int updatePhone(Phone phone){
		int status = 0;
		try{
			connection = DatabaseConnection.getInstance().getConnection();
			statement = connection.prepareStatement(updatePhone);
			statement.setString(1, phone.getPhone());
			statement.setBoolean(2, phone.isPrimary());
			statement.setInt(3, phone.getId());
			status = statement.executeUpdate();
		} 
		catch (SQLException e){
      e.printStackTrace();
		}
		return status;
	}
}
