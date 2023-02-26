package com.oguztasgin.SpringMono.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class UserControllerFindAllResponseDto {
    String username;
    String avatar;
    Long id;
}