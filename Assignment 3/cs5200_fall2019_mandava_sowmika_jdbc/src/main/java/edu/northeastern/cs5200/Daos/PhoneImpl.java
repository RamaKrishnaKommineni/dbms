package edu.northeastern.cs5200.Daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.northeastern.cs5200.DatabaseConnection;
import edu.northeastern.cs5200.Daos.PhoneDao;
import edu.northeastern.cs5200.models.Phone;

public class PhoneImpl implements PhoneDao {
    private DatabaseConnection connection= null;
    private PreparedStatement statement= null;
    private static PhoneImpl instance= null;
    private PhoneImpl() {}

    public static PhoneImpl getInstance() {
        if(instance == null)
            instance = new PhoneImpl();
        return instance;
    }

    String CREATE_PHONE = "INSERT INTO Phone (phone, `primary`, person) VALUES (?, ?, ?)";
    String UPDATE_PHONE = "UPDATE phone SET phone = ?, `primary` = ? WHERE phone.person = ?";

    @Override
    public void createPhone(Phone phone, int personId) {
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            this.statement = connection.prepareStatement(CREATE_PHONE);
            this.statement.setString(1, phone.getPhone());
            this.statement.setBoolean(2, phone.isPrimary());
            this.statement.setInt(3, personId);
            this.statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int updatePhone(Phone phone) {
        int status = 0;
        try {
            java.sql.Connection connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE_PHONE);
            statement.setString(1, phone.getPhone());
            statement.setBoolean(2, phone.isPrimary());
            statement.setInt(3, phone.getId());
            status = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
