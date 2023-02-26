package com.oguztasgin.SpringMono.controller;

import com.oguztasgin.SpringMono.repository.entity.Urun;
import com.oguztasgin.SpringMono.service.UrunService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.oguztasgin.SpringMono.constants.EndPoints.*;
@RestController
/**
 * localhost:8080/urun
 */
@RequestMapping(URUN)
@RequiredArgsConstructor
public class UrunController {

    private final UrunService urunService;

    @GetMapping(FINDALL)
    public ResponseEntity<List<Urun>> findAll(){
        return ResponseEntity.ok(urunService.findAll());
    }

    @GetMapping(SAVE)
    public ResponseEntity<Urun> save(String ad,Double fiyat){
        Urun urun =  Urun.builder().ad(ad).fiyat(fiyat).build();
        urunService.save(urun);
        return ResponseEntity.ok(urun);
    }

}