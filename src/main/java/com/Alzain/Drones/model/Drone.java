package com.Alzain.Drones.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "drone")
@Entity
public class Drone {

    @Id
    @Column(name = "serial_no", columnDefinition = "VARCHAR(16) NOT NULL")
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private DroneModel droneModel;

    @Column(name = "weight_limit", columnDefinition = "VARCHAR(10) NOT NULL")
    private double weightLimit;

    @Column(name = "battery",precision = 3, scale = 2)
    private BigDecimal battery;

    @Column(name = "speed_kmh", columnDefinition = "VARCHAR(10) NOT NULL")
    private double speedKMH;

    @Enumerated(EnumType.STRING)
    private DroneState state;

    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "order_number")
    private MedicationOrder medicationOrder;



}
