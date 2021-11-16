package com.example.clientproject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRegister {
    private String name;
    private String surname;
    private String email;
    private String password;
    @Override
    public String toString(){
        return this.getName() + ", " + this.getSurname() + ", " + this.getEmail() + ", " + this.getPassword();
    }
}
