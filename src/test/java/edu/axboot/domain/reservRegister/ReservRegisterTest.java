package edu.axboot.domain.reservRegister;

import edu.axboot.AXBootApplication;
import edu.axboot.controllers.dto.ReservRegisterDto;
import lombok.extern.java.Log;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AXBootApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReservRegisterTest {

    @Inject
    private ReservRegisterService reservRegisterService;

    @Test
    public void 시작화면() {

    }

    @Test
    public void 예약등록() {
        ReservRegisterDto reservRegisterDto = new ReservRegisterDto();

        ReservRegisterDto build = reservRegisterDto.builder()
                .rsvDt("2021-05-11")
                .sno(11111)
                .rsvNum("65151")
                .arrDt("2021-05-12")
                .depDt("2021-05-14")
                .nightCnt(2)
                .roomTypCd("SB")
                .adultCnt(1)
                .chldCnt(0)
                .saleTypCd("부킹예약")
                .sttusCd("무")
                .srcCd("전화")
                .advnYn("Y")
                .build();

        reservRegisterService.save(build.toEntity());
    }
}
