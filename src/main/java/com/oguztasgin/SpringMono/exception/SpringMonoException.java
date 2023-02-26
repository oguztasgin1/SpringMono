package com.oguztasgin.SpringMono.exception;

import lombok.Getter;

@Getter
public class SpringMonoException extends RuntimeException{
    private final ErrorType errorType;

    /**
     * Runtime' dan miras aldıgimiz icin hata mesajının kendisine iletilmesi gerekiyor.
     * @param errorType
     */
    public SpringMonoException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public SpringMonoException(ErrorType errorType, String message){
        super(message);
        this.errorType= errorType;
    }
}