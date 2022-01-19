package com.example.booking_service_01.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.booking_service_01.dto.BookingDTO;
import com.example.booking_service_01.dto.FacilityDTO;
import com.example.booking_service_01.service.BookingService;
import com.example.booking_service_01.service.FacilityService;
import com.example.booking_service_01.service.StudentsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/booking")
public class BookingController {
    @Autowired
    FacilityService facilityService;
    @Autowired
    BookingService bookingService;
    @Autowired
    StudentsService studentsService;

    //facility list
    @GetMapping(path="", produces = "application/json")
    public ResponseEntity<?> getAllFacility() {
        List<FacilityDTO> dtos = facilityService.findAll();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    //Select by fno
    @GetMapping(path="/{fno}", produces = "application/json")
    public ResponseEntity<?> getFnoToBooking(@PathVariable("fno") Integer fno) {
        if(!facilityService.checkFno(fno)) {
            return new ResponseEntity<>("fno can not found", HttpStatus.NOT_ACCEPTABLE);
        }
        else {
            // FacilityDTO facilityDTO = facilityService.findByFno(fno);
            List<BookingDTO> bookingDTOs = bookingService.findByFno(fno);
            return new ResponseEntity<>(bookingDTOs, HttpStatus.OK);
        }
    }

    //Booking facility, student
    @PostMapping(path="/{fno}", produces = "application/json")
    public ResponseEntity<?> bookingFacilityStudent(@PathVariable("fno") Integer fno, @RequestBody BookingDTO bookingDTO,HttpServletRequest request)  {
        System.out.println("fno="+fno);
        System.out.println("date="+bookingDTO.getDate());
        System.out.println("btn="+bookingDTO.getBtnradio());
        
        HttpSession session = request.getSession();
        Integer sid = (Integer) session.getAttribute("id");
        System.out.println("sid="+sid);
        if(sid == null) {
            return new ResponseEntity<>("로그인 후 이용해 주세요.", HttpStatus.NOT_ACCEPTABLE);
        }
        if(!facilityService.checkFno(fno)) {
            return new ResponseEntity<>("시설을 확인해 주세요.", HttpStatus.NOT_ACCEPTABLE);
        }
        else {
            if(bookingDTO.getBtnradio() >=24 || bookingDTO.getBtnradio() <0) {
                return new ResponseEntity<>("예약 시간을 확인해 주세요.", HttpStatus.NOT_ACCEPTABLE);  
            }
            LocalDateTime start = LocalDateTime.of(bookingDTO.getDate(), LocalTime.of(bookingDTO.getBtnradio(), 0));
            LocalDateTime end  = start.plusHours(1).minusSeconds(1);
            if(bookingService.checkBookingTime(fno, start, end)) {
                BookingDTO newBookingDTO = BookingDTO.builder()
                    .bno(bookingDTO.getBno())
                    .sid(sid)
                    .fno(fno)
                    .startTime(start)
                    .endTime(end)
                    .build();

                BookingDTO saveDto = bookingService.insertBookingDto(newBookingDTO);
            return new ResponseEntity<>(saveDto, HttpStatus.CREATED);
            }
            else
                return new ResponseEntity<>("예약 시간을 확인해 주세요.", HttpStatus.NOT_ACCEPTABLE);  
        }
    }
    @GetMapping(path = "/list", produces = "application/json")
    public ResponseEntity<?> bookingListByStudentSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer sid = (Integer) session.getAttribute("id");
        if(sid == null) {
            return new ResponseEntity<>("로그인 후 이용해 주세요.", HttpStatus.NOT_ACCEPTABLE);
        }
        else {
            List<BookingDTO> bookingDTOs = bookingService.findBySid(sid);
            return new ResponseEntity<>(bookingDTOs, HttpStatus.OK);
        } 
    }

    @PostMapping(path="/{fno}/date", produces = "application/json")
    public ResponseEntity<?> bookingByDate(@PathVariable("fno") Integer fno, @RequestBody BookingDTO bookingDTO) {
        LocalDate date =bookingDTO.getDate();
        List<BookingDTO> bookingDTOs = bookingService.findBookingListByFacilityWhitDate(fno, date);
        if(bookingDTOs.size()==0) {
            return new ResponseEntity<>("예약 날짜를 확인해 주세요.", HttpStatus.NOT_ACCEPTABLE);
        }
        else
            return new ResponseEntity<>(bookingDTOs, HttpStatus.OK);
    }
    
    @DeleteMapping(path="/{bno}", produces = "application/json")
    public ResponseEntity<?> deleteBookingByBno(@PathVariable("bno") Integer bno, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer sid = (Integer) session.getAttribute("id");

        if(sid == null) {
            return new ResponseEntity<>("로그인 후 이용해 주세요.", HttpStatus.MOVED_PERMANENTLY);
        }
        else {
            if(bookingService.checkByBnoSid(sid, bno)){
                bookingService.deleteBooking(bno);
                return new ResponseEntity<>("삭제되었습니다.",HttpStatus.OK);
            }
            return new ResponseEntity<>("예약을 번호를 확인해 주세요.", HttpStatus.NOT_ACCEPTABLE);       
        }
    }
    
}