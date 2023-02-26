package com.oguztasgin.SpringMono.controller;

import com.oguztasgin.SpringMono.repository.IMusteriRepository;
import com.oguztasgin.SpringMono.repository.entity.Musteri;
import com.oguztasgin.SpringMono.service.MusteriService;
import com.oguztasgin.SpringMono.repository.entity.Musteri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@RestController
/**
 * Uygulama ayağa kalktığı andan itibaren bir ip ve port üzerinde yayın yapar.
 * localhost:80 -> localhost
 * Uygulamanızın alt kırılımlarına yön vermek ve belli Class lara yönlendirme yapmak için
 * Mapping yaparız. bunun için @ReuestMapping anotasyonu kullanılır.
 * http://localhost/musteri
 */
@RequestMapping("/musteri")
public class MusteriController {

    @Autowired
    MusteriService musteriService;

    // Exceptions Handler icin ornek
    @GetMapping("error")
    public ResponseEntity<String> testError() {
        int i = 0;
        if(i==0)
            throw new IllegalStateException("Çok fene hata olustu.");
        return ResponseEntity.ok("Hata Sayfasi");
    }

    /**
     * http://localhost/musteri/save
     * Get -> bir sayfaya erişme ve ondan bilgi alma isteğidir. özel bir gereksinimi yoktur.
     * Browser ların tümü GET isteği gönderir.
     * @param ad
     * @param adres
     * @param telefon
     */
    @GetMapping("/save")
    public void save(String ad,String adres,String telefon){
        Musteri musteri = Musteri.builder()
                .ad(ad)
                .adres(adres)
                .telefon(telefon)
                .build();
        musteriService.save(musteri);
    }


    /**
     * ResponseEntity -> Pojo JsonObject olarak Resturn type kullan
     * localhost/musteri/findall
     * @return
     */
    @GetMapping("/findall")
    public ResponseEntity<List<Musteri>> findAll(){
        return ResponseEntity.ok(musteriService.findAll());
    }

    @GetMapping("/findbyad")
    public ResponseEntity<List<Musteri>> findByAd(String ad,String adres){
        if(adres==null)
            return ResponseEntity.ok(musteriService.adinaGoreGetir(ad));
        return ResponseEntity.ok(musteriService.adVeAdreseGoreGetir(ad,adres));
    }

    /**
     * select * from tblmusteri where ad like '%ad' - 'a%'
     * @param ad
     * @return
     */
    @GetMapping("/findallbyadlike")
    public ResponseEntity<List<Musteri>> findAllByStartwithAd(String ad){
        return ResponseEntity.ok(musteriService.findAllByAdLike(ad+"%"));
    }
}