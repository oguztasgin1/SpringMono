package com.oguztasgin.SpringMono.repository.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.MappedSuperclass;


@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
@SuperBuilder
public class BaseEntity {
    boolean state;
    Long createdate;
    Long updatedate;
}
