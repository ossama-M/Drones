package com.Alzain.Drones.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MedicationItemRequest {
    private String name;
    private double weight;
    private String image;
    private String code ;


}
