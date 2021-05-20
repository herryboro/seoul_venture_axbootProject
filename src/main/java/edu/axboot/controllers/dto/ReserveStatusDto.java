package edu.axboot.controllers.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
public class ReserveStatusDto {
    private Long id;
    private String rsvDt;
    private String rsvNum;
    private String guestNm;
    private String guestNmEng;
    private String guestTel;
    private String email;
    private String langCd;
    private String arrDt;
    private String depDt;
    private Integer nightCnt;
    private String roomTypCd;
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
    private boolean __created__;
    private boolean __modified__;
    private boolean __deleted__;

    public ReserveStatusDto(Long id, String rsvDt, String rsvNum, String guestNm, String guestNmEng, String guestTel, String email, String langCd, String arrDt, String depDt, Integer nightCnt,
                            String roomTypCd, Integer adultCnt, Integer chldCnt, String saleTypCd, String sttusCd, String srcCd, String brth, String gender, String payCd, String advnYn, BigDecimal salePrc,
                            BigDecimal svcPrc, boolean __created__, boolean __modified__, boolean __deleted__) {

        this.id = id;
        this.rsvDt = rsvDt;
        this.rsvNum = rsvNum;
        this.guestNm = guestNm;
        this.guestNmEng = guestNmEng;
        this.guestTel = guestTel;
        this.email = email;
        this.langCd = langCd;
        this.arrDt = arrDt;
        this.depDt = depDt;
        this.nightCnt = nightCnt;
        this.roomTypCd = roomTypCd;
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
        this.__created__ = __created__;
        this.__modified__ = __modified__;
        this.__deleted__ = __deleted__;
    }
}
