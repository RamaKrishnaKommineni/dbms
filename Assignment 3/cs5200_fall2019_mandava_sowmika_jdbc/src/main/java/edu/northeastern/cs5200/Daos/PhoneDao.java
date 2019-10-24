package edu.northeastern.cs5200.Daos;

import edu.northeastern.cs5200.models.Phone;

public interface PhoneDao {
    void createPhone(Phone phone, int personId);
    int updatePhone(Phone phone);
}
