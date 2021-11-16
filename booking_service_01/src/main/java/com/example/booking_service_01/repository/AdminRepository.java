package com.example.booking_service_01.repository;

import javax.transaction.Transactional;

import com.example.booking_service_01.entity.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{
    Admin findByAno(Integer ano);

    // @Modifying
    // @Transactional
    // @Query("delete from admin_models a where a.ano in :ano")
    // void deleteByAno(@Param("ano") Integer ano);
    // Admin delete()
}
