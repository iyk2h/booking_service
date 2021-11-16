package com.example.booking_service_01.service.Impl;

import com.example.booking_service_01.dto.AdminDTO;
import com.example.booking_service_01.entity.Admin;
import com.example.booking_service_01.mapper.BookingMapper;
import com.example.booking_service_01.repository.AdminRepository;
import com.example.booking_service_01.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminRepository adminRepository;

    
    //admin
    @Override
    public AdminDTO findByAno(Integer ano) {
        Admin admin = adminRepository.findByAno(ano);
        AdminDTO adminDTO = BookingMapper.INSTANCE.admin_To_DTO(admin);
        return adminDTO;
    }

    @Override
    public boolean checkAno(Integer ano) {
        Admin admin = adminRepository.findByAno(ano);
        if(admin==null) {
            return false;
        }
        return true;
    }

    @Override
    public Integer insertAdminDto(AdminDTO adminDTO) {
        Admin admin = BookingMapper.INSTANCE.adminDto_To_Entity(adminDTO);
        adminRepository.save(admin);
        return admin.getAno();
    }

    @Override
    public void delete(AdminDTO adminDTO) {
        Admin admin = BookingMapper.INSTANCE.adminDto_To_Entity(adminDTO);
        adminRepository.delete(admin);
    }

    @Override
    public boolean admin_login(Integer ano ,String pw) {
        Admin admin = adminRepository.findByAno(ano);
        if(admin.getPw().equals(pw)) {
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public Integer update(AdminDTO adminDTO) {
        Admin admin = BookingMapper.INSTANCE.adminDto_To_Entity(adminDTO);
        adminRepository.save(admin);
        return admin.getAno();
    }
}
