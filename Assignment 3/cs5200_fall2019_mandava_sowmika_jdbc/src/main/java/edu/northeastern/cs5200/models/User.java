package edu.northeastern.cs5200.models;

import java.util.Collection;
import java.util.Date;
import java.util.ArrayList;

public class User extends Person {
    private Boolean userAgreement;
    private Person person;


    public User(int id, String FirstName, String LastName, String username, String password, String email, Date dob, Boolean userAgreement) {
        super(id, FirstName, LastName, username, password, email, dob);
        this.userAgreement = userAgreement;
    }

    public User(int id, String FirstName, String LastName, String username, String password, String email, Date dob, Collection<Phone> Phone, Collection<Address> Address, Boolean userAgreement) {
        super(id, FirstName, LastName, username, password, email, dob, Phone, Address);
        this.userAgreement = userAgreement;
    }

    public Boolean getUserAgreement() {
        return userAgreement;
    }

    public void setUserAgreement(Boolean userAgreement) {
        this.userAgreement = userAgreement;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}

