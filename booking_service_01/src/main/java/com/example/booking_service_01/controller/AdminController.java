package com.example.booking_service_01.controller;

import com.example.booking_service_01.dto.AdminDTO;
import com.example.booking_service_01.repository.AdminRepository;
import com.example.booking_service_01.service.BookingServiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AdminController {
    @Autowired
    BookingServiceService bookingServiceService;
    @Autowired
    AdminRepository adminRepository;
    
    //select admin aid
    @GetMapping(path="/admin/{aid}", produces = "application/json")
    public ResponseEntity<?> getAid(@PathVariable("aid") String aid) {
        System.out.println("controller"); 
        System.out.println(aid);
        if(!bookingServiceService.checkaid(aid)) {

            System.out.println("controller-check-no"); 
            System.out.println(aid);

            return new ResponseEntity<>("aid can not found", HttpStatus.OK);
        }
        else {

            System.out.println("controller-check-yes"); 
            System.out.println(aid);

            AdminDTO adminDTO = bookingServiceService.findByAid(aid);
            return new ResponseEntity<>(adminDTO, HttpStatus.OK);
        }
    }

}
