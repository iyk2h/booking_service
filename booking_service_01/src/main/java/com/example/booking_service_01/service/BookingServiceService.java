package com.example.booking_service_01.service;

import com.example.booking_service_01.dto.AdminDTO;
import com.example.booking_service_01.dto.FacilityDTO;
import com.example.booking_service_01.dto.ManageDTO;

public interface BookingServiceService {
    FacilityDTO findByFno(Integer fno);

    ManageDTO findByMno(Integer mno);
    
    AdminDTO findByAno(Integer ano);
    boolean checkAno(Integer ano);
    Integer insertAdminDto(AdminDTO adminDTO);
    void delete(AdminDTO admindDto);
    Integer update(AdminDTO adminDTO);
}
