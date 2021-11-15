package com.example.booking_service_01.controller;

import com.example.booking_service_01.dto.AdminDTO;
import com.example.booking_service_01.entity.Admin;
import com.example.booking_service_01.repository.AdminRepository;
import com.example.booking_service_01.service.BookingServiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    BookingServiceService bookingServiceService;
    @Autowired
    AdminRepository adminRepository;
    
    //Select by ano
    @GetMapping(path="/{ano}", produces = "application/json")
    public ResponseEntity<?> getAno(@PathVariable("ano") Integer ano) {
        if(!bookingServiceService.checkAno(ano)) {
            return new ResponseEntity<>("ano can not found", HttpStatus.OK);
        }
        else {
            AdminDTO adminDTO = bookingServiceService.findByAno(ano);
            return new ResponseEntity<>(adminDTO, HttpStatus.OK);
        }
    }

    //Insert
    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<?> insertAdmin(@RequestBody AdminDTO adminDTO) {
        if(adminDTO.getAno()!=null) {
            if (bookingServiceService.checkAno(adminDTO.getAno()))
                return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
            return new ResponseEntity<>(bookingServiceService.findByAno(bookingServiceService.insertAdminDto(adminDTO)), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        }
    }
    //Login

    //Update   
    @PutMapping(path = "/{ano}/", produces = "application/json")
    public ResponseEntity<?> updateAdmin(@PathVariable("ano") Integer ano, @RequestBody AdminDTO adminDTO) {
        if(adminDTO != null){
            if(ano != adminDTO.getAno() && adminDTO.getAno()==null){
                return new ResponseEntity<>("Update fail", HttpStatus.NOT_ACCEPTABLE); 
            }
            return new ResponseEntity<>(bookingServiceService.findByAno(bookingServiceService.update(ano, adminDTO)), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Update fail", HttpStatus.NOT_ACCEPTABLE);
    }

    //Delete
    @DeleteMapping(path="/{ano}", produces = "application/json")
    public ResponseEntity<?> deleteAdmin(@PathVariable("ano") Integer ano) {
        if(!bookingServiceService.checkAno(ano))
            return new ResponseEntity<>("Admin ID can not found", HttpStatus.NOT_ACCEPTABLE);
        else {
            AdminDTO admindDto = bookingServiceService.findByAno(ano);
            bookingServiceService.delete(admindDto);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }
}
