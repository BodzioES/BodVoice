package com.bodex.bodvoice;

import java.time.LocalDate;

public class User {
    private String username;
    private String password;
    private String nickName;
    private String email;
    private LocalDate dateOfBirth;

    public User(String nickName, String username, String password, String email, LocalDate dateOfBirth) {
        this.nickName = nickName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }
    ///////////////////////////////////////////////////////
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    ///////////////////////////////////////////////////////
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    ///////////////////////////////////////////////////////
    public String getDisplayName(){
        return nickName;
    }
    public void setDisplayName(String displayName){
        this.nickName = displayName;
    }
    ///////////////////////////////////////////////////////
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    ///////////////////////////////////////////////////////
    public LocalDate getDateOfBirth(){
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
    ///////////////////////////////////////////////////////
}
