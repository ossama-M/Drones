package com.Alzain.Drones.service;

import com.Alzain.Drones.dto.request.AddDroneRequest;
import com.Alzain.Drones.dto.response.AddDroneResponse;
import com.Alzain.Drones.model.Drone;
import com.Alzain.Drones.model.DroneState;
import com.Alzain.Drones.repo.DroneRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
 public class DroneServiceImpl implements DroneService{

    DroneRepo droneRepo ;

    @Autowired
    public DroneServiceImpl(DroneRepo droneRepo) {
        this.droneRepo = droneRepo;
    }

    @Override
    public AddDroneResponse registerDroneToSystem(AddDroneRequest addDroneRequest) {
        Drone drone = Drone.builder()
                .serialNumber(UUID.randomUUID().toString().substring(0,5))
                .droneModel(addDroneRequest.getDroneModel())
                .battery(addDroneRequest.getBattery())
                .state(DroneState.IDLE)
                .weightLimit(addDroneRequest.getWeightLimit())
                .speedKMH(addDroneRequest.getSpeedKMH())
                .build();

        droneRepo.save(drone);
        AddDroneResponse response = AddDroneResponse.builder()
                .serialNumber(drone.getSerialNumber())
                .battery(drone.getBattery())
                .droneModel(drone.getDroneModel())
                .speedKMH(drone.getSpeedKMH())
                .state(drone.getState())
                .weightLimit(drone.getWeightLimit())
                .build();
        return response ;
    }
}
