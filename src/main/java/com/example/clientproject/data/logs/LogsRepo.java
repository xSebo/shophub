package com.example.clientproject.data.logs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * JPA Repo for the Logs entity
 */
public interface LogsRepo extends JpaRepository<Logs, Long> {
    /**
     * Find all the logs from a specific date time
     * @param dateTime - the datetime to search by
     * @return - list of logs from that date time
     */
    @Query("select l from Logs l where l.logDateTime like ?1")
    List<Logs> findByDateTime(String dateTime);

    /**
     * Find all the logs for a specific event
     * @param eventId - the id of the event to search by
     * @return - a list of logs with that event
     */
    @Query("select l from Logs l where l.event.eventId = ?1")
    List<Logs> findByEventId(long eventId);

    /**
     * Find all the logs for a specific user
     * @param userId - the id of the user to search by
     * @return - a list of all the logs for that user
     */
    @Query("select l from Logs l where l.user.userId = ?1")
    List<Logs> findByUserId(long userId);

    /**
     * Find all the logs by a specific super admin status
     * @param superAdminStatus - the status to search by
     * @return - a list of the logs found
     */
    @Query("select l from Logs l where l.logSuperAdmin = ?1")
    List<Logs> findBySuperAdminStatus(boolean superAdminStatus);
}
