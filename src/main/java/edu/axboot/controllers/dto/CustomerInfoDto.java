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
    private String rsvNum;
    private int sno;
    private String memoCn;
    private Timestamp memoDtti;
    private String memoMan;
    private String delYn;
    // 메모 관련
    private List<CustomerInfoDto> customerInfos;
    private boolean __created__;
    private boolean __modified__;
    private boolean __deleted__;

    @Builder
    public CustomerInfoDto(Long id, String rsvNum, int sno, String memoCn, Timestamp memoDtti, String memoMan, String delYn, List<CustomerInfoDto> customerInfos
        , boolean isCreated, boolean isModified, boolean isDeleted) {
        this.id = id;
        this.rsvNum = rsvNum;
        this.sno = sno;
        this.memoCn = memoCn;
        this.memoDtti = memoDtti;
        this.memoMan = memoMan;
        this.delYn = delYn;
        this.customerInfos = customerInfos;
        this.__created__ = isCreated;
        this.__modified__ = isModified;
        this.__deleted__ = isDeleted;
    }

    public CustomerInfo toEntityOfCustomerInfo() {
        return CustomerInfo.builder()
                .id(id)
                .rsvNum(rsvNum)
                .sno(sno)
                .memoCn(memoCn)
                .memoDtti(memoDtti)
                .memoMan("관리자")
                .delYn(delYn)
                .isCreated(__created__)
                .isModified(__modified__)
                .isDeleted(__deleted__)
                .build();
    }
}
