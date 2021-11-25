package com.example.clientproject.services;

import com.example.clientproject.data.users.Users;
import com.example.clientproject.data.users.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class findUserByEmailServiceStatic implements findUserByEmailService {
    private UsersRepo usersRepo;

    @Autowired
    public findUserByEmailServiceStatic(UsersRepo aRepo){
        usersRepo = aRepo;
    }

    public Optional<Users> findByUserEmail(String email) {
        return usersRepo.findByUserEmail(email);
    }
}
