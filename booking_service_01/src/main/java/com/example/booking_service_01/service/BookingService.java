package com.example.booking_service_01.service;

import com.example.booking_service_01.dto.BookingDTO;

public interface BookingService {
    BookingDTO findByBno(Integer bno);
    Integer insertBookingDto(BookingDTO bookingDTO);   
}
