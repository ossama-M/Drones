package com.Alzain.Drones.controller;

import com.Alzain.Drones.dto.request.AddDroneRequest;
import com.Alzain.Drones.dto.response.AddDroneResponse;
import com.Alzain.Drones.dto.response.OrderResponse;
import com.Alzain.Drones.model.Drone;
import com.Alzain.Drones.service.DroneService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drones")
@Validated
public class DroneController {
    private final DroneService droneService ;

    @Autowired
    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddDroneResponse addDrone(@RequestBody AddDroneRequest addDroneRequest){
        return droneService.registerDroneToSystem(addDroneRequest);

    }

    @GetMapping("/{serial}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse getOrderByDroneSerial(@Param("serial") String serial){
        return droneService.checkLoadedItemFroDrone(serial);
    }
    @GetMapping("/All")
    @ResponseStatus(HttpStatus.OK)
    public List<Drone> findAvailableDrone(){
        return droneService.findAvailableDrone();
    }

}
