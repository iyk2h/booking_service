package com.example.booking_service_01.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {
    private String id;
    private String pw;
    private String name;
    private String phone; 
    private String email;
}
