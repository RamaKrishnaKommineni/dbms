package edu.northeastern.cs5200.models;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.ArrayList;

public class Developer extends Person{

    private String developerKey;
    private Person person;

    public Developer(){
        super(1,"Sowmika","Mandava","sowmikam","Cs5200_f19",
                "mandava.so@husky.neu.edu", new Date(1996,8,29));
        this.developerKey="123";
    }

    public Developer(String developerKey, int id, String FirstName, String LastName, String username, String
            password, String email, Date dob){
        super(id, FirstName, LastName, username, password, email, dob);
        this.developerKey=developerKey;
    }


    public Developer(String developerKey, int id, String FirstName, String LastName, String username, String
            password, String email, Date dob, Collection<Phone> phones, Collection<Address> addresses){
        super(id, FirstName,LastName,username,password,email,dob,phones,addresses);
        this.developerKey=developerKey;
    }

    public String getDeveloperkey() {
        return developerKey;
    }

    public void setDeveloperkey(String developerKey) {
        this.developerKey = developerKey;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}

