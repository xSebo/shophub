package com.example.clientproject.services;
import com.example.clientproject.domain.AccountRegister;
import lombok.AllArgsConstructor;
import lombok.Value;

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
