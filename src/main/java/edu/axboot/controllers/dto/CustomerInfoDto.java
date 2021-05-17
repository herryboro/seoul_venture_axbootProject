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
    private String rsvNm;
    private int sno;
    private String memoCn;
    private Timestamp memoDtti;
    private String memoMan;
    private String delYn;
    // 메모 관련
    private List<CustomerInfoDto> customerInfoDtos;

    @Builder
    public CustomerInfoDto(String rsvNm, int sno, String memoCn, Timestamp memoDtti, String memoMan, String delYn, List<CustomerInfoDto> customerInfoDtos) {
        this.rsvNm = rsvNm;
        this.sno = sno;
        this.memoCn = memoCn;
        this.memoDtti = memoDtti;
        this.memoMan = memoMan;
        this.delYn = delYn;
        this.customerInfoDtos = customerInfoDtos;
    }

    public CustomerInfo toEntityOfCustomerInfo() {
        return CustomerInfo.builder()
                .memoCn(memoCn)
                .memoDtti(memoDtti)
                .delYn(delYn)
                .build();
    }
}
