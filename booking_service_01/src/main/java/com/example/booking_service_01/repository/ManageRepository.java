package com.example.booking_service_01.repository;

import java.util.Optional;

import com.example.booking_service_01.entity.Manage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ManageRepository extends JpaRepository<Manage,Integer>{
    Optional<Manage> findById(Integer mno);
}
