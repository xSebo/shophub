package com.example.clientproject.web.controllers;

import org.apache.tomcat.jni.Time;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import java.util.Arrays;
import java.util.HashMap;

import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.SharedHttpSessionConfigurer.sharedHttpSession;

@SpringBootTest
@Sql(scripts = {"/schema-test-h2.sql", "/script-test-h2.sql"})
@ActiveProfiles("h2")
@AutoConfigureMockMvc
public class UpdateSocials {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Test
    public void testUserIsLinked() throws Exception {

        HashMap<String, Object> sessionattr = new HashMap<String, Object>();

        this.mockMvc.perform(post("/login")
                        .contentType(APPLICATION_FORM_URLENCODED).
                        param("loginEmail", "ShrayaBELUSKO@email.com").
                        param("loginPassword", "password123"))
                .andDo(
                        result -> sessionattr.put("Set-Cookie",result.getResponse().getHeader("Set-Cookie"))
                ).andDo(print());

        System.out.println(sessionattr.get("Set-Cookie"));

        this.mockMvc.perform(post("/updateSocials")
                        .sessionAttrs(sessionattr)
                        .contentType(APPLICATION_FORM_URLENCODED).
                        param("shopId", "2").
                        param("instagram", "test").
                        param("facebook", "test").
                        param("twitter", "twitter").
                        param("tiktok", "test").
                        param("shopUrl", "http://www.test.com"))
                .andReturn().getRequest().getSession().getAttributeNames()
                .asIterator().forEachRemaining(x-> System.out.println(x));

        //session.getAttributeNames().asIterator().forEachRemaining(x -> System.out.println(x));


    }
}
