package com.Alzain.Drones.dto.response;

import com.Alzain.Drones.model.DroneModel;
import com.Alzain.Drones.model.DroneState;
import lombok.*;


import java.math.BigDecimal;

@Builder
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class AddDroneResponse {

    private String serialNumber;
    private DroneModel droneModel;
    private double weightLimit;
    private BigDecimal battery;
    private double speedKMH;
    private DroneState state;

}
