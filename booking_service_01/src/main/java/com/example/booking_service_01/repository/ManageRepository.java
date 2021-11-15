package com.example.booking_service_01.repository;


import com.example.booking_service_01.entity.Manage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManageRepository extends JpaRepository<Manage,Integer>{
    Manage findByMno(Integer mno);
}
