package com.oguztasgin.SpringMono.repository;

import com.oguztasgin.SpringMono.repository.entity.Musteri;
import com.oguztasgin.SpringMono.repository.view.VwMusteri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring BeanFactory ile olusturacagimiz nesneleri belirlemek için belli anatosyanlari arar.
 * Bunlardan 1. si Veritabani içiçe
 * Repository'dir
 *
 */

@Repository
public interface IMusteriRepository extends JpaRepository<Musteri, Long> {



    /**
     *  !!DİKKAT !!
     *  Spring' in devraldigi repository interface lerinde, method tanımlamaları için
     *  özel keyword ler kullanilmaktadir.
     *  bunlar üzerinden sorgular olusturulur.
     *
     *  1- ReturnType -> Musteri, List<Musteri>, Boolean, integer vs
     *  2- find, kelimesini kullaniyoruz.
     *  3- By, ne için arama yapilacaginin belirlenebilmesi için kullanilir.
     *  4- Entity Property [entity -> degisken adi], Üzerinde calistiginiz repository nin
     *  kullanmakta oldugu entitiy sinifindan bir degiskenin birebir adini yazmalisiniz.
     *
     *  !!! ÇOK DİKKAT !!!!
     *  Buarada varlik adi yazilirken buyuk harf ile baslanir yine dikkat edilmesi gereken
     *  bir husus eğer degişken adi camelcase şeklinde (musteriAdSoyad) şeklinde yazilmis
     *  ise buna dikkat edilerek yazilmalidir.
     *  5- Method için gerekli parametreler değişken türüne göre eklenir.
     *
     *  !! DİKKAT !!  Burada degisken adi önemli degil, degisken türü önemlidir.
     *
     */

    List<Musteri> findByAd(String burayaBirDegerGirmemGerekiyor);
    /**
     * select * from tblmusteri where ad=? and adres=?
     * @param bisey
     * @param baskabisey
     * @return
     */
    List<Musteri> findAllByAdAndAdres(String bisey,String baskabisey);

    /**
     * Hangi yaş grubunun hangi ürüleri daha fazla satın aldığını merak ediyorsunuz.
     * örn: 40 yaş üzeri müşterilerin listesi.
     *
     */
    List<Musteri> findAllByYasGreaterThan(Integer yas); // yas>?
    List<Musteri> findAllByYasGreaterThanEqual(Integer yas); // yas>=?

    /**
     * Belli bir harfin ya da kelimenin aranması  LIKE, ILIKE
     */
    List<Musteri> findAllByAdLike(String ad);

    /**
     * Select * From tblbmusteri ad like '?%'
     *
     */
    List<Musteri> findByAdStartingWith(String ad);

    /**
     * TEk bir musteri bilgisi gelmesini bekledigim durumda ise
     * Select * From tblmusteri where ad=? and adres=? and telefon=?
     */

    Musteri findByAdAndAdresAndTelefon(String ad, String adres, String telefon);

    /*
    Veri tabani bir sorgu attıgımzda o sorgu bize her daim bir sonuc donmez.
    Select * From tblmusteri where yas = 5000

    Eğer sonuc yoksa, null donebilir boyle durumlarda null kontrolü yapmak çok da doğru değildir.
    Bunun yerine Optional kullanmak doğru olacaktır.
     */

    Optional<Musteri> findOptionalByTelefon(String telefon); // => Eğer null ise empty dönecektir.

    Optional <List<Musteri>> findAllOptionalByAdres(String adres);

    /**
     * Order ->
     * -> ASC A....Z, 0..9
     * -> ASC Z..A, 9..0
     */

    List<Musteri> findByOrderByYas(); // -> Yasina gore kucukten buyuge gore sırali liste verir.
    List<Musteri> findByOrderByYasDesc();

    Musteri findTopByOrderByYasDesc(); // -> TopBy en yukarıdakini alır. -> En yasli musteri

    /**
     *  Select * From tblmusteri where yas>18 and yas <40
     */

    List<Musteri> findAllByYasBetween(Integer start, Integer end); // !! -> yas >=? and yas<=?
    //Baslangicve bitisi de dahil eder.

    List<Musteri> findAllByAdresAndYasBetween(String adres, Integer start, Integer end);

    /**
     * true-false
     */

    List<Musteri> findAllByState(boolean state); // aktif-pasif kayitlari getirir.

    List<Musteri> findAllByStateTrue(); // true -> aktif olan kayirlari getirir.
    List<Musteri> findAllByStateFalse();


    /**
     * Ahmet -> Tbl "ahmet" false
     */

    List<Musteri> findAllByAdIgnoreCaseAndAdresIgnoreCase(String ad, String adres);

    /**
     * ÇOK ONEMLİ!!!!
     *
     * Belli isimlerin listesini çekelim.
     * List<String> adListesi ={"Ahmet", "Ayse", "Canan"}
     */

    List<Musteri> findAllByAdIn(List<String> adListesi);

    /**
     *  JPQL ya da HQL veya NativeSql kullanilabilir.
     */
    //JPQl -> Bize sagladı sey su: Spring tarafından bize dayatilan key-word'  leri kullanmak zorunda kalmıyorsunuz.

    /**
     * HQL Kullanimi
     */
    @Query("select m from Musteri m where m.adres= ?1")
    List<Musteri> senBulTumunuMusterilerinAdresineGore(String adresOlabilir);

    @Query("select m from Musteri m where m.ad = ?3 and m.adres= ?1 and m.telefon= ?2")
    Musteri musteriyiBul(String adres, String telefon, String ad);

    /**
     * NativeSql Kullanimi
     */
    @Query(value = "select * from Musteri where ad= ?1", nativeQuery = true)
    List<Musteri> bulAdinaGore(String ad);

    /**
     * Sorgu parametrelerinin kullanilarak tanımlanmasi  ve method içinden çekilmesi
     */

    @Query("select m from Musteri m where m.ad= :ad and m.adres= :adres")
    List<Musteri> findAdAndAdres(
            @Param("ad") String musteriadi,
            @Param("adres") String musteriadresi
    );

    @Query("select COUNT (m)>0 from Musteri m where m.tckimlik=?1")
    Boolean musteriVarmi(String tckimlik);


    @Query("select new com.oguztasgin.SpringMono.repository.view.VwMusteri(m.id, m.ad) from Musteri m")
    List<VwMusteri> findAllViewMusteri();

}

