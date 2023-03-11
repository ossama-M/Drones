package com.Alzain.Drones.dto.request;


import lombok.*;

@Setter
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddMedication {

    private String code;
    private String name;
    private double weight;
    private String image;

}
