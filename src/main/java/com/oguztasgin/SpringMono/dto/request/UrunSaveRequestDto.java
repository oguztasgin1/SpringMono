package com.oguztasgin.SpringMono.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UrunSaveRequestDto {
    Long id;
    String ad;
    String fiyat;
    String kdv;
    String barkod;
    MultipartFile profileimg;

}