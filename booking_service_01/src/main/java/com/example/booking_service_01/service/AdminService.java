package com.example.booking_service_01.service;

import com.example.booking_service_01.dto.AdminDTO;

public interface AdminService {
    AdminDTO findByAno(Integer ano);
    boolean checkAno(Integer ano);
    Integer insertAdminDto(AdminDTO adminDTO);
    void delete(AdminDTO admindDto);
    Integer update(AdminDTO adminDTO);
    boolean admin_login(Integer ano ,String pw);
}
