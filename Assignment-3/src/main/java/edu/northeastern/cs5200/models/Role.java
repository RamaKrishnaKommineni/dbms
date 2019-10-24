package edu.northeastern.cs5200.models;

public enum Role{
    OWNER(1),
    ADMIN(2),
    WRITER(3),
    EDITOR(4),
    REVIEWER(5);

	private Role(int id){
    }
}