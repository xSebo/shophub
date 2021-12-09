package com.example.clientproject.data.logs;

import com.example.clientproject.data.converters.TinyIntToBoolean;
import com.example.clientproject.data.events.Events;
import com.example.clientproject.data.users.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entity object for logs
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Logs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long logId;
    private String logDetails;
    private String logDateTime;
    @Convert(converter = TinyIntToBoolean.class)
    private boolean logSuperAdmin;

    @ManyToOne
    @JoinColumn(name="User_Id", nullable=false)
    private Users user;

    @ManyToOne
    @JoinColumn(name="Event_Id", nullable=false)
    private Events event;

    public Logs(String details, String dateTime, boolean superAdmin, Users aUser, Events aEvent) {
        logDetails = details;
        logDateTime = dateTime;
        logSuperAdmin = superAdmin;
        user = aUser;
        event = aEvent;
    }
}
