package edu.axboot.controllers.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public class SalesSumResponseDto {
    private String rsvDt;
    private long count;
    private BigDecimal salePrc = BigDecimal.ZERO;
    private BigDecimal svcPrc = BigDecimal.ZERO;

    public BigDecimal getTotalPrc() {
        this.salePrc = BigDecimal.ZERO;
        this.svcPrc = BigDecimal.ZERO;
        return this.salePrc.add(svcPrc);
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
