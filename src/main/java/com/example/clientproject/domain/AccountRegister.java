package com.example.clientproject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

@Data
public class AccountRegister {
    private String name;
    private String surname;
    private String email;
    private String password;
    @Override
    public String toString(){
        return this.getName() + ", " + this.getSurname() + ", " + this.getEmail() + ", " + this.getPassword();
    }

    public AccountRegister(String name, String surname, String email, String password){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;


    }

    public void setPassword(String password) {
        final Random RANDOM = new SecureRandom();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


        byte[] salt = new byte[16];// credits to user "Assylias" https://stackoverflow.com/questions/18142745/how-do-i-generate-a-salt-in-java-for-salted-hash
        RANDOM.nextBytes(salt);
        String generatedSalt = Base64.getEncoder().encodeToString(salt);


        //System.out.println(generatedSalt);


        this.password = passwordEncoder.encode(password + generatedSalt);

        //System.out.println(this.password);
    }
}
