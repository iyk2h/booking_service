package com.example.booking_service_01.mapper;

import com.example.booking_service_01.dto.AdminDTO;
import com.example.booking_service_01.entity.Admin;
import com.example.booking_service_01.mapper.Impl.GenericMapper;

import org.mapstruct.Mapper;
// import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AdminMapper extends GenericMapper<AdminDTO,Admin>{
    // AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);
}