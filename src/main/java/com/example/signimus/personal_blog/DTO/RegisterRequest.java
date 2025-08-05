package com.example.signimus.personal_blog.DTO;

import com.example.signimus.personal_blog.Entity.Roles;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Roles role;
}
