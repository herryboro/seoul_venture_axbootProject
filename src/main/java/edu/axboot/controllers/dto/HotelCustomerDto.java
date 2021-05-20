package edu.axboot.controllers.dto;

import edu.axboot.domain.hotelcustomer.HotelCustomer;
import edu.axboot.domain.reservRegister.ReservRegister;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class HotelCustomerDto {
    private Long id;
    private String guestNm;
    private String guestNmEng;
    private String guestTel;
    private String email;
    private String brth;
    private String gender;
    private String langCd;
    private String rmk;
    private List<ReservRegister> customerList = new ArrayList<ReservRegister>();

    @Builder
    public HotelCustomerDto(Long id, String guestNm, String guestNmEng, String guestTel, String email, String brth, String gender, String langCd, String rmk) {
        this.id = id;
        this.guestNm = guestNm;
        this.guestNmEng = guestNmEng;
        this.guestTel = guestTel;
        this.email = email;
        this.brth = brth;
        this.gender = gender;
        this.langCd = langCd;
        this.rmk = rmk;
    }

    public HotelCustomer toEntity() {
        return HotelCustomer.builder()
                .id(id)
                .guestNm(guestNm)
                .guestNmEng(guestNmEng)
                .guestTel(guestTel)
                .email(email)
                .brth(brth)
                .gender(gender)
                .langCd(langCd)
                .rmk(rmk)
                .build();
    }

    public HotelCustomerDto(HotelCustomer hotelCustomer) {
        this.id = hotelCustomer.getId();
        this.guestNm = hotelCustomer.getGuestNm();
        this.guestNmEng = hotelCustomer.getGuestNmEng();
        this.guestTel = hotelCustomer.getGuestTel();
        this.email = hotelCustomer.getEmail();
        this.brth = hotelCustomer.getBrth();
        this.gender = hotelCustomer.getGender();
        this.langCd = hotelCustomer.getLangCd();
        this.rmk = hotelCustomer.getRmk();
        this.customerList.addAll(hotelCustomer.getCustomerList());
    }

    public HotelCustomerDto(ReservRegister reservRegister) {
        this.id = reservRegister.getGuestId();
        this.guestNm = reservRegister.getGuestNm();
        this.guestNmEng = reservRegister.getGuestNmEng();
        this.guestTel = reservRegister.getGuestTel();
        this.email = reservRegister.getEmail();
        this.brth = reservRegister.getBrth();
        this.gender = reservRegister.getGender();
        this.langCd = reservRegister.getLangCd();
    }
}
