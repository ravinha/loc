package com.loc_2.entities;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Rafal on 2016-03-20.
 */
@Document(collection="users")
public class User {

    @Id
    private String id;
    private String name;
    private String surname;
    private int age;
    private String username;
    private String password;
    private String role;

    public User() {
    }

    public User(String id, String name, String surname, String username,
                String password, String role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.role = role;
    }



    public String getId() {
        return id;
    }



    public void setId(String id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getSurname() {
        return surname;
    }


    public void setSurname(String surname) {
        this.surname = surname;
    }


    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        this.age = age;
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



    public String getRole() {
        return role;
    }



    public void setRole(String role) {
        this.role = role;
    }





}
