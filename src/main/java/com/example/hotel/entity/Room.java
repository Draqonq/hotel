package com.example.hotel.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Booking> bookingList;

    private Integer capacity;
    private Float cost;
    private Boolean isApartment;
    private Boolean isBalcony;
    private Boolean isSpecialJavaProgrammersRoom;
    private String imageNumber;

    public Room(Integer capacity, Float cost, Boolean isApartment, Boolean isBalcony, Boolean isSpecialJavaProgrammersRoom, String imageNumber) {
        this.capacity = capacity;
        this.cost = cost;
        this.isApartment = isApartment;
        this.isBalcony = isBalcony;
        this.isSpecialJavaProgrammersRoom = isSpecialJavaProgrammersRoom;
        this.imageNumber = imageNumber;
    }

}
