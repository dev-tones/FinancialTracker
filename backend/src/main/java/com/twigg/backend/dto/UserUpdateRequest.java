package com.twigg.backend.dto;

import com.twigg.backend.model.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {

    private UserRole userRole;
   
    private String firstName;
  
    private String lastName;

    @Size(min=8, message="Password must be at least 8 characters.")
    private String password;

    @Email(message="Please provide a valid email address.")
    private String email;
    
    private String phone;

}
