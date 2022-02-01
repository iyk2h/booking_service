package com.example.booking_service_01.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.booking_service_01.dto.LoginDTO;
import com.example.booking_service_01.dto.StudentsDTO;
import com.example.booking_service_01.service.BookingService;
import com.example.booking_service_01.service.StudentsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses({
    @ApiResponse(code = 200, message = "get 성공"),
    @ApiResponse(code = 201, message = "post 성공"),
    @ApiResponse(code = 204, message = "반환 값 없는 상태"),
    @ApiResponse(code = 400, message = "잘못된 요청 구문"),
    @ApiResponse(code = 401, message = "로그인 필요한 상태"),
    @ApiResponse(code = 404, message = "경로 혹은 입력값이 잘못된 상태"),
    @ApiResponse(code = 500, message = "서버에러")
})
@RestController
@RequestMapping("/students")
public class StudentsController {
    @Autowired
    StudentsService studentsService;
    @Autowired
    BookingService bookingService;
    
    // 아이디 중복 확인
    @PostMapping(path = "/check", produces = "application/json")
    public ResponseEntity<?> sidCheck(@RequestBody LoginDTO loginDTO) {
        if (studentsService.checkSid(loginDTO.getId()))
            return new ResponseEntity<>("id가 이미 존재합니다.",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Sing up
    @PostMapping(path = "/signup", produces = "application/json")
    public ResponseEntity<?> singupStudent(@RequestBody StudentsDTO studentsDTO) {
        if(studentsDTO.getSid()!=null) {
            if (studentsService.checkSid(studentsDTO.getSid()))
                return new ResponseEntity<>("id가 이미 존재합니다.",HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(studentsService.findBySid(studentsService.insertStudentsDTO(studentsDTO)), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("잘못 입력되었습니다.",HttpStatus.NOT_FOUND);
        }
    }

    //mypage
    @GetMapping(path = "", produces = "application/json")
    public ResponseEntity<?> mypage(HttpServletRequest request) {
        Integer sid = studentsService.checkSessionSid(request);
        if(!studentsService.checkSid(sid)){
            return new ResponseEntity<>("sid can not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentsService.findBySid(sid), HttpStatus.OK);
    }

    //Update   
    @PutMapping(path = "", produces = "application/json")
    public ResponseEntity<?> updateStudent(@RequestBody StudentsDTO studentsDTO, HttpServletRequest request) {
        Integer sid = studentsService.checkSessionSid(request);
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
            
            return new ResponseEntity<>(studentsService.findBySid(studentsService.update(updateDTO)), HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>("Update fail", HttpStatus.BAD_REQUEST);
    }
    
    //Delete 탈퇴
    @DeleteMapping(path="", produces = "application/json")
    public ResponseEntity<?> deleteStudent(HttpServletRequest request) {
        Integer sid = studentsService.checkSessionSid(request);
        if(!studentsService.checkSid(sid))
            return new ResponseEntity<>("sid can not found", HttpStatus.NOT_FOUND);
        else {
            StudentsDTO studentsDTO = studentsService.findBySid(sid);
            studentsService.delete(studentsDTO);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

    //Login
    @PostMapping(path = "/login", produces = "application/json")
    public ResponseEntity<?> loginStudent(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        if(!studentsService.checkSid(loginDTO.getId())) {
            return new ResponseEntity<>("sid can not found", HttpStatus.NOT_FOUND);
        }
        else {
            if(studentsService.students_login(loginDTO.getId(), loginDTO.getPw())){
                HttpSession session = request.getSession();
                session.setAttribute("id", loginDTO.getId());
                session.setAttribute("role", "student");
            return new ResponseEntity<>("성공",HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>("login fail", HttpStatus.NOT_FOUND);
            }
        }
    } 

    //logout
    @GetMapping(path = "/logout", produces = "application/json")
    public ResponseEntity<?> logoutStudent(HttpServletRequest request) {
        request.getSession().invalidate();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
