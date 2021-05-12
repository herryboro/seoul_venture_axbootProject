package edu.axboot.domain.herryboroproject;

import edu.axboot.AXBootApplication;
import edu.axboot.controllers.dto.HerryboroHotelDto;
import edu.axboot.domain.education.book.EducationBookServiceTest;
import lombok.extern.java.Log;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AXBootApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HerryboroHotelServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(EducationBookServiceTest.class);

    @Inject
    private HerryboroHotelService herryboroHotelService;

    @Test
    public void 리스트_조회() {
        String roomType = "SB";

        Page<HerryboroHotelDto> list = this.herryboroHotelService.getList(roomType, new PageRequest(0, 5));
        assertTrue(list.getSize() >= 0);
    }

    @Test
    public void 저장_혹은_업데이트() {

        HerryboroHotelDto hotelDto = new HerryboroHotelDto();

        HerryboroHotelDto build = hotelDto.builder()
                .roomNum("3021")
                .roomTypCd("SB")
                .dndYn("Y")
                .ebYn("Y")
                .__created__(true)
                .__modified__(false)
                .__deleted__(false)
                .build();

        HerryboroHotel herryboroHotel = build.toEntitiy();
        List<HerryboroHotel> list = new ArrayList<HerryboroHotel>();
        list.add(herryboroHotel);
        this.herryboroHotelService.save(list);
    }
}
