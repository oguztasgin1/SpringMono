package com.oguztasgin.SpringMono.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {
    /**
     *  Sırf benim uygulamamla alakalı olan bir exception yazacagimdam bunu kullaniyorum.
     */
    BAD_REQUEST_ERROR(1201,"Geçersiz parametre girişi yaptınız",BAD_REQUEST),
    INTERNAL_ERROR(3000,"Sunucuda beklenmeyen bir hata",INTERNAL_SERVER_ERROR),

    KULLANICI_BULUNAMADI(2301,"Aradiginiz id ye ait kullanici bulunamamistir", INTERNAL_SERVER_ERROR);
    private int code;
    private String message;
    private HttpStatus httpStatus;
}
