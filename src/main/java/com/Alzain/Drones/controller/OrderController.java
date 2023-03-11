package com.Alzain.Drones.controller;

import com.Alzain.Drones.dto.request.OrderRequest;
import com.Alzain.Drones.dto.response.OrderResponse;
import com.Alzain.Drones.service.DroneService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    DroneService droneService ;

    public OrderController(DroneService droneService) {
        this.droneService = droneService;
    }
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse PlaceOrder(@RequestBody OrderRequest orderRequest) throws Exception {
        return droneService.placeOrder(orderRequest);
    }

}
