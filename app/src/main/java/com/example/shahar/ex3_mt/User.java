package com.example.shahar.ex3_mt;

/**
 * Created by eliran on 18/12/17.
 */

public class User {
    private String UserName;
    private String Password;
    private String FirstName;
    private String LastName;
    private String City;
    private String Street;
    private String Email;


    public User(String userName, String password, String firstName, String lastName, String city, String street, String email) {
        UserName = userName;
        Password = password;
        FirstName = firstName;
        LastName = lastName;
        City = city;
        Street = street;
        Email = email;
    }

    public User(){}

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
