package com.example.hotel.service;

import com.example.hotel.entity.Room;

import java.util.List;

public interface RoomServiceI {
    void save(Room room);
    List<Room> findAll();
    Room findById(Long id);
}
