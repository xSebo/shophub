package com.example.clientproject.web.forms.signUpAndIn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {
    // Following validation found at: https://www.baeldung.com/javax-validation
    @Email(message="Email must be valid")
    private String loginEmail;

    @Size(min=7, max=15, message="Password must be between 7 and 15 characters")
    private String loginPassword;
}
