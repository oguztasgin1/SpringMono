package com.oguztasgin.SpringMono.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

import static com.oguztasgin.SpringMono.exception.ErrorType.BAD_REQUEST_ERROR;

@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Exceptions Handler kendisine verilen uygulama icinde
     */
    //Exception handlere hatayı söylemem lazım
    // Yakaladıgı hatayu parametre olarak geçecek
    //Hangi hatayı dinlediigmizi method adi olarak alırsak daha iyi olur.
    // Bu method bu hatasi nasıl yakalaycak ve işi bittiğinde nasıl br cevap verecek
    @ExceptionHandler(IllegalStateException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleIllegalArgumentException(IllegalArgumentException exception){
        ErrorType errorType = ErrorType.INTERNAL_ERROR;
        return new ResponseEntity<>(createError(errorType,exception), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(SpringMonoException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleSpringMonoException(SpringMonoException exception){
        return new ResponseEntity<>(createError(exception.getErrorType(),exception),exception.getErrorType().getHttpStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<ErrorMessage> handleMessageNotReadableException(
            HttpMessageNotReadableException exception) {
        ErrorType errorType = BAD_REQUEST_ERROR;
        return new ResponseEntity<>(createError(errorType, exception), errorType.getHttpStatus());
    }

    @ExceptionHandler(InvalidFormatException.class)
    public final ResponseEntity<ErrorMessage> handleInvalidFormatException(
            InvalidFormatException exception) {
        ErrorType errorType = BAD_REQUEST_ERROR;
        return new ResponseEntity<>(createError(errorType, exception), errorType.getHttpStatus());
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<ErrorMessage> handleMethodArgumentMisMatchException(
            MethodArgumentTypeMismatchException exception) {
        ErrorType errorType = BAD_REQUEST_ERROR;
        return new ResponseEntity<>(createError(errorType, exception), errorType.getHttpStatus());
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public final ResponseEntity<ErrorMessage> handleMethodArgumentMisMatchException(
            MissingPathVariableException exception) {
        ErrorType errorType = BAD_REQUEST_ERROR;
        return new ResponseEntity<>(createError(errorType, exception), errorType.getHttpStatus());
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        ErrorType errorType = BAD_REQUEST_ERROR;
        List<String> fields = new ArrayList<>();
        exception
                .getBindingResult()
                .getFieldErrors()
                .forEach(e -> fields.add(e.getField() + ": " + e.getDefaultMessage()));
        ErrorMessage errorMessage = createError(errorType, exception);

        return new ResponseEntity<>(errorMessage, errorType.getHttpStatus());
    }


    /**
     * 1- Hatalar tej String şeklinde dönülmemelidirler.
     * 2- Hataları bir JsonObject-Entity şeklinde dönmelisiniz.
     * 3- Dönüş nesnesi bir method ile oluşturmak mantıklıdır çünkü method içinde
     * loglama yapabilir gerebildirimleri depolayabilirsiniz.
     */


    private ErrorMessage createError(ErrorType errorType, Exception exception){
        System.out.println("HATA OLDU...:" + exception.getMessage());
        return ErrorMessage.builder()
                .code(errorType.getCode())
                .message(errorType.getMessage())
                .build();
    }

}
