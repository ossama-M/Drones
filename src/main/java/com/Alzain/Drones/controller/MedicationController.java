package com.Alzain.Drones.controller;

import com.Alzain.Drones.dto.request.AddMedication;
import com.Alzain.Drones.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medication")
public class MedicationController {
    DroneService droneService ;

    @Autowired
    public MedicationController(DroneService droneService) {
        this.droneService = droneService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddMedication addMedication(@RequestBody AddMedication addMedication) throws Exception {
        return droneService.addMedicationToSystem(addMedication);

    }

}
