package com.example.booking_service_01.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.example.booking_service_01.dto.BookingDTO;

public interface BookingService {
    BookingDTO findByBno(Integer bno);
    Integer insertBookingDto(BookingDTO bookingDTO);   
    List<BookingDTO> findByFno(Integer fno);
    List<BookingDTO> findBookingListByDate(LocalDate date);
    List<BookingDTO> findBookingListByFacilityWhitDate(Integer fno, LocalDate date);
}
