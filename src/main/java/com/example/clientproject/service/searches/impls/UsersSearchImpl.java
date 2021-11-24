package com.example.clientproject.service.searches.impls;

import com.example.clientproject.data.users.Users;
import com.example.clientproject.data.users.UsersRepo;
import com.example.clientproject.service.dtos.UsersDTO;
import com.example.clientproject.service.searches.UsersSearch;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersSearchImpl implements UsersSearch {
    private final UsersRepo usersRepo;

    public UsersSearchImpl(UsersRepo aUsersRepo) {
        usersRepo = aUsersRepo;
    }

    @Override
    public List<UsersDTO> findAll() {
        return usersRepo
                .findAll()
                .stream()
                .map(UsersDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UsersDTO> findByEmail(String email) {
        Optional<Users> usersOptional = usersRepo.findByUserEmail(email);
        if (usersOptional.isPresent()) {
            UsersDTO usersDTO = new UsersDTO(usersOptional.get());
            return Optional.of(usersDTO);
        } else {
            return Optional.empty();
        }
    }
}
