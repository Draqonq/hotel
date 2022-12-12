package com.example.hotel.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String guest;

    @ManyToOne
    private Room rooms;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    public Booking(String guest, Room rooms, LocalDate startDate, LocalDate endDate) {
        this.guest = guest;
        this.rooms = rooms;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
