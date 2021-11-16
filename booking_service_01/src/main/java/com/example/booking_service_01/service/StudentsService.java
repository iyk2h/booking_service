package com.example.booking_service_01.service;

import com.example.booking_service_01.dto.StudentsDTO;

public interface StudentsService {
    StudentsDTO findBySid(Integer sid);
    boolean checkSid(Integer sid);
    Integer insertStudentsDTO(StudentsDTO studentsDTO);
    void delete(StudentsDTO studentsDTO);
    Integer update(StudentsDTO studentsDTO);
    boolean students_login(Integer sid ,String pw); 
}
