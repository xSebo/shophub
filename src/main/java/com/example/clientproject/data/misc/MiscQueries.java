package com.example.clientproject.data.misc;

import com.example.clientproject.data.tags.Tags;
import com.example.clientproject.data.users.Users;
import com.example.clientproject.service.dtos.UsersDTO;

import java.util.List;

public interface MiscQueries {
    void saveUserFavouriteTags(Users user, Tags tag);
    void saveUser(Users user);
    void saveTag(Tags tag);
    List<UserFavouriteTags> findAllUserFavouriteTags();
}
