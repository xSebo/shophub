package com.example.clientproject.web.forms.userSettingsPage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NameEmailProfileChangeForm {
    private String newFirstName;
    private String newLastName;
    private String newEmail;
    private String newProfilePic;
}
