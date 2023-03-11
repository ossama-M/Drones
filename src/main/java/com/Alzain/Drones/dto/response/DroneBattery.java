package com.Alzain.Drones.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DroneBattery {
    private String serialNumber;
    private BigDecimal battery;
}
