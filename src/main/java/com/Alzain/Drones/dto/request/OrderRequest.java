package com.Alzain.Drones.dto.request;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class OrderRequest {


    private String sourceLocation;

    private String destination;

    private LocalDateTime orderTime;

    private double distanceKM;

    private List<MedicationItemRequest> medicationItemRequests;
}
