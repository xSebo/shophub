package com.example.clientproject.data.events;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * Repository for the "Events" Entity
 */
public interface EventsRepo extends JpaRepository<Events, Long> {
    /**
     * Find an event by its name
     * @param name - the name to search by
     * @return - the event as an optional
     */
    @Query("select e from Events e where e.eventName = ?1")
    Optional<Events> findByEventName(String name);
}
