package com.example.booking_service_01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.booking_service_01.dto.AdminDTO;
import com.example.booking_service_01.entity.Admin;
import com.example.booking_service_01.entity.Manage;
import com.example.booking_service_01.mapper.BookingMapper;
import com.example.booking_service_01.repository.AdminRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class BookingService01ApplicationTests {
	@BeforeEach
    public void setup() {

    }
	@Test
	@DisplayName("DTO에서 Entity로 변환하는 테스트")

    public void eToD(){
		/*given */
		final Admin admin= Admin.builder()
			.id("id")
			.pw("pw")
			.name("name")
			.phone("phone")
			.email("email")
			.build();
		/*when */
        final AdminDTO adminDto = BookingMapper.INSTANCE.admin_To_DTO(admin);
		/*then */
        assertEquals("id", adminDto.getId());
        assertEquals("pw", adminDto.getPw());
        assertEquals("name", adminDto.getName());
        assertEquals("phone", adminDto.getPhone());
		assertEquals("email", adminDto.getEmail());
        System.out.println("Entity to Dto all pass");
    }
    @Test
    void DtoE() {
		/*given */
		final AdminDTO adminDTO = AdminDTO.builder()
			.id("id")
			.pw("pw")
			.name("name")
			.phone("phone")
			.email("email")
			.build();
		/*when */
		final Admin admin = BookingMapper.INSTANCE.adminDto_To_Entity(adminDTO);
		/*then*/
		assertEquals("id", admin.getId());
        assertEquals("pw", admin.getPw());
        assertEquals("name", admin.getName());
        assertEquals("phone", admin.getPhone());
		assertEquals("email", admin.getEmail());
        System.out.println("DTO To Entity all pass");
    }

	// @Autowired
	// AdminRepository adminRepository;

	// @Test
    // public void admin_test() {
	// 	Admin admin = adminRepository.findById("a").get();
	// 	AdminDTO adminDTO = BookingMapper.INSTANCE.admin_To_DTO(admin);
	// 	log.info(adminDTO.toString());
    // }
}
