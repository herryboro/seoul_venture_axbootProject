package edu.axboot.controllers.dto;

import edu.axboot.domain.reservRegister.ReservRegister;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
public class ReservRegisterDto {
    private Long id;
    private String rsvDt1;
    private String rsvDt2;
    private Integer sno;
    private String rsvNum;
    private Long guestId;
    private String guestNm;
    private String guestNmEng;
    private String guestTel;
    private String email;
    private String langCd;
    private String arrDt1;
    private String arrDt2;
    private String arrTime;
    private String depDt1;
    private String depDt2;
    private String depTime;
    private Integer nightCnt;
    private String roomTypCd;
    private String roomNum;
    private Integer adultCnt;
    private Integer chldCnt;
    private String saleTypCd;
    private String sttusCd;
    private String srcCd;
    private String brth;
    private String gender;
    private String payCd;
    private String advnYn;
    private BigDecimal salePrc;
    private BigDecimal svcPrc;
    private List<CustomerInfoDto> memoList;
    private boolean __created__;
    private boolean __modified__;
    private boolean __deleted__;

    @Builder
    public ReservRegisterDto(
            Long id, String rsvDt1, String rsvDt2, Integer sno, String rsvNum, Long guestId, String guestNm,
            String guestNmEng, String guestTel, String email, String langCd, String arrDt1, String arrDt2, String arrTime,
            String depDt1, String depDt2, String depTime, Integer nightCnt, String roomTypCd, String roomNum, Integer adultCnt,
            Integer chldCnt, String saleTypCd, String sttusCd, String srcCd, String brth, String gender, String payCd,
            String advnYn, BigDecimal salePrc, BigDecimal svcPrc, List<CustomerInfoDto> memoList, boolean __created__, boolean __modified__,boolean __deleted__) {

        this.id = id;
        this.rsvDt1 = rsvDt1;
        this.rsvDt2 = rsvDt2;
        this.sno = sno;
        this.rsvNum = rsvNum;
        this.guestId = guestId;
        this.guestNm = guestNm;
        this.guestNmEng = guestNmEng;
        this.guestTel = guestTel;
        this.email = email;
        this.langCd = langCd;
        this.arrDt1 = arrDt1;
        this.arrDt2 = arrDt2;
        this.arrTime = arrTime;
        this.depDt1 = depDt1;
        this.depDt2 = depDt2;
        this.depTime = depTime;
        this.nightCnt = nightCnt;
        this.roomTypCd = roomTypCd;
        this.roomNum = roomNum;
        this.adultCnt = adultCnt;
        this.chldCnt = chldCnt;
        this.saleTypCd = saleTypCd;
        this.sttusCd = sttusCd;
        this.srcCd = srcCd;
        this.brth = brth;
        this.gender = gender;
        this.payCd = payCd;
        this.advnYn = advnYn;
        this.salePrc = salePrc;
        this.svcPrc = svcPrc;
        this.memoList = memoList;
        this.__created__ = __created__;
        this.__modified__ = __modified__;
        this.__deleted__ = __deleted__;
    }

    public ReservRegister toEntityOfReservRegister() {
        return ReservRegister.builder()
                .id(id)
                .guestId(guestId)
                .guestNm(guestNm)
                .guestNmEng(guestNmEng)
                .guestTel(guestTel)
                .email(email)
                .langCd(langCd)
//                .arrDt(arrDt)
                .arrTime(arrTime)
//                .depDt(depDt)
                .depTime(depTime)
                .nightCnt(nightCnt)
                .roomTypCd(roomTypCd)
                .roomNum(roomNum)
                .adultCnt(adultCnt)
                .chldCnt(chldCnt)
                .saleTypCd(saleTypCd)
                .srcCd(srcCd)
                .brth(brth)
                .gender(gender)
                .payCd(payCd)
                .advnYn(advnYn)
                .salePrc(salePrc)
                .svcPrc(svcPrc)
                .build();
    }
}
