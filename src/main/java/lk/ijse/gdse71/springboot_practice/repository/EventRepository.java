package lk.ijse.gdse71.springboot_practice.repository;


import lk.ijse.gdse71.springboot_practice.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {    // Entity & Primary Key Type
    // Additional query methods can be defined here if needed
}
