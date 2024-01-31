package com.example.demo.model;

import lombok.*;

import java.util.Collection;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CommonDto {

    private Collection<EmployeDto> employees;

}
