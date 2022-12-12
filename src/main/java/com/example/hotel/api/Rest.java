package com.example.hotel.api;

import com.example.hotel.service.BookingService;
import com.example.hotel.service.RoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Rest {

    private final RoomService roomService;
    private final BookingService bookingService;

    public Rest(RoomService roomService, BookingService bookingService) {
        this.roomService = roomService;
        this.bookingService = bookingService;
    }

    //Zysk po rest
    @GetMapping("/raport/{year}/{month}/{day}")
    public List<Object> dateRaport(@PathVariable("year") Integer year, @PathVariable("month") Integer month, @PathVariable("day") Integer day){
        List<Object> profitList = new ArrayList<>();
        LocalDate reportDay = LocalDate.of(year, month, day);

        profitList.add(reportDay);
        profitList.add(bookingService.dayProfit(reportDay));
        return profitList;

    }

}
