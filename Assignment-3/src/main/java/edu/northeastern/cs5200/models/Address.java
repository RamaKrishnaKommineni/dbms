package edu.northeastern.cs5200.models;

public class Address{
	private int id;
	private String street1;
	private String street2;
	private String city;
	private String state;
	private String zip;
  	private Boolean primary;
  	private Person person;

  	public int getId(){
  		return id;
  	}

  	public void setId(int id){
  		this.id = id;
  	}

  	public String getStreet1(){
  		return street1;
  	}

  	public void setStreet1(String street1){
  		this.street1 = street1;
  	}

  	public String getStreet2(){
  		return street2;
  	}

  	public void setStreet2(String street2){
  		this.street2 = street2;
  	}

  	public String getCity(){
  		return city;
  	}

  	public void setCity(String city){
  		this.city = city;
  	}

  	public String getState(){
  		return state;
  	}

  	public void setState(String state){
  		this.state = state;
  	}

  	public String getZip(){
  		return zip;
  	}

  	public void setZip(String zip){
  		this.zip = zip;
  	}

  	public Boolean getPrimary(){
  		return primary;
  	}

  	public void setPrimary(Boolean primary){
  		this.primary = primary;
  	}

  	public Person getPerson(){
  		return person;
  	}

  	public void setPerson(Person person){
  		this.person = person;
  	}

  	public Boolean isPrimary(){
  		return primary;
  	}
}
