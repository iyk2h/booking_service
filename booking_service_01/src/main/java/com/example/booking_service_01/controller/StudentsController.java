package com.example.booking_service_01.controller;

import com.example.booking_service_01.dto.JwtStudentsDTO;
import com.example.booking_service_01.dto.StudentsDTO;
import com.example.booking_service_01.repository.StudentsRepository;
import com.example.booking_service_01.service.StudentsService;

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
@RequestMapping("/students")
public class StudentsController {
    @Autowired
    StudentsService studentsService;
    @Autowired
    StudentsRepository studentsRepository;
    //Select 
    @GetMapping(path="/{sid}", produces = "application/json")
    public ResponseEntity<?> getSid(@PathVariable("sid") Integer sid) {
        if(!studentsService.checkSid(sid)) {
            return new ResponseEntity<>("sid can not found", HttpStatus.NOT_ACCEPTABLE);
        }
        else {
            StudentsDTO studentsDTO = studentsService.findBySid(sid);
            return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
        }
    }
    //Insert
    @PostMapping(path = "/join", produces = "application/json")
    public ResponseEntity<?> insertStudent(@RequestBody StudentsDTO studentsDTO) {
        if(studentsDTO.getSid()!=null) {
            if (studentsService.checkSid(studentsDTO.getSid()))
                return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
            return new ResponseEntity<>(studentsService.findBySid(studentsService.insertStudentsDTO(studentsDTO)), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        }
    }
    //Update   
    @PutMapping(path = "/{sid}/", produces = "application/json")
    public ResponseEntity<?> updateStudent(@PathVariable("sid") Integer sid, @RequestBody StudentsDTO studentsDTO) {
        StudentsDTO beforeDTO = studentsService.findBySid(sid);
        if(studentsDTO != null){
            Integer u_sid = studentsDTO.getSid()!=null?sid:beforeDTO.getSid();
            String u_pw = studentsDTO.getPw()!=null?studentsDTO.getPw():beforeDTO.getPw();
            String u_phone = studentsDTO.getPhone()!=null?studentsDTO.getPhone():beforeDTO.getPhone();
            String u_name = studentsDTO.getName()!=null?studentsDTO.getName():beforeDTO.getName();

            StudentsDTO updateDTO= StudentsDTO.builder()
                .sid(u_sid)
                .pw(u_pw)
                .phone(u_phone)
                .name(u_name)
                .build();
            
            return new ResponseEntity<>(studentsService.findBySid(studentsService.update(updateDTO)), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Update fail", HttpStatus.NOT_ACCEPTABLE);
    }
    //Delete
    @DeleteMapping(path="/{sid}", produces = "application/json")
    public ResponseEntity<?> deleteStudent(@PathVariable("sid") Integer sid) {
        if(!studentsService.checkSid(sid))
            return new ResponseEntity<>("Admin ID can not found", HttpStatus.NOT_ACCEPTABLE);
        else {
            StudentsDTO studentsDTO = studentsService.findBySid(sid);
            studentsService.delete(studentsDTO);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }

    //Login
    @PostMapping(path = "/login", produces = "application/json")

    public ResponseEntity<?> loginAdmin(@RequestBody JwtStudentsDTO loginDTO) {
        if(!studentsService.checkSid(loginDTO.getSid())) {
            return new ResponseEntity<>("ano can not found", HttpStatus.NOT_ACCEPTABLE);
        }
        else
            if(studentsService.students_login(loginDTO.getSid(), loginDTO.getPw()) == true){
            return new ResponseEntity<>(HttpStatus.OK);
            }
            else {
            return new ResponseEntity<>("login fail", HttpStatus.NOT_ACCEPTABLE);
            }
    }    
}
