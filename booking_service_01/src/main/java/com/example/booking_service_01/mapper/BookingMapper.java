package com.example.booking_service_01.mapper;

import com.example.booking_service_01.dto.BookingDTO;
import com.example.booking_service_01.entity.Booking;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookingMapper{
    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);
    BookingDTO bookingToBookingDTO(Booking booking);
    Booking bookingDTOToBooking(BookingDTO bookingDTO);
}