package edu.northeastern.cs5200.models;

import java.util.Collection;
import java.util.Date;

public class Developer extends Person{
	private String developerKey;
	private Person person;
	
	@SuppressWarnings("deprecation")
  	public Developer(){
		super(1,"Sowmika","Mandava","sowmika","alice123","mandava.so@husky.neu.edu", new Date(1996,8,29));
		this.developerKey="123";
	}

	public Developer(String developerKey, int id, String first_name, String last_name, String user_name, String password, String email, Date dob){
		super(id, first_name, last_name, user_name, password, email, dob);
		this.developerKey=developerKey;
	}

	public Developer(String developerKey, int id, String first_name, String last_name, String user_name, String password, String email, Date dob, Collection<Phone> phones, Collection<Address> addresses){
		super(id, first_name, last_name,user_name, password, email, dob, phones, addresses);
		this.developerKey=developerKey;
	}

	public String getDeveloperKey() {
		return developerKey;
	}

	public void setDeveloperKey(String developerKey) {
		this.developerKey = developerKey;
	}

	public Person getPerson() {
		return person;
	}
			
	public void setPerson(Person person) {
		this.person = person;
	}
}
