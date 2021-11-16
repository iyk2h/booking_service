package com.example.booking_service_01.service;

import com.example.booking_service_01.dto.ManageDTO;

public interface ManageService {
    ManageDTO findByMno(Integer mno); 
}
