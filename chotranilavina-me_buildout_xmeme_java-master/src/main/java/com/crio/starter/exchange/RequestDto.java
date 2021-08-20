package com.crio.starter.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class RequestDto {

    @NonNull
    private Integer id;
}