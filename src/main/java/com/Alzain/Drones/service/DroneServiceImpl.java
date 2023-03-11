package com.Alzain.Drones.service;

import com.Alzain.Drones.dto.request.AddDroneRequest;
import com.Alzain.Drones.dto.request.AddMedication;
import com.Alzain.Drones.dto.request.MedicationItemRequest;
import com.Alzain.Drones.dto.request.OrderRequest;
import com.Alzain.Drones.dto.response.AddDroneResponse;
import com.Alzain.Drones.dto.response.OrderResponse;
import com.Alzain.Drones.model.Drone;
import com.Alzain.Drones.model.DroneState;
import com.Alzain.Drones.model.Medication;
import com.Alzain.Drones.model.MedicationOrder;
import com.Alzain.Drones.repo.DroneRepo;
import com.Alzain.Drones.repo.MedicationOrderRepo;
import com.Alzain.Drones.repo.MedicationRepo;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
 public class DroneServiceImpl implements DroneService{

    private final DroneRepo droneRepo ;
    private final MedicationRepo medicationRepo ;
    private final MedicationOrderRepo medicationOrderRepo ;

    @Autowired
    public DroneServiceImpl(DroneRepo droneRepo,
                            MedicationRepo medicationRepo,
                            MedicationOrderRepo medicationOrderRepo) {
        this.medicationRepo = medicationRepo;
        this.droneRepo = droneRepo;
        this.medicationOrderRepo = medicationOrderRepo;
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

    @Override
    public AddMedication addMedicationToSystem(AddMedication addMedication) throws Exception{
        if (checkNameAndCode(addMedication.getName(),addMedication.getCode()) ) {
            Medication newMedication = Medication.builder()
                    .code(addMedication.getCode())
                    .image(addMedication.getImage())
                    .weight(addMedication.getWeight())
                    .name(addMedication.getName()).build();

            medicationRepo.save(newMedication);
        }else
            throw new Exception("not valid medication name or medication code") ;

        return addMedication;
    }


    @Transactional
    @Override
    public OrderResponse placeOrder(OrderRequest orderRequest) throws Exception {
        Double size = orderRequest.
                getMedicationItemRequests().
                stream().map(weight -> weight.getWeight()).reduce( 0.0,(a, b)->a+b);
        if(size>500.0)
            throw new Exception("sorry we do not have a drone for heavy " +
                    "weight more than 500g kindly keep checking if there is any update");

        String serial = droneRepo.findSerialByState(DroneState.IDLE.toString(),size);

        if (Objects.isNull(serial))
            throw new Exception("there is no available  drone now you can check later");
        MedicationOrder medicationOrder = MedicationOrder.builder()
                .orderTime(LocalDateTime.now())
                .orderNumber(UUID.randomUUID().toString().substring(0,10))
                .destination(orderRequest.getDestination())
                .distanceKM(orderRequest.getDistanceKM())
                .source(orderRequest.getSourceLocation())
                .build();

        List<Medication> medicationList =  orderRequest.getMedicationItemRequests()
                .stream().map((medicationItemRequest )-> {
                    try {
                        return mapToMedication(medicationItemRequest,medicationOrder);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).toList();

        Drone drone = droneRepo.findDroneBySerial(serial);
        medicationOrder.setDrone(drone);
        medicationOrder.setMedicationList(medicationList);
        medicationOrderRepo.save(medicationOrder);
        droneRepo.updateStateAndOrderNumber(medicationOrder.getId(),DroneState.LOADING,serial);
        OrderResponse response  = OrderResponse.builder()
                .medicationItemRequests(orderRequest.getMedicationItemRequests())
                .droneSerial(serial)
                .sourceLocation(orderRequest.getSourceLocation())
                .destination(orderRequest.getDestination())
                .distanceKM(orderRequest.getDistanceKM())
                .orderTime(orderRequest.getOrderTime()).build();


        return response ;
    }

    public OrderResponse checkLoadedItemFroDrone(String serial){

        String orderNumber= droneRepo.findOrderNumberBySerial(serial);
        List<Medication> medications = medicationRepo.findByOrderNumber(orderNumber) ;

        MedicationOrder medicationOrder = medicationOrderRepo.findById(orderNumber);
        OrderResponse orderResponse = OrderResponse.builder()
                .orderTime(medicationOrder.getOrderTime())
                .droneSerial(serial)
                .distanceKM(medicationOrder.getDistanceKM())
                .destination(medicationOrder.getDestination())
                .sourceLocation(medicationOrder.getSource())
                .build();
        List<MedicationItemRequest> medicationItemRequests  =
                medications.stream().map(medication -> mapToItem(medication)).toList();
        orderResponse.setMedicationItemRequests(medicationItemRequests);
        return orderResponse;
    }
    private Medication mapToMedication(MedicationItemRequest medicationItemRequest,
                                       MedicationOrder medicationOrder)  throws Exception{
        if(!checkNameAndCode(medicationItemRequest.getName(), medicationItemRequest.getCode()))
            throw new Exception("not valid medication name or code");
        return Medication.builder()
                .name(medicationItemRequest.getName())
                .image(medicationItemRequest.getImage())
                .weight(medicationItemRequest.getWeight())
                .code(medicationItemRequest.getCode())
                .medicationOrder(medicationOrder)
                .build();
    }


    private MedicationItemRequest mapToItem(Medication medication){
        return MedicationItemRequest.builder()
                .code(medication.getCode())
                .image(medication.getImage())
                .weight(medication.getWeight())
                .name(medication.getName())
                .build();
    }

    private boolean checkNameAndCode(String name, String code) {
        if(name.matches("[A-Za-z 0-9_]+") && code.matches("[A-Z-0-9_]+"))
            return true;
        return false;
    }



}
