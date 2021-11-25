package com.example.clientproject.services;

import com.example.clientproject.data.twoFactorMethods.TwoFactorMethods;
import com.example.clientproject.data.twoFactorMethods.TwoFactorMethodsRepo;
import com.example.clientproject.data.users.Users;
import com.example.clientproject.data.users.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class registerUserServiceStatic implements registerUserService{
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    TwoFactorMethodsRepo twoFactorMethodsRepo;

    public void save(newAccountDTO accountDTO){
        long ID = 1;
        TwoFactorMethods twoFactorMethods = new TwoFactorMethods();
        twoFactorMethods.setTwoFactorMethodId(ID);
        twoFactorMethods.setTwoFactorMethodName("None");

        twoFactorMethodsRepo.save(twoFactorMethods);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Users newUser = new Users(accountDTO.getName(), accountDTO.getSurname(), accountDTO.getEmail(), accountDTO.getPassword(),
                "", "",
                LocalDateTime.now().format(formatter), twoFactorMethods);
        usersRepo.save(newUser);

        //System.out.println(usersRepo.findById(newUser.getUserId()));

    }
}
