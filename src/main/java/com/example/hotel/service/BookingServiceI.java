package com.example.hotel.service;

import com.example.hotel.entity.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingServiceI {
    void save(Booking booking);
    List<Booking> findAll();
    Float dayProfit(LocalDate day);
}
