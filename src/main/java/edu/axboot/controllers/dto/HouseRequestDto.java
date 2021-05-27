package edu.axboot.controllers.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class HouseRequestDto {
    private Long id;
    private String rsvDt1;
    private String rsvDt2;
    private String rsvNum;
    private String guestNm;
    private String guestNmEng;
    private String guestTel;
    private String email;
    private String langCd;
    private String arrDt1;
    private String arrDt2;
    private String depDt1;
    private String depDt2;
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
    private boolean __created__;
    private boolean __modified__;
    private boolean __deleted__;
}
