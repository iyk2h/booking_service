package com.example.booking_service_01.service.Impl;

import com.example.booking_service_01.dto.ManageDTO;
import com.example.booking_service_01.entity.Manage;
import com.example.booking_service_01.mapper.BookingMapper;
import com.example.booking_service_01.repository.ManageRepository;
import com.example.booking_service_01.service.ManageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageServiceImpl implements ManageService{
    @Autowired
	private ManageRepository manageRepository;
    
    @Override
    public ManageDTO findByMno(Integer mno) {
        ManageDTO manageDTO;
        Manage manage = manageRepository.findByMno(mno);
        manageDTO = BookingMapper.INSTANCE.manage_To_DTO(manage);
        return manageDTO;
    }
}
