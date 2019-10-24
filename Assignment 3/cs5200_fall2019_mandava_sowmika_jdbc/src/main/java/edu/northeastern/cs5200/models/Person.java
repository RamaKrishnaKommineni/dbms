package edu.northeastern.cs5200.models;

import java.util.Collection;
import java.util.Date;
import java.util.ArrayList;

public class Person {

    private int id;
    private String FirstName;
    private String LastName;
    private String username;
    private String password;
    private String email;
    private Date dob;
    private Collection<Phone> Phone = new ArrayList<>();
    private Collection<Address> Address = new ArrayList<>();


    public Person(int id, String FirstName, String LastName, String username, String password, String email, Date dob) {
        this.id = id;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dob = dob;
    }

    public Person(int id, String FirstName, String LastName, String username, String password, String email, Date dob, Collection<Phone> Phone, Collection<Address> Address) {
        this.id = id;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dob = dob;
        this.Phone = Phone;
        this.Address = Address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Collection<Phone> getPhone() {
        return Phone;
    }

    public void setPhone(Collection<Phone> Phone) {
        this.Phone = Phone;
    }

    public Collection<Address> getAddress() {
        return Address;
    }

    public void setAddress(Collection<Address> Address) {
        this.Address = Address;
    }
}
