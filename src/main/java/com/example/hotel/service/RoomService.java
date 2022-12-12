package com.example.hotel.service;

import com.example.hotel.entity.Room;
import com.example.hotel.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class RoomService implements RoomServiceI{

    private final RoomRepo roomRepo;

    @Autowired
    public RoomService(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }

    //Zapis
    public void save(Room room){
        roomRepo.save(room);
    }

    //Lista wszystkich pokoi
    public List<Room> findAll(){
        return roomRepo.findAll();
    }

    //Wyszukiwanie po id
    public Room findById(Long id){
        return roomRepo.findAll().stream()
                .filter(room -> room.getId().equals(id))
                .findFirst()
                .orElse(new Room(1, 1F, false, false, false, "2"));
    }

    //Tworzenie pokoi
    @PostConstruct
    public void init(){
        save(new Room(1, 80F, false, false, false,"1"));
        save(new Room(1, 150F, true, true, false, "2"));
        save(new Room(3, 140F, false, false, false, "2"));
        save(new Room(3, 180F, false, false, false, "2"));
        save(new Room(2, 250F, true, false, false, "2"));
        save(new Room(1, 120F, true, false, false, "5"));
        save(new Room(1, 210F, false, false, false,"1"));
        save(new Room(2, 250F, true, false, false,"2"));
        save(new Room(1, 170F, true, false, false, "4"));
        save(new Room(4, 220F, false, true, false, "4"));
        save(new Room(2, 450F, false, true, false,"2"));
        save(new Room(3, 160F, true, true, false, "4"));
        save(new Room(2, 340F, true, false, false, "4"));
        save(new Room(2, 120F, true, false, false,"2"));
        save(new Room(2, 1000F, true, false, false,"1"));
        save(new Room(2, 800F, false, false, true, "5"));
    }

}
