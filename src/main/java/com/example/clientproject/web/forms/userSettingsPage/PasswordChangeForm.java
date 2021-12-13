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
    private String oldPassword;
    private String newPassword;
    private String newPasswordConfirm;
}
