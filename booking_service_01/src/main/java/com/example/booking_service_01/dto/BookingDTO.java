package com.example.booking_service_01.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private Integer bno;
    private Integer fno;
    private String sid;
    private Date start_time;
    private Date end_time;
    private Integer headcount; 
}
