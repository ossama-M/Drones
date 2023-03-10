package com.Alzain.Drones.dto.request;

import com.Alzain.Drones.model.DroneModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Setter
@Getter
public class AddDroneRequest {
    @NotBlank
    @NotNull
    private DroneModel droneModel;
    @NotBlank
    @NotNull
    private double weightLimit;
    @NotBlank
    @NotNull
    private BigDecimal battery;
    @NotBlank
    @NotNull
    private double speedKMH;
}
