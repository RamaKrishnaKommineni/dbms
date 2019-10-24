package edu.northeastern.cs5200.Daos;

import edu.northeastern.cs5200.models.Address;

public interface AddressDao{
	void createAddress(Address address, int personId);
	int updateAddress(Address address);
	void deleteAddress(int personId, int primaryaddress);
}
