package com.example.booking_service_01.controller;

import java.util.List;

import com.example.booking_service_01.dto.BookingDTO;
import com.example.booking_service_01.dto.FacilityDTO;
import com.example.booking_service_01.service.BookingService;
import com.example.booking_service_01.service.FacilityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/booking")
public class BookingController {
    @Autowired
    FacilityService facilityService;
    @Autowired
    BookingService bookingService;

    //facility list
    @GetMapping(path="", produces = "application/json")
    public ResponseEntity<?> getAllFacility() {
        List<FacilityDTO> dtos = facilityService.findAll();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping(path="/{fno}", produces = "application/json")
    public ResponseEntity<?> insetBooking(@PathVariable Integer fno ,@RequestBody BookingDTO bookingDTO) {
        if(bookingDTO!=null && bookingDTO.getBno()==null) {
            // if (adminService.checkAno(adminDTO.getAno()))
                // return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
            return new ResponseEntity<>(bookingService.findByBno(bookingService.insertBookingDto(bookingDTO)), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        }
    }
}