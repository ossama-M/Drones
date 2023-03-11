package com.Alzain.Drones.service;

import com.Alzain.Drones.dto.request.AddDroneRequest;
import com.Alzain.Drones.dto.request.AddMedication;
import com.Alzain.Drones.dto.request.OrderRequest;
import com.Alzain.Drones.dto.response.AddDroneResponse;
import com.Alzain.Drones.dto.response.DroneBattery;
import com.Alzain.Drones.dto.response.OrderResponse;
import com.Alzain.Drones.model.Drone;

import java.util.List;

public interface DroneService {
    AddDroneResponse registerDroneToSystem(AddDroneRequest addDroneRequest);
    AddMedication addMedicationToSystem(AddMedication addMedication) throws Exception;
    OrderResponse placeOrder(OrderRequest orderRequest) throws  Exception ;
    OrderResponse checkLoadedItemFroDrone(String serial);
    List<Drone> findAvailableDrone();
    String findBatteryLevel(String serial);

}
