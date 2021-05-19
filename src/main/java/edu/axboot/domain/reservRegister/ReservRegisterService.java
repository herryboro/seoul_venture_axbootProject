package edu.axboot.domain.reservRegister;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQuery;
import edu.axboot.controllers.dto.HotelCustomerDto;
import edu.axboot.controllers.dto.ReservRegisterDto;
import edu.axboot.domain.education.EducationTeachService;
import edu.axboot.domain.hotelcustomer.HotelCustomer;
import edu.axboot.domain.hotelcustomer.HotelCustomerService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
public class ReservRegisterService extends BaseService<ReservRegister, Long> {
    private static final Logger logger = LoggerFactory.getLogger(EducationTeachService.class);
    private ReservRegisterRepository reservRegisterRepository;

    @Inject
    private HotelCustomerService hotelCustomerService;

    @Inject
    public ReservRegisterService(ReservRegisterRepository reservRegisterRepository) {
        super(reservRegisterRepository);
        this.reservRegisterRepository = reservRegisterRepository;
    }

    public List<ReservRegister> gets(ReservRegister requestParams) {
        return findAll();
    }

    // 일렬번호 최대값 조회
    public int getMaxSno() {
        List<ReservRegister> reservRegister = select().from(qReservRegister).fetch();

        if(reservRegister.size() != 0) {
            int[] arr = new int[reservRegister.size()];

            for(int i = 0; i < reservRegister.size(); i++) {
                int val = reservRegister.get(i).getSno().intValue();
                arr[i] += val;
            }
            int asInt = Arrays.stream(arr).max().getAsInt();
            logger.info("asInt ===========================================>" + asInt);

            return asInt;
        }
       return 0;
    }

    @Transactional
    public ReservRegister saveReserveRegisgter(ReservRegisterDto reservRegisterDto) {
        ReservRegister reservRegister = reservRegisterDto.toEntityOfReservRegister();

        // 예약 일자
        LocalDate date = LocalDate.now(); //오늘 날짜 LocalDate 객체 생성
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String today = date.format(dateTimeFormatter); //LocalDate 객체를 String 객체로 바꿈
        reservRegister.setRsvDt(today);

        // 상태(STTUS_CD)
        String sttus = "RSV_01";
        reservRegister.setSttusCd(sttus);

        int maxSno = getMaxSno();

        if (maxSno == 0) {
            // 초기 sno
            reservRegister.setSno(100);

            // 예약 번호
            String convertNum = "R" + StringUtils.rightPad(today.replace("-", ""), 12, String.valueOf(reservRegister.getSno()));
            reservRegister.setRsvNum(convertNum);

            // 숙박정보 save
            ReservRegister save = this.reservRegisterRepository.save(reservRegister);

            Long guestId = reservRegisterDto.getId();
            update(qReservRegister).set(qReservRegister.guestId, guestId).where(qReservRegister.id.eq(save.getId())).execute();

            return save;
        } else {
            int plusSno = ++maxSno;
            reservRegister.setSno(plusSno);

            // 예약 번호
            String convertNum = "R" + StringUtils.rightPad(today.replace("-", ""), 12, String.valueOf(plusSno));
            reservRegister.setRsvNum(convertNum);

            // 숙박정보 save
            ReservRegister save = this.reservRegisterRepository.save(reservRegister);

            Long guestId = reservRegisterDto.getId();
            update(qReservRegister).set(qReservRegister.guestId, guestId).where(qReservRegister.id.eq(save.getId())).execute();

            return save;
        }
    }
}


// 예약 등록 시 guest 테이블 저장 후 id 가지고 와서 예약등록 guest_id update 하는 로직
//    @Transactional
//    public void updateGuestId(Long guestId, Long reserveId) {
//        update(qReservRegister).set(qReservRegister.guestId, guestId).where(qReservRegister.id.eq(reserveId)).execute();
//    }

              // guest id
//            reservRegister.setGuestId(100l);

//            // (동시) 투숙객 save
//            HotelCustomerDto hotelCustomerDto = new HotelCustomerDto(save);
//            HotelCustomer hotelCustomer = hotelCustomerService.save(hotelCustomerDto);
//            Long reserveId = save.getId();
//            Long guestId = hotelCustomer.getId();
//
//            if(guestId != 0) {
//                updateGuestId(guestId, reserveId);
//            }

            // (동시) 투숙객 save
//            HotelCustomerDto hotelCustomerDto = new HotelCustomerDto(save);
//            HotelCustomer hotelCustomer = hotelCustomerService.save(hotelCustomerDto);
//            Long reserveId = save.getId();
//            Long guestId = hotelCustomer.getId();
//
//            if(reserveId != 0) {
//                updateGuestId(guestId, reserveId);
//            }