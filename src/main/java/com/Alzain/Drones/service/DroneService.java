package com.Alzain.Drones.service;

import com.Alzain.Drones.dto.request.AddDroneRequest;
import com.Alzain.Drones.dto.request.AddMedication;
import com.Alzain.Drones.dto.request.OrderRequest;
import com.Alzain.Drones.dto.response.AddDroneResponse;
import com.Alzain.Drones.dto.response.OrderResponse;

public interface DroneService {
    AddDroneResponse registerDroneToSystem(AddDroneRequest addDroneRequest);
    AddMedication addMedicationToSystem(AddMedication addMedication) throws Exception;
    OrderResponse placeOrder(OrderRequest orderRequest) throws  Exception ;
    OrderResponse checkLoadedItemFroDrone(String serial);

}
