package com.Alzain.Drones.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medication_order")
@Entity
public class MedicationOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    private String orderNumber ;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "medicationOrder",cascade = CascadeType.ALL)
    private List<Medication> medicationList ;

    @Column(name = "source", columnDefinition = "VARCHAR(30) NOT NULL")
    private String source;

    @Column(name = "destination", columnDefinition = "VARCHAR(30) NOT NULL")
    private String destination;

    @Column(name = "order_time_creation", columnDefinition = "VARCHAR(30) NOT NULL")
    private LocalDateTime orderTime;

    @Column(name = "distance_km", columnDefinition = "VARCHAR(10) NOT NULL")
    private double distanceKM;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "drown_id")
    private Drone drone;

    public void addOrderMedication (Medication medication){
        if(medicationList ==null){
            medicationList = new ArrayList<>();
        }
        medicationList.add(medication);
        medication.setMedicationOrder(this);
    }

}


