package com.example.booking_service_01.controller;

import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.booking_service_01.dto.JwtStudentsDTO;
import com.example.booking_service_01.dto.StudentsDTO;
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
    
    //Insert
    @PostMapping(path = "/join", produces = "application/json")
    public ResponseEntity<?> insertStudent(@RequestBody StudentsDTO studentsDTO) {
        if(studentsDTO.getSid()!=null) {
            if (studentsService.checkSid(studentsDTO.getSid()))
                return new ResponseEntity<>("id가 이미 존재합니다.",HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(studentsService.findBySid(studentsService.insertStudentsDTO(studentsDTO)), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("잘못 입력되었습니다.",HttpStatus.NOT_FOUND);
        }
    }

    //Select  
    @GetMapping(path="/{sid}", produces = "application/json")
    public ResponseEntity<?> getSid(@PathVariable("sid") Integer sid, HttpServletRequest request) {
        //admin
        if(!studentsService.checkSid(sid)) {
            return new ResponseEntity<>("sid can not found", HttpStatus.NOT_FOUND);
        }
        else {
            StudentsDTO studentsDTO = studentsService.findBySid(sid);
            return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
        }
    }

    //mypage
    @GetMapping(path = "/mypage", produces = "application/json")
    public ResponseEntity<?> mypage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer sid = (Integer) session.getAttribute("id");
        if(sid==null) {
            session.invalidate();
            return new ResponseEntity<>("로그인 후 이용해 주세요.", HttpStatus.UNAUTHORIZED);
        }
        else {
            StudentsDTO studentsDTO = studentsService.findBySid(sid);
            return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
        }
    }

    //Update   
    @PutMapping(path = "/{sid}", produces = "application/json")
    public ResponseEntity<?> updateStudent(@PathVariable("sid") Integer sid, @RequestBody StudentsDTO studentsDTO, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer sessionId = (Integer)session.getAttribute("id");
        if(sessionId==null) {
            session.invalidate();
            return new ResponseEntity<>("로그인 후 이용해 주세요.", HttpStatus.UNAUTHORIZED);
        }
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
    
    //Delete 탈퇴
    @DeleteMapping(path="/{sid}", produces = "application/json")
    public ResponseEntity<?> deleteStudent(@PathVariable("sid") Integer sid, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer sessionId = (Integer)session.getAttribute("id");
        StudentsDTO studentsDTO = studentsService.findBySid(sid);

        if(sessionId==null) {
            session.invalidate();
            return new ResponseEntity<>("로그인 후 이용해 주세요.", HttpStatus.UNAUTHORIZED);
        }
        if(!studentsService.checkSid(sid))
            return new ResponseEntity<>("sid can not found", HttpStatus.NOT_FOUND);
        else {
            studentsService.delete(studentsDTO);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

    //Login 로그인
    @PostMapping(path = "/login", produces = "application/json")
    public ResponseEntity<?> loginAdmin(@RequestBody JwtStudentsDTO loginDTO, HttpServletRequest request) throws URISyntaxException {
        if(!studentsService.checkSid(loginDTO.getSid())) {
            return new ResponseEntity<>("sid can not found", HttpStatus.UNAUTHORIZED);
        }
        else {
            if(studentsService.students_login(loginDTO.getSid(), loginDTO.getPw()) == true){
                HttpSession session = request.getSession();
                session.setAttribute("id", loginDTO.getSid());
                session.setAttribute("role", "student");

                // URI redirectUrl = new URI("/booking");
                // org.springframework.http.HttpHeaders httpHeaders = new org.springframework.http.HttpHeaders();
                // httpHeaders.setLocation(redirectUrl);
            return new ResponseEntity<>("성공", HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>("login fail", HttpStatus.NOT_FOUND);
            }
        }
    } 

    //logout
    @GetMapping(path = "/logout", produces = "application/json")
    public ResponseEntity<?> logoutStudent(HttpServletRequest request) throws URISyntaxException {
        HttpSession session = request.getSession();
        if(session == null) {
            return new ResponseEntity<>("로그인 후 이용해 주세요.", HttpStatus.UNAUTHORIZED); 
        }
        else{
            session.invalidate();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
