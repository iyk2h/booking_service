package com.example.booking_service_01.controller;

import java.util.List;

import com.example.booking_service_01.dto.FacilityDTO;
import com.example.booking_service_01.service.FacilityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {
    @Autowired
    FacilityService facilityService;

    //facility list
    @GetMapping(path="", produces = "application/json")
    public ResponseEntity<?> getAllFacility() {
        List<FacilityDTO> dtos = facilityService.findAll();
        if(dtos.size() <= 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }    
}
