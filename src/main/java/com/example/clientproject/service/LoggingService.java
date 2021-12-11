package com.example.clientproject.service;

import com.example.clientproject.data.events.Events;
import com.example.clientproject.data.events.EventsRepo;
import com.example.clientproject.data.logs.Logs;
import com.example.clientproject.data.logs.LogsRepo;
import com.example.clientproject.service.Utils.JWTUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Service for all logging related methods
 */
@Service
public class LoggingService {
    LogsRepo logsRepo;
    EventsRepo eventsRepo;
    JWTUtils jwtUtils;

    /**
     * Constructor
     * @param aLogsRepo - object of type LogsRepo
     * @param aJWTUtils - object of type JWTUtils
     */
    public LoggingService(LogsRepo aLogsRepo, EventsRepo aEventRepo, JWTUtils aJWTUtils) {
        jwtUtils = aJWTUtils;
        eventsRepo = aEventRepo;
        logsRepo = aLogsRepo;
    }

    public Optional<Events> findEventByName(String eventName) {
        return eventsRepo.findByEventName(eventName);
    }

    /**
     * Method for logging an event
     * @param event - the event
     * @param session - the session
     * @param details - details of the event
     */
    public void logEvent(String event, HttpSession session, String details) {
        // If the user attempting to log is not logged in
        if (!jwtUtils.getLoggedInUserId(session).isPresent()) {
            return;
        }

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
                findEventByName(event).get()
        );

        // Save the new log
        logsRepo.save(newLog);
    }
}
