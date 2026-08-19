package com.twigg.backend.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {
    

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Size(min=8)
    @NotBlank
    private String password;

    @Email
    @NotBlank
    private String email;
    
    private String phone;

}
