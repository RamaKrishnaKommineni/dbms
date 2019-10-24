package edu.northeastern.cs5200.Daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.northeastern.cs5200.DatabaseConnection;
import edu.northeastern.cs5200.Daos.AddressDao;
import edu.northeastern.cs5200.models.Address;

public class AddressImpl implements AddressDao {

    private DatabaseConnection connection= null;
    private PreparedStatement statement= null;
    private static AddressImpl instance= null;
    private AddressImpl() {}

    public static AddressImpl getInstance() {
        if(instance == null)
            instance = new AddressImpl();
        return instance;
    }

    String CREATE_ADDRESS =  "INSERT INTO address (street1, street2, city, state, zip, `primary`, " +
            "person, id) VALUES (?, ?, ? , ? , ? , ? , ?, ?, ?)";
    String UPDATE_ADDRESS = "UPDATE address SET street1 = ?, street2 = ?, city = ?, state = ?, " +
            "zip = ?, `primary` = ? WHERE person = ?";

    @Override
    public void createAddress(Address address, int personId) {
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.prepareStatement(CREATE_ADDRESS);
            statement.setString(1, address.getStreet1());
            statement.setString(2, address.getStreet2());
            statement.setString(3, address.getCity());
            statement.setString(4, address.getState());
            statement.setString(5,address.getZip());
            statement.setBoolean(6,address.isPrimary());
            statement.setInt(7, personId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int updateAddress(Address address) {
        int status = 0;
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE_ADDRESS);
            statement.setString(1, address.getStreet1());
            statement.setString(2, address.getStreet2());
            statement.setString(3, address.getCity());
            statement.setString(4, address.getState());
            statement.setString(5,address.getZip());
            statement.setBoolean(6,address.isPrimary());
            status = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
