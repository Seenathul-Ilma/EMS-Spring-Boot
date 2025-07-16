package lk.ijse.gdse71.springboot_practice.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * --------------------------------------------
 * Author: Zeenathul Ilma
 * GitHub: https://github.com/Seenathul-Ilma
 * Website: https://zeenathulilma.vercel.app/
 * --------------------------------------------
 * Created: 7/16/2025 12:29 PM
 * Project: SpringBoot_Practice
 * --------------------------------------------
 **/

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventDTO {
    private Integer eventId;
    private String eventName;
    private String eventDescription;
    private String eventLocation;
    private LocalDate eventDate;
    private LocalDateTime eventBookedDate;
    private String eventStatus;
}

