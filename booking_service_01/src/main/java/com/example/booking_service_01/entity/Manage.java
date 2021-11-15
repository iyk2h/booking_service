package com.example.booking_service_01.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "manage_models")
public class Manage {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer mno;

    @ManyToOne
    @JoinColumn(name = "ano")
    private Admin admin;

    @ManyToOne
    @JoinColumn(name = "fno")
    private Facility facility;

    @Column(nullable = false)
    private Date start_time;
 
    @Column(nullable = false)
    private Date end_time;;
    
    @Column(length = 20)
    private String reason;

}