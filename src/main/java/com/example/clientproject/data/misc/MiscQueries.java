package com.example.clientproject.data.misc;

import com.example.clientproject.data.tags.Tags;
import com.example.clientproject.data.users.Users;
import com.example.clientproject.service.dtos.UsersDTO;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for any misc queries, JDBC will be used instead of JPA for more customization
 */
@Repository
public class MiscQueries {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<UserFavouriteTags> userFavouriteTagsRowMapper;

    /**
     * Constructor
     * @param aJdbcTemplate - the JDBC Template to pass in
     */
    public MiscQueries(JdbcTemplate aJdbcTemplate) {
        this.jdbcTemplate = aJdbcTemplate;

        userFavouriteTagsRowMapper = (rs, i) -> new UserFavouriteTags(
                rs.getLong("User_Favourite_Tag_Id"),
                rs.getLong("User_Id"),
                rs.getLong("Tag_Id")
        );
    }

    /**
     * Insert into "User_Favourite_Tags"
     * @param userDTO - the user as a DTO object
     * @param tag - the tag
     */
    public void saveUserFavouriteTags(UsersDTO userDTO, Tags tag) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("User_Favourite_Tage")
                .usingGeneratedKeyColumns("User_Favourite_Tag_Id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("User_Id", userDTO.getUserId());
        parameters.put("Tag_Id", tag.getTagId());

        Number id = simpleJdbcInsert.execute(parameters);
    }

    public List<UserFavouriteTags> findAllUserFavouriteTags() {
        return jdbcTemplate.query(
                "select * from User_Favourite_Tags",
                userFavouriteTagsRowMapper,
                new Object[]{}
        );
    }
}
