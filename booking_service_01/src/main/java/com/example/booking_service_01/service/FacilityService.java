package com.example.booking_service_01.service;

import com.example.booking_service_01.dto.FacilityDTO;

public interface FacilityService {
    FacilityDTO findByFno(Integer fno);
}
