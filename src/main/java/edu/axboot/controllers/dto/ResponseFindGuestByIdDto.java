package edu.axboot.controllers.dto;

import edu.axboot.domain.customerinfo.CustomerInfo;
import edu.axboot.domain.reservRegister.ReservRegister;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseFindGuestByIdDto {

    private Long id;
    private String rsvDt;
    private Integer sno;
    private String rsvNum;
    private Long guestId;
    private String guestNm;
    private String guestNmEng;
    private String guestTel;
    private String email;
    private String langCd;
    private String arrDt;
    private String arrTime;
    private String depDt;
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
    private List<CustomerInfo> customerInfos = new ArrayList<>();

    public ResponseFindGuestByIdDto(ReservRegister reservRegister) {
        this.id = reservRegister.getId();
        this.rsvDt = reservRegister.getRsvDt();
        this.sno = reservRegister.getSno();
        this.rsvNum = reservRegister.getRsvNum();
        this.guestId = reservRegister.getGuestId();
        this.guestNm = reservRegister.getGuestNm();
        this.guestNmEng = reservRegister.getGuestNmEng();
        this.guestTel = reservRegister.getGuestTel();
        this.email = reservRegister.getEmail();
        this.langCd = reservRegister.getLangCd();
        this.arrDt = reservRegister.getArrDt();
        this.arrTime = reservRegister.getArrTime();
        this.depDt = reservRegister.getDepDt();
        this.depTime = reservRegister.getDepTime();
        this.nightCnt = reservRegister.getNightCnt();
        this.roomTypCd = reservRegister.getRoomTypCd();
        this.roomNum = reservRegister.getRoomNum();
        this.adultCnt = reservRegister.getAdultCnt();
        this.chldCnt = reservRegister.getChldCnt();
        this.saleTypCd = reservRegister.getSaleTypCd();
        this.sttusCd = reservRegister.getSttusCd();
        this.srcCd = reservRegister.getSrcCd();
        this.brth = reservRegister.getBrth();
        this.gender = reservRegister.getGender();
        this.payCd = reservRegister.getPayCd();
        this.advnYn = reservRegister.getAdvnYn();
        this.salePrc = reservRegister.getSalePrc();
        this.svcPrc = reservRegister.getSvcPrc();
        this.customerInfos.addAll(reservRegister.getCustomerInfo());
    }
}
