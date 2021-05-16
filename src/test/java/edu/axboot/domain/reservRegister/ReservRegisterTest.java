package edu.axboot.domain.reservRegister;

import edu.axboot.AXBootApplication;
import edu.axboot.controllers.dto.ReservRegisterDto;
import edu.axboot.domain.education.EducationTeachService;
import lombok.extern.java.Log;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AXBootApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReservRegisterTest {
    private static final Logger logger = LoggerFactory.getLogger(EducationTeachService.class);

    @Inject
    private ReservRegisterService reservRegisterService;

    @Test
    public void 예약등록() {
        ReservRegisterDto reservRegisterDto = new ReservRegisterDto();

        ReservRegisterDto build = reservRegisterDto.builder()
                .arrDt("2021-05-17")
                .depDt("2021-05-19")
                .nightCnt(2)
                .roomTypCd("SB")
                .adultCnt(1)
                .chldCnt(0)
                .saleTypCd("부킹예약")
                .srcCd("전화")
                .advnYn("Y")
                .build();

        ReservRegister save = reservRegisterService.save(build.toEntityOfReservRegister());
        Integer sno = save.getSno();
        String rsvNum = save.getRsvNum();
        logger.info("sno ==============================> " + sno.intValue());
        logger.info("rsvNum ==============================> " + rsvNum);
    }
}
