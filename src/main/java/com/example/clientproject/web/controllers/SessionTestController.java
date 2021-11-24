package com.example.clientproject.web.controllers;

import com.example.clientproject.service.Utils.JWTUtils;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SessionTestController {
    @GetMapping("/session")
    public String getSessionPage(Model model, HttpSession session){
        JWTUtils.getKey();

        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
        model.addAttribute("sessionData",messages);
        return "session-test";
    }

    @GetMapping("/sessionSave")
    public String setSessionData(Model model, HttpSession session){
        @SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
        if (messages == null) {
            messages = new ArrayList<>();
            session.setAttribute("MY_SESSION_MESSAGES", messages);
        }
        messages.add("test");
        session.setAttribute("MY_SESSION_MESSAGES", messages);

        model.addAttribute("sessionData",messages);
        return "session-test";
    }

    @GetMapping("/sessionDelete")
    public String deleteSessionData(Model model, HttpSession session){
        List<String> messages = new ArrayList<>();
        session.setAttribute("MY_SESSION_MESSAGES", messages);
        model.addAttribute("sessionData",messages);
        return "session-test";
    }
}




