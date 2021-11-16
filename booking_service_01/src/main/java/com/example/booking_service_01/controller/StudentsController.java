package com.example.booking_service_01.controller;

import com.example.booking_service_01.dto.StudentsDTO;
import com.example.booking_service_01.repository.StudentsRepository;
import com.example.booking_service_01.service.StudentsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
    //Insert
    @PostMapping(path = "/join", produces = "application/json")
    public ResponseEntity<?> insertAdmin(@RequestBody StudentsDTO studentsDTO) {
        if(studentsDTO.getSid()!=null) {
            if (studentsService.checkSid(studentsDTO.getSid()))
                return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
            return new ResponseEntity<>(studentsService.findBySid(studentsService.insertStudentsDTO(studentsDTO)), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        }
    }
}
