package com.example.booking_service_01.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.booking_service_01.dto.BookingDTO;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/booking")
public class BookingController {
    @Autowired
    FacilityService facilityService;
    @Autowired
    BookingService bookingService;
    @Autowired
    StudentsService studentsService;

    //Select by fno
    @GetMapping(path="/{fno}", produces = "application/json")
    public ResponseEntity<?> getFnoToBooking(@PathVariable("fno") Integer fno) {
        if(!facilityService.checkFno(fno)) {
            return new ResponseEntity<>("시설을 확인해 주세요.", HttpStatus.NOT_FOUND);
        }
        else {
            // FacilityDTO facilityDTO = facilityService.findByFno(fno);
            List<BookingDTO> bookingDTOs = bookingService.findByFno(fno);
            if(bookingDTOs.size() <= 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(bookingDTOs, HttpStatus.OK);
        }
    }

    //Booking facility, student
    @PostMapping(path="/{fno}", produces = "application/json")
    public ResponseEntity<?> bookingFacilityStudent(@PathVariable("fno") Integer fno, @RequestBody BookingDTO bookingDTO,HttpServletRequest request)  {
        Integer time = bookingDTO.getSelectedTime().getHour();
        Integer seletedHour = bookingDTO.getMaxHour();
        Integer sid = studentsService.checkSessionSid(request);
        if(sid==null) {
            return new ResponseEntity<>("로그인 후 이용해 주세요.", HttpStatus.UNAUTHORIZED);
        }
        if(!facilityService.checkFno(fno)) {
            return new ResponseEntity<>("시설을 확인해 주세요.", HttpStatus.NOT_FOUND);
        }
        else {
            if(time>=24 || time<0) {
                return new ResponseEntity<>("예약 시간을 확인해 주세요.", HttpStatus.NOT_FOUND);  
            }
            LocalDateTime start = LocalDateTime.of(bookingDTO.getDate(), bookingDTO.getSelectedTime());
            LocalDateTime end  = start.plusHours(seletedHour).minusSeconds(1);
            if(bookingService.checkBookingTime(fno, start, end)) {
                BookingDTO newBookingDTO = BookingDTO.builder()
                    .bno(bookingDTO.getBno())
                    .sid(sid)
                    .fno(fno)
                    .startTime(start)
                    .endTime(end)
                    .maxHour(bookingDTO.getMaxHour())
                    .build();
                BookingDTO saveDto = bookingService.insertBookingDto(newBookingDTO);
            return new ResponseEntity<>(saveDto, HttpStatus.CREATED);
            }
            else
                return new ResponseEntity<>("예약 시간을 확인해 주세요.", HttpStatus.NOT_FOUND);  
        }
    }

    // 사용자 자신이 예약한 리스트 보기
    @GetMapping(path = "/students", produces = "application/json")
    public ResponseEntity<?> bookingListByStudentSession(HttpServletRequest request) {
        Integer sid = studentsService.checkSessionSid(request);
        if(sid==null) {
            return new ResponseEntity<>("로그인 후 이용해 주세요.", HttpStatus.UNAUTHORIZED);
        }
        else {
            List<BookingDTO> bookingDTOs = bookingService.findBySid(sid);
            return new ResponseEntity<>(bookingDTOs, HttpStatus.OK);
        } 
    }

    // 날자에 예약된 목록 조회
    @PostMapping(path="/{fno}/date", produces = "application/json")
    public ResponseEntity<?> bookingByDate(@PathVariable("fno") Integer fno, @RequestBody BookingDTO bookingDTO) {
        LocalDate date =bookingDTO.getDate();
        List<BookingDTO> bookingDTOs = bookingService.findBookingListByFacilityWhitDate(fno, date);
        if(bookingDTOs.size()<=0) {
            return new ResponseEntity<>("예약 날짜를 확인해 주세요.", HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(bookingDTOs, HttpStatus.CREATED);
        }
    }

    // 예약 삭제
    @DeleteMapping(path="/{bno}", produces = "application/json")
    public ResponseEntity<?> deleteBookingByBno(@PathVariable("bno") Integer bno, HttpServletRequest request) {
        Integer sid = studentsService.checkSessionSid(request);
        if(sid==null) {
            return new ResponseEntity<>("로그인 후 이용해 주세요.", HttpStatus.UNAUTHORIZED);
        }
        else {
            if(bookingService.checkByBnoSid(sid, bno)){
                bookingService.deleteBooking(bno);
                return new ResponseEntity<>("삭제되었습니다.",HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>("예약을 번호를 확인해 주세요.", HttpStatus.NOT_FOUND);       
        }
    }
}