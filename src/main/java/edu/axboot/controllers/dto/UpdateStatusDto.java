package edu.axboot.controllers.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UpdateStatusDto {
    private Long id;
    private String sttusCd;

    @Builder
    UpdateStatusDto(Long id, String sttusCd) {
        this.id = id;
        this.sttusCd = sttusCd;
    }
}
