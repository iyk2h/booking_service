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
public class ManageDTO {
    private Integer mno;
    private String id;
    private Integer fno;
    private Date start_time;
    private Date end_time;
    private String reason;

}   