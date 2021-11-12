package com.example.booking_service_01.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ManageMapper{
    ManageMapper INSTANCE = Mappers.getMapper(ManageMapper.class);
}