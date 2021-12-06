package com.example.clientproject.web.forms.userSettingsPage;

import com.example.clientproject.data.users.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordChangeForm {
    @Size(min=7, max=15, message="Password must be between 7 and 15 characters")
    private String oldPassword;
    @Size(min=7, max=15, message="Password must be between 7 and 15 characters")
    private String newPassword;
    @Size(min=7, max=15, message="Password must be between 7 and 15 characters")
    private String newPasswordConfirm;
}
