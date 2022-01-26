package com.example.booking_service_01.controller;

import com.example.booking_service_01.dto.FacilityDTO;
import com.example.booking_service_01.service.FacilityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/facility")
public class FacilityController {
    @Autowired
    FacilityService facilityService;

    //insert
    @PostMapping(path = "/join", produces = "application/json")
    public ResponseEntity<?> insertFacility(@RequestBody FacilityDTO facilityDTO) {
        if(facilityDTO!=null && facilityDTO.getFno()==null) {
            return new ResponseEntity<>(facilityService.findByFno(facilityService.insertFacilityDto(facilityDTO)), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("잘못 입력되었습니다.",HttpStatus.NOT_FOUND);
        }
    }

    //select all
    @GetMapping(path="", produces = "application/json")
    public ResponseEntity<?> getAllFacility() {
        return new ResponseEntity<>(facilityService.findAll(), HttpStatus.OK);
    }

    //Select by fno
    @GetMapping(path="/{fno}", produces = "application/json")
    public ResponseEntity<?> getByFno(@PathVariable("fno") Integer fno) {
        if(!facilityService.checkFno(fno)) {
            return new ResponseEntity<>("fno can not found", HttpStatus.NOT_FOUND);
        }
        else {
            FacilityDTO facilityDTO = facilityService.findByFno(fno);
            return new ResponseEntity<>(facilityDTO, HttpStatus.OK);
        }
    }

    //Update   
    @PutMapping(path = "/{fno}", produces = "application/json")
    public ResponseEntity<?> updateFacility(@PathVariable("fno") Integer fno, @RequestBody FacilityDTO facilityDTO) {
        FacilityDTO beforeDTO = facilityService.findByFno(fno);
        if(facilityDTO != null){
            Integer u_fno = facilityDTO.getFno()!=null?fno:beforeDTO.getFno();
            String u_place = facilityDTO.getPlace()!=null?facilityDTO.getPlace():beforeDTO.getPlace();
            String u_placeUrl = facilityDTO.getPlaceUrl()!=null?facilityDTO.getPlaceUrl():beforeDTO.getPlaceUrl();
            Integer u_maxHour = facilityDTO.getMaxHour()!=null?facilityDTO.getMaxHour():beforeDTO.getMaxHour();
            String u_name = facilityDTO.getName()!=null?facilityDTO.getName():beforeDTO.getName();

            FacilityDTO updateDTO= FacilityDTO.builder()
                .fno(u_fno)
                .place(u_place)
                .placeUrl(u_placeUrl)
                .name(u_name)
                .maxHour(u_maxHour)
                .build();
            
            return new ResponseEntity<>(facilityService.findByFno(facilityService.update(updateDTO)), HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>("Update fail", HttpStatus.BAD_REQUEST);
    }

    //Delete
    @DeleteMapping(path="/{fno}", produces = "application/json")
    public ResponseEntity<?> deleteFacility(@PathVariable("fno") Integer fno) {
        if(!facilityService.checkFno(fno))
            return new ResponseEntity<>("Facility Number can not found", HttpStatus.NOT_FOUND);
        else {
            FacilityDTO facilityDTO = facilityService.findByFno(fno);
            facilityService.delete(facilityDTO);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }
}
