package com.example.booking_service_01.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.booking_service_01.dto.AdminDTO;
import com.example.booking_service_01.dto.JwtAdminDTO;
import com.example.booking_service_01.dto.StudentsDTO;
import com.example.booking_service_01.service.AdminService;
import com.example.booking_service_01.service.BookingService;
import com.example.booking_service_01.service.FacilityService;
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
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    StudentsService studentsService;
    @Autowired
    FacilityService facilityService;
    @Autowired
    BookingService bookingService;
    
    //Insert
    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<?> insertAdmin(@RequestBody AdminDTO adminDTO) {
        if(adminDTO.getAid()!=null) {
            if (adminService.checkAid(adminDTO.getAid()))
                return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
            return new ResponseEntity<>(adminService.findByAid(adminService.insertAdminDto(adminDTO)), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        }
    }

    //mypage
    @GetMapping(path="", produces = "application/json")
    public ResponseEntity<?> getAid(HttpServletRequest request) {
        String aid = adminService.checkAdminRole(request);
        if(aid == null){
            return new ResponseEntity<>("관리자 로그인 후 이용해 주세요.", HttpStatus.UNAUTHORIZED); 
        }
        if(!adminService.checkAid(aid)) {
            return new ResponseEntity<>("aid can not found", HttpStatus.NOT_ACCEPTABLE);
        }
        else {
            AdminDTO adminDTO = adminService.findByAid(aid);
            return new ResponseEntity<>(adminDTO, HttpStatus.OK);
        }
    }

    //Update   
    @PutMapping(path = "", produces = "application/json")
    public ResponseEntity<?> updateAdmin(@RequestBody AdminDTO adminDTO, HttpServletRequest request) {
        String aid = adminService.checkAdminRole(request);
        if(aid == null){
            return new ResponseEntity<>("관리자 로그인 후 이용해 주세요.", HttpStatus.UNAUTHORIZED); 
        }
        AdminDTO beforeDTO = adminService.findByAid(aid);
        if(adminDTO != null){
            String u_aid = adminDTO.getAid()!=null?aid:beforeDTO.getAid();
            String u_pw = adminDTO.getPw()!=null?adminDTO.getPw():beforeDTO.getPw();
            String u_phone = adminDTO.getPhone()!=null?adminDTO.getPhone():beforeDTO.getPhone();
            String u_name = adminDTO.getName()!=null?adminDTO.getName():beforeDTO.getName();
            String u_email = adminDTO.getEmail()!=null?adminDTO.getEmail():beforeDTO.getEmail();

            AdminDTO updateDTO= AdminDTO.builder()
                .aid(u_aid)
                .pw(u_pw)
                .phone(u_phone)
                .name(u_name)
                .email(u_email)
                .build();
            
            return new ResponseEntity<>(adminService.findByAid(adminService.update(updateDTO)), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Update fail", HttpStatus.NOT_ACCEPTABLE);
    }

    //Delete
    @DeleteMapping(path="", produces = "application/json")
    public ResponseEntity<?> deleteAdmin(HttpServletRequest request) {
        String aid = adminService.checkAdminRole(request);
        if(aid == null){
            return new ResponseEntity<>("관리자 로그인 후 이용해 주세요.", HttpStatus.UNAUTHORIZED); 
        }
        if(!adminService.checkAid(aid))
            return new ResponseEntity<>("Admin ID can not found", HttpStatus.NOT_ACCEPTABLE);
        else {
            AdminDTO admindDto = adminService.findByAid(aid);
            adminService.delete(admindDto);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }

    // login 로그인
    @PostMapping(path = "/login", produces = "application/json")
    public ResponseEntity<?> loginAdmin(@RequestBody JwtAdminDTO loginDTO, HttpServletRequest request) throws URISyntaxException {
        if(!adminService.checkAid(loginDTO.getAid())) {
            return new ResponseEntity<>("aid can not found", HttpStatus.NOT_ACCEPTABLE);
        }
        else
            if(adminService.admin_login(loginDTO.getAid(), loginDTO.getPw()) == true){
                HttpSession session = request.getSession();
                session.setAttribute("id", loginDTO.getAid());
                session.setAttribute("role", "admin");

            return new ResponseEntity<>("성공 ",HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("login fail", HttpStatus.NOT_ACCEPTABLE);
            }
    }  
    //logout
    @PostMapping(path = "/logout", produces = "application/json")
    public ResponseEntity<?> logoutAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session != null){
            session.invalidate();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //사용자 list
    @GetMapping(path="/students", produces = "application/json")
    public ResponseEntity<?> getStudentsList(HttpServletRequest request){
        if(adminService.checkAdminRole(request) == null){
            return new ResponseEntity<>("관리자 로그인 후 이용해 주세요.", HttpStatus.UNAUTHORIZED); 
        }
        return new ResponseEntity<>(studentsService.findAll(), HttpStatus.OK);
    }
    // 시설 리스트
    @GetMapping(path = "/facility", produces = "application/json")
    public ResponseEntity<?> getFacilityList(HttpServletRequest request) {
        if(adminService.checkAdminRole(request) == null){
            return new ResponseEntity<>("관리자 로그인 후 이용해 주세요.", HttpStatus.UNAUTHORIZED); 
        } 
        return new ResponseEntity<>(facilityService.findAll(), HttpStatus.OK);
    }
    //bookinglist
    @GetMapping(path = "/booking", produces = "application/json")
    public ResponseEntity<?> getBookingList(HttpServletRequest request) {
        if(adminService.checkAdminRole(request) == null){
            return new ResponseEntity<>("관리자 로그인 후 이용해 주세요.", HttpStatus.UNAUTHORIZED); 
        }
        return new ResponseEntity<>(bookingService.findAll(),HttpStatus.OK);
    }

    //Select 
    @GetMapping(path="/students/{sid}", produces = "application/json")
    public ResponseEntity<?> getSid(@PathVariable("sid") Integer sid, HttpServletRequest request) {
        if(adminService.checkAdminRole(request) == null){
            return new ResponseEntity<>("관리자 로그인 후 이용해 주세요.", HttpStatus.UNAUTHORIZED); 
        }
        if(!studentsService.checkSid(sid)) {
            return new ResponseEntity<>("sid can not found", HttpStatus.NOT_ACCEPTABLE);
        }
        else {
            StudentsDTO studentsDTO = studentsService.findBySid(sid);
            return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
        }
    }
}
