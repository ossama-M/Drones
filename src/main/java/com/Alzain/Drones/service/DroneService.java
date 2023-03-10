package com.Alzain.Drones.service;

import com.Alzain.Drones.dto.request.AddDroneRequest;
import com.Alzain.Drones.dto.response.AddDroneResponse;

public interface DroneService {
    AddDroneResponse registerDroneToSystem(AddDroneRequest addDroneRequest);

}
