package edu.northeastern.cs5200.models;

public class Phone{
    private int id;
    private String phone;
    private Boolean primary;
    private Person person;

    public Phone(int id, String phone, Boolean primary){
        this.id = id;
        this.phone = phone;
        this.primary = primary;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
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
