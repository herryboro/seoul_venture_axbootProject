package edu.axboot.controllers.dto;

import edu.axboot.domain.herryboroproject.HerryboroHotel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.awt.print.Pageable;

@Getter
@NoArgsConstructor
public class HerryboroHotelDto {
    private String roomNum;
    private String roomTypCd;
    private String dndYn;
    private String ebYn;
    private String roomSttusCd;
    private String clnSttusCd;
    private String svcSttusCd;
    private Pageable pageable;
    private boolean __created__;
    private boolean __modified__;
    private boolean __deleted__;

    @Builder
    public HerryboroHotelDto(String roomNum, String roomTypCd, String dndYn, String ebYn, String roomSttusCd, String clnSttusCd, String svcSttusCd, Pageable pageable,
                             boolean __created__, boolean __modified__, boolean __deleted__) {
        this.roomNum = roomNum;
        this.roomTypCd = roomTypCd;
        this.dndYn = dndYn;
        this.ebYn = ebYn;
        this.roomSttusCd = roomSttusCd;
        this.clnSttusCd = clnSttusCd;
        this.svcSttusCd = svcSttusCd;
        this.__created__ = __created__;
        this.__modified__ = __modified__;
        this.__deleted__ = __deleted__;
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
                .isCreated(__created__)
                .isModified(__modified__)
                .isDeleted(__deleted__)
                .build();
    }
}
