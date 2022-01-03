package com.example.booking_service_01.service.Impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.example.booking_service_01.dto.BookingDTO;
import com.example.booking_service_01.entity.Booking;
import com.example.booking_service_01.entity.Facility;
import com.example.booking_service_01.mapper.BookingMapper;
import com.example.booking_service_01.repository.BookingRepository;
import com.example.booking_service_01.repository.FacilityRepository;
import com.example.booking_service_01.service.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    FacilityRepository facilityRepository;

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

    @Override
    public List<BookingDTO> findByFno(Integer fno) {
        Facility facility = facilityRepository.findByFno(fno);
        List<BookingDTO> dtos = new ArrayList<>();
        List<Booking> entitys = bookingRepository.findByFacility(facility);
        dtos = BookingMapper.INSTANCE.booking_To_List_DTO(entitys);
        return dtos;
    }

    @Override
    public List<BookingDTO> findBookingListByDate(LocalDate date) {
        LocalDateTime start = LocalDateTime.of(date, LocalTime.of(0, 0));
        LocalDateTime end = start.plusDays(1);
        List<Booking> entitys = bookingRepository.findAllByStartTimeBetween(start, end);
        
        List<BookingDTO> dtos = BookingMapper.INSTANCE.booking_To_List_DTO(entitys);
        
        return dtos;
    }
}
