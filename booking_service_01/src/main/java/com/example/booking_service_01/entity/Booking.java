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
@Table(name = "booking_models")
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer bno;

    @ManyToOne
    @JoinColumn(name = "fno")
    private Facility facility;

    @ManyToOne
    @JoinColumn(name = "sid")
    private Students students;

    @Column(nullable = false)
    private Date start_time;
 
    @Column(nullable = false)
    private Date end_time;

    @Column(nullable = false)
    private Integer headcount;
}
