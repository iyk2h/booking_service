package com.example.booking_service_01.service.Impl;

import com.example.booking_service_01.dto.FacilityDTO;
import com.example.booking_service_01.entity.Facility;
import com.example.booking_service_01.mapper.BookingMapper;
import com.example.booking_service_01.repository.FacilityRepository;
import com.example.booking_service_01.service.FacilityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacilityServiceImpl implements FacilityService{
    @Autowired
    private FacilityRepository facilityRepository;    
    
    //findByFno
    @Override
    public FacilityDTO findByFno(Integer fno) {
        FacilityDTO facilityDTO;
        Facility facility = facilityRepository.findByFno(fno);
        facilityDTO = BookingMapper.INSTANCE.facility_To_DTO(facility);
        return facilityDTO;
    }
    
}
