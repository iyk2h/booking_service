package com.example.booking_service_01.controller;

import com.example.booking_service_01.dto.AdminDTO;
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
        AdminDTO beforeDTO = bookingServiceService.findByAno(ano);
        if(adminDTO != null){
            Integer u_ano = adminDTO.getAno()!=null?adminDTO.getAno():beforeDTO.getAno();
            String u_pw = adminDTO.getPw()!=null?adminDTO.getPw():beforeDTO.getPw();
            String u_phone = adminDTO.getPhone()!=null?adminDTO.getPhone():beforeDTO.getPhone();
            String u_name = adminDTO.getName()!=null?adminDTO.getName():beforeDTO.getName();
            String u_email = adminDTO.getEmail()!=null?adminDTO.getEmail():beforeDTO.getEmail();

            AdminDTO updateDTO= AdminDTO.builder()
                .ano(u_ano)
                .pw(u_pw)
                .phone(u_phone)
                .name(u_name)
                .email(u_email)
                .build();
            
            return new ResponseEntity<>(bookingServiceService.findByAno(bookingServiceService.update(updateDTO)), HttpStatus.OK);
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
