package com.example.demo.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResponseDto {

    private String id;
    private String time;

}
