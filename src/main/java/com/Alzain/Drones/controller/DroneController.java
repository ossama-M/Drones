package com.Alzain.Drones.controller;

import com.Alzain.Drones.dto.request.AddDroneRequest;
import com.Alzain.Drones.dto.response.AddDroneResponse;
import com.Alzain.Drones.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drones")
public class DroneController {
    DroneService droneService ;

    @Autowired
    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddDroneResponse AddDrone(@RequestBody AddDroneRequest addDroneRequest){
        return droneService.registerDroneToSystem(addDroneRequest);

    }


}
