package edu.axboot.controllers.dto;

import edu.axboot.domain.customerinfo.CustomerInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Getter
@NoArgsConstructor
public class CustomerInfoDto {
    private Long id;
    private String rsvNm;
    private int sno;
    private String memoCn;
    private Timestamp memoDtti;
    private String memoMan;
    private String delYn;
    // 메모 관련
    private List<CustomerInfoDto> customerInfoDtos;
    private boolean __created__;
    private boolean __modified__;
    private boolean __deleted__;

    @Builder
    public CustomerInfoDto(Long id, String rsvNm, int sno, String memoCn, Timestamp memoDtti, String memoMan, String delYn, List<CustomerInfoDto> customerInfoDtos
        , boolean __created__, boolean __modified__, boolean __deleted__) {
        this.id = id;
        this.rsvNm = rsvNm;
        this.sno = sno;
        this.memoCn = memoCn;
        this.memoDtti = memoDtti;
        this.memoMan = memoMan;
        this.delYn = delYn;
        this.customerInfoDtos = customerInfoDtos;
        this.__created__ = __created__;
        this.__modified__ = __modified__;
        this.__deleted__ = __deleted__;
    }

    public CustomerInfo toEntityOfCustomerInfo() {
        return CustomerInfo.builder()
                .rsvNum(rsvNm)
                .memoCn(memoCn)
                .memoDtti(memoDtti)
                .delYn(delYn)
                .build();
    }
}
