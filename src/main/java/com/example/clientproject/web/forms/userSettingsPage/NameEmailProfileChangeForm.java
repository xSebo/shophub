package com.example.clientproject.web.forms.userSettingsPage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NameEmailProfileChangeForm {
    @Size(max=15, message="First Name incorrect length")
    private String newFirstName;
    @Size(max=15, message="Last Name incorrect length")
    private String newLastName;
    @Email(message="Email format incorrect")
    private String newEmail;
    private String newProfilePic;
}
