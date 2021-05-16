package edu.axboot.controllers.dto;

import edu.axboot.domain.customerinfo.CustomerInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class CustomerInfoDto {
    private String rsvNm;
    private int sno;
    private String memoCn;
    private Timestamp memoDtti;
    private String memoMan;
    private String delYn;

    @Builder
    public CustomerInfoDto(String rsvNm, int sno, String memoCn, Timestamp memoDtti, String memoMan, String delYn) {
        this.rsvNm = rsvNm;
        this.sno = sno;
        this.memoCn = memoCn;
        this.memoDtti = memoDtti;
        this.memoMan = memoMan;
        this.delYn = delYn;
    }
}
