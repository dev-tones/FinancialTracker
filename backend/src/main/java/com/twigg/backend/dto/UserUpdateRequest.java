package com.twigg.backend.dto;

import com.twigg.backend.model.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UserUpdateRequest {

  
    private String userName;

    private UserRole userRole;
   
    private String firstName;
  
    private String lastName;
    @Size(min=8, message="Password must be at least 8 characters.")
    private String password;
    @Email(message="Please provide a valid email address.")
    private String email;
    
    private String phone;

    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public UserRole getUserRole(){
        return userRole;
    }
    public void setUserRole(UserRole userRole){
        this.userRole = userRole;
    }
    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword (String password){
        this.password = password;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
}
