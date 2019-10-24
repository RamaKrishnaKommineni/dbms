package edu.northeastern.cs5200.models;

import java.util.Collection;
import java.util.Date;

public class User extends Person{
	private Boolean userAgreement;
	private Person person;


	public User(int id, String firstName, String lastName, String username, String password, String email, Date dob, Boolean userAgreement) {
		super(id, firstName, lastName, username, password, email, dob);
		this.userAgreement = userAgreement;
	}

	public User(int id, String firstName, String lastName, String username, String password, String email, Date dob, Collection<Phone> phones, Collection<Address> addresses, Boolean userAgreement) {
		super(id, firstName, lastName, username, password, email, dob, phones, addresses);
    	this.userAgreement = userAgreement;
	}

	public Boolean getUserAgreement(){
		return userAgreement;
	}

	public void setUserAgreement(Boolean userAgreement){
		this.userAgreement = userAgreement;
	}

	public Person getPerson(){
		return person;
	}

	public void setPerson(Person person){
		this.person = person;
	}
}
