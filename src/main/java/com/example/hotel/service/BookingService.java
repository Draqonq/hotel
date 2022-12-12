package com.example.hotel.service;

import com.example.hotel.entity.Booking;
import com.example.hotel.repo.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService implements BookingServiceI {

    private final BookingRepo bookingRepo;

    @Autowired
    public BookingService(BookingRepo bookingRepo) {
        this.bookingRepo = bookingRepo;
    }

    //Zapis rezerwacji
    public void save(Booking booking){
        bookingRepo.save(booking);
    }

    //Lista rezerwacji
    public List<Booking> findAll(){
        return bookingRepo.findAll();
    }

    //Zysk z danego dnia
    public Float dayProfit(LocalDate day){
        //List<Object> profitList = new ArrayList<>();
        Float profit = findAll().stream()
                .filter(booking -> booking.getStartDate().minusDays(1).isBefore(day))
                .filter(booking -> booking.getEndDate().plusDays(1).isAfter(day))
                .map(booking -> booking.getRooms().getCost())
                .reduce(0F, Float::sum);

        //profitList.add(day);
        //profitList.add(profit);
        return profit;
    }

    public String profity(){
        List<LocalDate> dates = new ArrayList<>();
        List<Float> profits = new ArrayList<>();
        LocalDate max = bookingRepo.findAll().stream()
                .map(u -> u.getEndDate()).max(LocalDate::compareTo).get();
        LocalDate startFirm = LocalDate.of(2021,06,25);
        for (LocalDate date = startFirm; date.isBefore(max); date = date.plusDays(1))
        {
            dates.add(date);
            profits.add(0F);
        }

        return "";
    }

}
