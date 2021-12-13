package com.example.clientproject.data.misc;

import com.example.clientproject.data.tags.Tags;
import com.example.clientproject.data.users.Users;
import com.example.clientproject.service.dtos.UsersDTO;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface MiscQueries {
    void saveUserFavouriteTags(Users user, Tags tag, HttpSession session);
    void saveUser(Users user, HttpSession session);
    void updateUser(int userId, String field, Object value, HttpSession session);
    void saveTag(Tags tag, HttpSession session);
    List<UserFavouriteTags> findAllUserFavouriteTags();
}
