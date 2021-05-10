package edu.axboot.controllers.dto;

import edu.axboot.domain.herryboroproject.HerryboroHotel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.awt.print.Pageable;

@Getter
@NoArgsConstructor
public class HerryboroHotelDto {
    private Long id;
    private String roomNum;
    private String roomTypCd;
    private String dndYn;
    private String ebYn;
    private String roomSttusCd;
    private String clnSttusCd;
    private String svcSttusCd;
    private Pageable pageable;

    @Builder
    public HerryboroHotelDto(String roomNum, String roomTypCd, String dndYn, String ebYn, String roomSttusCd, String clnSttusCd, String svcSttusCd, Pageable pageable) {
        this.roomNum = roomNum;
        this.roomTypCd = roomTypCd;
        this.dndYn = dndYn;
        this.ebYn = ebYn;
        this.roomSttusCd = roomSttusCd;
        this.clnSttusCd = clnSttusCd;
        this.svcSttusCd = svcSttusCd;
        this.pageable = pageable;
    }

    public HerryboroHotel toEntitiy() {
        return HerryboroHotel.builder()
                .roomNum(roomNum)
                .roomTypCd(roomTypCd)
                .dndYn(dndYn)
                .ebYn(ebYn)
                .roomSttusCd(roomSttusCd)
                .clnSttusCd(clnSttusCd)
                .svcSttusCd(svcSttusCd)
                .build();
    }
}
