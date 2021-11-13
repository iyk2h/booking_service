package com.example.booking_service_01.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;

@Entity
@Builder
@Getter
@Table(name = "students_models")
public class Students {
    @Id
    @GeneratedValue (strategy = GenerationType.TABLE)
    @Column(length = 20, nullable = false)
    private String sid;

    @Column(length = 20, nullable = false)
    private String pw;
 
    @Column(length = 20, nullable = false)
    private String name;
    
    @Column(length = 13, nullable = false)
    private String phone;
}