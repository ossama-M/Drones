package com.Alzain.Drones.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medication")
public class Medication {
    @Id
    @Column(name = "code", columnDefinition = "VARCHAR(16) NOT NULL")
    private String code;

    @Column(name = "name", columnDefinition = "VARCHAR(30) NOT NULL")
    private String name;

    @Column(name = "weight", columnDefinition = "VARCHAR(10) NOT NULL")
    private double weight;

    @Column(name = "medication_image" ,columnDefinition = "VARBINARY(8000)")
    private String image;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_number")
    private MedicationOrder medicationOrder;
}
