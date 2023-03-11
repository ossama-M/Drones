package com.Alzain.Drones.dto.response;

import com.Alzain.Drones.dto.request.MedicationItemRequest;
import com.Alzain.Drones.model.Drone;
import com.Alzain.Drones.model.Medication;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@ToString
@Builder
public class OrderResponse {
    private String sourceLocation;
    private String destination;
    private LocalDateTime orderTime;
    private double distanceKM;
    private String droneSerial;
    private List<MedicationItemRequest> medicationItemRequests;
}
