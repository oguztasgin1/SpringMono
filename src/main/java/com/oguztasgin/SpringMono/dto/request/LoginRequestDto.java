package com.oguztasgin.SpringMono.dto.request;

import com.sun.istack.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class LoginRequestDto {
    String username;
    String password;
}