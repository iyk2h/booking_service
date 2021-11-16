package com.example.booking_service_01.controller;

import com.example.booking_service_01.dto.StudentsDTO;
import com.example.booking_service_01.entity.Students;
import com.example.booking_service_01.repository.StudentsRepository;
import com.example.booking_service_01.service.StudentsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
