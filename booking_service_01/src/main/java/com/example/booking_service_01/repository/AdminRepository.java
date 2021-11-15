package com.example.booking_service_01.repository;

import com.example.booking_service_01.entity.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,String>{
    Admin findByAno(Integer ano);
}
