package com.example.clientproject.service;

import com.example.clientproject.data.events.Events;
import com.example.clientproject.data.logs.Logs;
import com.example.clientproject.data.logs.LogsRepo;
import com.example.clientproject.service.Utils.JWTUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Service for all logging based methods
 */
@Service
public class LoggingService {
    LogsRepo logsRepo;
    JWTUtils jwtUtils;

    /**
     * Constructor
     * @param aLogsRepo - object of type LogsRepo
     * @param aJWTUtils - object of type JWTUtils
     */
    public LoggingService(LogsRepo aLogsRepo, JWTUtils aJWTUtils) {
        jwtUtils = aJWTUtils;
        logsRepo = aLogsRepo;
    }

    /**
     * Method for logging an event
     * @param event - the event
     * @param session - the session
     * @param details - details of the event
     */
    public void logEvent(Events event, HttpSession session, String details) {
        // Instantiate a flagging variable
        boolean superAdminStatus;
        // If the session attribute "superAdmin" doesn't exist (super admin not logged in)
        if (session.getAttribute("superAdmin") == null) {
            // Set the flag to false
            superAdminStatus = false;
        // Else
        } else {
            // Set the flag to the state of the session attribute
            superAdminStatus = (boolean) session.getAttribute("superAdmin");
        }

        // Instantiate a DateTimeFormatter with the correct format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Create a new Log object
        Logs newLog = new Logs(
                details,
                LocalDateTime.now().format(formatter),
                superAdminStatus,
                jwtUtils.getLoggedInUserRow(session).get(),
                event
        );

        // Save the new log
        logsRepo.save(newLog);
    }
}
