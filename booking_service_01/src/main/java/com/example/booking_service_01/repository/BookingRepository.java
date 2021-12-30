package com.example.booking_service_01.repository;

import java.util.List;

import com.example.booking_service_01.entity.Booking;
import com.example.booking_service_01.entity.Facility;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>{
//    List<Booking> findByBnoList(Integer bno);
    
    Booking findByBno(Integer bno);
    
    List<Booking> findByFacility(Facility facility);
}
