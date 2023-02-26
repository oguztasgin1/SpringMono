package com.oguztasgin.SpringMono.mvccontroller.models;

import com.oguztasgin.SpringMono.repository.entity.Urun;
import lombok.*;


import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UrunIndexModel {
    String title;
    List<String> menulistesi;
    List<Urun> urunlistesi;
}
