package com.example.booking_service_01.repository;


import java.util.Optional;

import com.example.booking_service_01.entity.Admin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,String>{
    Optional<Admin> findById(String id);
}
