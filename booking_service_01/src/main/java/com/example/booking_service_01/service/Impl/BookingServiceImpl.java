package com.example.booking_service_01.service.Impl;

import com.example.booking_service_01.dto.BookingDTO;
import com.example.booking_service_01.entity.Booking;
import com.example.booking_service_01.mapper.BookingMapper;
import com.example.booking_service_01.repository.BookingRepository;
import com.example.booking_service_01.service.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingRepository bookingRepository;

    @Override
    public BookingDTO findByBno(Integer bno) {
        Booking booking = bookingRepository.findByBno(bno);
        BookingDTO bookingDTO = BookingMapper.INSTANCE.booking_To_DTO(booking);
        return bookingDTO;
    }

    @Override
    public Integer insertBookingDto(BookingDTO bookingDTO) {
        Booking booking = BookingMapper.INSTANCE.bookingDto_To_Entity(bookingDTO);
        bookingRepository.save(booking);
        return booking.getBno();
    }
}
