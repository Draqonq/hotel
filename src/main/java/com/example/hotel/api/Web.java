package com.example.hotel.api;

import com.example.hotel.entity.Booking;
import com.example.hotel.entity.Room;
import com.example.hotel.service.BookingService;
import com.example.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/hotel")
public class Web {

    private final RoomService roomService;
    private final BookingService bookingService;

    @Autowired
    public Web(RoomService roomService, BookingService bookingService) {
        this.roomService = roomService;
        this.bookingService = bookingService;
    }


    //Wyszukiwanie wolnych pokoi
    public List<Room> findRoom(Integer capacity, Boolean isApartment, Boolean isBalcony, Boolean isSpecialJavaProgrammersRoom, LocalDate startBooking, LocalDate endBooking){
        List<Room> roomList = new ArrayList<>();
        roomService.findAll().stream()
                .filter(room -> room.getCapacity().equals(capacity))
                .filter(room -> room.getIsApartment().equals(isApartment))
                .filter(room -> room.getIsBalcony().equals(isBalcony))
                .filter(room -> room.getIsSpecialJavaProgrammersRoom().equals(isSpecialJavaProgrammersRoom))
                .filter(room -> {
                    List<Booking> bookingList = bookingService.findAll().stream()
                            .filter(booking -> booking.getRooms().equals(room))
                            .filter(booking -> booking.getStartDate().compareTo(endBooking) <= 0)
                            .filter(booking -> booking.getEndDate().compareTo(startBooking) >= 0)
                            .collect(Collectors.toList());
                    return bookingList.size() == 0;
                })
                .forEach(roomList::add);
        return roomList;
    }

    //Dodanie wynajmu
    @PostConstruct
    public void init(){
        bookingService.save(new Booking("Andrzej", roomService.findById(2L), LocalDate.now(), LocalDate.of(2021, 7, 1)));
    }

    //Strona główna hotelu
    @GetMapping("")
    public String home(Model model, Booking booking, Room room){
        model.addAttribute("booking", booking);
        model.addAttribute("room", room);
        return "home";
    }

    //Strona wyszukiwania ofert
    @PostMapping("/room")
    public String room(Model model, Booking booking, Room room){
        model.addAttribute("booking", booking);
        model.addAttribute("room", room);

        //Błąd w przypadku podania daty wynajmu później niż data zakończenia
        if(booking.getStartDate().compareTo(booking.getEndDate()) >= 0){
            return "errors";
        }
        else{
            List<Room> rm = findRoom(room.getCapacity(),
                    room.getIsApartment(),
                    room.getIsBalcony(),
                    room.getIsSpecialJavaProgrammersRoom(),
                    booking.getStartDate(),
                    booking.getEndDate());

            model.addAttribute("availableRoom", rm);
            return "rooms";
        }
    }

    //Dodawanie wynajmu
    @PostMapping("/add")
    public String add(Model model, Booking booking, Room room){
        model.addAttribute("booking", booking);
        model.addAttribute("room", room);
        if(booking.getGuest().length() > 3
                && booking.getRooms() != null
                && booking.getStartDate() != null
                && booking.getEndDate() != null){
            Booking b = new Booking(booking.getGuest(), booking.getRooms(), booking.getStartDate(), booking.getEndDate());
            bookingService.save(b);
            return "add";
        }
        //Błąd dla błędnych wartości
        else{
            return "errors";
        }
    }
}
