package com.example.booking_service_01.service.Impl;

import com.example.booking_service_01.dto.AdminDTO;
import com.example.booking_service_01.dto.FacilityDTO;
import com.example.booking_service_01.dto.ManageDTO;
import com.example.booking_service_01.entity.Admin;
import com.example.booking_service_01.entity.Facility;
import com.example.booking_service_01.entity.Manage;
import com.example.booking_service_01.mapper.BookingMapper;
import com.example.booking_service_01.repository.AdminRepository;
import com.example.booking_service_01.repository.FacilityRepository;
import com.example.booking_service_01.repository.ManageRepository;
import com.example.booking_service_01.service.BookingServiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceServiceImpl implements BookingServiceService{
    @Autowired
    private AdminRepository adminRepository;
	@Autowired
    private FacilityRepository facilityRepository;
	@Autowired
	private ManageRepository manageRepository;
    
    //admin
    @Override
    public AdminDTO findByAid(String aid) {
        System.out.println("findByAid"); 
        System.out.println(aid);
        Admin admin = adminRepository.findByAid(aid);
        AdminDTO adminDTO = BookingMapper.INSTANCE.admin_To_DTO(admin);
        return adminDTO;
    }

    @Override
    public boolean checkaid(String aid) {
        System.out.println("checkaid1"); 
        System.out.println(aid);
        //여기 까지 확인
        Admin admin = adminRepository.findByAid(aid);
        
        System.out.println("checkaid2"); 
        System.out.println(aid);
        
        if(admin==null) {
        System.out.println("controller==null"); 
        System.out.println(aid);
            return false;
        }
        System.out.println("checked"); 
        System.out.println(aid);
        return true;
    }

    @Override
    public String insertAdminDto(AdminDTO adminDTO) {
        Admin admin = BookingMapper.INSTANCE.adminDto_To_Entity(adminDTO);
        adminRepository.save(admin);
        return admin.getAid();
    }

    //facility
    @Override
    public FacilityDTO findByFno(Integer fno) {
        FacilityDTO facilityDTO;
        Facility facility = facilityRepository.findByFno(fno);
        facilityDTO = BookingMapper.INSTANCE.facility_To_DTO(facility);
        return facilityDTO;
    }

    @Override
    public ManageDTO findByMno(Integer mno) {
        ManageDTO manageDTO;
        Manage manage = manageRepository.findByMno(mno);
        manageDTO = BookingMapper.INSTANCE.manage_To_DTO(manage);
        return manageDTO;
    }
}
