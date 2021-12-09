package com.example.clientproject.data.events;

import com.example.clientproject.data.logs.Logs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * Entity object for events which trigger a log
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long eventId;
    private String eventName;
}
