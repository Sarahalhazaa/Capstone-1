package com.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    @NotEmpty
    private String id ;
    @NotEmpty
    @Size(min = 6)
    private String username ;
    @NotEmpty
    @Size(min = 7)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,20}$")
    private String password ;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Pattern(regexp ="^(Admin|Customer)$")
    private String role;
    @NotNull
    @Positive
    private int balance;

}
