package com.oguztasgin.SpringMono.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UrunUpdateRequestDto {
    Long id;
    String ad;
    Double fiyat;
    String barkod;
    Integer kdv;

}