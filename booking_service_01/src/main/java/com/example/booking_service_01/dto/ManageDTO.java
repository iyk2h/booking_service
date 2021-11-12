package com.example.booking_service_01.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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