package edu.axboot.domain.reservRegister;

import com.chequer.axboot.core.parameter.RequestParams;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import edu.axboot.controllers.dto.*;
import edu.axboot.domain.education.EducationTeachService;
import edu.axboot.domain.hotelcustomer.HotelCustomerService;
import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

        int maxSno = getMaxSno();

        if (maxSno == 0) {
            // 초기 sno
            reservRegister.setSno(100);

            // 상태(STTUS_CD)
            String sttus = "예약";
            reservRegister.setSttusCd(sttus);

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

            // 상태(STTUS_CD)
            String sttus = "예약";
            reservRegister.setSttusCd(sttus);

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

    @Transactional
    public List<ReservRegister> updateInModal(ReservRegisterDto reservRegisterDto) {

        update(qReservRegister)
                .set(qReservRegister.rsvNum, reservRegisterDto.getRsvNum())
                .set(qReservRegister.rsvDt, reservRegisterDto.getRsvDt())
                .set(qReservRegister.guestNm, reservRegisterDto.getGuestNm())
                .set(qReservRegister.guestNmEng, reservRegisterDto.getGuestNmEng())
                .set(qReservRegister.guestTel, reservRegisterDto.getGuestTel())
                .set(qReservRegister.email, reservRegisterDto.getEmail())
                .set(qReservRegister.langCd, reservRegisterDto.getLangCd())
                .set(qReservRegister.roomNum, reservRegisterDto.getRoomNum())
                .set(qReservRegister.arrDt, reservRegisterDto.getArrDt())
                .set(qReservRegister.depDt, reservRegisterDto.getDepDt())
                .set(qReservRegister.nightCnt, reservRegisterDto.getNightCnt())
                .set(qReservRegister.roomTypCd, reservRegisterDto.getRoomTypCd())
                .set(qReservRegister.adultCnt, reservRegisterDto.getAdultCnt())
                .set(qReservRegister.chldCnt, reservRegisterDto.getChldCnt())
                .set(qReservRegister.saleTypCd, reservRegisterDto.getSaleTypCd())
                .set(qReservRegister.sttusCd, reservRegisterDto.getSttusCd())
                .set(qReservRegister.srcCd, reservRegisterDto.getSrcCd())
                .set(qReservRegister.brth, reservRegisterDto.getBrth())
                .set(qReservRegister.gender, reservRegisterDto.getGender())
                .set(qReservRegister.payCd, reservRegisterDto.getPayCd())
                .set(qReservRegister.salePrc, reservRegisterDto.getSalePrc())
                .set(qReservRegister.svcPrc, reservRegisterDto.getSvcPrc())
                .set(qReservRegister.advnYn, reservRegisterDto.getAdvnYn())
                .where(qReservRegister.id.eq(reservRegisterDto.getId()))
                .execute();

        List<ReservRegister> fetch = select().from(qReservRegister).where(qReservRegister.id.eq(reservRegisterDto.getId())).fetch();
        return fetch;
    }

    public Page<ReserveStatusDto> getReserveList(RequestParams<ReserveStatusDto> requestParams, Pageable pageable) {
        String guestNm = requestParams.getString("guestNm", "");
        String rsvNum = requestParams.getString("rsvNum", "");
        String rsvDt1 = requestParams.getString("rsvDt1", "");
        String rsvDt2 = requestParams.getString("rsvDt2", "");
        String roomTypCd = requestParams.getString("roomTypCd", "");
        String depDt1 = requestParams.getString("depDt1", "");
        String depDt2 = requestParams.getString("depDt2", "");
        String arrDt1 = requestParams.getString("arrDt1", "");
        String arrDt2 = requestParams.getString("arrDt2", "");
        String sttusCd = requestParams.getString("sttusCd", "");

        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(guestNm)) {
            builder.and(qReservRegister.guestNm.like("%" + guestNm + "%"));
        }
        if (isNotEmpty(rsvNum)) {
            builder.and(qReservRegister.rsvNum.like(rsvNum));
        }
        if (isNotEmpty(rsvDt1) && isNotEmpty(rsvDt2)) {
            builder.and(qReservRegister.rsvDt.between(rsvDt1, rsvDt2));
        }
        if (isNotEmpty(roomTypCd)) {
            builder.and(qReservRegister.roomTypCd.like(roomTypCd));
        }
        if (isNotEmpty(depDt1) && isNotEmpty(depDt2)) {
            builder.and(qReservRegister.depDt.between(depDt1, depDt2));
        }
        if (isNotEmpty(arrDt1) && isNotEmpty(arrDt2)) {
            builder.and(qReservRegister.arrDt.between(arrDt1, arrDt2));
        }
        if (isNotEmpty(sttusCd)) {
            builder.and(qReservRegister.sttusCd.like(sttusCd));
        }

        List<ReservRegister> reserveList = select().from(qReservRegister).where(builder).orderBy(qReservRegister.id.asc()).fetch();

        int totalSize = reserveList.size();
        int start = pageable.getOffset();
        int end = (start + pageable.getPageSize()) > totalSize ? totalSize : (start + pageable.getPageSize());
        return new PageImpl(reserveList.subList(start, end), pageable, totalSize);
    }

    public Page<CheckInRequestDto> getFrontList(RequestParams<CheckInRequestDto> requestParams, Pageable pageable) {
        String guestNm = requestParams.getString("guestNm", "");
        String rsvNm = requestParams.getString("rsvNm", "");
        String depDt = requestParams.getString("depDt", "");

        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(guestNm)) {
            builder.and(qReservRegister.guestNm.like("%" + guestNm + "%"));
        }
        if (isNotEmpty(rsvNm)) {
            builder.and(qReservRegister.rsvNum.like(rsvNm));
        }
        if (isNotEmpty(depDt)) {
            builder.and(qReservRegister.depDt.eq(depDt));
        }

        BooleanBuilder builder2 = new BooleanBuilder();

        builder2.or(qReservRegister.sttusCd.eq("예약"));
        builder2.or(qReservRegister.sttusCd.eq("예약대기"));
        builder2.or(qReservRegister.sttusCd.eq("예약확정"));

        builder.and(builder2);

        List<ReservRegister> frontList = select().from(qReservRegister).where(builder).orderBy(qReservRegister.id.asc()).fetch();

        int totalSize = frontList.size();
        int start = pageable.getOffset();
        int end = (start + pageable.getPageSize()) > totalSize ? totalSize : (start + pageable.getPageSize());
        return new PageImpl(frontList.subList(start, end), pageable, totalSize);
    }

    public Page<HouseRequestDto> getCustomerListForInHouse(String filter, String rsvNum, String roomTypCd, String rsvSttDate, String rsvEndDate,
                                                           String arrSttDate, String arrEndDate, String depSttDate, String depEndDate, Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(filter)) {
            BooleanBuilder builder2 = new BooleanBuilder();
            builder2.or(qReservRegister.guestNm.contains(filter));
            builder2.or(qReservRegister.guestTel.contains(filter));
            builder2.or(qReservRegister.email.contains(filter));
            builder.and(builder2);
        }

        if (isNotEmpty(rsvNum)) {
            builder.and(qReservRegister.rsvNum.contains(rsvNum));
        }

        if (isNotEmpty(roomTypCd)) {
            builder.and(qReservRegister.roomTypCd.eq((roomTypCd)));
        }

        if (isNotEmpty(rsvSttDate)) {
            if (isNotEmpty(rsvEndDate)) {
                builder.and(qReservRegister.rsvDt.between(rsvSttDate, rsvEndDate));
            } else {
                builder.and(qReservRegister.rsvDt.goe(rsvSttDate));
            }
        }

        if (isNotEmpty(arrSttDate)) {
            if (isNotEmpty(arrEndDate)) {
                builder.and(qReservRegister.arrDt.between(arrSttDate, arrEndDate));
            } else {
                builder.and(qReservRegister.arrDt.goe(arrSttDate));
            }
        }

        if (isNotEmpty(depSttDate)) {
            if (isNotEmpty(depEndDate)) {
                builder.and(qReservRegister.depDt.between(depSttDate, depEndDate));
            } else {
                builder.and(qReservRegister.depDt.goe(depSttDate));
            }
        }

        BooleanBuilder builder2 = new BooleanBuilder();
        builder2.or(qReservRegister.sttusCd.eq("체크인"));
        builder.and(builder2);

        List<ReservRegister> customerList = select().from(qReservRegister).where(builder).orderBy(qReservRegister.id.asc()).fetch();

        int totalSize = customerList.size();
        int start = pageable.getOffset();
        int end = (start + pageable.getPageSize()) > totalSize ? totalSize : (start + pageable.getPageSize());
        return new PageImpl(customerList.subList(start, end), pageable, totalSize);
    }

    public Page<HouseRequestDto> getCustomerListForCheckOutStatus(String filter, String rsvNum, String roomTypCd, String rsvSttDate, String rsvEndDate,
                                                                  String arrSttDate, String arrEndDate, String depSttDate, String depEndDate, Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(filter)) {
            BooleanBuilder builder2 = new BooleanBuilder();
            builder2.or(qReservRegister.guestNm.contains(filter));
            builder2.or(qReservRegister.guestTel.contains(filter));
            builder2.or(qReservRegister.email.contains(filter));
            builder.and(builder2);
        }

        if (isNotEmpty(rsvNum)) {
            builder.and(qReservRegister.rsvNum.contains(rsvNum));
        }

        if (isNotEmpty(roomTypCd)) {
            builder.and(qReservRegister.roomTypCd.eq((roomTypCd)));
        }

        if (isNotEmpty(rsvSttDate)) {
            if (isNotEmpty(rsvEndDate)) {
                builder.and(qReservRegister.rsvDt.between(rsvSttDate, rsvEndDate));
            } else {
                builder.and(qReservRegister.rsvDt.goe(rsvSttDate));
            }
        }

        if (isNotEmpty(arrSttDate)) {
            if (isNotEmpty(arrEndDate)) {
                builder.and(qReservRegister.arrDt.between(arrSttDate, arrEndDate));
            } else {
                builder.and(qReservRegister.arrDt.goe(arrSttDate));
            }
        }

        if (isNotEmpty(depSttDate)) {
            if (isNotEmpty(depEndDate)) {
                builder.and(qReservRegister.depDt.between(depSttDate, depEndDate));
            } else {
                builder.and(qReservRegister.depDt.goe(depSttDate));
            }
        }

        BooleanBuilder builder2 = new BooleanBuilder();
        builder2.or(qReservRegister.sttusCd.eq("체크아웃"));
        builder2.or(qReservRegister.sttusCd.eq("체크인취소"));
        builder.and(builder2);

        List<ReservRegister> customerList = select().from(qReservRegister).where(builder).orderBy(qReservRegister.id.asc()).fetch();

        int totalSize = customerList.size();
        int start = pageable.getOffset();
        int end = (start + pageable.getPageSize()) > totalSize ? totalSize : (start + pageable.getPageSize());
        return new PageImpl(customerList.subList(start, end), pageable, totalSize);
    }

    public List<SalesSumResponseDto> getReportInformation(String start, String end) {
        BooleanBuilder builder = new BooleanBuilder();

        if(isNotEmpty(start)) {
            if (isNotEmpty(end)) {
                builder.and(qReservRegister.rsvDt.between(start, end));
            }
        }

        List<SalesSumResponseDto> salesSumResult = select().select(Projections.fields(SalesSumResponseDto.class,
                qReservRegister.rsvDt,
                qReservRegister.rsvDt.count().as("count"),
                qReservRegister.salePrc.sum().as("salePrc"),
                qReservRegister.svcPrc.sum().as("svcPrc"))
        ).from(qReservRegister)
                .where(builder)
                .groupBy(qReservRegister.rsvDt)
                .orderBy(qReservRegister.rsvDt.asc())
                .fetch();

        return salesSumResult;
    }

    public ResponseFindGuestByIdDto findGuestById(Long id) {
        ReservRegister guestList = reservRegisterRepository.findOne(id);
        ResponseFindGuestByIdDto responseFindGuestByIdDto = new ResponseFindGuestByIdDto(guestList);
        return responseFindGuestByIdDto;
    }

    @Transactional
    public void updateStatus(List<UpdateStatusDto> updateStatusDtos) {
        for (UpdateStatusDto dto: updateStatusDtos) {
            ReservRegister one = reservRegisterRepository.findOne(dto.getId());
            one.updateStatus(dto.getSttusCd());
        }
    }
}
