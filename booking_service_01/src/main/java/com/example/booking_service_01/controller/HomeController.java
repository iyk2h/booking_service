package com.example.booking_service_01.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    //check session
    @GetMapping(path = "/check", produces = "application/json")
    public ResponseEntity<?> checkSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sid = (String) session.getAttribute("id");
        if(sid == null) {
            return new ResponseEntity<>("로그인 후 이용해 주세요.", HttpStatus.UNAUTHORIZED); 
        }        
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
