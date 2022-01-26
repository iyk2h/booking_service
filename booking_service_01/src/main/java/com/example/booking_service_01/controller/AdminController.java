package com.example.booking_service_01.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.booking_service_01.dto.AdminDTO;
import com.example.booking_service_01.dto.BookingDTO;
import com.example.booking_service_01.dto.FacilityDTO;
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
    
    //singup
    @PostMapping(path = "/singup", produces = "application/json")
    public ResponseEntity<?> singupAdmin(@RequestBody AdminDTO adminDTO) {
        if(adminDTO.getAid()!=null) {
            if (adminService.checkAid(adminDTO.getAid()))
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(adminService.findByAid(adminService.insertAdminDto(adminDTO)), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //mypage
    @GetMapping(path="", produces = "application/json")
    public ResponseEntity<?> getAid(HttpServletRequest request) {
        String aid = adminService.checkAdminRole(request);
        if(!adminService.checkAid(aid)) {
            return new ResponseEntity<>("aid can not found", HttpStatus.NOT_FOUND);
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
            
            return new ResponseEntity<>(adminService.findByAid(adminService.update(updateDTO)), HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>("Update fail", HttpStatus.BAD_REQUEST);
    }

    //Delete
    @DeleteMapping(path="", produces = "application/json")
    public ResponseEntity<?> deleteAdmin(HttpServletRequest request) {
        String aid = adminService.checkAdminRole(request);
        if(!adminService.checkAid(aid))
            return new ResponseEntity<>("Admin ID can not found", HttpStatus.NOT_FOUND);
        else {
            AdminDTO admindDto = adminService.findByAid(aid);
            adminService.delete(admindDto);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

    // login 로그인
    @PostMapping(path = "/login", produces = "application/json")
    public ResponseEntity<?> loginAdmin(@RequestBody JwtAdminDTO loginDTO, HttpServletRequest request) throws URISyntaxException {
        if(!adminService.checkAid(loginDTO.getAid())) {
            return new ResponseEntity<>("aid can not found", HttpStatus.NOT_FOUND);
        }
        else
            if(adminService.admin_login(loginDTO.getAid(), loginDTO.getPw()) == true){
                HttpSession session = request.getSession();
                session.setAttribute("id", loginDTO.getAid());
                session.setAttribute("role", "admin");

            return new ResponseEntity<>("성공 ",HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>("login fail", HttpStatus.NOT_FOUND);
            }
    }  
    //logout
    @PostMapping(path = "/logout", produces = "application/json")
    public ResponseEntity<?> logoutAdmin(HttpServletRequest request) {
        request.getSession().invalidate();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //-------------- manage students -------------//
    //사용자 list
    @GetMapping(path="/students", produces = "application/json")
    public ResponseEntity<?> getStudentsList(){
        return new ResponseEntity<>(studentsService.findAll(), HttpStatus.OK);
    }
    //회원 정보
    @GetMapping(path="/students/{sid}", produces = "application/json")
    public ResponseEntity<?> getStudentBySid(@PathVariable("sid") Integer sid) {
        if(!studentsService.checkSid(sid)) {
            return new ResponseEntity<>("sid can not found", HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(studentsService.findBySid(sid), HttpStatus.OK);
        }
    }
    //회원 탈퇴
    @DeleteMapping(path="/students/{sid}", produces = "application/json")
    public ResponseEntity<?> deleteStudent(@PathVariable("sid") Integer sid) {
        if(!studentsService.checkSid(sid))
            return new ResponseEntity<>("sid can not found", HttpStatus.NOT_FOUND);
        else {
            studentsService.delete(studentsService.findBySid(sid));
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

    //------------ manage booking --------------//
    //bookinglist
    @GetMapping(path = "/booking", produces = "application/json")
    public ResponseEntity<?> getBookingList(HttpServletRequest request) {
        return new ResponseEntity<>(bookingService.findAll(),HttpStatus.OK);
    }

    //예약 정보
    @GetMapping(path = "/booking/{bno}", produces = "application/json")
    public ResponseEntity<?> getBookingByBno(@PathVariable("bno") Integer bno ) { 
        if(!bookingService.checkByBno(bno)) {
            return new ResponseEntity<>("bno can not found", HttpStatus.NOT_FOUND);
        }
        else {
            BookingDTO bookingDTO = bookingService.findByBno(bno);
            return new ResponseEntity<>(bookingDTO, HttpStatus.OK);   
        }
    }
    // 예약 삭제
    @DeleteMapping(path="booking/{bno}", produces = "application/json")
    public ResponseEntity<?> deleteBookingByBno(@PathVariable("bno") Integer bno) {
        if(!bookingService.checkByBno(bno)) {
            return new ResponseEntity<>("bno can not found", HttpStatus.NOT_FOUND);
        }
        else {
            bookingService.deleteBooking(bno);
            return new ResponseEntity<>("삭제되었습니다.",HttpStatus.NO_CONTENT);
        }
    }
}
