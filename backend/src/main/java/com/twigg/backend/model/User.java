package com.twigg.backend.model;

import java.time.OffsetDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;




@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole userRole;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String passwordHash;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phone;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    public User(){
    }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public UserRole getUserRole(){
        return userRole;
    }
    public void setUserRole(UserRole userRole){
        this.userRole = userRole;
    }
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
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
    public String getPasswordHash(){
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash){
        this.passwordHash = passwordHash;
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
    public OffsetDateTime getCreatedAt(){
        return createdAt;
    }
    public OffsetDateTime getUpdatedAt(){
        return updatedAt;
    }
}
