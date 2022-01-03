package com.example.booking_service_01.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    //Select by fno
    @GetMapping(path="/{fno}", produces = "application/json")
    public ResponseEntity<?> getFnoToBooking(@PathVariable("fno") Integer fno) {
        if(!facilityService.checkFno(fno)) {
            return new ResponseEntity<>("fno can not found", HttpStatus.NOT_ACCEPTABLE);
        }
        else {
            // FacilityDTO facilityDTO = facilityService.findByFno(fno);
            List<BookingDTO> bookingDTOs = bookingService.findByFno(fno);
            return new ResponseEntity<>(bookingDTOs, HttpStatus.OK);
        }
    }

    //Booking facility, student
    @PostMapping(path="/{fno}", produces = "application/json")
    public ResponseEntity<?> bookingFacilityStudent(@PathVariable("fno") Integer fno, @RequestBody BookingDTO bookingDTO,HttpServletRequest request) throws URISyntaxException {
        HttpSession session = request.getSession();
        Integer sid = (Integer) session.getAttribute("id");
        org.springframework.http.HttpHeaders httpHeaders = new org.springframework.http.HttpHeaders();
        if(sid == null) {
            URI redirectUrl = new URI("/students/login");
            httpHeaders.setLocation(redirectUrl);
            return new ResponseEntity<>("로그인 후 이용해 주세요.", httpHeaders, HttpStatus.MOVED_PERMANENTLY);
        }

        if(!facilityService.checkFno(fno)) {
            URI redirectUrl = new URI("/booking");
            httpHeaders.setLocation(redirectUrl);
            return new ResponseEntity<>("fno can not found", httpHeaders, HttpStatus.MOVED_PERMANENTLY);
        }
        else {
            URI redirectUrl = new URI("/students/mybooking");
            httpHeaders.setLocation(redirectUrl);
            return new ResponseEntity<>(bookingService.insertBookingDto(bookingDTO), httpHeaders, HttpStatus.MOVED_PERMANENTLY);
        }
    }

    @PostMapping(path="/{fno}/date", produces = "application/json")
    public ResponseEntity<?> bookingByDate(@PathVariable("fno") Integer fno, @RequestBody BookingDTO bookingDTO) {
        LocalDate date =bookingDTO.getDate();
        
        List<BookingDTO> bookingDTOs = bookingService.findBookingListByDate(date);
        
        return new ResponseEntity<>(bookingDTOs, HttpStatus.OK);
    }

    
}