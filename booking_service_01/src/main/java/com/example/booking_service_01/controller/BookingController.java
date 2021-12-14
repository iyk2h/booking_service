package com.example.booking_service_01.controller;

import java.util.List;

import com.example.booking_service_01.dto.FacilityDTO;
import com.example.booking_service_01.service.FacilityService;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/booking")
public class BookingController {
    @Autowired
    FacilityService facilityService;
    
    //facility list
    @GetMapping(path="", produces = "application/json")
    public ResponseEntity<?> getAllFacility() {
        List<FacilityDTO> dtos = facilityService.findAll();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
