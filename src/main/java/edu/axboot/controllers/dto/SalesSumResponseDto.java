package edu.axboot.controllers.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public class SalesSumResponseDto {
    private String rsvDt;
    private long count = 0;
    private BigDecimal salePrc = BigDecimal.ZERO;
    private BigDecimal svcPrc = BigDecimal.ZERO;

    public BigDecimal getTotalPrc() {
        if(this.salePrc == null) this.salePrc = BigDecimal.ZERO;
        if(this.svcPrc == null) this.svcPrc = BigDecimal.ZERO;
        return this.salePrc.add(this.svcPrc);
    }

    public void setRsvDt(String rsvDt) {
        this.rsvDt = rsvDt;
    }

    public void add(SalesSumResponseDto salesSumResponseDto) {
        if (salesSumResponseDto == null) return;
        this.count += salesSumResponseDto.getCount();

        if (salesSumResponseDto.getSalePrc() != null) {
            this.salePrc = this.salePrc.add(salesSumResponseDto.getSalePrc());
        }

        if (salesSumResponseDto.getSvcPrc() != null) {
            this.svcPrc = this.svcPrc.add(salesSumResponseDto.getSvcPrc());
        }
    }
}
