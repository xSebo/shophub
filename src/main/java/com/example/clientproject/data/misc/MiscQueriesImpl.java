package com.example.clientproject.data.misc;

import com.example.clientproject.data.tags.Tags;
import com.example.clientproject.data.users.Users;
import com.example.clientproject.service.LoggingService;
import com.example.clientproject.service.dtos.UsersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
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
    private LoggingService loggingService;

    /**
     * Constructor
     * @param aJdbcTemplate - the JDBC Template to pass in
     */
    public MiscQueriesImpl(JdbcTemplate aJdbcTemplate, LoggingService aLoggingService) {
        this.jdbcTemplate = aJdbcTemplate;

        userFavouriteTagsRowMapper = (rs, i) -> new UserFavouriteTags(
                rs.getLong("User_Favourite_Tag_Id"),
                rs.getLong("User_Id"),
                rs.getLong("Tag_Id")
        );

        loggingService = aLoggingService;
    }

    /**
     * Insert into "User_Favourite_Tags"
     * @param user - the user
     * @param tag - the tag
     */
    public void saveUserFavouriteTags(Users user, Tags tag, HttpSession session) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("User_Favourite_Tags")
                .usingGeneratedKeyColumns("User_Favourite_Tag_Id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("User_Id", user.getUserId());
        parameters.put("Tag_Id", tag.getTagId());

        Number id = simpleJdbcInsert.execute(parameters);
        // Log the changes
        loggingService.logEvent(
                "UserFavouriteTag Inserted",
                session,
                "UserFavouriteTag Inserted with User Id: " + user.getUserId() +
                        " and Tag Id: " + tag.getTagId() +
                        " in MiscQueriesImpl.saveUserFavouriteTags()"
        );
    }

    /**
     * Insert into "Users" table
     * @param user - the user to insert
     */
    public void saveUser(Users user, HttpSession session) {
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
        // Log the change
        loggingService.logEvent(
                "New User",
                session,
                "New User Inserted with Email: " + user.getUserEmail() +
                        " in MiscQueriesImpl.saveUser()"
        );
    }

    public void updateUser(int userId, String field, Object value, HttpSession session) {
        switch (field) {
            case "User_First_Name": {
                String sql = "UPDATE Users SET User_First_Name = ? WHERE User_Id = ?";
                jdbcTemplate.update(
                        // Script
                        sql,
                        // Arguments
                        value, userId
                );
                // Log the change
                loggingService.logEvent(
                        "User Details Changed",
                        session,
                        "User Details Updated with User Id: " + userId +
                                " with field: User_First_Name and value: " + value +
                                " in MiscQueriesImpl.updateUser()"
                );
                break;
            }
            case "User_Last_Name": {
                String sql = "UPDATE Users SET User_Last_Name = ? WHERE User_Id = ?";
                jdbcTemplate.update(
                        // Script
                        sql,
                        // Arguments
                        value, userId
                );
                // Log the change
                loggingService.logEvent(
                        "User Details Changed",
                        session,
                        "User Details Updated with User Id: " + userId +
                                " with field: User_Last_Name and value: " + value +
                                " in MiscQueriesImpl.updateUser()"
                );
                break;
            }
            case "User_Email": {
                String sql = "UPDATE Users SET User_Email = ? WHERE User_Id = ?";
                jdbcTemplate.update(
                        // Script
                        sql,
                        // Arguments
                        value, userId
                );
                // Log the change
                loggingService.logEvent(
                        "User Details Changed",
                        session,
                        "User Details Updated with User Id: " + userId +
                                " with field: User_Email and value: " + value +
                                " in MiscQueriesImpl.updateUser()"
                );
                break;
            }
            case "User_Profile_Picture": {
                String sql = "UPDATE Users SET User_Profile_Picture = ? WHERE User_Id = ?";
                jdbcTemplate.update(
                        // Script
                        sql,
                        // Arguments
                        value, userId
                );
                // Log the change
                loggingService.logEvent(
                        "User Details Changed",
                        session,
                        "User Details Updated with User Id: " + userId +
                                " with field: User_Profile_Picture and value: " + value +
                                " in MiscQueriesImpl.updateUser()"
                );
                break;
            }
            case "User_Password": {
                String sql = "UPDATE Users SET User_Password = ? WHERE User_Id = ?";
                jdbcTemplate.update(
                        // Script
                        sql,
                        // Arguments
                        value, userId
                );
                // Log the change
                loggingService.logEvent(
                        "User Details Changed",
                        session,
                        "User Details Updated with User Id: " + userId +
                                " with field: User_Password in MiscQueriesImpl.updateUser()"
                );
                break;
            }
        }
    }

    /**
     * Insert into the "Tags" table
     * @param tag - the tag to insert
     */
    public void saveTag(Tags tag, HttpSession session) {
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
