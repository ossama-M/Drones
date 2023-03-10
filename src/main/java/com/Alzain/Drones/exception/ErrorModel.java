package com.Alzain.Drones.exception;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorModel {
    private int status ;
    private String message ;
    private long timestamp ;
}
