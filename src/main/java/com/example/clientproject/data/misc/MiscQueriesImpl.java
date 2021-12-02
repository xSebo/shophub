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
public class MiscQueriesImpl implements MiscQueries{
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<UserFavouriteTags> userFavouriteTagsRowMapper;

    /**
     * Constructor
     * @param aJdbcTemplate - the JDBC Template to pass in
     */
    public MiscQueriesImpl(JdbcTemplate aJdbcTemplate) {
        this.jdbcTemplate = aJdbcTemplate;

        userFavouriteTagsRowMapper = (rs, i) -> new UserFavouriteTags(
                rs.getLong("User_Favourite_Tag_Id"),
                rs.getLong("User_Id"),
                rs.getLong("Tag_Id")
        );
    }

    /**
     * Insert into "User_Favourite_Tags"
     * @param user - the user
     * @param tag - the tag
     */
    public void saveUserFavouriteTags(Users user, Tags tag) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("User_Favourite_Tags")
                .usingGeneratedKeyColumns("User_Favourite_Tag_Id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("User_Id", user.getUserId());
        parameters.put("Tag_Id", tag.getTagId());

        Number id = simpleJdbcInsert.execute(parameters);
    }

    /**
     * Insert into "Users" table
     * @param user - the user to insert
     */
    public void saveUser(Users user) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Users")
                .usingGeneratedKeyColumns("User_Id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("User_Id", user.getUserId());
        parameters.put("User_First_Name", user.getUserFirstName());
        parameters.put("User_Last_Name", user.getUserLastName());
        parameters.put("User_Email", user.getUserEmail());
        parameters.put("User_Password", user.getUserPassword());
        parameters.put("User_Profile_Picture", user.getUserProfilePicture());
        parameters.put("Two_Factor_Method_Id", user.getTwoFactorMethod().getTwoFactorMethodId());
        parameters.put("User_Reset_Code", user.getUserResetCode());
        parameters.put("User_Reset_Code_Expiry", user.getUserResetCodeExpiry());

        Number id = simpleJdbcInsert.execute(parameters);
    }

    /**
     * Insert into the "Tags" table
     * @param tag - the tag to insert
     */
    public void saveTag(Tags tag) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Tags")
                .usingGeneratedKeyColumns("Tag_Id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Tag_Id", tag.getTagId());
        parameters.put("Tag_Name", tag.getTagName());

        Number id = simpleJdbcInsert.execute(parameters);
    }

    /**
     * Find all entries into the "User_Favourite_Tags" table
     * @return - list of all entries found
     */
    public List<UserFavouriteTags> findAllUserFavouriteTags() {
        return jdbcTemplate.query(
                "select * from User_Favourite_Tags",
                userFavouriteTagsRowMapper,
                new Object[]{}
        );
    }
}
