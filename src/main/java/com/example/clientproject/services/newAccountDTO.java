package com.example.clientproject.services;
import com.example.clientproject.data.twoFactorMethods.TwoFactorMethods;
import com.example.clientproject.data.users.Users;
import com.example.clientproject.data.users.UsersRepo;
import com.example.clientproject.domain.AccountRegister;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.context.annotation.Bean;
import com.example.clientproject.data.twoFactorMethods.TwoFactorMethods;
import com.example.clientproject.data.twoFactorMethods.TwoFactorMethodsRepo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Value
@AllArgsConstructor
public class newAccountDTO {
    String name;
    String surname;
    String email;
    String password;
    public newAccountDTO(AccountRegister aAccount){
        this(
                aAccount.getName(),
                aAccount.getSurname(),
                aAccount.getEmail(),
                aAccount.getPassword()
        );
    }


}
