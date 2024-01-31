package com.example.demo.model;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmployeDto {

    private String id;

    private LocalDateTime dateStart;

    private LocalDateTime dateEnd;

}
