package com.oguztasgin.SpringMono.exception;

import lombok.*;
import org.springframework.stereotype.Component;


import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class ErrorMessage {

    private int code;
    private String message;

    /**
     *  String username -> @Valid => min=3, max=16, nutNull, Regex(aZ, 0-9, '*-/?')
     */
    private List<String> field;
}
