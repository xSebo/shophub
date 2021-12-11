package com.example.clientproject.web.controllers;

import com.example.clientproject.data.misc.MiscQueries;
import com.example.clientproject.data.tags.Tags;
import com.example.clientproject.data.tags.TagsRepo;
import com.example.clientproject.data.twoFactorMethods.TwoFactorMethods;
import com.example.clientproject.data.twoFactorMethods.TwoFactorMethodsRepo;
import com.example.clientproject.data.users.Users;
import com.example.clientproject.data.users.UsersRepo;
import com.example.clientproject.service.dtos.UsersDTO;
import com.example.clientproject.service.searches.UsersSearch;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql(scripts={"/schema-test-h2.sql","/script-test-h2.sql"})
@ActiveProfiles("h2")
@DirtiesContext
public class SelectCategoriesTests {
    @Autowired
    TwoFactorMethodsRepo twoFactorMethodsRepo;
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    TagsRepo tagsRepo;
    @Autowired
    MiscQueries miscQueries;

    /**
     * Testing that the correct number of favourite tags are saved
     * @param numOfTags - the number of tags being saved
     * @param tags - the tag names, connected with a "," between each
     */
    @ParameterizedTest
    @MethodSource("provideParameters")
    public void expectCorrectAdditionsToTableAfterFavouriteTagsSelected(int numOfTags, String tags) {
        // Get the size of the table at the beginning
        int expectedSize = miscQueries.findAllUserFavouriteTags().size() + numOfTags;

        // Create a new user
        TwoFactorMethods twoFactorMethods = twoFactorMethodsRepo.findByTwoFactorMethodId(1).get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Users newUser = new Users("", "", "", "",
                "", "",
                LocalDateTime.now().format(formatter), twoFactorMethods);
        // Save the user
        miscQueries.saveUser(newUser, new MockHttpSession());
        // Get the user as a DTO object
        Optional<Users> usersOptional = usersRepo.findByUserEmail(newUser.getUserEmail());

        // Split the array of tags by a comma
        String[] tagNames = tags.split(",");
        // For each name in the array
        for (String tagName: tagNames) {
            // Create a new "Tags" object with that name
            Tags newTag = new Tags(tagName);
            // Save a new tag with that name
            miscQueries.saveTag(newTag, new MockHttpSession());
            // Get the newly saved tag
            Optional<Tags> tagsOptional = tagsRepo.findByTagName(tagName);
            // Add a row to the "User_Favourite_Tags" table
            miscQueries.saveUserFavouriteTags(usersOptional.get(), tagsOptional.get(), new MockHttpSession());
        }

        // Get the size of the table at the beginning
        int actualSize = miscQueries.findAllUserFavouriteTags().size();

        // Assert that the size has grown by the correct number
        assertEquals(expectedSize, actualSize);
    }


    public static Stream<Arguments> provideParameters() {
        String[] stringArgs = {"Test1,Test2,Test3", "Test1,Test2,Test3,Test4,Test5", "Test1,Test2,Test3,Test4,Test5,Test6,Test7"};
        int[] intArgs = {3, 5, 7};

        return Stream.of(
                Arguments.of(intArgs[0], stringArgs[0]),
                Arguments.of(intArgs[1], stringArgs[1]),
                Arguments.of(intArgs[2], stringArgs[2])
        );
    }
}
