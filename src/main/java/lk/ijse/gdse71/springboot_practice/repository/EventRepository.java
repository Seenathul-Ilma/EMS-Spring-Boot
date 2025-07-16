package lk.ijse.gdse71.springboot_practice.repository;


import jakarta.transaction.Transactional;
import lk.ijse.gdse71.springboot_practice.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE event SET event_status='Completed' WHERE event_id=?", nativeQuery = true)
    void updateEventStatusById(Integer id);    // Entity & Primary Key Type

    List<Event> findEventsByEventStatusContainingIgnoreCaseOrderByEventDate(String keyword);
}
